#!/bin/sh

# remember to leave no slashes on commonVariations in source command, 
# so that users path is used to find it (first). But, path on 
# commonComputedVariables means we expect to execute only our 
# version

if [ -n $BUILD_INITIALIZED ] 
then
   source commonVariations.shsource 
   source ${BUILD_HOME}/releng.control/commonComputedVariables.shsource
fi

# For most ant tasks, we want Java 4 to be default, 
# so if not desired (such as for WTP 2.0 unit tests), 
# then we have to spec Java 5 right there where we run 
# the tests. 
export JAVA_HOME=${JAVA_4_HOME}


ANT_CMD=${ANT_HOME}/bin/ant

exec "$ANT_CMD" "$@"

