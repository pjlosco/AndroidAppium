package com.project.appium.ios.iPhone.suites;

import com.project.appium.ios.iPhone.iPhoneNavigationHelper;
import com.project.appium.ios.iPhone.locators.LoginLocators;
import org.junit.Assert;
import org.junit.Test;

public class LoginTest extends iPhoneNavigationHelper {

    @Test
    public void loginSuccessfully() {
        waitForVisible(LoginLocators.emailTextInput);
        // Input valid email and password
        getWebElement(LoginLocators.emailTextInput).sendKeys("automation@email.com");
        click(LoginLocators.continueButton);
        getWebElement(LoginLocators.passwordInput).sendKeys("p@ssw0rd");
        click(LoginLocators.loginButton);
    }

    @Test
    public void missingEmail() {
        // Input blank email
        getWebElement(LoginLocators.emailTextInput).sendKeys("");
        click(LoginLocators.continueButton);

        // Store the validation error text
        String validationText = getWebElement(LoginLocators.validationText).getText();
        String expectedText = "Please enter an email to continue.";

        // Compare to what we expect vs the actually text displayed
        assertText(expectedText, validationText);
    }

    @Test
    public void malformatedEmail() {
        // Input an invalid email
        getWebElement(LoginLocators.emailTextInput).sendKeys("bad-email!");
        click(LoginLocators.continueButton);

        // Store the validation error text
        String validationText = getWebElement(LoginLocators.validationText).getText();
        String expectedText = "That does not look like a valid email address.";

        // Compare to what we expect vs the actually text displayed
        assertText(expectedText, validationText);
    }

    @Test
    public void missingPassword() {
        // Input valid email
        getWebElement(LoginLocators.emailTextInput).sendKeys("automation@test.com");
        click(LoginLocators.continueButton);

        // Input no password
        getWebElement(LoginLocators.passwordInput).sendKeys("");
        click(LoginLocators.loginButton);

        // Store the validation error text
        String validationText = getWebElement(LoginLocators.validationText).getText();
        String expectedText = "Please enter a password.";

        // Compare to what we expect vs the actually text displayed
        assertText(expectedText, validationText);
    }

    @Test
    public void incorrectPassword() {
        // Input valid email
        getWebElement(LoginLocators.emailTextInput).sendKeys("automation@email.com");
        click(LoginLocators.continueButton);

        // Input no password
        getWebElement(LoginLocators.passwordInput).sendKeys("doodoo");
        click(LoginLocators.loginButton);

        // Compare to what we expect vs the actually text displayed
        waitForVisible(LoginLocators.incorrectPasswordValidationText);
        assertText("Incorrect password.", getWebElement(LoginLocators.incorrectPasswordValidationText).getText());

        // Make sure we're still on the password screen on the login screen
        waitForNotVisible(LoginLocators.incorrectPasswordValidationText);
        Assert.assertTrue("No longer on the password screen.", isElementVisible(LoginLocators.loginButton));
    }

    @Test
    public void forgotPassword() {
        // Input valid email
        getWebElement(LoginLocators.emailTextInput).sendKeys("automation@email.com");
        click(LoginLocators.continueButton);

        // Click forgot password
        waitForVisible(LoginLocators.forgotPasswordButton);
        click(LoginLocators.forgotPasswordButton);

        // Store the validation error text
        String validationText = getWebElement(LoginLocators.validationText).getText();
        for (int tried = 0;  validationText.equalsIgnoreCase("Working") && tried < 2; tried++) {
            sleep(1);
            validationText = getWebElement(LoginLocators.validationText).getText();
        }
        String expectedText = "Please check your email. We've sent instructions to reset your password.";

        // Compare to what we expect vs the actually text displayed
        assertText(expectedText, validationText);
    }

}
