cd /D %~dp0
 
title Start Windows Desktop Node
set CHROME_DRIVER=%cd%/../drivers/windows/chromedriver.exe
java -Dwebdriver.chrome.driver=%CHROME_DRIVER% -jar selenium-server-standalone-3.14.0.jar -role node -nodeConfig node_config_win.json