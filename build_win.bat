echo %JAVA_HOME%
echo %PATH%

PATH=C:\Program Files\Java\jdk1.8.0_11\bin;%PATH%
export PATH

old_jh=%JAVA_HOME%

JAVA_HOME=C:\Program Files\Java\jdk1.8.0_11
export JAVA_HOME

ant

echo "built ok"



JAVA_HOME=old_jh
export JAVA_HOME

