package com.project.appium.ios.iPad.locators;

import com.project.appium.LayoutLocator;

/**
 * Locators that are used across the app
 */
public class GlobalLocators {

    public static final LayoutLocator CurrentTitle = new LayoutLocator("CurrentTitle", "//window[1]/navigationBar[2]/text[1]", "");

    // Tap and hold edit interactions
    public static final LayoutLocator PasteText = new LayoutLocator("PasteText", "//window[4]/UIAEditingMenu[1]/element[17]", "Paste");

    // iOS Keyboard
    public static final LayoutLocator keyboard = new LayoutLocator("keyboard", "//window[2]/UIAKeyboard[1]", "");
    public static final LayoutLocator A = new LayoutLocator("A", "//window[2]/UIAKeyboard[1]/UIAKey[11]", "A");
    public static final LayoutLocator B = new LayoutLocator("B", "//window[2]/UIAKeyboard[1]/UIAKey[24]", "B");
    public static final LayoutLocator C = new LayoutLocator("C", "//window[2]/UIAKeyboard[1]/UIAKey[22]", "C");
    public static final LayoutLocator D = new LayoutLocator("D", "//window[2]/UIAKeyboard[1]/UIAKey[13]", "D");
    public static final LayoutLocator E = new LayoutLocator("E", "//window[2]/UIAKeyboard[1]/UIAKey[3]", "E");
    public static final LayoutLocator F = new LayoutLocator("F", "//window[2]/UIAKeyboard[1]/UIAKey[14]", "F");
    public static final LayoutLocator G = new LayoutLocator("G", "//window[2]/UIAKeyboard[1]/UIAKey[15]", "G");
    public static final LayoutLocator H = new LayoutLocator("H", "//window[2]/UIAKeyboard[1]/UIAKey[16]", "H");
    public static final LayoutLocator I = new LayoutLocator("I", "//window[2]/UIAKeyboard[1]/UIAKey[8]", "I");
    public static final LayoutLocator J = new LayoutLocator("J", "//window[2]/UIAKeyboard[1]/UIAKey[17]", "J");
    public static final LayoutLocator K = new LayoutLocator("K", "//window[2]/UIAKeyboard[1]/UIAKey[18]", "K");
    public static final LayoutLocator L = new LayoutLocator("L", "//window[2]/UIAKeyboard[1]/UIAKey[19]", "L");
    public static final LayoutLocator M = new LayoutLocator("M", "//window[2]/UIAKeyboard[1]/UIAKey[26]", "M");
    public static final LayoutLocator N = new LayoutLocator("N", "//window[2]/UIAKeyboard[1]/UIAKey[25]", "N");
    public static final LayoutLocator O = new LayoutLocator("O", "//window[2]/UIAKeyboard[1]/UIAKey[9]", "O");
    public static final LayoutLocator P = new LayoutLocator("P", "//window[2]/UIAKeyboard[1]/UIAKey[10]", "P");
    public static final LayoutLocator Q = new LayoutLocator("Q", "//window[2]/UIAKeyboard[1]/UIAKey[1]", "Q");
    public static final LayoutLocator R = new LayoutLocator("R", "//window[2]/UIAKeyboard[1]/UIAKey[4]", "R");
    public static final LayoutLocator S = new LayoutLocator("S", "//window[2]/UIAKeyboard[1]/UIAKey[12]", "S");
    public static final LayoutLocator T = new LayoutLocator("T", "//window[2]/UIAKeyboard[1]/UIAKey[5]", "T");
    public static final LayoutLocator U = new LayoutLocator("U", "//window[2]/UIAKeyboard[1]/UIAKey[7]", "U");
    public static final LayoutLocator V = new LayoutLocator("V", "//window[2]/UIAKeyboard[1]/UIAKey[23]", "V");
    public static final LayoutLocator W = new LayoutLocator("W", "//window[2]/UIAKeyboard[1]/UIAKey[2]", "W");
    public static final LayoutLocator X = new LayoutLocator("X", "//window[2]/UIAKeyboard[1]/UIAKey[21]", "X");
    public static final LayoutLocator Y = new LayoutLocator("Y", "//window[2]/UIAKeyboard[1]/UIAKey[6]", "Y");
    public static final LayoutLocator Z = new LayoutLocator("Z", "//window[2]/UIAKeyboard[1]/UIAKey[20]", "Z");
    public static final LayoutLocator shift = new LayoutLocator("shift", "//window[2]/UIAKeyboard[1]/button[1]", "shift");
    public static final LayoutLocator Delete = new LayoutLocator("Delete", "//window[2]/UIAKeyboard[1]/UIAKey[27]", "Delete");
    public static final LayoutLocator more = new LayoutLocator("more", " //window[2]/UIAKeyboard[1]/UIAKey[28]", "more, numbers");
    public static final LayoutLocator space = new LayoutLocator("space", "//window[2]/UIAKeyboard[1]/UIAKey[29]", "space");
    public static final LayoutLocator Next = new LayoutLocator("Next", "//window[2]/UIAKeyboard[1]/Button[4]", "Next");
    public static final LayoutLocator HideKeyboard = new LayoutLocator("HideKeyboard", "", "Hide keyboard");

    // iOS Alert Dialogs
    public static final LayoutLocator AlertOK = new LayoutLocator("AlertOK", "//window[4]/alert[1]/tableview[1]/cell[1]", "OK");
    public static final LayoutLocator Alert = new LayoutLocator("Alert", "//window[4]/alert", "");

    // GroupMe Alert Dialogs
    public static final LayoutLocator GenericAlert = new LayoutLocator("GenericAlert", "//window[2]/toolbar[1]", "");
    public static final LayoutLocator GenericAlertText = new LayoutLocator("GenericAlert", "//window[2]/toolbar[1]/text[1]", "");
}
