#!/bin/bash
SOURCE_PATH=$1
cd $SOURCE_PATH
for file in $SOURCE_PATH/*
do
  cp $file $file'_'
  sed 's/:pserver:anonymous@dev.eclipse.org/:ext:kurtov@turbine.xored.local/g' $file'_' > $file
  rm $file'_'
done
