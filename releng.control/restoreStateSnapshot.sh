#!/bin/sh

# This file is to "copy up" the state saved in cvs, 
# in stateSnapshot directory. Since the snapshot is 
# only taken occasionally, the best procedure is to 
# save the state locally and restore each time releng.control 
# is deleted and restored ... but, in case that is not 
# possible, this this the second best alternative. 
# If the state files do not exists at all, cruise control 
# will lose track of their status and state and attempt to 
# build every project. 

mv stateSnapshot/*.ser . 

