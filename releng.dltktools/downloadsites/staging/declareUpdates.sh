
declaredStream=$1
declaredDir=$2

FROMDIR=$HOME/downloads/technology/dltk/staging/drops
FROMDIR=$FROMDIR/${declaredStream}/${declaredDir}/updateSite

TODIR=$HOME/downloads/technology/dltk/updates/1.0

echo  "declaring update ${declaredDir} on buildstream  ${declaredStream}"
echo  "   into ${TODIR}"
echo  "   using the build from ${FROMDIR}"


# update the update site with changes only
rsync --ignore-existing -rv $FROMDIR/features $TODIR
rsync --ignore-existing -rv $FROMDIR/plugins $TODIR
