#!/bin/bash

# Adjusts the CVS for the build by
# 	1. RTagging all source files
#	2. Creating the map file (directory.txt) and build.cfg (build properties, such as eclipseURL and emfURL)
#	3. Checking and tagging the mapping file

proj=""; # REQUIRED
if [ $# -eq 0 ]; then
	echo "usage: adjustCVS.sh"
	echo "-proj             <REQUIRED: shortname of the project to be build, eg. ocl, validation, query>"
	echo "-baseDir          <directory where org.eclipse.dltk.*.releng was checked out>"
	echo "-branch           <CVS branch of the files to be built (eg., build_200409171617 instead of HEAD)>"
	echo "-projRelengBranch <CVS branch of org.eclipse.dltk.*.releng>"
	echo "-buildTag         <the tag for the files of this build>"
	echo "-repoInfoFile     <The build configuration file>"
	echo "-tagBuild         <Optional: defines if the files are tagged - Values: true|false  - Default: true>"
	echo "-URL              <The URLs of the Eclipse driver, EMF driver, and any other zips that need to be unpacked into"
	echo "                   the eclipse install to resolve all dependencies. Enter one -URL [...] per required URL."
	echo "                   This script will guess which is which based on file patterns specified below>"
	exit 1
fi

tagBuild='true'
dependURL=""; # loaded from -URL
echo "[`date +%H\:%M\:%S`] adjustCVS.sh executing with the following options:"
# Create local variable based on the input
while [ "$#" -gt 0 ]; do
	echo "  $1 $2"
	case $1 in
		'-proj')
			proj=$2;
			shift 1
			;;
		'-branch')
			branchCVS=$2;
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
			dependURL=$dependURL"$2";
			shift 1
			;;
		'-baseDir')
			baseDir=$2;
			shift 1
			;;
		'-buildTag')
			buildTag=$2;
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

baseDirCommon=$baseDir/../org.eclipse.dltk.common.releng

# build two arrays of dependencies by name and by URL
depNames=( "eclipse" "dltk" );
depMatches=("eclipse-platform" "dltk-*-SDK" );
depURLs=();

echo "";
index=0;
element_count=${#depNames[@]}
while [ "$index"  -lt "$element_count" ]; do
	# find a substring in a URL to determine if that URL is for a given project
	guessedURL="";
	match=${depMatches[$index]};
	for url in $dependURL; do
	  #echo -n "Look for $match in $url ... "
	  left=${url##*$match*}
	  if [ "x$left" = "x" ]; then # found substring, so resulting string is null
	  depURLs[$index]=$url
	    echo "[adjustCVS] Got: "${depNames[$index]}" = "${depURLs[$index]}
	    #echo -n "found!"
	  fi
	  #echo ""
	done
	let "index = $index + 1";
done
echo "";

# break if minimum REQUIRED URLs are missing
if [ "x"${depURLs[0]} = "x" ]; then
	echo "Error: no Eclipse URL specified! Script will now exit."
	exit 99;
#elif [ "x"${depURLs[1]} = "x" ]; then
#	echo "Error: no EMF URL specified! Script will now exit."
#	exit 99;
fi 
# do nothing if secondary required URLs (like validationURL, oclURL or uml2URL) are missing

# Reading properties
scriptDir=`dirname $0`
cvsHost=`$scriptDir/readProperty.sh $repoInfoFile cvsHost`
cvsReadProtocol=`$scriptDir/readProperty.sh $repoInfoFile cvsReadProtocol`
cvsWriteProtocol=`$scriptDir/readProperty.sh $repoInfoFile cvsWriteProtocol`
cvsReadUser=`$scriptDir/readProperty.sh $repoInfoFile cvsReadUser`
cvsWriteUser=`$scriptDir/readProperty.sh $repoInfoFile cvsWriteUser`
cvsWriteRelengUser=`$scriptDir/readProperty.sh $repoInfoFile cvsWriteRelengUser`
cvsRep=`$scriptDir/readProperty.sh $repoInfoFile cvsRep`

# Setting environment variables
export CVS_RSH=ssh
export CVSROOT=:$cvsWriteProtocol:$cvsWriteUser@$cvsHost:$cvsRep
echo "CVSROOT = $CVSROOT"

projRelengBranchCmd="";
if [ "$projRelengBranch" = "HEAD" ]; then
	projRelengBranchCmd="";
elif [ x$projRelengBranch != x ]; then
	projRelengBranchCmd="-r $projRelengBranch";
elif [ $branchCVS != HEAD ]; then
	projRelengBranchCmd="-r $branchCVS"; # by default, if project build from R1_0_maintenance, use same tag for o.e.*.releng
fi
	
# RTagging source files
if [ $tagBuild != 'false' ]; then
	command="cvs -d :$cvsWriteProtocol:$cvsWriteUser@$cvsHost:$cvsRep -q rtag -r $branchCVS $buildTag"
	command=$command" org.eclipse.dltk/$proj/features"
	command=$command" org.eclipse.dltk/$proj/plugins"
	command=$command" org.eclipse.dltk/$proj/tests"
	command=$command" org.eclipse.dltk/$proj/examples"
	command=$command" org.eclipse.dltk/$proj/doc"
	$baseDirCommon/scripts/executeCommand.sh "$command"
fi

# TODO: replace with Ant script(s) instead, bug 143365
# will require passing in a few extra params, including:
#   regex to match when searching for plugins/features/fragments, eg: org.eclipse.emf.jet.editor
#   dir to search: ${baseDir%/*}/eclipse
# add these new params to the buildcfg file, then pass that to Ant when running createMapAndTestManifestFile.xml (or whatever it's now called)

# Creating the map file
command="perl $baseDirCommon/scripts/createMapAndTestManifestFile.pl"
command=$command" $repoInfoFile"
command=$command" $baseDir/maps/$proj.map"
command=$command" $baseDir/templateFiles/$proj.map.template"
command=$command" $baseDir/testManifest.xml"
command=$command" $baseDir/templateFiles/testManifest.xml.template"
command=$command" $buildTag"
command=$command" $proj"
echo "";
echo "[`date +%H\:%M\:%S`]"
for c in $command; do echo "  "$c; echo -n "  "; done; echo "";
$command

buildDir=${baseDir%/*};     # up 1 from basedir (N2005.../... => N2005...)
buildID=${buildDir##*/};    # trim up to N2005..., leaving just N2005...
branchNum=${buildDir%/*};   # trim off N2005..., 
branchNum=${branchNum##*/}; # then up to 1.0.0

# Creating the build.cfg file
buildcfg="$buildDir/build.cfg";

echo "#Build server absolute paths" >> $buildcfg
echo "downloadsDir=/home/www-data/build/downloads" >> $buildcfg; 
echo "thirdPartyJarsDir=/home/www-data/build/3rdPartyjars" >> $buildcfg;
echo "" >> $buildcfg

echo "#Platform details" >> $buildcfg
echo "baseos=linux" >> $buildcfg
echo "basews=gtk" >> $buildcfg
echo "basearch=x86" >> $buildcfg
echo "" >> $buildcfg

echo "#general details" >> $buildcfg;
echo "subprojectName=$proj" >> $buildcfg;
echo "buildDir="$buildDir >> $buildcfg; 
echo "buildID="${buildID} >> $buildcfg; 
echo "buildType="${buildID:0:1} >> $buildcfg; # get single letter, N
echo "buildDateStamp="${buildID:1} >> $buildcfg; # get the rest, yyyymmddhhmm
echo "branch="$branchNum >> $buildcfg; 
echo "branchCVS=$branchCVS" >> $buildcfg
echo "" >> $buildcfg

echo "#Base download URL" >> $buildcfg
echo "eclipseDownloadURL=http://www.eclipse.org/downloads/download.php?file=" >> $buildcfg
echo "" >> $buildcfg

index=0;
element_count=${#depNames[@]}
while [ "$index" -lt "$element_count" ]; do
	n=${depNames[$index]}; # label
	u=${depURLs[$index]}; # URL
	dropFile=${u/http:\/\//}; dropFile="/"${dropFile#*/}; # trim http://servername, keep prefix /
	echo "#$n" >> $buildcfg
	echo $n"URL=$u" >> $buildcfg
	echo $n"File="${u##*/} >> $buildcfg
	if [ "$n" = "eclipse" ]; then # eclipse drops have /eclipse in them, but the direct URLs used do not
	    #echo -n "Look for /eclipse in $dropFile ... "
	    left=${dropFile##/eclipse*}
	    if [ "x$left" = "x" ]; then # found substring, so resulting string is null
			echo $n"DropFile="$dropFile >> $buildcfg
			echo $n"BuildURL="${dropFile%/*} >> $buildcfg
		else # prepend /eclipse since it's not there
			echo $n"DropFile=/eclipse"$dropFile >> $buildcfg
			echo $n"BuildURL=/eclipse"${dropFile%/*} >> $buildcfg
		fi
	else
		echo $n"DropFile=$dropFile" >> $buildcfg
		echo $n"BuildURL="${dropFile%/*} >> $buildcfg
	fi
	if [ "$n" = "emf" ]; then
		# assume examples are from the same build as the SDK; just rename the zip to get the right URL
		examplesURL=${depURLs[$index]/-SDK-/-Examples-};
		echo $n"ExamplesURL=$examplesURL" >> $buildcfg
		echo $n"ExamplesFile="${examplesURL##*/} >> $buildcfg
	fi
	echo "" >> $buildcfg
	let "index = $index + 1";
done

# copy mapfile to root too & rename
cp $baseDir/maps/$proj.map $baseDir/../directory.txt;

# Checking in and tagging the files
if [ $tagBuild != 'false' ]; then
	currentDir=$PWD
	cd $baseDir
	$baseDirCommon/scripts/executeCommand.sh "cvs -d :$cvsWriteProtocol:$cvsWriteRelengUser@$cvsHost:$cvsRep -q ci -m $buildTag"
	cd $currentDir
	$baseDirCommon/scripts/executeCommand.sh "cvs -d :$cvsWriteProtocol:$cvsWriteRelengUser@$cvsHost:$cvsRep -q rtag $projRelengBranchCmd $buildTag org.eclipse.dltk/releng/common"
	$baseDirCommon/scripts/executeCommand.sh "cvs -d :$cvsWriteProtocol:$cvsWriteRelengUser@$cvsHost:$cvsRep -q rtag $projRelengBranchCmd $buildTag org.eclipse.dltk/releng/$proj"
fi
