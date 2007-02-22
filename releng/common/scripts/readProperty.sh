#!/bin/sh

file=$1
property=$2
grep $property $file | sed -e "s/$property=//"
