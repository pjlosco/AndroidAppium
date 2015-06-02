package com.project.appium.android;

import com.project.appium.LayoutLocator;
import com.project.appium.SeleniumHelper;
import com.project.appium.User;
import com.project.appium.android.locators.*;
import io.appium.java_client.AndroidKeyCode;
import org.junit.Assert;
import org.openqa.selenium.*;


public class AndroidNavigationHelper extends SeleniumHelper {

    /**
     * Waits for the title to change at least a couple seconds when asserting
     * @param expectedTitle
     * @return true or false depending on title has changed
     */
    public boolean assertTitleChange(String expectedTitle) {
        for (int tried = 0; tried < 4; tried++) {
            String actualTitle = getWebElement(NavigationBarLocators.ActionBarTitle).getText();
            if (expectedTitle.equals(actualTitle)) {
                return true;
            }
            sleep(0.5);
        }
        return false;
    }

    public void pressMenuButton() {
        try {
            waitForVisible(NavigationBarLocators.MenuButton);
            click(NavigationBarLocators.MenuButton);
        } catch (Exception e) {
            pressKey(AndroidKeyCode.MENU);
        }
    }

    public void pressDeleteKey() {
        pressKey(AndroidKeyCode.DEL);
    }

    public void pressDoneKey() {
        pressKey(AndroidKeyCode.ENTER);
    }

    public void pressAndroidHomeKey() {
        pressKey(AndroidKeyCode.HOME);
    }

    public void pressAndroidSettingsKey() {
        pressKey(AndroidKeyCode.SETTINGS);
    }

    private void pressKey(int keycode) {
        driver.sendKeyEvent(keycode);
    }

    public void sendKeys(LayoutLocator locator, String keys) {
        WebElement element = getWebElement(locator);
        click(element);
        element.sendKeys(keys);
        closeKeyboard();
    }

    public void closeKeyboard() {
        goBack();
        sleep(0.3); // wait for animation
    }
    public void clearTextField(LayoutLocator locator) {
        WebElement element = (getWebElement(locator));
        if (element.getText().isEmpty()) {
            return; // nothing to delete
        }
        waitForVisible(locator);
        for (int i = 0; i < 5; i++) { // sometimes the tap doesnt always work. try a few times before giving up
            tap(element, 300);
            pressDeleteKey();
            if (element.getText().isEmpty()) {
                break;
            }
        }
        Assert.assertTrue("Field was not cleared.", element.getText().isEmpty());
    }
    public void clickDeletePrompt() {
        // might have a prompt show up depending on OS version
        if (isElementVisible(By.name("Delete"))) {
            click(By.name("Delete"));
        }
    }

    public void goBack() {
        try {
            driver.navigate().back();
        } catch (Exception e) {
            pressKey(AndroidKeyCode.BACK);
        }
    }

    public void tapAlertOkButton() {
        boolean acceptedAlert = false;
        try {
            for (int tried = 0; tried < 2; tried++) {
                if (isElementVisible(GlobalLocators.AlertOK)) {
                    click(GlobalLocators.AlertOK);
                    acceptedAlert = true;
                } else {
                    Alert alert = driver.switchTo().alert();
                    if (alert !=null) {
                        alert.accept();
                        acceptedAlert = true;
                    }
                    driver.switchTo().defaultContent();
                }
                if (!acceptedAlert) {
                    sleep(1);
                } else {
                    Thread.sleep(200);
                    break;
                }
            }
        } catch (Exception e){
            // do nothing
        }
    }

    public void login(User user) {
        waitForVisible(LoginLocators.emailAddressTextField);
        click(LoginLocators.emailAddressTextField);
        sendKeys(LoginLocators.emailAddressTextField, user.getEmail());
        waitForVisible(LoginLocators.passwordTextField);
        click(LoginLocators.passwordTextField);
        sendKeys(LoginLocators.passwordTextField, user.getPassword());
        click(LoginLocators.signInButton);
        Assert.assertTrue("message", assertTitleChange("Welcome!"));
    }

    public void openAppSettings() {
        pressMenuButton();
        click(GlobalLocators.Settings);
    }
}
