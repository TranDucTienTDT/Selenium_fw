cd /D %~dp0

title Start Selenium Hub Server
java -jar selenium-server-standalone-3.14.0.jar -role hub -port 4445 -maxSession 20