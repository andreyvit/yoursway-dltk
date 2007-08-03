echo $1 $2

declaredStream=$1
declaredDir=$2

FROMDIR=../staging/drops
TODIR=../downloads/drops/${declaredStream}
FROMDIR=$FROMDIR/${declaredStream}/${declaredDir}
echo  "declaring build ${declaredDir} on buildstream  ${declaredStream}"
echo  "   into ${TODIR}"
echo  "   using the build from ${FROMDIR}"


cp -R ${FROMDIR} ${TODIR}

fromString="staging/drops/${declaredStream}/"
toString="downloads/drops/${declaredStream}/"
replaceCommand="s!${fromString}!${toString}!g"

perl -w -pi -e ${replaceCommand} ${TODIR}/${declaredDir}/*.php

#       update the update site
#cp -ruv $HOME/downloads/webtools/committers/drops/$1/updateSite/features/   $HOME/downloads/webtools/milestones/
#cp -ruv $HOME/downloads/webtools/committers/drops/$1/updateSite/plugins/   $HOME/downloads/webtools/milestones/
