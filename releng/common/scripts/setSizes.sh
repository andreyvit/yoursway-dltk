#!/bin/sh

# this file is only referenced by old versions of buildAll.xml; newer versions no longer need this

packageDir=$1
if [ ! -d $packageDir ]; then
        echo "The package directory ($packageDir) is not valid."
        exit
fi

fileToChange=$2
if [ ! -f $fileToChange ]; then
        echo "There is no file to change."
        exit
fi

text=`cat $fileToChange`

zipFiles=`find $packageDir  -name *.zip -printf '%f\n' | sort`
for zipFile in $zipFiles; do
        size=`ls -sh $packageDir/$zipFile | sed 's/^ *\(.*\) .*/\1/'`
        token="$zipFile.size"
        token=`echo $token | sed -e 's/\//\\\\\\//g' | sed -e 's/\./\\\\\./g'`
        text=`echo "$text" | sed -e "s/\@$token\@/${size}/g"`
done

echo "$text" > $fileToChange