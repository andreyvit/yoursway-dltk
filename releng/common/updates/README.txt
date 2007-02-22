NOTE:

Creating a set of UM jars for features/plugins + the site.xml 
(or site-interim.xml) is done via a shell script:
/cvsroot/org.eclipse.emft/releng/common/scripts/buildUpdate.sh.

Note also that promoteToEclipse.properties (same folder) is used 
to define global properties used by the above script. This file 
is shared with promoteToEclipse.sh as it includes an automated 
step for initiating buildUpdate.sh during a build promotion.

For web content, see /cvsroot/org.eclipse/www/emft/updates/.

