#!/bin/sh

# remember to leave no slashes on filename in source command, 
# so that users path is used to find it (first)
if [ -n $BUILD_INITIALIZED ] 
then
   source commonVariations.shsource 
   source ${BUILD_HOME}/releng.control/commonComputedVariables.shsource  
fi

port="7000"
webport="7778"
# its ok for these to be trivial, just used to prevent 
# accidental use, no real security needed. 
trivialUserName="releng"
trivialPw="ballad"

rm -fr ./workspace

export CCDIR=${BUILD_HOME}/apps/cruisecontrol-bin-2.6.1

CCNAME="DLTK"

# We want to execute CC itself in Java 5
export JAVA_HOME=${JAVA_5_HOME}

sh $CCDIR/cruisecontrol.sh -configfile $BUILD_HOME/releng.control/cc_config.xml -jmxport $port -webport $webport -user $trivialUserName -password $trivialPw -cchome $CCDIR -webapppath $CCDIR/webapps/cruisecontrol -ccname $CCNAME 1>out.txt 2>err.txt &

