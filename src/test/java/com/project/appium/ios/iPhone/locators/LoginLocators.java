package com.project.appium.ios.iPhone.locators;

import com.project.appium.LayoutLocator;

public class LoginLocators {

    /** Login - email **/
    public static final LayoutLocator emailTextInput = new LayoutLocator("emailTextInput", "//UIAApplication[1]/UIAWindow[1]/UIATextField[1]/UIATextField[1]", "");
    public static final LayoutLocator cancelButton = new LayoutLocator("cancelButton", "//window[1]/button[2]", "Cancel");
    public static final LayoutLocator continueButton = new LayoutLocator("continueButton", "//UIAApplication[1]/UIAWindow[1]/UIAButton[3]\n", "Continue");
    public static final LayoutLocator invalidEmailPopUpMessage = new LayoutLocator("invalidEmailPopUpMessage", "//window[3]/toolbar[1]/text[1]", "");

    /** Login - password **/
    public static final LayoutLocator passwordInput = new LayoutLocator("passwordInputField", "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]/UIASecureTextField[1]", "Password");
    public static final LayoutLocator welcomeBackNavBar = new LayoutLocator("welcomeBackNavBar", "//window[1]/navigationBar[1]/image[1]", "");
    public static final LayoutLocator forgotPasswordButton = new LayoutLocator("forgotPasswordButton", "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]", "Forgot password?");
    public static final LayoutLocator loginButton = new LayoutLocator("loginButton", "//UIAApplication[1]/UIAWindow[1]/UIAButton[2]", "Log In");
    public static final LayoutLocator noPasswordEnteredMessage = new LayoutLocator("noPasswordEnteredMessage", "//window[3]/toolbar[1]/text[1]", "");

    public static final LayoutLocator backButton = new LayoutLocator("backButton", "//window[1]/navigationBar[1]/button[1]", "");
    public static final LayoutLocator emailText = new LayoutLocator("emailText", "//window[1]/text[1]", "");
    public static final LayoutLocator validationPopUp = new LayoutLocator("validationPopUp", "//window[3]/toolbar[1]]", "");
    public static final LayoutLocator validationText = new LayoutLocator("validationText", "//UIAApplication[1]/UIAWindow[3]/UIAToolbar[1]/UIAStaticText[1]", "");
    public static final LayoutLocator validEmailValidationText = new LayoutLocator("validEmailValidationText", "//window[3]/toolbar[1]/text[1]", "That does not look like a valid email address");
    public static final LayoutLocator noPasswordValidationText = new LayoutLocator("noPasswordValidationText", "//window[3]/toolbar[1]/text[1]", "Please enter a username and password.");
    public static final LayoutLocator incorrectPasswordValidationText = new LayoutLocator("incorrectPasswordValidationText", "", "Incorrect password.");
    public static final LayoutLocator passwordResetPopUp = new LayoutLocator("passwordResetPopUp", "//window[3]/toolbar[1]/image[3]", "");
    public static final LayoutLocator passwordResetTextWorking = new LayoutLocator("passwordResetTextWorking", "//window[3]/toolbar[1]/text[1]", "");
    public static final LayoutLocator passwordResetTextMessage = new LayoutLocator("passwordResetTextMessage", "", "Please check your email. We've sent instructions to reset your password.");

}
