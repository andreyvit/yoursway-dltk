#!/bin/bash
# $Id: start.sh,v 1.1 2007/02/08 12:38:55 aplatov Exp $

export PATH=/bin:/usr/bin/:/usr/local/bin
export ANT=/opt/apache-ant-1.6/bin/ant
if [ "x$JAVA_HOME" = "x" ]; then 
	export JAVA_HOME=/usr/local/java
fi
export CVS_RSH=/usr/bin/ssh

scriptspath=$HOME/scripts
mkdir -p $scriptspath
cd $scriptspath


#default values
dltkCVS="xored.com:/opt/org.eclipse.dltk";
project=""; # REQUIRED
version=""; # REQUIRED
tagBuild=true
dependURL=""; # loaded from -URL
branch=HEAD
projRelengBranch=""; # default set below
commonRelengBranch=""; # default set below
basebuilderBranch=HEAD
antTarget=run
buildAlias=""
buildType=N
javaHome=""
downloadsDir=""; # default set below
buildTimestamp=`date +%Y%m%d%H%M`
buildDir=""; # default set below
email=""
noclean=0; # clean up temp files when done
quietCVS=-Q; # QUIET!

function usage()
{
	echo "usage: start.sh"
	echo "-proj           <REQUIRED: shortname of the project to be build, eg. ocl, validation, query>"
	echo "-version        <REQUIRED: version to use, eg., 1.0.0>"
	echo "-tagBuild       <Tag files during build? true|false; default: true>"
	echo "-URL            <The URLs of the Eclipse driver, EMF driver, and any other zips that need to be unpacked into"
	echo "                 the eclipse install to resolve all dependencies. Enter one -URL [...] per required URL.>"     
	echo "-branch         <CVS branch of the files to be built (eg., build_200409171617); default HEAD)>"
	echo "-projRelengBranch   <CVS branch of org.eclipse.dltk.*.releng>"
	echo "-commonRelengBranch <CVS branch of org.eclipse.dltk.common.releng>"
	echo "-basebuilderBranch <CVS branch of org.eclipse.releng.basebuilder>"
	echo "-antTarget      <The Ant target of the build script: run, runWithoutTest; default: run>"
	echo "-buildAlias     <The Alias of the build (for named S and R builds), eg. 2.0.2RC1; default: none>"
	echo "-buildType      <The type of the build: N, I, M, S, R; default: N>"
	echo "-javaHome       <The JAVA_HOME directory; default: $JAVA_HOME>"
	echo "-downloadsDir   <The directory where dependent zips are downloaded; default: $HOME/build/dltk/\$proj/downloads>"
	echo "-buildTimestamp <optional: YYYYmmddhhMM timestamp to be used to label the build; default will be generated>"
	echo "-buildDir       <The directory of this build; default: \$downloadsDir/drops/\$version/\$buildType\$buildTimestamp>"
	echo "-email          <The email address(es) to be contacted when the tests complete. Separate multiple w/ commas>"
	echo "-noclean        <DON'T clean up temp files after build>"
	echo ""
	echo "example: "
	echo "./start.sh \\"
	echo "  -proj ocl -version 1.0.0 -tagBuild false \\"
	echo "  -URL http://fullmoon.torolab.ibm.com/downloads/drops/S-3.2M3-200511021600/eclipse-SDK-3.2M3-linux-gtk.tar.gz \\"
	echo "  -URL http://fullmoon.torolab.ibm.com/tools/emf/downloads/drops/2.2.0/I200511030200/emf-sdo-xsd-SDK-I200511030200.zip \\"
	echo "  -email codeslave@ca.ibm.com \\"
	echo "  >> ~/buildlog_\`date +%Y%m%d_%H%M%S\`.txt 2>&1 &"
	exit 1
}

if [ $# -lt 1 ]; then
	usage;
fi

echo "[`date +%Y%m%d\ %H\:%M\:%S`] start.sh executing with the following options:"
# Create local variable based on the input
tmpfile=`mktemp`;
while [ "$#" -gt 0 ]; do
	case $1 in
		'-proj')
			proj=$2;
			echo "   $1 $2";
			echo "${1:1}=$2" >> $tmpfile
			shift 1
			;;
		'-version')
			version=$2;
			echo "   $1 $2";
			echo "${1:1}=$2" >> $tmpfile
			shift 1
			;;
		'-branch')
			branch=$2;
			echo "   $1 $2";
			echo "${1:1}=$2" >> $tmpfile
			shift 1
			;;
		'-URL')
			if [ "x$dependURL" != "x" ]; then
			  dependURL="$dependURL "
			fi
			dependURL=$dependURL"$2";
			echo "   $1 $2";
			shift 1
			;;
		'-javaHome')
			javaHome=$2;
			echo "   $1 $2";
			echo "${1:1}=$2" >> $tmpfile
			shift 1
			;;
		'-antTarget')
			antTarget=$2;
			echo "   $1 $2";
			echo "${1:1}=$2" >> $tmpfile
			shift 1
			;;
		'-buildAlias')
			buildAlias=$2;
			echo "   $1 $2";
			echo "${1:1}=$2" >> $tmpfile
			shift 1
			;;
		'-buildType')
			buildType=$2;
			echo "   $1 $2";
			echo "${1:1}=$2" >> $tmpfile
			shift 1
			;;
		'-buildDir')
			buildDir=$2;
			echo "   $1 $2";
			echo "${1:1}=$2" >> $tmpfile
			shift 1
			;;
		'-downloadsDir')
			downloadsDir=$2;
			echo "   $1 $2";
			echo "${1:1}=$2" >> $tmpfile
			shift 1
			;;
		'-buildTimestamp')
			buildTimestamp=$2;
			echo "   $1 $2";
			echo "${1:1}=$2" >> $tmpfile
			shift 1
			;;
		'-tagBuild')
			tagBuild=$2;
			echo "   $1 $2";
			echo "${1:1}=$2" >> $tmpfile
			shift 1
			;;
		'-email')
			email=$2;
			echo "   $1 $2";
			echo "${1:1}=$2" >> $tmpfile
			shift 1
			;;
		'-basebuilderBranch')
			basebuilderBranch=$2;
			echo "   $1 $2";
			echo "${1:1}=$2" >> $tmpfile
			shift 1
			;;
		'-projRelengBranch')
			projRelengBranch=$2;
			echo "   $1 $2";
			echo "${1:1}=$2" >> $tmpfile
			shift 1
			;;
		'-commonRelengBranch')
			commonRelengBranch=$2;
			echo "   $1 $2";
			echo "${1:1}=$2" >> $tmpfile
			shift 1
			;;
		'-branch')
			branch=$2;
			echo "   $1 $2";
			echo "${1:1}=$2" >> $tmpfile
			shift 1
			;;
		'-noclean')
			noclean=1;
			echo "   $1";
			echo "${1:1}=1" >> $tmpfile
			shift 0
			;;
	esac
	shift 1
done	

# get path to PHP interpreter
PHP=php
if [ -x /usr/bin/php ]; then
	PHP=/usr/bin/php
elif [ -x /usr/bin/php4 ]; then
	PHP=/usr/bin/php4
elif [ -x /usr/bin/php5 ]; then
	PHP=/usr/bin/php5
else
	PHP=php
fi

sendEmail () 
{
	if [ "x$email" != "x" ]; then
		buildBranchAndID=${buildDir##*drops/}
		$scriptspath/executeCommand.sh "$PHP -q $scriptspath/sendEmail.php -email $email -productPath technology/dltk/$proj -branch \
		$buildBranchAndID -buildID $branch -hostname "`hostname -f`
	fi
}

# collect values from input / set defaults from input values

if [ "x$proj" = "x" ]; then
	usage;
fi
if [ "x$buildDir" = "x" ] && [ "x$version" = "x" ]; then
	usage;
fi

if [ "x$downloadsDir" = "x" ]; then
	downloadsDir=$HOME/build/dltk/$proj/downloads
fi

if [ "x$buildDir" = "x" ]; then
	buildDir=$downloadsDir/drops/$version/$buildType$buildTimestamp
fi

if [ "x$javaHome" != "x" ]; then
	export JAVA_HOME=$javaHome;
else # use default
	javaHome="$JAVA_HOME"
fi

projRelengBranchCmd="";
if [ "$projRelengBranch" = "HEAD" ]; then
	projRelengBranchCmd="";
elif [ "x$projRelengBranch" != "x" ]; then
	projRelengBranchCmd="-r $projRelengBranch";
elif [ "$branch" != "HEAD" ]; then
	echo "[start] Defaulting -projRelengBranch to $branch. If that's not good, override using a debug build."
	projRelengBranchCmd="-r $branch"; # by default, if build from R1_0_maintenance, use same tag for o.e.*.releng
fi

commonRelengBranchCmd="";
if [ "$commonRelengBranch" = "HEAD" ]; then
	commonRelengBranchCmd="";
elif [ "x$commonRelengBranch" != "x" ]; then
	commonRelengBranchCmd="-r $commonRelengBranch";
fi

basebuilderBranchCmd="";
if [ "$basebuilderBranch" = "HEAD" ]; then
	basebuilderBranchCmd="";
elif [ "x$basebuilderBranch" != "x" ]; then
	basebuilderBranchCmd="-r $basebuilderBranch";
fi

# extract latest versions of scripts
#echo "[start] Checking out org.eclipse.dltk.common.releng using branch $commonRelengBranch"
#cd $HOME/build/dltk; cvs -d nickb@dev.eclipse.org:/cvsroot/technology -q co -P -d scripts org.eclipse.dltk/releng/common/scripts; cd scripts; chmod 754 *.sh

echo "[start] Check if dependent drivers exist or can be downloaded:"

checkZipExists ()
{
	theURL=$1;
	theFile=`echo $theURL | sed -e 's/^.*\///'`
	$ANT -f checkZipExists.xml -DdownloadsDir=$downloadsDir -DtheFile=$theFile -DtheURL=$theURL
	#echo "[start] Ant returned: $#"
}

for dep in $dependURL; do
	outfile=`mktemp`;
	checkZipExists $dep 2>&1 | tee $outfile;
	result=`cat $outfile | grep -c FAILED`
	rm -fr $outfile
	#echo $result
	if [ "$result" != "0" ]; then
		echo "[start] An error occurred finding or downloading $dep."
		sendEmail;
		echo "[start] This script will now exit."
		exit 999;
	fi
done

echo "[start] Creating build directory $buildDir"
$scriptspath/executeCommand.sh "mkdir -p $buildDir/eclipse"
cd $buildDir
#echo "[start] Now in $PWD"

# add some properties to build.cfg
buildcfg="$buildDir/build.cfg";
echo "Storing build properties in $buildcfg";
echo -n "" > $buildcfg; # truncate file if exists; create if not

echo "#Build options (all but -URL)" >> $buildcfg;
cat $tmpfile >> $buildcfg;
echo "" >> $buildcfg;
rm -fr $tmpfile

# eventually, shouldn't need to do this
echo "#Build options (renamed)" >> $buildcfg;
echo "buildVer=$version" >> $buildcfg;
echo "subprojectName=$proj" >> $buildcfg;
echo "" >> $buildcfg;

echo "#Build options (more)" >> $buildcfg;
echo "repoInfoFile=$buildDir/org.eclipse.dltk.$proj.releng/repoInfo.properties" >> $buildcfg;
echo "" >> $buildcfg;

echo "[start] Checking out org.eclipse.dltk.common.releng using '"$commonRelengBranchCmd"' (if blank, using HEAD)";
$scriptspath/executeCommand.sh "cvs -d :pserver:anonymous@$dltkCVS $quietCVS co $commonRelengBranchCmd -P -d org.eclipse.dltk.common.releng org.eclipse.dltk/releng/common";
chmod 754 org.eclipse.dltk.common.releng/scripts/*.sh
echo "[start] Checkout done."
echo ""

echo "[start] Checking out org.eclipse.dltk.$proj.releng using '"$projRelengBranchCmd"' (if blank, using HEAD)";
$scriptspath/executeCommand.sh "cvs -d :pserver:anonymous@$dltkCVS $quietCVS co $projRelengBranchCmd -P -d org.eclipse.dltk.$proj.releng org.eclipse.dltk/releng/$proj";
mv ./org.eclipse.dltk.common.releng/org.eclipse.dltk.common.build ./org.eclipse.dltk.$proj.releng/
echo "[start] Checkout done."
echo ""

if [ "x$basebuilderBranch" != "x" ]; then
	bbDir=$downloadsDir/basebuilder/$basebuilderBranch
else
	bbDir=$downloadsDir/basebuilder/HEAD
fi

if [ -d $bbDir ]; then
    echo "[start] Using base builder from $bbDir"
else
    mkdir -p $bbDir
    cd $bbDir #we will cache bb instance (expensive traffic)
    echo "[start] Checking out org.eclipse.releng.basebuilder using branch '"$basebuilderBranch"' (if blank, using HEAD)";
    $scriptspath/executeCommand.sh "cvs -d :pserver:anonymous@dev.eclipse.org:/cvsroot/eclipse $quietCVS co $basebuilderBranchCmd -P org.eclipse.releng.basebuilder"
    echo "[start] Checkout done."
fi

echo ""
echo "[start] copying basebuilder to the build directory..."
$scriptspath/executeCommand.sh "cp -r $bbDir/org.eclipse.releng.basebuilder $buildDir"

cd $buildDir/org.eclipse.dltk.common.releng/scripts

command="$buildDir/org.eclipse.dltk.common.releng/scripts/run.sh"
command=$command" -branch $branch";
command=$command" -proj $proj";
if [ "x$projRelengBranch" != "x" ]; then
	command=$command" -projRelengBranch $projRelengBranch"; # override default $branch
fi
if [ "x$dependURL" != "x" ]; then
	for dep in $dependURL; do
		command=$command" -URL $dep"
	done
fi
command=$command" -antTarget $antTarget"
if [ "x$buildAlias" != "x" ]; then
	command=$command" -buildAlias $buildAlias"
fi
command=$command" -buildType $buildType"
command=$command" -javaHome $javaHome"
command=$command" -downloadsDir $downloadsDir"
command=$command" -buildDir $buildDir"
command=$command" -buildTimestamp $buildTimestamp"
command=$command" -repoInfoFile $buildDir/org.eclipse.dltk.$proj.releng/repoInfo.properties"
command=$command" -tagBuild $tagBuild"

# fire run.sh
#$scriptspath/executeCommand.sh "$command"
$command

cd $buildDir;

if [ $noclean -eq 0 ]; then
	echo "[start] Cleaning up & removing temporary directories in $buildDir"
	rm -fr $buildDir/org.eclipse.dltk.common.releng
	rm -fr $buildDir/org.eclipse.dltk.$proj.releng
	rm -fr $buildDir/org.eclipse.releng.basebuilder
	rm -fr $buildDir/eclipse
	rm -fr $buildDir/testing
else
	echo "[start] Cleaning up & removing temporary directories in $buildDir ... OMITTED." 
	echo "[start] Please scrub the following folders manually:"
	echo "[start]   $buildDir/org.eclipse.dltk.common.releng"
	echo "[start]   $buildDir/org.eclipse.dltk.$proj.releng"
	echo "[start]   $buildDir/org.eclipse.releng.basebuilder"
	echo "[start]   $buildDir/eclipse"
	echo "[start]   $buildDir/testing"
fi

sendEmail;

echo "[start] start.sh finished on: `date +%Y%m%d\ %H\:%M\:%S`"
echo ""
