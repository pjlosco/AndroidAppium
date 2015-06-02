package com.project.appium.android.suites;

import com.project.appium.User;
import com.project.appium.android.AndroidNavigationHelper;
import com.project.appium.android.locators.LoginLocators;
import com.project.appium.android.locators.SettingsLocators;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SettingsTest extends AndroidNavigationHelper {

    User automationUser = new User("tester@email.com", "Firstname", "Lastname", "password", "android", "12345");

    @Test
    public void Logout() {
        login(automationUser);
        openAppSettings();
        swipeToElement(SettingsLocators.logoutButton, SettingsLocators.SettingsList, "down");
        click(SettingsLocators.logoutButton);
        click(SettingsLocators.LogoutPopupOKButton);
        waitForVisible(LoginLocators.emailAddressTextField);
    }

}
