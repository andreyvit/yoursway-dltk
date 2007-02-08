#!/bin/bash
# $Id: run.sh,v 1.1 2007/02/08 12:38:55 aplatov Exp $

# Runs the build.
#
# IMPORTANT: This script is supposed to be run from the "scripts" directory of a
#            checked out version of "org.eclipse.dltk.$project.releng"
if [ $# -lt 16 ]; then
	echo "usage: run.sh"
	echo "-branch           <CVS branch of the files to be built>"
	echo "-proj             <REQUIRED: shortname of the project to be build, eg. ocl, validation, query>"
	echo "-branch       <CVS branch of the files to be built (eg., build_200409171617 instead of HEAD)>"
	echo "-projRelengBranch <CVS branch of org.eclipse.dltk.$project.releng>"
	echo "-URL              <The URLs of the Eclipse driver, EMF driver, and any other zips that need to be unpacked into"
	echo "                   the eclipse install to resolve all dependencies. Enter one -URL [...] per required URL>"
	echo "-antTarget        <The Ant target of the build script - controls the packages that will be built"
	echo "-buildType        <The type of the build>"
	echo "-javaHome         <The JAVA_HOME directory>"
	echo "-buildDir         <The build directory>"
	echo "-downloadsDir     <The directory where the Eclipse drivers (and other downloads) are located>"
	echo "-repoInfoFile     <The build configuration file>"
	echo "-buildTimestamp   <Optional: the build timestamp>"
	echo "-buildAlias       <Optional: the build alias - Ex. 2.0.0, to be used in the zip names>"
	echo "-tagBuild         <Optional: defines if the files are tagged - Values: true|false  - Default: true>"
	echo " "
	echo "Note: to run tests only (no build) use -antTarget runTestsOnly"
	exit 1
fi

# Default value for the build timestamp
buildTimestamp=`date +%Y%m%d\%H%M`
buildID=$buildTimestamp

buildDir=$PWD/$buildType$buildTimestamp
tagBuild='true'

dependURL=""; # loaded from -URL

proj=""; # REQUIRED

echo "";
echo "[`date +%Y%m%d\ %H\:%M\:%S`] run.sh executing with the following options:"
# Create local variable based on the input
while [ "$#" -gt 0 ]; do
	echo "    $1 $2";
	case $1 in
		'-proj')
			proj=$2;
			shift 1
			;;
		'-branch')
			branch=$2;
			shift 1
			;;
		'-branch')
			branch=$2;
			shift 1
			;;
		'-projRelengBranch')
			projRelengBranch=$2;
			shift 1
			;;
		'-URL')
			if [ "x$dependURL" != "x" ]; then
			  dependURL="$dependURL "
			fi
			dependURL=$dependURL"-URL $2";
			shift 1
			;;
		'-javaHome')
			javaHome=$2;
			shift 1
			;;
		'-antTarget')
			antTarget=$2;
			shift 1
			;;
		'-buildType')
			buildType=$2;
			shift 1
			;;
		'-buildDir')
			buildDir=$2;
			shift 1
			;;
		'-downloadsDir')
			downloadsDir=$2;
			shift 1
			;;
		'-buildTimestamp')
			buildTimestamp=$2;
			buildID=$buildTimestamp; # buildID and buildTimestamp should be the same value
			shift 1
			;;
		'-buildAlias')
			buildAlias=$2;
			shift 1
			;;
		'-tagBuild')
			tagBuild=$2;
			shift 1
			;;
		'-repoInfoFile')
			repoInfoFile=$2;
			shift 1
			;;
	esac
	shift 1
done

if [ -z $repoInfoFile ]; then
	echo "Flag -repoInfoFile missing!"
	exit 1;
fi


# org.eclipse.*releng* directories
relengBuilderDir=$buildDir/org.eclipse.dltk.$proj.releng
relengCommonBuilderDir=$buildDir/org.eclipse.dltk.common.releng
relengBaseBuilderDir=$buildDir/org.eclipse.releng.basebuilder

echo ""
echo "relengBuilderDir: $relengBuilderDir"
echo "relengCommonBuilderDir: $relengCommonBuilderDir"
echo "relengBaseBuilderDir: $relengBaseBuilderDir"


# Getting the build tag
buildTag=build_$buildTimestamp
try=`echo $buildDir | sed 's/eclipse$//'`
if [ $buildDir == `echo $buildDir | sed 's/eclipse$//'` ]; then
	buildDir="$buildDir/eclipse"
fi

if [ $tagBuild == 'false' ]; then
	buildTag=$branch
fi

echo "buildID: $buildID"
echo "buildTag: $buildTag"
echo "buildDir: $buildDir"

$relengCommonBuilderDir/scripts/executeCommand.sh "mkdir -p $buildDir $downloadsDir"

# Creates the mapping files and adjusting CVS
command="$relengCommonBuilderDir/scripts/adjustCVS.sh"
command=$command" -repoInfoFile $repoInfoFile"
command=$command" -baseDir $relengBuilderDir"
command=$command" -proj $proj"
command=$command" -branch $branch"

if [ x$emfBranch != x ]; then
	command=$command" -branch $branch"; # override default $branch
fi

if [ x$projRelengBranch != x ]; then
	command=$command" -projRelengBranch $projRelengBranch"; # override default $branch
fi

command=$command" -buildTag $buildTag"
command=$command" -tagBuild $tagBuild"
command=$command" $dependURL"; # attach all the -URL ... -URL ... values 

#$relengCommonBuilderDir/scripts/executeCommand.sh "$command"
$command

export JAVA_HOME=$javaHome

if [ $tagBuild == 'false' ]; then
	mkdir -p $buildDir/maps/org.eclipse.dltk.$proj/
	cp $relengBuilderDir/maps/* $buildDir/maps/org.eclipse.dltk.$proj/
fi

if [ x$buildAlias != x ]; then
	buildAlias="-buildAlias $buildAlias"
fi

# Invoking the Eclipse build
command="$relengCommonBuilderDir/scripts/build.sh"
command=$command" -mapVersionTag $buildTag"
command=$command" -vm $javaHome/bin/java"
#command=$command" -bc $javaHome/jre/lib/rt.jar"
#command=$command" -bc $javaHome" # removed as this is now defined in the ant script instead
command=$command" -antScript $relengBuilderDir/buildAll.xml"
command=$command" -target $antTarget"
command=$command" -buildID $buildID"
command=$command" -buildTimestamp $buildTimestamp"
command=$command" -relengBaseBuilderDir $relengBaseBuilderDir"
command=$command" -downloadsDir $downloadsDir"
command=$command" -buildDir $buildDir"
command=$command" -baseDir $relengBuilderDir"
command=$command" $buildAlias"
command=$command" $buildType"
$relengCommonBuilderDir/scripts/executeCommand.sh "$command"

# generate a log of any compiler problems, warnings, errors, or failures
echo -n "Generating compilelogs summary... ";
if [ -d $buildDir/../compilelogs ]; then
  summary=$($relengCommonBuilderDir/scripts/getCompilerResults.sh $buildDir/../compilelogs);
  echo $summary > $buildDir/../compilelogs/summary.txt
  if [ "x$summary" != "x" ]; then 
  	echo $summmary": ";
  fi
  echo "done.";
else
  echo "skipped.";
fi 