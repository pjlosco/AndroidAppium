package com.project.appium.ios.iPad.locators;

import com.project.appium.LayoutLocator;

public class LoginLocators {

    /** Login **/
    public static final LayoutLocator emailTextInput = new LayoutLocator("emailTextInput", "//window[1]/textfield[1]", "Enter your email address…");
    public static final LayoutLocator cancelButton = new LayoutLocator("cancelButton", "//window[1]/button[2]", "Cancel");
    public static final LayoutLocator continueButton = new LayoutLocator("continueButton", "//window[1]/button[3]", "Continue");

    public static final LayoutLocator emailText = new LayoutLocator("emailText", "//window[1]/text[1]", "");
    public static final LayoutLocator passwordInput = new LayoutLocator("passwordInputField", "//window[1]/secure[1]", "Password");
    public static final LayoutLocator forgotPasswordButton = new LayoutLocator("forgotPasswordButton", "//window[1]/button[1]", "");
    public static final LayoutLocator noPasswordEnteredMessage = new LayoutLocator("noPasswordEnteredMessage", "//window[3]/toolbar[1]/text[1]", "");
    public static final LayoutLocator loginButton = new LayoutLocator("loginButton", "//window[1]/button[2]", "Log In");

    public static final LayoutLocator validationPopUp = new LayoutLocator("validationPopUp", "//window[3]/toolbar[1]]", "");
    public static final LayoutLocator validationText = new LayoutLocator("validationText", "//window[3]/toolbar[1]/text[1]", "");
    public static final LayoutLocator validEmailValidationText = new LayoutLocator("validEmailValidationText", "//window[3]/toolbar[1]/text[1]", "That does not look like a valid email address");
    public static final LayoutLocator noPasswordValidationText = new LayoutLocator("noPasswordValidationText", "//window[3]/toolbar[1]/text[1]", "Please enter a username and password.");
    public static final LayoutLocator incorrectPasswordValidationText = new LayoutLocator("incorrectPasswordValidationText", "", "Incorrect password.");

    public static final LayoutLocator passwordResetPopUp = new LayoutLocator("passwordResetPopUp", "//window[3]/toolbar[1]/image[3]", "");
    public static final LayoutLocator passwordResetTextWorking = new LayoutLocator("passwordResetTextWorking", "//window[3]/toolbar[1]/text[1]", "");
    public static final LayoutLocator passwordResetTextMessage = new LayoutLocator("passwordResetTextMessage", "", "Please check your email. We've sent instructions to reset your password.");


}
