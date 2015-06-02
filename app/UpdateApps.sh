#!/bin/bash
if [ "$1" != "" ]; then
    APPDIRECTORY=$1
    DEST=$1
else
    read -p "Do you have the iOS-app repo (y/n)? " CONT
    if [ "$CONT" == "y" ]; then
        echo "Enter the location of the iOS-app directory (not including the ios-app folder and without a / on the end)."
        echo "Example:"
        echo "If your iOS-app directory is /Users/Bob/ProjectApp/ios-app, enter in /Users/Bob/ProjectApp"
    	read -p ">>> " APPDIRECTORY
        echo "Enter the location of the automation directory (not including the automation folder and without a / on the end)."
        echo "Example:"
        echo "If your automation directory is /Users/Bob/ProjectApp/automation/GroupMeiPhone/app, enter in /Users/Bob/ProjectApp"
    	read -p ">>> " DEST
    fi
fi

if [ "$APPDIRECTORY" != "" ]; then
    echo "Pulling latest updates"
    cwd=$(pwd)
    echo "Current working directory is " $cwd
    cd $APPDIRECTORY/ios-app 
    git pull --rebase
    cd $cwd
	echo "Building Production version of app"
	xcodebuild -sdk iphonesimulator -workspace $APPDIRECTORY/ios-app/ProjectApp-iOS.xcworkspace -scheme Prod -configuration Debug CONFIGURATION_BUILD_DIR=$DEST/automation/AppiumAutomator/app RUN_APPLICATION_TESTS_WITH_IOS_SIM=YES ONLY_ACTIVE_ARCH=NO clean build 2>&1
	xcodebuild -sdk iphonesimulator -workspace $APPDIRECTORY/ios-app/ProjectApp-iOS.xcworkspace -scheme Staging -configuration Debug CONFIGURATION_BUILD_DIR=$DEST/automation/AppiumAutomator/app RUN_APPLICATION_TESTS_WITH_IOS_SIM=YES ONLY_ACTIVE_ARCH=NO clean build 2>&1
fi