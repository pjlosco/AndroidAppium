package com.project.appium.ios.iPad.suites;

import com.project.appium.ios.iOSNavigationHelper;
import com.project.appium.ios.iPad.locators.GlobalLocators;
import com.project.appium.ios.iPad.locators.LoginLocators;
import org.junit.Test;

public class LoginTest extends iOSNavigationHelper {

    @Test
    public void LoginSuccessfully() {
        logoutFromAnywhere();
        waitForVisible(LoginLocators.emailTextInput);
        // Input valid email and password
        getWebElement(LoginLocators.emailTextInput).sendKeys("automation@groupmetest.com");
        click(LoginLocators.continueButton);
        getWebElement(LoginLocators.passwordInput).sendKeys("p@ssw0rd");
        click(GlobalLocators.HideKeyboard);
        click(LoginLocators.loginButton);
        closeTourIfNeeded();
    }

    @Test
    public void MissingEmail() {
        logoutFromAnywhere();
        // Input blank email
        getWebElement(LoginLocators.emailTextInput).sendKeys("");
        click(GlobalLocators.HideKeyboard);
        click(LoginLocators.continueButton);

        // Store the validation error text
        String validationText = getWebElement(LoginLocators.validationText).getText();
        String expectedText = "Please enter an email to continue.";

        // Compare to what we expect vs the actually text displayed
        assertText(expectedText, validationText);
    }

    @Test
    public void MalformatedEmail() {
        logoutFromAnywhere();
        // Input an invalid email
        getWebElement(LoginLocators.emailTextInput).sendKeys("sup~bro!");
        click(GlobalLocators.HideKeyboard);
        click(LoginLocators.continueButton);

        // Store the validation error text
        String validationText = getWebElement(LoginLocators.validationText).getText();
        String expectedText = "That does not look like a valid email address.";

        // Compare to what we expect vs the actually text displayed
        assertText(expectedText, validationText);
    }

    @Test
    public void MissingPassword() {
        logoutFromAnywhere();
        // Input valid email
        getWebElement(LoginLocators.emailTextInput).sendKeys("automation@groupmetest.com");
        click(GlobalLocators.HideKeyboard);
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
    public void IncorrectPassword() {
        logoutFromAnywhere();
        // Input valid email
        getWebElement(LoginLocators.emailTextInput).sendKeys("automation@groupmetest.com");
        click(LoginLocators.continueButton);

        // Input no password
        getWebElement(LoginLocators.passwordInput).sendKeys("doodoo");
        click(LoginLocators.loginButton);

        // Compare to what we expect vs the actually text displayed
        assertText("Incorrect password.", getWebElement(LoginLocators.incorrectPasswordValidationText).getText());
    }

    @Test
    public void ForgotPassword() {
        logoutFromAnywhere();
        // Input valid email
        getWebElement(LoginLocators.emailTextInput).sendKeys("automation@groupmetest.com");
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
