# !/bin/sh
# $Id: buildUpdate.sh,v 1.5 2007/03/12 11:35:07 aplatov Exp $

# buildUpdate.sh script to generate Update Managers jars & promote them to download.eclipse
# Copyright \(c\) 2004-2006, IBM. Nick Boldt. codeslave\(at\)ca.ibm.com

# This script should build the required project plugins, features + site.xml 

# requires accompanying properties file, promoteToEclipse.*.properties or buildUpdate.properties, where * = emf or uml2
# can also specify any other properties file with -f flag

echo "[umj] buildUpdate.sh started on: `date +%Y%m%d\ %H\:%M\:%S`"

##########################################################################################

if [ $# -lt 1 ]; then
	echo " "
	echo "usage: buildUpdate.sh"
	echo "-f            <properties file used to run script (default ./promoteToEclipse.properties)>"
	echo "-sub          <REQUIRED: specify subproject as ocl, validation, query, transaction, etc.>"
	echo "-user         <username on *.eclipse.org (default is $USER)>"
	echo "-q, -Q        <scp, unzip, cvs checkout: -Q (quieter) where possible, -q elsewhere>"
	echo "-debug        <debug this script and ProductUpdateBuilder script [0|1|2] (default 0)>"
	echo "-buildID      <perform UM site jars build on which build ID (eg., I200601191242)>"
	echo "-branch       <branch of the files to be built, eg., 1.0.0, 1.0.1 (overrides value in property file)>"
	echo "-promote      <promote built jars to download> (optional)"
	echo "-nobuilder    <skip checking out o.e.releng.basebuilder> (optional)"
	echo "-skipjars     <skip uploading jars to download.eclipse (just the new XML)> (optional)"
	echo "-nocleanup    <don't delete temp files when done> (optional)"
	echo "-noCompareUMFolders <after uploading the drop, DO NOT compare source and target for matching MD5s, etc.>"
	echo "-basebuilderBranch  <org.eclipse.releng.basebuilder CVS branch>" #; default to value in build.options.txt if avail>"
	echo "-no4thPart    <build 3-part jars (2.0.1) instead of the default, 4-part jars (2.0.1.I200601191242)>"
	echo " "
	echo "Examples:"
	echo "Build (normal):     ./buildUpdate.sh -sub ocl -Q -buildID I200601191242 \\"
	echo "                       2>&1 | tee ~/buildUpdate_\`date +%Y%m%d_%H%M%S\`.txt"
	echo "Build then promote: ./buildUpdate.sh -sub query -Q -buildID I200601191242 -promote"
	echo "Build 3-part jars:  ./buildUpdate.sh -sub query -Q -buildID I200601191242 -no4thPart"
	echo " "
	exit 1
fi

# default to default properties file
defaultPropertiesFile=./promoteToEclipse.properties
propertiesFiles="";

# Create local variable based on the input
while [ "$#" -gt 0 ]; do
	case $1 in
		'-f')
			propertiesFile=$2;
			echo "   $1 $2";
			shift 1
			;;

		'-sub')
			subprojectName=$2;
			echo "   $1 $2";
			# chain them together in order of priority: override (if applic), subproj specific one, default
			propertiesFiles=$propertiesFiles" ./promoteToEclipse."$subprojectName".properties "$defaultPropertiesFile; 
			loaded=0;
			for propertiesFile in $propertiesFiles; do
			  if [ "$loaded" -eq 0 ] && [ -r $propertiesFile ]; then 
				echo -n "    [loading $propertiesFile ... "; . $propertiesFile; echo "done]"; loaded=1;
			  fi
			done
			if [ "$loaded" -eq 0 ]; then
			    echo "    [Can't load any of: $propertiesFiles. Exiting!]";
			    exit 99;
			fi
			shift 1
			;;
		'-user')
			echo "   $1 $2";
			user=$2;
			shift 1
			;;
		'-buildID')
			echo "   $1 $2";
			buildID=$2;
			shift 1
			;;
		'-branch')
			echo "   $1 $2";
			branch=$2;
			shift 1
			;;
		'-promote')
			echo "   $1";
			promote=1;
			build=1;
			shift 0
			;;
		'-nobuilder')
			echo "   $1";
			builder=0;
			shift 0
			;;
		'-nocleanup' | '-noclean')
			echo "   $1";
			cleanup=0;
			shift 0
			;;
		'-skipjars')
			echo "   $1";
			skipjars=1;
			shift 0
			;;
		'-q')
			echo "   $1";
			quietCVS=-q;
			quiet=-q;
			shift 0
			;;
		'-Q')
			echo "   $1";
			quietCVS=-Q;
			quiet=-q;
			shift 0
			;;
		'-debug')
			echo "   $1 $2";
			debug=$2;
			shift 1
			;;
		'-basebuilderBranch')
			echo "   $1 $2";
			basebuilderBranch=$2;
			shift 1
			;;
		'-noCompareUMFolders')
			echo "   $1";
			noCompareUMFolders=1;
			shift 0
			;;
		'-no4thPart')
			echo "   $1";
			no4thPart=1;
			shift 0
			;;
	esac
	shift 1
done

if [ "$subprojectName" = "" ]; then # no value set!
  echo "[promote] No subproject name set in properties file or by -sub flag. Script cannot continue. Exiting...";
  exit 99;
fi

if [ "$branch" = "" ]; then # no value set!
  echo "[promote] No branch value set in properties file or by -branch flag. Script cannot continue. Exiting...";
  exit 99;
fi

if [ "$buildID" = "" ]; then # no value set!
  echo "[promote] No build ID value set in properties file or by -buildID flag. Script cannot continue. Exiting...";
  exit 99;
fi

##########################################################################################

#users (for ssh and cvs connections)
buildServerCVSUser=$user"@"$buildServerFullName

if [[ $sub = "ocl" ]] || [[ $sub = "uml2-ocl" ]] || [[ $sub = "eodm" ]]; then
	#path to update site on build server
	localUpdatesWebDir=$localWebDir/../updates
	# path to update site on download - instead of mdt/uml2-ocl/updates, use mdt/updates (common for all mdt)
	updatesDir=$projectWWWDir/../updates
else	
	#path to update site on build server
	localUpdatesWebDir=$localWebDir/updates
	# path to update site on download
	updatesDir=$projectWWWDir/updates
fi

#TODO: move into modeling
CVSRep=":ext:"$user"@"$eclipseServerFullName":/cvsroot/technology"
wwwCVSRep=":ext:"$user"@"$eclipseServerFullName":/cvsroot/technology"
wwwRemote=$user"@"$downloadServerFullName

# temp folder
tmpfolder=$HOME/tmp-buildUpdate.sh-$subprojectName-$user-`date +%Y%m%d_%H%M%S`

# working directory for CVS checkouts & building
buildDir=$tmpfolder/1 ;

bootclasspath="$javaHome/jre/lib/*.jar:$buildDir/org.eclipse.releng.generators/buildTools.jar:$buildDir/org.eclipse.releng.generators/productUpdateBuilder.jar"

##########################################################################################

getBuildIDactual ()
{
	#new, more efficient method as of nov 12 thanks to ken's identification of the old way's limitation
	buildIDactual=`find $buildDropsDir/$branch/$buildID -name "$SDKfilenamepattern"`
	buildIDactual=${buildIDactual##*SDK-}; # trim up to SDK- (substring notation)
	buildIDactual=${buildIDactual%%\.zip}; # trim off .zip (substring notation)
	echo "Using build ID: $buildIDactual"
}


##########################################################################################

buildIDactual=buildID;
getBuildIDactual;

##########################################################################################


#ssh $buildServerCVSUser "mkdir -p $buildDir";
mkdir -p $buildDir

if [ $promote -eq 1 ]; then
	mkdir -p $buildDir/../2
fi
cd $buildDir ;

if [ $builder -eq 1 ]; then
	if [ "x$basebuilderBranch" = "x" ]; then
		if [[ ! $basebuilderBranch ]]; then
			basebuilderBranch=$(cat $buildDropsDir/$branch/$buildID/build.cfg | grep basebuilderBranch); basebuilderBranch=${basebuilderBranch#basebuilderBranch=}; # echo $basebuilderBranch
			if [[ ! $basebuilderBranch ]]; then
				basebuilderBranch="HEAD";
			fi
		fi
	fi
	echo "[umj-co] [1] Checking out org.eclipse.releng.basebuilder from dev using $basebuilderBranch"
	#ssh $buildServerCVSUser "cd $buildDir; cvs -d :pserver:anonymous@dev.eclipse.org:/cvsroot/eclipse $quietCVS co -P -r $basebuilderBranch org.eclipse.releng.basebuilder"
	cp -r ~/basebuilder/M3_33/org.eclipse.releng.basebuilder $buildDir
else
	echo "[umj-co] [1] Checking out org.eclipse.releng.basebuilder from dev... omitted."
fi

# org.eclipse.releng.basebuilder directory
relengBaseBuilderDir=$buildDir/org.eclipse.releng.basebuilder
echo "[umj] relengBaseBuilderDir: $relengBaseBuilderDir"

#unpack files from cvs to get buildUpdateJars.xml, productUpdateBuilder.jar, buildTools.jar

echo "[umj-co] [2] Checking out $relengGeneratorsCVSPath"
cd $buildDir
cvs -d $CVSRep $quietCVS co -P -d org.eclipse.releng.generators $relengGeneratorsCVSPath

#TODO: move to www/modeling/mdt/updates and www/modeling/emf/updates
if [[ $sub = "ocl" ]] || [[ $sub = "uml2-ocl" ]] || [[ $sub = "eodm" ]]; then
	updatesCVSPath=www/modeling/mdt/updates; # www/modeling/mdt, not www/emft/updates
else
	updatesCVSPath=www/dltk/updates
fi
echo "[umj-co] [3] Checking out $updatesCVSPath/site/* from $wwwCVSRep"
cd $buildDir/../1
echo "cvs -d $wwwCVSRep $quietCVS co -P -d site $updatesCVSPath/site.xml" 
cvs -d $wwwCVSRep $quietCVS co -P -d site $updatesCVSPath/site.xml 
cvs -d $wwwCVSRep $quietCVS co -P -d site $updatesCVSPath/site-interim.xml
cvs -d $wwwCVSRep $quietCVS co -P -d site $updatesCVSPath/index.php 
cd $buildDir/../1/site

cd $buildDir/ ;
#run ant script

echo "[umj] [4] Invoking Eclipse build to create UM jars for build ID $buildID..."
buildIDactual=`find $buildDropsDir/$branch/$buildID -name "$SDKfilenamepattern"`
buildIDactual=${buildIDactual##*SDK-}; # trim up to SDK-
buildIDactual=${buildIDactual%%\.zip}; # trim off .zip
#echo $buildIDactual

if [ "$buildIDactual" != "$buildID" ]; then
	buildDesc=$buildIDactual; # eg., 2.0.1RC1 != M200409081700
else
	buildDesc=$branch; # eg., 2.0.1
fi

# new, for use with plugins as jars: unpack SDK zips and then replace foo.jar with foo/ folders instead

rm -fr $buildDir/org.eclipse.releng.generators/updateJars
mkdir -p $buildDir/org.eclipse.releng.generators/updateJars/site

index=0;
element_count=${#filePrefixesToUnzipArray[@]}
while [ "$index"  -lt "$element_count" ]; do
	zipfilename=${filePrefixesToUnzipArray[$index]}"$buildIDactual.zip"
	unzip -uo -qq $buildDropsDir/$branch/$buildID/$zipfilename -d $buildDir/org.eclipse.releng.generators/updateJars
	let "index = $index + 1";
done

jarlist=`find $buildDir/org.eclipse.releng.generators/updateJars/eclipse/plugins -maxdepth 1 -name "*.jar"`
for jarfile in $jarlist; do
	folder=${jarfile%\.jar}
	echo "Unpacking "${jarfile#*plugins/}" ...";
	#echo "Unpacking $jarfile into $folder ...";
	unzip -uo -qq $jarfile -d $folder;
	#echo "Removing $jarfile ...";
	rm -fr $jarfile;
done

# java home & vm used to run the build.  Defaults to java on system path
## must be Sun 1.4 - IBM 1.4 crashes server and Sun 5.0 throws NPE (SAXParser problem)
javaHome=/usr/local/j2re1.4.2_13
vm=$javaHome/bin/java

command="$vm -cp $relengBaseBuilderDir/startup.jar org.eclipse.core.launcher.Main"
command=$command" -application org.eclipse.ant.core.antRunner"
command=$command" -f $buildDir/$antScript $target"
command=$command" -Dbootclasspath=$bootclasspath"
command=$command" -Dtimestamp=$buildID"
command=$command" -DbuildDesc=$buildDesc"
command=$command" -DdropDir=$buildDropsDir/$branch/$buildID"
command=$command" -DprojectName=$projectName"
command=$command" -DsubprojectName=$subprojectName"
command=$command" -Ddebug=$debug"

if [ $no4thPart -eq 1 ]; then
	sitexml="site.xml";
	command=$command" -DfourthPart=no4thPart"
	command=$command" -Dsitexml=$sitexml"
	no4thPart=1;
elif [ "$buildIDactual" = "$branch" ]; then
	sitexml="site.xml";
	command=$command" -DfourthPart=no4thPart"
	command=$command" -Dsitexml=$sitexml"
	no4thPart=1;
elif [ "$buildIDactual" != "$buildID" ]; then
	# if alias 2.0.1RC1 != buildID M200408121234, we have an aliased build; need 4th part to ensure proper sequencing
	sitexml="site-interim.xml";
	command=$command" -DfourthPart=add4thPart"
	command=$command" -Dsitexml=$sitexml"
else
	# if alias 2.0.1RC1 != 2.0.1
	# if alias M200408121234 = buildID M200408121234
	sitexml="site-interim.xml";
	command=$command" -DfourthPart=add4thPart"
	command=$command" -Dsitexml=$sitexml"
fi

cp -f $buildDir/site/$sitexml $buildDir/org.eclipse.releng.generators/updateJars/site/

echo "$command" | perl -pe "s/ -/\n  -/g";
$command

# generate MD5s
md5file="./md5s/"$subprojectName"_"$buildIDactual".md5";
md5filepath=$buildDir"/../"$md5file
mkdir -p $buildDir/../md5s
cd $buildDir/org.eclipse.releng.generators/updateJars/site
md5sum features/*.jar plugins/*.jar > $md5filepath  # list md5s for all new jars

echo "[umj] [5a] Copy new jars & site/* to $localUpdatesWebDir ..."
#TODO: move to www/modeling/mdt/updates and www/modeling/emf/updates
# copy new jars & site.xml to /var/www/technology/$projectName/updates
cd $buildDir/org.eclipse.releng.generators/updateJars/site && cp -r . $localUpdatesWebDir && cd $buildDir/site && cp -r . $localUpdatesWebDir

# copy md5 file into both places, too: first to local build/cvs server
mkdir -p $localUpdatesWebDir/md5s && cp $md5filepath $localUpdatesWebDir/md5s

echo "[umj] [5b] Fix permissions in $localUpdatesWebDir ..."
echo " \
	cd $localUpdatesWebDir ; \
	chgrp -fR $eclipseUserGroup *; \
	find $localUpdatesWebDir -type f -exec chmod -f $eclipsePermsFile {} \; ; \
	find $localUpdatesWebDir -type f -exec chmod -f $eclipsePermsFile {} \; ; \
";


#promote to download
if [ $promote -eq 1 ]; then
	echo "[umj] [6] Update site/* to dev.eclipse.org ..." ;
	cd $buildDir/site ;
	cvs -d $wwwCVSRep $quietCVS ci -m "buildUpdate: automatic UM site update" ;

	if [ $skipjars -eq 0 ]; then
		echo "[umj] [7a] Promoting jars to $downloadServerFullName..." ;
		cd $buildDir/org.eclipse.releng.generators/updateJars/site ;
		echo "scp -r -v $buildServerCVSUser:$buildDir/org.eclipse.releng.generators/updateJars/site/. $wwwRemote:$updatesDir ";
	else
		echo "[umj] [7a] Promoting jars to $downloadServerFullName... omitted." ;
	fi

	echo "[umj] [7b] Promoting site/* to $downloadServerFullName..." ;
	cd $buildDir/site ;
	echo "scp -r $quiet $buildDir/site/. $wwwRemote:$updatesDir ";

	# copy md5 file into both places, too: second onto production server
	echo $wwwRemote "mkdir -p $updatesDir/md5s/" ;
	echo scp $quiet $md5filepath $wwwRemote:$updatesDir/md5s/ ;

	echo "[umj] [7c] Fix permissions in $updatesDir ..." ;
	echo $wwwRemote "
		cd $updatesDir/..;
		chgrp -fR $eclipseUserGroup updates;
		find $updatesDir/../updates -type d -exec chmod -f $eclipsePermsDir {} \; ;
		find $updatesDir/../updates -type f -exec chmod -f $eclipsePermsFile {} \; ;
	";

	# validate MD5s
	if [ $noCompareUMFolders -eq 0 ]; then
		### CHECK MD5s and compare dir filesizes for match (du -s)
		echo "[umj] [7d] [`date +%H:%M:%S`] Comparing local and remote folders MD5 sums to ensure SCP completeness... "
		cmd="$buildScriptsDir/compareFolders.sh -md5only -md5file $md5file -user $user -local $localUpdatesWebDir -remote $updatesDir -server $wwwRemote"
		if [ $debug -gt 0 ]; then
			echo "$cmd" | perl -pe "s/ -/\n  -/g";			
		fi
		echo $buildServerCVSUser "$cmd"
		returnCode=$?
		if [ $returnCode -gt 0 ]; then
			echo "[umj] [`date +%H:%M:%S`] ERROR! Script exiting with code $returnCode from compareFolders.sh"
			exit $returnCode;
		fi
	else
		echo "[umj] [7d] [`date +%H:%M:%S`] Comparing local and remote folders to ensure SCP completeness ... omitted."
	fi

else
	echo "[umj] [6] Check in new site/* to CVS... omitted."
	echo "[umj] [7] Promoting jars & site/* to $downloadServerFullName... omitted."
fi

exit 0

# cleanup
if [ $cleanup -eq 1 ]; then
	ssh $buildServerCVSUser "rm -fr $tmpfolder/";
fi

########### DONE ###########

echo "[umj] buildUpdate.sh completed on: `date +%Y%m%d\ %H\:%M\:%S`"

