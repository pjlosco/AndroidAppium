package com.project.appium;

import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class TestSetup {

    public static AppiumDriver driver;
    public static WebDriverWait wait;
    private boolean isProd = true;
    public static CapabilityManager capabilityManager;
    public static String currentTestName;

    public static final String appDomainName = "project";
    public static final String appName = "Project";

    @Rule
    public TestName testName = new TestName();
    @Rule
    public TestRule timeout = new Timeout(300000); // 5 minute max timeout

    @Before
    public void setUp() throws Exception {
        ProcessKiller.closeSimulatorAndInstruments();
        currentTestName = testName.getMethodName();
        capabilityManager = new CapabilityManager(this);

        if (this.getClass().getPackage().toString().contains("ios")) {
            capabilityManager.setPlatformName(CapabilityManager.iOS);
        } else if (this.getClass().getPackage().toString().contains("android")) {
            capabilityManager.setPlatformName(CapabilityManager.Android);
            capabilityManager.setAndroidCapabilities();
        } else {
            throw new Exception("App type not valid. ERROR");
        }

        capabilityManager.setDeviceCapability();
        capabilityManager.setAppCapability();
        capabilityManager.setVersionCapability();
        setIsProd();

        System.out.println("Running " + currentTestName + " on " + capabilityManager.getAppCapability());
        driver = new AppiumDriver(getAppiumURL(), capabilityManager.getCapabilities());
    }

    @After
    public void tearDown() throws Exception {
        // new ScreenshotCapturer(driver).captureScreenshot();
        try {
            if (capabilityManager.getPlatformName().equals(CapabilityManager.Android)){
                driver.sendKeyEvent(3);
                Thread.sleep(200);
                driver.navigate().back(); // sometimes the keyboard stays open
                driver.navigate().back();
                driver.quit();
            }
        } catch (Exception e) {
        } finally {
            try {
                driver.quit();
            } catch (Exception e) {
            }
            System.out.println("Finished running test: " + currentTestName);
        }
    }

    public boolean isProdEnvironment() {
        return isProd;
    }
    private void setIsProd() {
        if (capabilityManager.getCapabilities().getCapability("app").toString().contains("Staging")) {
            this.isProd = false;
        }
    }


    /**
     * Appium can be running on any port, and might be on multiple based on tests running in parallel
     * This checks to get the current session and designates it to a default if not provided
     * @return appiumURL
     * @throws MalformedURLException
     */
    private URL getAppiumURL() throws MalformedURLException {
        String url = System.getProperty("appiumUrl");
        if (url == null || url.isEmpty()) {
            url = "http://0.0.0.0:4723/wd/hub";
        }
        return new URL(url);
    }

}
