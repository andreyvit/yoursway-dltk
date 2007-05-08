#!/bin/sh

# This script file is to help get builds started "fresh", when 
# the releng.control directory already exists on local file system.
# While it is in the cvs repository in releng.control, it is 
# meant to be executed from the parent directory
# of releng.control on the file system. 

# export is used, instead of checkout, just to avoid the CVS directories and since this code
# for a local build, there should never be a need to check it back in to CVS.
# 

echo "	saving serialized state ..."
cp releng.control/*.ser .

echo "	removing all of releng.control ..."
rm -fr releng.control

echo "	checking out head of releng.control from cvs ..."
cvs -Q -d aplatov@dev.eclipse.org:/cvsroot/technology export -r HEAD -d releng.control org.eclipse.dltk/releng.control 

echo "	restoring serialized state ..."
mv *.ser releng.control

echo "	making sure releng.control files are executable and have proper EOL format ..."
dos2unix -quiet -keepdate releng.control/*.sh* releng.control/**/*.properties releng.control/*.xml > /dev/null 2>/dev/null
chmod +x releng.control/*.sh

