@echo off
setlocal
set PROJECT_HOME=%~dp0
set BUILD_HOME=/home/shared/webtools
call %ANT_HOME%\bin\ant.bat %*
endlocal
