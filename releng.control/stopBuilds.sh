#!/bin/sh
# remember to leave no slashes on filename in source command, 
# so that users path is used to find it (first)
if [ -n $BUILD_INITIALIZED ] 
then
   source commonVariations.shsource
   source computeCommon.shsource   
fi

# allow to terminate themselves, if they will
echo "Attempting normal terminate of all java processes"
killall ${JAVA_HOME}/jre/bin/java
#   pause briefly to allow shutdown to finish
sleep 10s

# but if the do not terminate themselves, we can safely force them down
echo "forcing termination of any remaing java processes"
killall -9 ${JAVA_HOME}/jre/bin/java/jre/bin/java
#    pause briefly to allow ports to free up, etc.
sleep 10s

