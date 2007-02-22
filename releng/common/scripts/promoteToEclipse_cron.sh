#!/bin/bash

#required
proj=""
branch=""
type="";

#optional or just passing thru
args="";

if [ $# -eq 0 ]; then
  echo "[promo_cron] This is a wrapper for promoteToEclipse.sh to be run in a crontab.";
  echo "[promo_cron] BuildID will be calculated as the most recent folder in the drops dir which"
  echo "[promo_cron] starts with the appropriate prefix letter (N, I or M)."
  echo "";
  echo "[promo_cron] Must specify user, project, branch and type, and OPTIONALLY, feedURL & feedFile, eg:";
  echo "";
  echo "[promo_cron]   ./promoteToEclipse_cron.sh -user $USER -proj emf -branch 2.3.0 -type I";
  echo "[promo_cron]         - or - ";
  echo "[promo_cron]   ./promoteToEclipse_cron.sh -user $USER -proj emf -branch 2.3.0 -type N";
  echo "[promo_cron]     -feedURL http://download.eclipse.org/tools/emf/feeds/builds-emf-N.xml";
  echo "[promo_cron]     -feedFile /var/www/emf/feeds/builds-emf-N.xml";
  echo "";
  echo "[promo_cron] Note: if type = I or M, build will be promoted along with feed."
  echo "[promo_cron]       if type = N, only the feed will be promoted (-rssonly).";
  exit 1;
fi

# Create local variable based on the input
echo " "
echo "[promo_cron] Started `date`. Executing with the following options:"
while [ "$#" -gt 0 ]; do
	echo "   $1 $2";
	case $1 in
		'-proj') proj=$2;;
		'-branch') branch=$2;;
		'-type') type=$2;;
		*) args=$args" $1 $2";;
	esac
	shift 2;
done

dropsDir=/home/www-data/build/emft/$proj/downloads/drops/$branch
scriptsDir=/home/www-data/build/emft/scripts/;

if [ -d $scriptsDir ]; then cd $scriptsDir; else echo "Error - can't enter $scriptsDir!"; exit 1; fi
if [ ! -d $dropsDir ]; then echo "Invalid project or branch - can't enter $dropsDir!"; exit 1; fi

buildID=`cd $dropsDir && find . -maxdepth 1 -type d -name "$type*" -exec date -r {} +%s\ {} \; | sort | tail -1 | sed -e "s/[0-9]\+\ \.\///g"`;

# last N build - promote feed only 
if [ "$type" = "N" ]; then
	echo "[promo_cron] Promoting RSS, buildID = $buildID ...";
	./promoteToEclipse.sh -sub $proj -Q -rssonly -branch $branch -buildID $buildID $args 
# last I or M build - promote BOTH feed + build
elif [ "$type" = "I" -o "$type" = "M" -o "$type" = "S" ]; then
	echo "[promo_cron] Promoting buildID = $buildID ...";
	./promoteToEclipse.sh -SUB $proj -Q -branch $branch -buildID $buildID $args 
else 
	echo "[promo_cron] Release Builds should not be promoted automatically by crontab!"; exit 1;
fi