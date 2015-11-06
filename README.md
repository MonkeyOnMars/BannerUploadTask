# BannerUploadTask
RV banner upload task via java.

TODO: Need to look into shceduling java functionality. 
Meanwhile threads are used. 

INSTALL AND RUN
------------------
WINDOWS:

Build code with "build_win.bat". 
JAVA_HOME must be set. 

Added ANT into project directory for builds. external/apache-ant-1.9.6  

When code is built, run jar via  calling "run_win.bat"


UNX:

Build code with buildx.sh. JAVA_HOME must be set. 
Run code with runx.sh.


Description:
-------------------
Application reads values from http://www.pukukurjers.lv/veikals.xml every 15 min.
Every 1 min one banner from that list is written to banner.html as DIV
bannerConsumer.html injects banner.html onload.

You can change intervals in cfg/config.properties 


Refresh bannerConsumer.html to see results.

