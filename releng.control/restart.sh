#!/bin/sh

./stopBuilds.sh


# now, restart CC's
echo "restarting CruiseControl"
./startbuild.sh


# and list our processes for a warm fuzzy, we pause briefly, since in some
# error conditions, they will start, but then shutdown a few seconds later
sleep 10s
echo "current java processes"
ps x ww  | grep java


