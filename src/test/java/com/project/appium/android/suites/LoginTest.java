package com.project.appium.android.suites;

import com.project.appium.User;
import com.project.appium.android.AndroidNavigationHelper;
import com.project.appium.android.locators.LoginLocators;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Example suite of tests
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTest extends AndroidNavigationHelper {

    User automationUser = new User("tester@email.com", "Firstname", "Lastname", "password", "android", "12345");

    @Test
    public void NoEmail(){
        waitForVisible(LoginLocators.emailAddressTextField);
        click(LoginLocators.emailAddressTextField);
        sendKeys(LoginLocators.emailAddressTextField, "");
        click(LoginLocators.signInButton);
        waitForVisible(LoginLocators.spinningIcon);
        Assert.assertTrue("message", !assertTitleChange("Welcome!"));
    }

    @Test
    public void BadEmail(){
        waitForVisible(LoginLocators.emailAddressTextField);
        click(LoginLocators.emailAddressTextField);
        sendKeys(LoginLocators.emailAddressTextField, "8782sugv");
        click(LoginLocators.signInButton);
        waitForVisible(LoginLocators.spinningIcon);
        Assert.assertTrue("message", !assertTitleChange("Welcome!"));
    }

    @Test
    public void NoPassword(){
        waitForVisible(LoginLocators.emailAddressTextField);
        click(LoginLocators.emailAddressTextField);
        sendKeys(LoginLocators.emailAddressTextField, automationUser.getEmail());
        waitForVisible(LoginLocators.passwordTextField);
        click(LoginLocators.signInButton);
        waitForVisible(LoginLocators.spinningIcon);
        Assert.assertTrue("message", !assertTitleChange("Welcome!"));
    }

    @Test
    public void IncorrectPassword(){
        waitForVisible(LoginLocators.emailAddressTextField);
        click(LoginLocators.emailAddressTextField);
        sendKeys(LoginLocators.emailAddressTextField, automationUser.getEmail());
        waitForVisible(LoginLocators.passwordTextField);
        sendKeys(LoginLocators.passwordTextField, "uvuhsb223333");
        click(LoginLocators.signInButton);
        waitForVisible(LoginLocators.spinningIcon);
        Assert.assertTrue("message", !assertTitleChange("Welcome!"));
    }

    @Test
    public void ForgotPassword(){
        waitForVisible(LoginLocators.emailAddressTextField);
        click(LoginLocators.emailAddressTextField);
        sendKeys(LoginLocators.emailAddressTextField, automationUser.getEmail());
        waitForVisible(LoginLocators.passwordTextField);

        click(LoginLocators.forgotPasswordLink);
        tapAlertOkButton();
        Assert.assertTrue("message", !assertTitleChange("Welcome!"));
    }

    @Test
    public void LoginSuccessfully(){
        waitForVisible(LoginLocators.emailAddressTextField);
        click(LoginLocators.emailAddressTextField);
        sendKeys(LoginLocators.emailAddressTextField, automationUser.getEmail());
        waitForVisible(LoginLocators.passwordTextField);
        click(LoginLocators.passwordTextField);
        sendKeys(LoginLocators.passwordTextField, automationUser.getPassword());
        click(LoginLocators.signInButton);
        Assert.assertTrue("message", assertTitleChange("Welcome!"));
    }
}
