package com.project.appium.android.locators;

import com.project.appium.LayoutLocator;

/**
 * Created by patrick on 7/8/14.
 */
public class AndroidNotificationsLocators {

    public static final LayoutLocator Clear = new LayoutLocator("Clear").setLabel("Clear").setContentDescription("Clear all notifications.").setId("com.android.systemui:id/clear_all_button").setXpath("//android.widget.LinearLayout[1]/android.widget.ImageView[1]");

}
