
declaredStream=$1
declaredDir=$2

FROMDIR=$HOME/downloads/webtools/committers/drops
FROMDIR=$FROMDIR/${declaredStream}/${declaredDir}/updateSite

TODIR=$HOME/downloads/webtools/declaredUpdates

echo  "declaring update ${declaredDir} on buildstream  ${declaredStream}"
echo  "   into ${TODIR}"
echo  "   using the build from ${FROMDIR}"


# update the update site with changes only
rsync --ignore-existing -rv $FROMDIR/features $TODIR
rsync --ignore-existing -rv $FROMDIR/plugins $TODIR
