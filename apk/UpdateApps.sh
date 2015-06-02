#!/bin/bash
if [ "$1" != "" ]; then
    APKDIRECTORY=$1
    DEST=$1
else
    read -p "Do you have the Android repo (y/n)? " CONT
  if [ "$CONT" == "y" ]; then
    echo "Enter the location of the android directory (not including the android folder and without a / on the end)."
    echo "Example:"
    echo "If your android directory is /Users/Bob/projectname/android, enter in /Users/Bob/projectname"
    read -p ">>> " APKDIRECTORY
    echo "Enter the location of the automation directory (not including the automation folder and without a / on the end)."
    echo "Example:"
    echo "If your automation directory is /Users/Bob/projectname/automation/Android/apk, enter in /Users/Bob/projectname"
    read -p ">>> " DEST
  fi
fi

if [ "$APKDIRECTORY" != "" ]; then
  echo "Pulling latest updates"
  cwd=$(pwd)
  echo "Current working directory is " $cwd
  cd $APKDIRECTORY/android
  git pull --rebase

	echo "Building Production version of app"
  FILE=gradle.properties

  if [ -f $FILE ]; then
       echo "File $FILE exists."
  else
       echo "File $FILE does not exist. Creating one"
       touch gradle.properties
       echo "# App keystore password." >> gradle.properties
       echo "releasePassword [RELEASE_PASSWORD]" >> gradle.properties
       echo "debugPassword [DEBUG_PASSWORD]" >> gradle.properties
  fi

  ./gradlew assembleProductionDebug

  cp -rf $APKDIRECTORY/android/GroupMe/build/apk/ProjectApp.apk $DEST/automation/AppiumAutomator/apk

  cd $cwd
fi

 adb uninstall com.project.android
