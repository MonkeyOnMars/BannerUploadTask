
echo "start"

echo "check JAVA_HOME..."

echo "java home = $JAVA_HOME"

if [ x$JAVA_HOME != x ]
then
echo "$JAVA_HOME is not empty: ${JAVA_HOME}"
else
echo "JAVA_HOME is empty"
exit
fi

echo "calling java..."

$JAVA_HOME/bin/java -Dfile.encoding=UTF-8 -jar dist/BannerUploadTask.jar

