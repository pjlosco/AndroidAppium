package com.project.appium.android.locators;

import com.project.appium.LayoutLocator;

public class SettingsLocators {

    public static final LayoutLocator SettingsList = new LayoutLocator("SettingsList").setClassName("android.widget.ListView");

    public static final LayoutLocator logoutButton = new LayoutLocator("logoutButton").setLabel("Logout");
    public static final LayoutLocator LogoutPopupOKButton = new LayoutLocator("LogoutPopupOKButton", "//android.widget.Button[contains(@text, 'Logout')]", "");
    public static final LayoutLocator logoutPopUpCancelButton = new LayoutLocator("logoutCancelPopUpOkButton").setLabel("Cancel");

}
