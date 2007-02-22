#!/bin/sh

# $Id: fixJavadocs.sh,v 1.3 2007/02/22 08:39:51 asobolev Exp $
# Called by promoteToEclipse.sh

# TODO: support subfolders, eg. transaction/workspace/1.0.0

showhelp()
{
	echo " "
	echo "usage: fixJavadocs.sh"
	echo "-sub        <REQUIRED: subproject for which to fix javadocs, eg. ocl, validation, query>"
	echo "-branch     <REQUIRED: branch for which javadocs are needed, eg., 1.0.0>"
	echo "-buildID    <REQUIRED ON LOCAL: The ID of the build>"
	echo "-noclean    <OPTIONAL: don't delete temp folder with zips>"
	echo "-user       <username on *.eclipse.org (default: $USER)>"
	echo "-q,-Q       <Quiet!>"

	echo " "
	echo "eg., on local (including sed to fix paths):"
	echo "  fixJavadocs.sh -sub ocl -Q -branch 1.0.0 -buildID I200601100010 "
}

if [ $# -lt 1 ]; then
	showhelp;
	exit 1
fi

norm="\033[0;39m";
grey="\033[1;30m";

# default to default properties file
defaultPropertiesFile=./promoteToEclipse.properties
propertiesFiles="";

# Create local variable based on the input
echo " "
echo "[fixdocs] Started `date`. Executing with the following options:"
while [ "$#" -gt 0 ]; do
	case $1 in
		'-f')
			propertiesFiles=$2;
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
			    echo "    [Can not load any of: $propertiesFiles. Exiting!]";
			    exit 99;
			fi
			shift 1
			;;
			
		'-branch')
			branch=$2;
			echo "   $1 $2";
			shift 1
			;;
		'-buildID')
			buildID=$2;
			echo "   $1 $2";
			shift 1
			;;
		'-noclean')
			noclean=1;
			echo "   $1";
			shift 0
			;;
		'-user')
			user=$2;
			echo "   $1 $2";
			shift 1
			;;
		'-q'|'-Q')
			quiet="-q";
			shift 0
			echo "   $1";
			;;
	esac
	shift 1
done	

if [ "$subprojectName" = "" ]; then # no value set!
  echo "[promote] No subproject name set by -sub flag. Script cannot continue. Exiting...";
  exit 9;
fi

if [ "x$buildID" = "x" ]; then
	echo "[fixdocs] [`date +%H:%M:%S`] ERROR: No buildID specified!";
	showhelp;
	exit 10;
fi

if [ "x$branch" = "x" ]; then
	echo "[fixdocs] [`date +%H:%M:%S`] ERROR: No branch specified!";
	showhelp;
	exit 11;
fi

# params 

buildServerCVSUser=$user"@"$buildServerFullName
eclipseSSHUser=$user"@"$downloadServerFullName
eclipseSSHUserHome=$(ssh $eclipseSSHUser "echo \$HOME");

dropsDir=$localWebDir/$subprojectName/downloads/drops
javadocDirPath="$subprojectName/javadoc";
tmpfolderLocal="$HOME/tmp-fixJavadocs-"$subprojectName"-"$user-`date +%Y%m%d_%H%M`;
tmpfolderRemote="$eclipseSSHUserHome/tmp-fixJavadocs-"$subprojectName"-"$user-`date +%Y%m%d_%H%M`; 

####################### METHODS ####################### 

slashescape () {
	target=`echo $1 | sed -e 's/\//\\\\\\//g' | sed -e 's/\./\\\\\./g'`
	echo $target;
}


contains () { # check if $1 contains $2, eg., if "./org.eclipse.emf.transaction.doc_1.0.0.v200604061140/doc.zip" contains "workspace.doc_1.0.0"
	if [ ""${1##*$2*} = "" ]; then _CONTAINS_=1; else _CONTAINS_=0; fi
	#echo "Contains: "$_CONTAINS_
}

fixJavadocPaths () # $1 = dir to work on, $2 target dir
{	# given a source and a target folder, create a list of files to fix, change that list to a series of script commands, and run that script
	# basically, we're doing string substitutions in multiple files to fix eclipse help platform urls to work on \$downloadServerFullName instead.
	sourcedir=$1; sourcedirESC="`slashescape $sourcedir`";
	targetdir=$2; 
	
	mkdir -p $sourcedir; # first, create the source folder in case it doesn't exist
	mkdir -p $targetdir; # then, create the target folder in case it doesn't exist
	cp -fr $sourcedir/* $targetdir/; # then, copy to ensure that all subfolders will exist

	# container for temporary script file
    mkdir -p $tmpfolderLocal;
	tmpfile=$tmpfolderLocal/fixJavadocs.sh.tmp
	if [ -f $tmpfile ]; then
		rm -fr $tmpfile; # purge tmpfile if it already exists
	fi

	sourceMatches=(
		"/help/topic/org.eclipse.platform.doc.isv"
		"/help/topic/org.eclipse.emf.doc/references/javadoc"
		"/help/topic/org.eclipse.emf.ecore.sdo.doc/references/javadoc"
		"/help/topic/org.eclipse.xsd.doc/references/javadoc"
		"/help/topic/org.eclipse.$subprojectName/references/javadoc"
		"/help/topic/org.eclipse.emf.$subprojectName/references/javadoc"
	) # array, before

	targetMatches=(
		"http://help.eclipse.org/help30/index.jsp?topic=/org.eclipse.platform.doc.isv"
		"/tools/emf/emf/javadoc/$branch"
		"/tools/emf/sdo/javadoc/$branch"
		"/tools/emf/xsd/javadoc/$branch"
		"/technology/$projectName/$javadocDirPath/$branch"
		"/technology/$projectName/$javadocDirPath/$branch"
	) # array, after

	# then, we need to get a list of files, and sed them into script form
	filesToFixCount="`find $sourcedir -type f -name \*.html -o -name \*.txt -o -name \*.xml | grep -c .`"
	filesToFix="`find $sourcedir -type f -name \*.html -o -name \*.txt -o -name \*.xml`"
	if [ $filesToFixCount -gt 0 ]; then
		echo "[fixpath] [`date +%H:%M:%S`] There are $filesToFixCount files to fix. Assembling sed script ...";
  		for fileToFix in $filesToFix; do
			fileToFixNoSource=`echo $fileToFix | sed -e "s/${sourcedirESC}\///g"`;	#echo "fileToFixNoSource=$fileToFixNoSource"
			if [ -f $fileToFix ]; then
				#echo "  "${fileToFix##*/};			# loop thru source/target matches
				index=0;
				elemcount=${#sourceMatches[@]}
				echostring="cat $fileToFix"
				while [ "$index"  -lt "$elemcount" ]; do
					sm="`slashescape ${sourceMatches[$index]}`";
					tm="`slashescape ${targetMatches[$index]}`";
					echostring=$echostring" | sed -e \"s/${sm}/${tm}/g\""
					let "index = $index + 1"
				done
				echostring=$echostring" > $targetdir/${fileToFixNoSource}"
				echo $echostring >> $tmpfile
				#echo "[fixpath] $echostring >> $tmpfile"
			else
				echo "ERROR: $fileToFix does not exist!";
			fi
		done
	else
		echo "[fixpath] [`date +%H:%M:%S`] There are $filesToFixCount files to fix. Nothing to do! Script will now exit.";
		exit 17;
	fi
	
	if [ -f $tmpfile ]; then
		echo "[fixpath] [`date +%H:%M:%S`] Running sed script..."
		#du -sh $tmpfile
		#head -5 $tmpfile;
		#cat $tmpfile | grep "EDataObjectSimpleAnyType.html";
		. $tmpfile;
		rm -fr $tmpfile;
	else 
		echo "ERROR: $tmpfile does not exist!";
	fi

	# to check diffs (testing/debuggery)
	#X#	echo " ";
	#X#	for fileToFix in $filesToFix; do
	#X#		fileToFixNoSource=`echo $fileToFix | sed -e "s/${sourcedirESC}\///g"`; 
	#X#		diff -r -H -t -y --suppress-common-lines $fileToFix $targetdir/$fileToFixNoSource 
	#X#	done
}

fixJavadocPerms() # $1 - user to ssh as, $2 - group owner, $3 - folder to fix
{
	targUser=$1;
	targOwner=$2;
	targFold=$3;
	echo "[fixperm] [`date +%H:%M:%S`] Fix perms & $targOwner group ... ";
	ssh $targUser "\
		cd $targFold ; \
		chgrp -fR $targOwner $targFold ; \
		find $targFold -type d -exec chmod -f 775 {} \; ; \
		find $targFold -type f -exec chmod -f 664 {} \; ; \
	";
	echo "[fixperm] [`date +%H:%M:%S`] done."
}

fixJavadoc () # $1 - package name, $2 base folder, $3 - drops dir, $4 - zipfile prefix
{
	targPack=$1; # subprojectName
	targFold=$2;
	dropsDir=$3;
	zipPrefix=$4

	echo "[fixdoc ] [`date +%H:%M:%S`] Unzip $targPack SDK zip to get doc plugin(s)... ";
	mkdir -p $tmpfolderLocal/$targPack
	unzip -o $quiet $dropsDir/$branch/$buildID/$zipPrefix-*.zip eclipse/plugins/*.doc_* -d $tmpfolderLocal/$targPack ; 

	for f in `cd $tmpfolderLocal/$targPack/eclipse/plugins ; find . -name "*.doc_*" -type f`; do
		echo "[fixdoc ] [`date +%H:%M:%S`] Unzip .jar $f ... ";
		unzip -o $quiet $tmpfolderLocal/$targPack/eclipse/plugins/$f -d $tmpfolderLocal/$targPack/eclipse/plugins/${f%\.jar}
		rm -fr $tmpfolderLocal/$targPack/eclipse/plugins/$f 
	done
	
	targPacks="";
	doczips=`cd $tmpfolderLocal/$targPack/eclipse/plugins ; find . -name "*doc.zip" -type f`; #echo "Found zips: '"$doczips"'"; 
	if [ "x$doczips" != "x" ]; then  
		for f in $doczips; do
			echo -n "[fixdoc ] [`date +%H:%M:%S`] Unzip doc.zip $f";
			# if we have more than one namespace, we need to not overwrite into same folder
			# if $targPack != $subprojectName, determine nested subfolder name to use instead
			# eg., check for "transaction.doc_1.0.0" in "./org.eclipse.emf.workspace.doc_1.0.0.v200604061140/doc.zip"
			contains $f $targPack".doc_"$branch; 
			if [ "x"$_CONTAINS_ != "x" ] && [ $_CONTAINS_ -le 0 ]; then 
				subtarget=${f%%.doc_*}; subtarget=${subtarget##*org.eclipse.emf.}; subtarget=${subtarget##*org.eclipse.};
				echo " ("$subtarget")";
				targPackFolder=$targPack"/"$subtarget;
				targPacks=$targPacks" "$subtarget; # store target folder(s)
			else
				targPackFolder=$targPack;
				targPacks=$targPacks" ."; # store target folder(s)
				echo " ("$targPack")";
			fi
			mkdir -p $tmpfolderLocal/javadoc/$targPackFolder;
			unzip $quiet $tmpfolderLocal/$targPack/eclipse/plugins/$f references/javadoc/* -d $tmpfolderLocal/javadoc/$targPackFolder ;
			 
			echo "[fixdoc ] [`date +%H:%M:%S`] Fix paths in javadoc/$targPackFolder/references/javadoc/ ... ";
			fixJavadocPaths $tmpfolderLocal/javadoc/$targPackFolder/references/javadoc $tmpfolderLocal"2/javadoc/"$targPackFolder/references/javadoc ;
		done
	else
		docdirs=`cd $tmpfolderLocal/$targPack/eclipse/plugins ; find . -name "*.doc_*" -type d`; # echo "Found dirs: '"$docdirs"'"; 
		for f in $docdirs; do
			echo -n "[fixdoc ] [`date +%H:%M:%S`] Copy $f";
			contains $f $targPack".doc_"$branch; 
			if [ "x"$_CONTAINS_ != "x" ] && [ $_CONTAINS_ -le 0 ] && [ "$subtarget" != "$subprojectName" ]; then 
				subtarget=${f%%.doc_*}; subtarget=${subtarget##*org.eclipse.emf.}; subtarget=${subtarget##*org.eclipse.};
				echo " ("$subtarget")";
				targPackFolder=$targPack"/"$subtarget;
				targPacks=$subtarget" "$targPacks; # store target folder(s)
			else
				targPackFolder=$targPack;
				targPacks=". "$targPacks; # store target folder(s)
				echo " ("$targPack")";
			fi
			mkdir -p $tmpfolderLocal/javadoc/$targPackFolder/references/javadoc/;
			cp -r $tmpfolderLocal/$targPack/eclipse/plugins/$f/references/javadoc/* $tmpfolderLocal/javadoc/$targPackFolder/references/javadoc/
			
			echo "[fixdoc ] [`date +%H:%M:%S`] Fix paths in javadoc/$targPackFolder/references/javadoc/ ... ";
			fixJavadocPaths $tmpfolderLocal/javadoc/$targPackFolder/references/javadoc $tmpfolderLocal"2/javadoc/"$targPackFolder/references/javadoc ;
		done
	fi

	for t in $targPacks; do
		if [ "$t" != "." ] && [ "$t" != "$subprojectName" ]; then 
			targFolder=$targFold"/"$t"/"$branch;
		else 
			targFolder=$targFold"/"$branch
		fi
		#echo "[fixdoc ] [`date +%H:%M:%S`] Delete "$targFolder" ... ";
		rm -fr $targFolder; mkdir -p $targFolder;
		echo -e "[fixdoc ] [`date +%H:%M:%S`] Move .../"$grey$targPack$norm"/"$grey$t$norm"/references/javadoc/* to "$targFolder" ...";
		if [ -d $tmpfolderLocal"2/javadoc/"$targPack"/"$t"/references/javadoc/" ]; then
			mv -f $tmpfolderLocal"2/javadoc/"$targPack"/"$t"/references/javadoc/"* $targFolder; 
		else
			echo "[fixdoc ] Error 13: Can not move $targPack docs! Javadoc creation failed!"
			exit 13;
		fi
	done
	echo "[fixdoc ] [`date +%H:%M:%S`] Cleanup temp space... ";
	rm -fr $tmpfolderLocal $tmpfolderLocal"2"
}

####################### REMOTE ####################### 

sendZips () 
{
	index=0;
	elemcount=${#javadocModuleArray[@]}
	ssh $eclipseSSHUser "mkdir -p $tmpfolderRemote;";
	while [ "$index"  -lt "$elemcount" ]; do
		targPackRoot=${javadocModuleArray[$index]}; # eg., emft-transaction
		targFoldRoot=${javadocFolderArray[$index]}; # eg., /transaction

		searchPath=$localWebDir$targFoldRoot"/javadoc/";
		#echo "find $searchPath -maxdepth 2 -type d | egrep \"$branch\$\" ...";		
		for f in `find $searchPath -maxdepth 2 -type d | egrep "$branch$" | sort`; do
			subtarg=$searchPath; subtarg=${f##$subtarg}; subtarg=${subtarg%%/$branch}; subtarg=${subtarg/\//}; 
			# should get "" or "transaction", or something different like "workspace"
			if [ "x$subtarg" = "x" ] || [ "/$subtarg" = "$targFoldRoot" ] || [ "$subtarg" = "$branch" ]; then
				targFold=$targFoldRoot"/javadoc";
				targPack=$targPackRoot;
			else 
				targFold=$targFoldRoot"/javadoc/"$subtarg;
				targPack=$targPackRoot"-"$subtarg;
			fi
		    
			echo -e "[fixdoc ] [`date +%H:%M:%S`] Create $targPack-doc.zip from $localWebDir$grey$targFold$norm/$branch ...";
			
			# create zip
			ssh $buildServerCVSUser "
				mkdir -p $tmpfolderLocal; \
				cd $localWebDir/$targFold/$branch; \
				zip $quiet -9 -r $tmpfolderLocal/$targPack-doc.zip * \
			";
			# test zip
			cd $tmpfolderLocal; check=`zip -Tq $tmpfolderLocal/$targPack-doc.zip`
			if [ "x$check" != "x" ]; then echo "  ERROR! Zipfile problem: $check"; return 2; fi
			
			# scp zip to remote, scrub existing target dir, then unpack it into new target dir
			echo "[fixdoc ] [`date +%H:%M:%S`] SCP $targPack-doc.zip ...";
		    scp $tmpfolderLocal/$targPack-doc.zip $eclipseSSHUser:$tmpfolderRemote/$targPack-doc.zip;
		    
			echo "[fixdoc ] [`date +%H:%M:%S`] Unpack $targPack-doc.zip into $projectWWWDir$targFold/$branch ...";
			ssh $eclipseSSHUser "if [ -f $tmpfolderRemote/$targPack-doc.zip ]; then \
					cd $tmpfolderRemote; rm -fr $projectWWWDir$targFold/$branch/*; mkdir -p $projectWWWDir$targFold/$branch; \
					unzip -uo $quiet $tmpfolderRemote/$targPack-doc.zip -d $projectWWWDir$targFold/$branch; \
				else \
					echo Error: Can not find $tmpfolderRemote/$targPack-doc.zip. Javadoc regeneration failed.; \
				fi \
			";
		done
					
		# clean up on remote
		if [ $noclean -eq 0 ]; then 
		  ssh $eclipseSSHUser "if [ -d $tmpfolderRemote ]; then rm -fr $tmpfolderRemote; fi";
		fi
	    let "index = $index + 1"
	done
	# clean up on local
	if [ $noclean -eq 0 ]; then 
	  ssh $buildServerCVSUser "if [ -d $tmpfolderLocal ]; then rm -fr $tmpfolderLocal; fi";
	fi
	echo " "; # newline
}

####################### RUN ####################### 

# 1. local: unpack from SDK & fix paths in javadoc; delete existing, copy to web path & fix perms

# fix docs :: $1 - package name, $2 base folder, $3 - drops dir, $4 - zipfile prefix
echo " ";
echo "Using tmpfolderLocal: $tmpfolderLocal";
echo " "
echo "== $buildServerCVSUser:$localWebDir/$javadocDirPath/$branch ==";
fixJavadoc $subprojectName $localWebDir/$javadocDirPath $dropsDir $projectName-$subprojectName-SDK;
fixJavadocPerms $buildServerCVSUser $buildUserGroup $localWebDir/$javadocDirPath;
echo " ";
echo "Using tmpfolderRemote: $tmpfolderRemote";
echo " "
# 2. remote: zip up javadocs, scp to remote, delete existing, unpack, fix perms
echo "== $eclipseSSHUser:$projectWWWDir/$javadocDirPath/$branch == ";
sendZips;
fixJavadocPerms $eclipseSSHUser $eclipseUserGroup $projectWWWDir/$javadocDirPath;
ssh $eclipseSSHUser "rm -fr $tmpfolderRemote ${tmpfolderRemote}*"
echo " ";
echo "[fixdocs] [`date +%H:%M:%S`] done!";
echo " ";