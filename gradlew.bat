@echo off
REM Minimal gradlew.bat that calls local gradle if available
where gradle >nul 2>nul
if %errorlevel%==0 (
  gradle %*
  exit /b %errorlevel%
)
echo Gradle not found. Install Gradle or run 'gradle wrapper' locally to create the wrapper.
exit /b 1
