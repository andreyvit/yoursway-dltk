# !/bin/sh

#Min Hr Mday Month Wday Cmd
# www-data crontab: an I build every Thu (4) at 12am (HEAD)
#0 0 * * 4 /home/www-data/build/emft/scripts/start_cron.sh -proj ocl -buildType N -tagBuild false -cvsbranch HEAD 2>&1 > $HOME/cron_logs/start_cron.sh.$proj.N.log.txt 

function usage()
{
	echo "usage: start_cron.sh"
	echo "-proj            <required: project name, eg. emf, uml2, etc.>"
	echo "-URL             <The URLs of the Eclipse driver, EMF driver, and any other zips that need to be unpacked into"
	echo "                  the eclipse install to resolve all dependencies. Enter one -URL [...] per required URL.>"     
	echo "-depends         <if not specifying dependency URLs, specify targets: eclipse, emf, uml2, ocl, validation, ..."
	echo "                  Enter one -depends per required project. Default: eclipse and emf>"
	echo "-buildType       <required: The type of the build - N, I, S, M (not R!)>"
	echo "-branchNum       <required: define the branch to build, eg., 2.0.2 or 2.1.0>"
	echo "-cvsbranch       <optional: define the CVS branch to build, eg., R2_0_maintenance or HEAD (default: HEAD)>"
	echo "-buildAlias      <The Alias of the build, eg. 2.0.6>"
	echo "-tagBuild        <optional: defines if the files are tagged - Values: true|false - Default: false>"
	echo "-javaHome        <default is /opt/ibm-java2-1.4, but you can specify something else here>"
	echo "-basebuilderBranch <optional: CVS branch of org.eclipse.releng.basebuilder>"
	echo "-projRelengBranch  <optional: CVS branch of org.eclipse.\$proj.releng.build>"
	echo ""
	exit 1
}

if [ $# -lt 1 ]; then
	usage;
fi

proj="";
tagBuild='false';
branchNum="";
cvsbranch="HEAD";
javaHome=/opt/sun-java2-1.4;

# Create local variable based on the input
while [ "$#" -gt 0 ]; do
	case $1 in
		'-proj')
			proj=$2;
			shift 1
			;;
		'-URL')
			if [ "x$dependURL" != "x" ]; then
			  dependURL="$dependURL "
			fi
			dependURL=$dependURL"$2";
			#echo "   $1 $2";
			shift 1
			;;
		'-depends')
			if [ "x$depends" != "x" ]; then
			  depends=$depends" ";
			fi
			depends=$depends"$2";
			#echo "   $1 $2";
			shift 1
			;;
		'-buildType')
			buildType=$2;
			shift 1
			;;
		'-branchNum')
			branchNum=$2;
			shift 1
			;;
		'-cvsbranch')
			cvsbranch=$2;
			shift 1
			;;
		'-buildAlias')
			buildAlias="-buildAlias "$2;
			shift 1
			;;
		'-tagBuild')
			tagBuild=$2;
			shift 1
			;;
		'-javaHome')
			javaHome=$2;
			shift 1
			;;
		'-basebuilderBranch')
			basebuilderBranch="-basebuilderBranch "$2;
			shift 1
			;;
		'-projRelengBranch')
			projRelengBranch="-projRelengBranch "$2;
			shift 1
			;;
	esac
	shift 1
done

if [ "x$depends" = "x" ]; then
	depends="eclipse emf"; # default reqs if none other specified
fi

if [ "x$proj" = "x" ]; then
	usage;
fi

requestsdir=/home/www-data/build/emft/requests;
buildOptions=/var/www/technology/emft/build.options.txt;

deltas=1;
# TODO: add support for only doing a build if changes in CVS (what's new cvs check)

# get branch (assume $cvsbranch), need numerical value, eg., 2.1.0
if [ "x$branchNum" == "x" ]; then
	branch=`cat $buildOptions | grep -v "#" | grep "=$cvsbranch"`;
	#echo $branch
	indx=`expr index "$branch" "=$cvsbranch"`; (( indx-- ));
	cvsbranch=${branch##*=};
	cvsbranch=${cvsbranch%%|*};
	branch=${branch%%=*};
	branchNum=${branch%%=*};
else
	branch=$branchNum;
fi
#echo "branch=$branch"
#echo "cvsbranch=$cvsbranch"
#echo "branchNum=$branchNum"

# get timestamp to use
timestamp=`date +%Y%m%d%H%M`;

# create folder
mkdir -p /home/www-data/build/emft/$proj/downloads/drops/$branch/$buildType$timestamp/eclipse ;

# define logfile	
logfile=/home/www-data/build/emft/$proj/downloads/drops/$branch/$buildType$timestamp/buildlog.txt;

if [ "$dependURL" == "" ]; then 
	# 4. get eclipseURL from dependencies.urls.txt (last line, for each matching dependency type
	URLFile=$requestsdir/dependencies.urls.txt
	for dep in $depends; do
		depURL="";
		if [ "$dep" = "eclipse" ]; then
			echo -n "[nightly] Get $dep dependency: " >> $logfile;
			if [ "${cvsbranch##*_}" == "maintenance" ]; then # working on an M build
			  depURL=""`cat $URLFile | egrep "[[:digit:]]$dep" | egrep "drops/([RS]-|M200)" | tail -1`; # get R, S, M builds
			elif [ "${branchNum##*\.}" == "0" ]; then # working on an x.y.0 build:
			  depURL=""`cat $URLFile | egrep "[[:digit:]]$dep" | egrep "drops/([RS]-|I200)" | tail -1`; # get R, S, I builds
			else
			  depURL=""`cat $URLFile | egrep "[[:digit:]]$dep" | egrep "drops/([RS]-|I200|M200)" | tail -1`; # get R, S, I, or M builds
			fi
		else 
			echo -n "[nightly] Get $dep dependency: " >> $logfile;
			if [ "${cvsbranch##*_}" == "maintenance" ]; then # working on an M build
			  depURL=""`cat $URLFile | egrep "[[:digit:]]$dep" | egrep "drops/[0-9.]+/([RSM]200)" | tail -1`; # get R, S, M builds
			elif [ "${branchNum##*\.}" == "0" ]; then # working on an x.y.0 build:
			  depURL=""`cat $URLFile | egrep "[[:digit:]]$dep" | egrep "drops/[0-9.]+/([RSI]200)" | tail -1`; # get R, S, I builds
			else
			  depURL=""`cat $URLFile | egrep "[[:digit:]]$dep" | egrep "drops/[0-9.]+/([RSIM]200)" | tail -1`; # get R, S, I builds
			fi
		fi
		depURL=${depURL#*$dep=}; # remove the prefix, eg. 1eclipse=, 2emf=
	    echo $depURL >> $logfile;
	    dependURL=$dependURL" "$depURL;
	done
fi

# assemble instructions for start.sh
command="/home/www-data/build/emft/scripts/start.sh"
command=$command" \
 -proj $proj \
 -branch $cvsbranch \
 -version $branch \
";
if [ "x$dependURL" != "x" ]; then
	for dep in $dependURL; do
		command=$command" -URL $dep"
	done
fi
command=$command" \
 -antTarget run \
 -tagBuild $tagBuild \
 -buildType $buildType \
 -javaHome $javaHome \
 -downloadsDir /home/www-data/build/emft/$proj/downloads \
 -buildDir /home/www-data/build/emft/$proj/downloads/drops/$branch/$buildType$timestamp \
 -buildTimestamp $timestamp \
 $buildAlias \
 $basebuilderBranch \
 $projRelengBranch \
";

# echo instructions
echo " ";
echo "$command" | perl -pe "s/ -/\n  -/g";
echo "  > $logfile";
echo " ";

# log instuctions
echo "[nightly] Starting automatic $buildType build at `date +%Y%m%d\ %H:%M:%S`" >> $logfile;
echo " " >> $logfile;
echo "$command" | perl -pe "s/ -/\n  -/g" >> $logfile;
echo " " >> $logfile;

# execute instructions (start build)
$command 1>> $logfile 2>> $logfile &

