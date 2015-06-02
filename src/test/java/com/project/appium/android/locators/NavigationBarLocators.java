package com.project.appium.android.locators;

import com.project.appium.LayoutLocator;

/**
 * Created by cass on 4/24/14.
 */
public class NavigationBarLocators {

    public static final LayoutLocator ActionBar = new LayoutLocator("ActionBar").setXpath("/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.LinearLayout[1]");
    public static final LayoutLocator ActionBarTitle = new LayoutLocator("ActionBarTitle").setXpath("//android.widget.LinearLayout/android.widget.TextView[1]").setId("android:id/action_bar_title");
    public static final LayoutLocator ActionBarSubtitle = new LayoutLocator("ActionBarSubtitle").setXpath("//android.widget.LinearLayout/android.widget.TextView[2]").setId("android:id/action_bar_title");

    public static final LayoutLocator MenuButton = new LayoutLocator("MenuButton").setId("Menu");
}
