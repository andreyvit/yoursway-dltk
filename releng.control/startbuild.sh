#!/bin/sh

# remember to leave no slashes on filename in source command, 
# so that users path is used to find it (first)
if [ -n $BUILD_INITIALIZED ] 
then
   source commonVariations.shsource
#   source computeCommon.shsource
fi

cd ${BUILD_HOME}/releng.control
sh ${BUILD_HOME}/releng.control/cc.sh



