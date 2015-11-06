Echo Off

set "JAVA_HOME=C:\Program Files\Java\jdk1.8.0_11"
echo "%JAVA_HOME%"
set "ANT_HOME=C:\Users\root\AppData\Roaming\NetBeans\8.0\ant"
echo "%ANT_HOME%"

set s="JAVA_HOME"
IF ["%JAVA_HOME%"] == [] GOTO HomeNE
set s="ANT_HOME"
IF ["%ANT_HOME%"]  == [] GOTO HomeNE

echo "JAVA_HOME and ANT_HOME found."


call "%ANT_HOME%\bin\ant"

exit /B

:HomeNE
ECHO set "%s%" first. Bye!




