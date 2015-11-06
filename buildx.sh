#!/bin/sh

echo "start"

echo "check JAVA_HOME"

echo "java home = $JAVA_HOME"

CUR_DIR="$PWD"

chmod -R 777 $CUR_DIR
#set ant location from local external dir
ANT_LOCATION=$CUR_DIR/external/apache-ant-1.9.6

if [ x$JAVA_HOME != x ]
then
echo "$JAVA_HOME is not empty: ${JAVA_HOME}"
echo 'java -version'
else
echo "JAVA_HOME is empty"
fi

if [ x$ANT_LOCATION != x ]
then
echo "java home is not empty: ${ANT_LOCATION}"
else
echo "ANT_LOCATION is empty - must be set!"
exit
fi




echo "calling ant..."

$ANT_LOCATION/bin/ant -buildfile buildBanner.xml compile jar


echo "end"