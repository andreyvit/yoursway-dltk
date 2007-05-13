#!/bin/sh
# script to delete warm-up builds, except the one promoted
# the promoted on is left just for safety


declaredStream=$1
declaredDir=$2
# make sure directories to delete start with same 3 chars as target
pattern=${declaredDir:0:5}


echo restrict to directory names that match $pattern
echo act on directories older than $declaredDir


# example of full form from command line
# find ./drops -maxdepth 1 -type d -name I-I* -not -cnewer drops/I-I200603180020-200603180020  -exec rm -fr {} \;


if [ "$3" != "-doit" ] ; then
   echo "    This is a dry run. Add -doit to actually remove"
   thisCommand=" echo " 
else 
   thisCommand=" rm -fr "
fi 



find ./drops -maxdepth 2 -type d -name "$pattern*" -not -newer "./drops/$declaredStream/$declaredDir" -exec $thisCommand  {} \; 




