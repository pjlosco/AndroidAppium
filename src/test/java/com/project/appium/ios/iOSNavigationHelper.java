package com.project.appium.ios;

import com.project.appium.SeleniumHelper;
import com.project.appium.User;
import com.project.appium.ios.iPad.locators.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;

import java.util.HashMap;

public class iOSNavigationHelper extends SeleniumHelper {

    /**
     * Dismisses a keyboard by tapping key with the provided name. Done, Search, etc...
     * @param keyText
     */
    public void dismissKeyboard(String keyText) {
        JavascriptExecutor js = driver;
        HashMap<String, String> keyObject = new HashMap<String, String>();
        keyObject.put("keyName", keyText);
        js.executeScript("mobile: hideKeyboard", keyObject);
    }

    public void logoutFromAnywhere() {
        // TODO
    }

    public void login(User user) {
        String loginUserFullName = user.getFullName();
        String loginUserEmail = user.getEmail();
        String loginUserPassword = user.getPassword();

        getWebElement(LoginLocators.emailTextInput).sendKeys(loginUserEmail);
        click(LoginLocators.continueButton);
        waitForVisible(LoginLocators.passwordInput);
        getWebElement(LoginLocators.passwordInput).sendKeys(loginUserPassword);
        click(LoginLocators.loginButton);
        // Skip the new feature walk through
        tapAlertOkButton();
        closeTourIfNeeded();
    }

    public void login(String email, String password) {
        if (!isElementVisible(LoginLocators.emailTextInput) || !getWebElement(LoginLocators.emailTextInput).getAttribute("value").contains("Enter your email address")) {
            logoutFromAnywhere();
        }
        // Input valid email and password
        getWebElement(LoginLocators.emailTextInput).sendKeys(email);
        click(LoginLocators.continueButton);
        getWebElement(LoginLocators.passwordInput).sendKeys(password);
        click(LoginLocators.loginButton);
        // Skip the new feature walk through
        closeTourIfNeeded();
    }

    public void closeTourIfNeeded() {
        // TODO
    }

    public void tapAlertOkButton() {
        sleep(1);
        boolean acceptedAlert = false;
        try {
            for (int tried = 0; tried < 2; tried++) {
                Alert alert = driver.switchTo().alert();
                if (alert !=null) {
                    alert.accept();
                    acceptedAlert = true;
                }
                driver.switchTo().defaultContent();

                if (!acceptedAlert) {
                    sleep(1);
                } else {
                    Thread.sleep(200);
                    break;
                }
            }
        } catch (Exception e){
            // fail ?
        }
    }

    public String getCurrentTitle() {
        String currentTitle = getWebElement(GlobalLocators.CurrentTitle).getText();
        int count = 0;
        while (count < 5 && !currentTitle.contains("Loading")) {
            sleep(0.5);
            currentTitle = getWebElement(GlobalLocators.CurrentTitle).getText();
            count ++;
        }
        return currentTitle;
    }
}
