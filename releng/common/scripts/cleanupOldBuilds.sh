#!/bin/bash

mkdir -p ~/tmp;

#Min    Hr      Mday    Month   Wday    Cmd
#1       1       *       *       *       sudo -u www-data /home/www-data/build/emft/scripts/cleanupOldBuilds.sh > $HOME/cron_logs/cleanupOldBuilds.sh.emft.log.txt

# cleanup script (see details below)

debug=1; # default 0; set to 1 for more output
run=1; # default 1; set to 0 to only show intent, but not actually run

dropsDir=/home/www-data/build/emft
dlsDir=/home/www-data/build/downloads

# set ages to use below

oldAge1=30 # num days old step 1., purge builds in OLD/ older than oldAge1 days
oldAge2=10 # num days old step 2., move [N] builds older than oldAge2 days from 2.0/ into OLD/ 
oldAge3=60 # num days old step 3., move [IMS] builds older than oldAge3 days from 2.0/ into OLD/ (keep R builds!)
oldAge4=20 # num days old step 4., purge old dependency downloads

step=0;

function doSizes () {
	for workingDir in "$dropsDir" "$dlsDir" ; do
		du -sh $workingDir | sed -e "s!^!\[clean\]   !g"
	done
}
###############################################################################
echo " "
echo "[clean] Started `date`. Initial dir sizes:"
doSizes
echo " "

###############################################################################

	#cmd="du -sh " # informative only [space used]
	#cmd="date -r " # informative [datestamp on folder]

###############################################################################


###############################################################################

for workingDir in "$dropsDir" ; do
	(( step++ ));
	echo "[clean] [$step] Purge $oldAge1 day old builds from $workingDir/OLD"

	cmd="rm -fr " 
	find $workingDir/OLD -maxdepth 3 -mtime +$oldAge1 -type d | sed -e "s!^!$cmd!g" > ~/tmp/cleanupOldBuilds.sh.tmp
	find $workingDir/OLD -maxdepth 2 -depth -empty -type d | sed -e "s!^!$cmd!g" >> ~/tmp/cleanupOldBuilds.sh.tmp
	if [ $debug -eq 1 ]; then
		cat ~/tmp/cleanupOldBuilds.sh.tmp; echo " "; 
	fi
	# run it
	if [ $run -eq 1 ]; then
		. ~/tmp/cleanupOldBuilds.sh.tmp
	fi

	###############################################################################

	branches=`find $workingDir -maxdepth 4 -name "?\.?" -o -name "?\.?\.?" -o -name "?\.?\.?\.?" | grep -v "OLD"`
	for branch in $branches; do
		(( step++ ));
		echo "[clean] [$step] Move $oldAge2 day old [N] builds from $branch"
		sub=${branch%%/downloads/drops/*};sub=${sub#*build/emft/}
		mkdir -p $workingDir/OLD/$sub/${branch##*/};
		cmd="mv " 
		cmd2=" $workingDir/OLD/$sub/"${branch##*/}" " 
		find $branch -maxdepth 1 -mtime +$oldAge2 -type d  | grep [N][0-9] | sed -e "s!^!$cmd!g" -e "s!\$!$cmd2!g" > ~/tmp/cleanupOldBuilds.sh.tmp
		if [ $debug -eq 1 ]; then
			cat ~/tmp/cleanupOldBuilds.sh.tmp; echo " "; 
		fi
		# run it
		if [ $run -eq 1 ]; then
			. ~/tmp/cleanupOldBuilds.sh.tmp
		fi

		###############################################################################

	done

	for branch in $branches; do
		(( step++ ));
		echo "[clean] [$step] Move $oldAge3 day old [IMS] builds from $branch"; # don't move R builds!
		sub=${branch%%/downloads/drops/*};sub=${sub#*build/emft/}
		mkdir -p $workingDir/OLD/$sub/${branch##*/};
		cmd="mv " 
		cmd2=" $workingDir/OLD/$sub/"${branch##*/}" " 
		find $branch -maxdepth 1 -mtime +$oldAge3 -type d | grep [IMS][0-9] | sed -e "s!^!$cmd!g" -e "s!\$!$cmd2!g" > ~/tmp/cleanupOldBuilds.sh.tmp
		if [ $debug -eq 1 ]; then
			cat ~/tmp/cleanupOldBuilds.sh.tmp; echo " "; 
		fi
		# run it
		if [ $run -eq 1 ]; then
			. ~/tmp/cleanupOldBuilds.sh.tmp
		fi

		###############################################################################

	done

done

(( step++ ));
echo "[clean] [$step] Purge $oldAge4 day old *SDK*.zip files from $dlsDir"

# test with
# find . -type f -maxdepth 1 -name \*SDK\*.zip -mtime +1 -exec date -r {} \;

cmd="rm -fr " 
find $dlsDir -type f -maxdepth 1 -name \*SDK\*\.zip -mtime +$oldAge4 | sed -e "s!^!$cmd!g" > ~/tmp/cleanupOldBuilds.sh.tmp
if [ $debug -eq 1 ]; then
	cat ~/tmp/cleanupOldBuilds.sh.tmp; echo " "; 
fi
# run it
if [ $run -eq 1 ]; then
	. ~/tmp/cleanupOldBuilds.sh.tmp
fi

###############################################################################

# clean up
rm -fr ~/tmp/cleanupOldBuilds.sh.tmp

echo " "
echo "[clean] Final dir sizes:"
doSizes
echo "[clean] Done `date`."
