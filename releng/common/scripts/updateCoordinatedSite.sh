#!/bin/bash
# $Id: updateCoordinatedSite.sh,v 1.3 2007/02/22 08:39:51 asobolev Exp $

###### ###### ###### ###### ###### ###### INSTRUCTIONS & OPTIONS ###### ###### ###### ###### ###### ###### 

norm="\033[0;39m";
grey="\033[1;30m";
green="\033[1;32m";
brown="\033[0;33m";
yellow="\033[1;33m";
blue="\033[1;34m";
cyan="\033[1;36m";
red="\033[1;31m";

function basichelp ()
{
	echo \$Id: updateCoordinatedSite.sh,v 1.3 2007/02/22 08:39:51 asobolev Exp $
	echo "";
	echo -e "You must specify the properties file used by this script BEFORE any overrides, such as $red-branch$norm or $red-buildID$norm, on the commandline."
	echo "";
	echo -e "usage: $blue$0$norm";
	echo -e "$red-sub$norm          <REQUIRED: MUST BE FIRST FLAG; if ./promoteToEclipse.${red}sub$norm.properties not found, try ./promoteToEclipse.properties>"
	echo -e "$red-branch$norm       <REQUIRED: release version of the files to be promoted, eg., 1.0.0, 1.0.1 (overrides property file)>"
	echo -e "$red-buildID$norm      <REQUIRED: ID of the build>"
	echo -e "$yellow-q$norm, $yellow-Q$norm                   <OPTIONAL: scp, unzip, cvs checkout: quiet or VERY QUIET output>"
}

if [ $# -lt 1 ]; then
	basichelp;
	exit 1;
fi

###### ###### ###### ###### ###### ###### BEGIN SETUP ###### ###### ###### ###### ###### ###### 

# default to default properties file
defaultPropertiesFile=./promoteToEclipse.properties
propertiesFiles="";

# Create local variable based on the input
echo " "
echo "[coordsite] Started `date +%H:%M:%S`. Executing with the following options:"
while [ "$#" -gt 0 ]; do
	case $1 in
		'-f')    propertiesFile=$2; 								echo "   $1 [using $propertiesFile]"; if [ -r $propertiesFile ]; then source $propertiesFile; else echo "[promote] Properties file $propertiesFile not found. Exiting..."; exit 99; fi; shift 1;;
		'-sub')  subprojectName=$2; 								echo "   $1 $2";
			# chain them together in order of priority: subproj specific one, default
			propertiesFiles=$propertiesFiles" ./promoteToEclipse."$subprojectName".properties "$defaultPropertiesFile; 
			loaded=0; for propertiesFile in $propertiesFiles; do if [ "$loaded" -eq 0 ] && [ -r $propertiesFile ]; then echo -n "    [loading $propertiesFile ... "; . $propertiesFile; echo "done]"; loaded=1; fi; done
			if [ "$loaded" -eq 0 ]; then echo "    [Can't load any of: $propertiesFiles. Exiting!]";exit 99; fi
			shift 1;;

	# build details

		'-branch')   branch=$2; echo "   $1 $2"; shift 1;;
		'-buildID') buildID=$2; echo "   $1 $2"; shift 1;;

	# debug options
	
		'-q') quietCVS=-q; quiet=-q;  echo "   $1"; shift 0;;
		'-Q') quietCVS=-Q; quiet=-q;  echo "   $1"; shift 0;;

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

# create default temp folder
mkdir -p /home/$user/tmp;

###### ###### ###### ###### ###### VARIABLES DERIVED FROM $user ###### ###### ###### ###### ###### 

# temp folder base (make 'em unique so concurrent builds don't overlap)
tempfold=/home/$user/tmp/promoteToEclipse-$projectName-$subprojectName-$user-`date +%Y%m%d_%H%M%S`

#cvs paths
coordsiteCVSRep=:ext:$eclipseCVSUser:/cvsroot/callisto

if [[ ! $coordsiteName ]];   then coordsiteName="europa"; fi # set default if missing
if [[ ! $coordsiteBranch ]]; then coordsiteBranch="HEAD"; fi # set default if missing

# setup
tmpfolder=$tempfold/coordsite; mkdir -p $tmpfolder; cd $tempfold;
qualifier=".v"${buildID:1};
buildType=${buildID:0:1};
if [ $buildType = "R" ]; then sitexml="site.xml"; else sitexml="site-interim.xml"; fi

featuresXML="features-$subprojectName.xml";

echo "[coordsite] [`date +%H:%M:%S`] Update Coordinated Update Site ($sitexml):";
# checkout
if [ "$coordsiteBranch" != "HEAD" ]; then coordsiteBranch="-r "$coordsiteBranch; else coordsiteBranch=""; fi
cvscmd="cvs -d $coordsiteCVSRep $quietCVS co $coordsiteBranch -d coordsite org.eclipse."$coordsiteName".tools/build-home";
if [[ $debug -gt 0 ]]; then echo "[coordsite] [`date +%H:%M:%S`] "$cvscmd; fi
$cvscmd;

# get actual feature versions for the following defined list: org.eclipse.*sub_x.y.z.vqualifier.jar
cd $localWebDir/updates/features; features="`find . -regex \".*/org\.eclipse\.\(emf\.$subprojectName\|$subprojectName\)_.*$qualifier\.jar\"`";

cd $tmpfolder;
if [[ ! -f $featuresXML ]]; then touch $featuresXML; cvs add $featuresXML; fi
echo -n '' > $featuresXML
echo '<?xml version="1.0" encoding="UTF-8"?>
<project name="update" default="update">
    <target name="update">

      <property name="updateSite"' >> $featuresXML
if [[ $subprojectName = "ocl" ]] || [[ $subprojectName = "uml2-ocl" ]]; then
	echo '                value="file://${user.home}/downloads/modeling/mdt/updates/'$sitexml'" />' >> $featuresXML
else
	echo '                value="file://${user.home}/downloads/technology/emft/updates/'$sitexml'" />' >> $featuresXML
fi
echo '
    <echo message="   pulling update jars from ${updateSite}" />' >> $featuresXML
for feat in $features; do
	f=${feat:2}; # trim first two chars
	featureId=${f%_*}; # trim from _ to end
	featureVersion=${f#*_}; featureVersion=${featureVersion%.v*}; # trim from start to _, then from .v to end
	echo "            "$featureId" "$featureVersion""$qualifier;
echo '
        <ant antfile="updateMirrorProject.xml">
            <property name="featureId"
                      value="'$featureId'" />
            <property name="version"
                      value="'$featureVersion''$qualifier'" />
        </ant>' >> $featuresXML;
done
echo '
    </target>
</project>
' >> $featuresXML;
#cat $featuresXML;

# commit changes
cvs ci -m "promoteToEclipse: $sitexml, $branch$qualifier" $featuresXML; 

echo "[coordsite] [`date +%H:%M:%S`] done.";
