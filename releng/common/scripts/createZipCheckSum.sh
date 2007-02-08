#!/bin/sh

files=`ls *.zip`
for file in $files; do
	md5sum -b $file > $file.md5
done

