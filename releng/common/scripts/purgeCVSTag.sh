#!/bin/bash

# eg1., cvs -d nickb@dev.eclipse.org:/cvsroot/tools -q rtag -d build_200501150009 org.eclipse.emf
# eg2., cvs -d nickb@dev.eclipse.org:/cvsroot/tools -q rtag -d build_200501150100 org.eclipse.emf

buildIDs="";
num=0;
cnt=0;

# flag options
delete=0;
projUser="vramaswamy";
relengUser="nickb";

if [ $# -lt 1 ]; then
	echo " "
	echo "usage: purgeCVSTag.sh <options> <subproject> <buildID1> <buildID2> <buildID3> ... "
	echo "examples:"
	echo " purge tag, no delete build:     purgeCVSTag.sh query 200501150009 200501150100"
	echo " purge tag, delete build folder: purgeCVSTag.sh -delete ocl 200501150009 200501150100"
	echo " purge tag, non-standard user:  purgeCVSTag.sh -relengUser $USER -delete ocl 200501150009"
	echo " purge tag, non-standard user:  purgeCVSTag.sh -projUser $USER -delete eodm 200602131652"
	exit 1;
fi

next=$1; rem=${next##-*};
while [ "x$rem" == "x" ]; do # found -, so it's a flag
	if [ "$next" = "-delete" ]; then
		#echo "$1";
		delete=1; shift 1;
	elif [ "$next" = "-projUser" ]; then
		#echo "$1 $2 ";
		projUser=$2; shift 2;
	elif [ "$next" = "-relengUser" ]; then
		#echo "$1 $2 ";
		relengUser=$2; shift 2;
	fi
	next=$1; rem=${next##-*};
done
	
# must be first param
subproj=$1; shift 1;

# Create array of values
while [ "$#" -gt 0 ]; do
	buildIDs=$buildIDs" "$1;
	(( num++ ));
	shift 1;
done	


if [ "x$buildIDs" != "x" ]; then

	echo "[purge] Started `date +%Y%m%d\ %H\:%M\:%S`.";	
	echo "[purge] Purging the following $num CVS Tags:";
	echo "       $buildIDs";
	for buildID in $buildIDs; do
		(( cnt++ ));
		echo "";
		echo "[purge] [$cnt/$num] Purge build_$buildID ...";
		for project in "org.eclipse.emft"; do
			cmd="cvs -d "$projUser"@dev.eclipse.org:/cvsroot/technology -q rtag -d build_$buildID $project/$subproj";
			echo $cmd
			$cmd;
			cmd="cvs -d "$relengUser"@dev.eclipse.org:/cvsroot/technology -q rtag -d build_$buildID $project/releng";
			echo $cmd
			$cmd;
		done
		if [ $delete -eq 1 ]; then
			for d in `find	/home/www-data/build/emft/$subproj/ -name "*$buildID" -type d`; do
				echo "[purge] Delete $d ..."
				sudo -u www-data rm -fr $d;
			done
		fi
	done

	echo "";
	echo "[purge] Finished `date +%Y%m%d\ %H\:%M\:%S`.";

fi
