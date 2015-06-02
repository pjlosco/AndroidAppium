package com.project.appium;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class CapabilityManager {
    public static final String iPhone = "iPhone Simulator";
    public static final String iPad = "iPad Simulator";
    public static final String iOS = "iOS";
    public static final String Android = "Android";

    private DesiredCapabilities capabilities;
    private static String packageName;

    protected CapabilityManager(TestSetup testSetup) {
        this.capabilities = new DesiredCapabilities();
        // capabilities.setCapability("newCommandTimeout", 100000); // Uncomment this to keep appium running for debugging
        // capabilities.setCapability("launchTimeout", 60000);
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(CapabilityType.PLATFORM, "Mac");
        packageName = testSetup.getClass().getPackage().toString();
    }

    public void setAndroidCapabilities() {
        capabilities.setCapability("appPackage", "com."+ TestSetup.appDomainName +".android");
        capabilities.setCapability("appActivity", ".MainActivity");
        // capabilities.setCapability("appWaitActivity", ".HomeActivity, .welcome.WelcomeActivity");
    }

    public DesiredCapabilities getCapabilities() {
        return capabilities;
    }

    public void setVersionCapability() {
        String version = System.getProperty("version");
        if (version == null || version.isEmpty()) {
            if (packageName.contains("ios")) {
                version = "1.0";
            } else { // android
                version = "1.0";
            }
        }
        capabilities.setCapability("platformVersion", version);
    }

    // (either "iOS" or "Android")
    public void setPlatformName(String platform) {
        capabilities.setCapability("platformName", platform);
    }
    public String getPlatformName() {
        return capabilities.getCapability("platformName").toString();
    }

    // (the kind of device you want, like "iPhone Simulator")
    public void setDeviceCapability(){
        String device = System.getProperty("deviceName");
        if (device == null || device.isEmpty()) {
            if (packageName.contains("iPad")) {
                device = iPad;
            } else if (packageName.contains("ios")) {
                device = iPhone;
            } else {
                device = Android;
            }
        }
        if (device.equalsIgnoreCase(iPad) || device.contains("iPad")) {
            capabilities.setCapability("deviceOrientation","landscape"); // more stuff displayed sometimes, easier to work with
        }
        capabilities.setCapability("deviceName", device);
    }
    public String getDeviceCapability() {
        return capabilities.getCapability("deviceName").toString();
    }

    public void setAppCapability() {
        String appName = System.getProperty("appName");
        File appDir;
        if (capabilities.getCapability("platformName").equals(iOS)){
            appDir = new File(System.getProperty("user.dir"), "./app/");
            if (appName == null || appName.isEmpty()) {
                appName = TestSetup.appName + ".app";
            }
        } else {
            appDir = new File(System.getProperty("user.dir"), "./apk/");
            if (appName == null || appName.isEmpty()) {
                appName = TestSetup.appName + ".apk";
            }
        }
        File app = new File(appDir, appName);
        capabilities.setCapability("app", app.getAbsolutePath());
    }
    public String getAppCapability() {
        return capabilities.getCapability("app").toString();
    }

    public void useSelendroid(boolean answer) {
        if (answer) {
            capabilities.setCapability("automationName", "Selendroid");
        }
    }

}
