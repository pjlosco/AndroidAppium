package com.project.appium;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;

import java.io.File;

public class ScreenshotCapturer implements MethodRule {

    AppiumDriver driver;
    public ScreenshotCapturer(AppiumDriver driver) {
        this.driver = driver;
    }

    public void captureScreenshot() {
        String imagesLocation = "target/surefire-reports/screenshot/" + TestSetup.capabilityManager.getPlatformName() + "/";
        new File(imagesLocation).mkdirs(); // Insure directory is there
        String filename = imagesLocation + TestSetup.currentTestName + ".jpg";

        try {
            Thread.sleep(500);
            File scrFile = driver.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(filename), true);
        } catch (Exception e) {
            System.err.println("Error capturing screen shot of " + TestSetup.currentTestName + " test failure. " + e.getMessage());
            // remove old pic to prevent wrong assumptions
            File f = new File(filename);
            f.delete(); // don't care if this doesn't succeed, but would like it to.
        }
    }

    public Statement apply(final Statement statement, FrameworkMethod frameworkMethod, Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
            try {
                statement.evaluate();
            } catch (Throwable t) {
                captureScreenshot();
                throw t;
            }
            }
        };
    }
}
