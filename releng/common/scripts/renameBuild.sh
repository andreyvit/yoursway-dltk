#!/bin/bash
# see /home/www-data/emf-build/scripts/renameBuild.sh
while [ $# -gt 0 ]; do 
    case $1 in
    	*) PARAM=$PARAM" "$1;;
    esac
    shift 1;
done
/home/www-data/emf-build/scripts/renameBuild.sh $PARAM;
