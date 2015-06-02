package com.project.appium.android.locators;

import com.project.appium.LayoutLocator;

public class LoginLocators {

    public static final LayoutLocator emailAddressTextField = new LayoutLocator("input_field_email", "//android.widget.EditText[contains(@text, 'e-mail')]", "Enter your e-mail address");

    public static final LayoutLocator passwordTextField = new LayoutLocator("passwordTextField").setXpath("//android.widget.EditText[2]");

    public static final LayoutLocator signInButton = new LayoutLocator("signInButton").setLabel("Sign In");

    public static final LayoutLocator spinningIcon = new LayoutLocator("spinningIcon");

    public static final LayoutLocator forgotPasswordLink = new LayoutLocator("forgotPasswordLink").setLabel("Forgot password?");

}
