#!/bin/sh

usage() {
	echo "usage: buildAll"
	echo "	[-mapVersionTag HEAD|<branch name>]"
	echo "	[-vm <url to java executable to run build>]"
	echo "	[-bc <bootclasspath>]"
	echo "	[-antScript <the ant script to be executed>]"
	echo "	[-target <build target to execute>]"
	echo "	[-buildID <buildID, e.g. 200406171012>]"
	echo "  [-baseDir <directory where org.eclipse.dltk/releng/{subproj} is checked out>]"
	echo "  [--relengBaseBuilderDir <directory where org.eclipse.releng.basebuilder is located>]"
	echo "	[-buildTimestamp <timestamp of the build>]"
	echo "  [-downloadsDir <the directory where the eclipse drivers and other downloads (zip files) are located>]"
	echo "	[-buildDir <the directory of the current build>]"
	echo "  [-buildAlias <Optional: the build alias - Ex. 2.0.0, to be used in the zip names>]"
	echo "	[-ftp <userid> <password>] [-rsync <rsync password file>]"
	echo "	S|I|M|N|R"
	echo " "
	echo "Note: to run tests only (no build) use -target runTestsOnly"
}

# tag to use when checking out .map file project
mapVersionTag=HEAD

# default setting for buildType
buildType=""

# default setting for buildID
buildID=""

# default bootclasspath
bootclasspath=""

# vm used to run the build.  Defaults to java on system path
vm=java

# ant script to be executed
antScript=../buildAll.xml

# target used if not default (to allow run just a portion of buildAll)
target=""

# build timestamp
buildTimestamp=""

# FTP user/password, required for Windows to ftp. Without it, no push.
ftpUser=""
ftpPassword=""

# RSYNC Password file location, required for Linux. Without it, no push.
rsyncPWFile=""

# org.eclipse.releng.basebuilder directory
relengBaseBuilderDir=""

baseDir="$PWD/.."

if [ "x$1" == "x" ] ; then
        usage
        exit 0
fi

while [ "$#" -gt 0 ] ; do
        case $1 in
                '-mapVersionTag')
                        mapVersionTag=$2;
                        shift 1
                    ;;
                '-vm')
                        vm=$2;
                        shift 1
                    ;;
                '-bc')
                        bootclasspath="-Dbootclasspath=$2";
                        shift 1
                    ;;
                '-antScript')
                        antScript=$2;
                        shift 1
                    ;;
                '-target')
                        target=$2;
                        shift 1
                    ;;
                '-buildID')
                        buildID="-DbuildID=$2";
                        shift 1
                    ;;
                '-buildDir')
                        buildDir="-DbuildDirectory=$2";
                        shift 1
                    ;;
                '-downloadsDir')
                        downloadsDir="-DdownloadsDir=$2";
                        shift 1
                    ;;
		'-baseDir')
			baseDir=$2;
			shift 1
			;;
                '-buildTimestamp')
                        buildTimestamp="-Dtimestamp=$2";
                        shift 1
                    ;;
		'-buildAlias')
			buildAlias=$2;
			shift 1
			;;
                '-relengBaseBuilderDir')
                        relengBaseBuilderDir=$2;
                        shift 1
                    ;;
                '-ftp')
                        ftpUser="-DftpUser=$2";
                        ftpPassword="-DftpPassword=$3"
                        shift 2
                    ;;
                '-rsync')
               		rsyncPWFile="-DrsyncPWFile=$2"
               		shift 1
                	;;
                *)
                    buildType=$1
                    ;;
        esac
        shift 1
done

if [ -z $buildType ] ; then
	usage
	exit 0
fi

baseDirCommon=$baseDir/../org.eclipse.dltk.common.releng

if [ x$buildAlias != x ]; then
	buildAlias=" -DbuildAlias=$buildAlias"
fi

echo Invoking Eclipse build with -enableassertions 

command="$vm -enableassertions -cp $relengBaseBuilderDir/startup.jar org.eclipse.core.launcher.Main"
command=$command" -application org.eclipse.ant.core.antRunner"
command=$command" -f $antScript $target"
#command=$command" $bootclasspath" # removed: this is now specified in the ant task
command=$command" -DmapVersionTag=$mapVersionTag"
command=$command" -DbuildType=$buildType"
command=$command" $buildID"
command=$command" $buildTimestamp"
command=$command" $buildDir"
command=$command" $buildAlias"
command=$command" $downloadsDir"
command=$command" $rsyncPWFile $ftpUser $ftpPassword"
$baseDirCommon/scripts/executeCommand.sh "$command"
