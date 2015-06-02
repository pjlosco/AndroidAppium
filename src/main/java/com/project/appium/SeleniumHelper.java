package com.project.appium;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SeleniumHelper extends TestSetup {

    private int timeoutInSeconds = 2;
    private int waitTimeout = 5;

    /**
     * Finds the element using the given search parameter and returns a WebElement, or null if nothing is found
     * @param by
     * @return
     */
    public WebElement getWebElement(By by){
        try {
            return driver.findElement(by);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Will throw a runtime exception if not found ending a test.
     * @param by
     * @return
     */
    public WebElement getWebElementNotNull(By by) {
        return driver.findElement(by);
    }

    /**
     * Gets a web element by trying first a search by name and then by xpath if nothing is found
     * @param locator
     * @return
     */
    public WebElement getWebElement(LayoutLocator locator) {
        WebElement locatedElement = null;

        if (!locator.hasLocatorSupplied()) {
            throw new NotFoundException("Cannot find element because no way of selecting was provided. Check locator initialization for " + locator.getElementName());
        }

        if (locatedElement == null && locator.getLabel() != null && !locator.getLabel().isEmpty()) {
            locatedElement = getWebElement(By.name(locator.getLabel()));
        }
        if (locatedElement == null && locator.contentDescription != null && !locator.contentDescription.isEmpty()) {
            locatedElement = getWebElement(By.name(locator.getContentDescription()));
        }
        if (locatedElement == null && locator.getId() != null && !locator.getId().isEmpty()) {
            locatedElement = getWebElement(By.id(locator.getId()));
        }
        if (locatedElement == null && locator.androidSelector != null && !locator.androidSelector.isEmpty()) {
            locatedElement = driver.findElementByAndroidUIAutomator(locator.getAndroidSelector());
        }
        if (locatedElement == null && locator.getXpath() != null && !locator.getXpath().isEmpty()) {
            locatedElement = getWebElement(By.xpath(locator.getXpath()));
        }
        if (locatedElement == null && locator.getClassName() != null && !locator.getClassName().isEmpty()) {
            locatedElement = getWebElement(By.className(locator.getClassName()));
        }


        if (locatedElement == null) {
            throw new NoSuchElementException("Tried to find " + locator.getElementName() + " with all locator options but was not found");
        }
        return locatedElement;
    }

    // Getting sub WebElements
    public WebElement getWebElement(By by, WebElement parentElement) {
        try {
            return parentElement.findElement(by);
        } catch (NotFoundException e) {
            fail("Couldn't find element with locator: " + by.toString());
        }
        return null;
    }
    public WebElement getSubWebElementByXpath(WebElement element, LayoutLocator locator) {
        return getWebElement(By.xpath(locator.getXpath()), element);
    }
    public WebElement getSubWebElementByName(WebElement element, LayoutLocator locator) {
        return getWebElement(By.name(locator.getLabel()), element);
    }

    // Getting a list of web elements
    public List<WebElement> getWebElements(By by){
        try {
            return driver.findElements(by);
        } catch (Exception e) {
            waitForPresent(by, timeoutInSeconds);
            try {
                return driver.findElements(by);
            } catch (NotFoundException f) {
                fail("Couldn't find elements with locator: " + by.toString());
            }
        }
        return null;
    }
    public List<WebElement> getWebElementsByXpath(LayoutLocator locator) {
        if (locator.getXpath() == null || locator.getXpath().isEmpty()) {
            throw new NotFoundException("Cannot find elements because no xpath value provided");
        }
        return getWebElements(By.xpath(locator.getXpath()));
    }
    public List<WebElement> getWebElementsByName(LayoutLocator locator) {
        if (locator.getLabel() == null || locator.getLabel().isEmpty()) {
            throw new NotFoundException("Cannot find elements because no name value provided");
        }
        return getWebElements(By.name(locator.getLabel()));
    }
    public List<WebElement> getWebElementsByAndroidUIAutomator(LayoutLocator locator) {
        if (locator.getAndroidSelector() == null || locator.getAndroidSelector().isEmpty()) {
            throw new NotFoundException("Cannot find elements because no name value provided");
        }
        try {
            return driver.findElementsByAndroidUIAutomator(locator.getAndroidSelector());
        } catch (AssertionError e) {
            fail("Couldn't find element with locator: " + locator.getAndroidSelector());
        }
        return null;
    }
    public List<WebElement> getWebElements(LayoutLocator locator) {
        try {
            return getWebElementsByName(locator);
        } catch (NotFoundException e) {
            return getWebElementsByXpath(locator);
        }
    }

    // Wait helpers
    public void waitForVisible(By by) {
        waitForVisible(by, waitTimeout);
    }
    public void waitForVisible(By by, int timeout) {
        Boolean elementVisible = false;
        for (int i=0; i<timeout; i++) {
            if (isElementVisible(by)) {
                elementVisible = true;
                break;
            } else {
                sleep(1);
            }
        }
        if (!elementVisible) {
            throw new NotFoundException("Waited, but cannot find element with locator: " + by.toString());
        }
    }
    public void waitForVisible(LayoutLocator locator) {
        waitForVisible(locator, waitTimeout);
    }
    public void waitForVisible(LayoutLocator locator, int timeout) {
        Boolean elementVisible = false;
        for (int i=0; i<timeout; i++) {
            if (isElementVisible(locator)) {
                elementVisible = true;
                break;
            } else {
                sleep(1);
            }
        }
        if (!elementVisible) {
            throw new NotFoundException("Waited, but cannot find element: " + locator.getElementName());
        }
    }

    public void waitForNotVisible(By by) {
        Boolean elementNotVisible = false;
        for (int i=0; i<waitTimeout; i++) {
            if (!isElementVisible(by)) {
                elementNotVisible = true;
                break;
            } else {
                sleep(1);
            }
        }
        if (!elementNotVisible) {
            throw new NotFoundException("Waited, but element is still visible: " + by.toString());
        }
    }
    public void waitForNotVisible(LayoutLocator locator) {
        Boolean elementNotVisible = false;
        for (int i=0; i<waitTimeout; i++) {
            if (!isElementVisible(locator)) {
                elementNotVisible = true;
                break;
            } else {
                sleep(1);
            }
        }
        if (!elementNotVisible) {
            throw new NotFoundException("Waited, but element is still visible: " + locator.getElementName());
        }
    }

    public void waitForTextPresent(LayoutLocator locator, String expectedText) {
        waitForTextPresent(locator, expectedText, waitTimeout);
    }
    public void waitForTextPresent(LayoutLocator locator, String expectedText, int timeout) {
        Boolean textPresent = false;
        String actualText = "";
        for (int i=0; i<timeout; i++) {
            actualText = getWebElement(locator).getAttribute("name");
            if (expectedText.equalsIgnoreCase(actualText)) {
                textPresent = true;
                break;
            } else {
                sleep(1);
            }
        }
        if (!textPresent) {
            throw new NotFoundException("Text not found. Expected: '" + expectedText + "', Actual: '" + actualText + "'");
        }
    }
    public void waitForTextPresent(By by, String expectedText) {
        Boolean textPresent = false;
        String actualText = "";
        for (int i=0; i<waitTimeout; i++) {
            actualText = getWebElement(by).getAttribute("name");
            if (expectedText.equalsIgnoreCase(actualText)) {
                textPresent = true;
                break;
            } else {
                sleep(1);
            }
        }
        if (!textPresent) {
            throw new NotFoundException("Text not found. Expected: '" + expectedText + "', Actual: '" + actualText + "'");
        }
    }

    public void waitForTextNotPresent(LayoutLocator locator, String text) {
        By xpath = By.xpath(locator.getXpath());
        Boolean textNotPresent = false;
        for (int attempt = 0; attempt < waitTimeout; attempt++) {
            textNotPresent = !driver.findElement(xpath).getAttribute("name").contains(text);
            if (textNotPresent) {
                break;
            } else {
                sleep(1);
            }
        }
        try {
            assertEquals(true, textNotPresent);
        } catch (Exception e) {
            System.err.print("Text " + text + " was found in element: " +  locator.getElementName() + ". " + e.getMessage());
        }
    }
    public void waitForPresent(LayoutLocator locator) {
        waitForPresent(locator, waitTimeout);
    }
    public void waitForPresent(LayoutLocator locator, int timeout) {
        Boolean isPresent = false;
        for (int i=0; i<timeout; i++) {
            if (isElementPresent(locator)) {
                isPresent = true;
                break;
            } else {
                sleep(1);
            }
        }
        if (!isPresent) {
            throw new NotFoundException("Element isn't present: " + locator.getElementName());
        }
    }
    public void waitForPresent(By by) {
        waitForPresent(by, waitTimeout);
    }
    public void waitForPresent(By by, int timeout) {
        Boolean isPresent = false;
        for (int i=0; i<timeout; i++) {
            if (isElementPresent(by)) {
                isPresent = true;
                break;
            } else {
                sleep(1);
            }
        }
        if (!isPresent) {
            throw new NotFoundException("Element isn't present: " + by.toString());
        }
    }

    public void implicitWait(int second) {
        driver.manage().timeouts().implicitlyWait(second, TimeUnit.SECONDS);
    }

    public void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            // who cares?
        }
    }

    // Assertions
    public boolean isElementVisible(By by) {
        try {
            return isElementVisible(getWebElement(by));
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isElementVisible(LayoutLocator locator) {
        try {
            return isElementVisible(getWebElement(locator));
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Visibility is a better way of checking. Can be used when element is covered but needs to be known if there.
     */
    public boolean isElementPresent(By by) {
        try {
            getWebElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isElementPresent(LayoutLocator locator) {
        try {
            getWebElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void click(LayoutLocator locator)  {
        waitForPresent(locator);
        try {
            click(getWebElement(locator));
        } catch (NotFoundException b) {
            fail("Cannot click element: " + locator.getElementName());
        }
    }
    public void click(By by) {
        waitForPresent(by);
        click(getWebElementNotNull(by));
    }
    public void click(WebElement element) {
        try  {
            element.click();
        } catch (Exception e) {
            tap(element);
        }
        sleep(0.6); // animation wait
    }

    public void assertText(String expectedText, String actualText) {
        try {
            assertEquals(expectedText, actualText);
        } catch (Exception e) {
            fail("Expected text: '" + expectedText + "', but got '" + actualText + "' instead.");
        }
    }


    // ****************** Gestures **********************
    /**
     * Tap an element with the provided duration in milliseconds
     * @param element
     */
    public void tap(WebElement element) {
        tap(element, 10);
    }
    public void tap(WebElement element, int duration) {
        tap(element, duration, 2);
    }
    private void tap(WebElement element, int duration, int touchCount) {
        driver.tap(touchCount, element, duration);
    }
    /**
     * Use this when getting trouble selecting something with a static size
     * @param x
     * @param y
     * @param duration
     */
    public void preciseTap(int touchCount, int x, int y, int duration) {
        driver.tap(touchCount, x, y, duration);
    }
    public void preciseTap(int x, int y) {
        preciseTap(2, x, y, 10);
    }

    /**
     * Scrolls a list element in the given direction
     * @param direction
     * @param element
     */
    public void scroll(String direction, WebElement element) {
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", direction);
        scrollObject.put("element", ((RemoteWebElement) element).getId());
        driver.executeScript("mobile: scroll", scrollObject);
    }

    public void swipe(int startX, int startY, int endX, int endY, int duration) {
        driver.swipe(startX, startY, endX, endY, duration);
    }

    /**
     * Sets coordinates for swiping on an element
     */
    private HashMap<String, Double> getCoordinates(LayoutLocator locator) {
        return getCoordinates(getWebElement(locator));
    }
    private HashMap<String, Double> getCoordinates(WebElement element) {
        // Get the coordinates and size of the element
        final double Width = (double) element.getSize().getWidth();
        final double Height = (double) element.getSize().getHeight();
        final double XCoordinate = (double) element.getLocation().getX();
        final double YCoordinate = (double) element.getLocation().getY();

        // Set interaction points of the element
        final double CenterX = XCoordinate + (Width / 2);
        final double CenterY = YCoordinate + (element.getSize().getHeight() / 2);
        final double LeftX = XCoordinate + 1.0;
        final double RightX = XCoordinate + Width - 1.0;
        final double TopY = YCoordinate + 1.0;
        final double BottomY = YCoordinate + Height - 1.0;

        HashMap<String, Double> elementCoordinates = new HashMap<String, Double>()
        {{
            put("Width", Width);
            put("Height", Height);
            put("XCoordinate", XCoordinate);
            put("YCoordinate", YCoordinate);
            put("CenterX", CenterX);
            put("CenterY", CenterY);
            put("LeftX", LeftX);
            put("RightX", RightX);
            put("TopY", TopY);
            put("BottomY", BottomY);
        }};

        return elementCoordinates;
    }

    public void swipe(int startX, int startY, int endX, int endY) {
        swipe(startX, startY, endX, endY, 1500);
    }

    public void swipeUp(WebElement element) {
        swipeUp(getCoordinates(element));
    }
    public void swipeUp(LayoutLocator locator) {
        swipeUp(getCoordinates(locator));
    }
    private void swipeUp(HashMap<String, Double> coordinates) {
        int CenterX = coordinates.get("CenterX").intValue();
        int BottomY = coordinates.get("BottomY").intValue();
        int TopY = coordinates.get("TopY").intValue();
        swipe(CenterX, BottomY, CenterX, TopY);
    }

    public void swipeDown(WebElement element) {
        swipeDown(getCoordinates(element));
    }
    public void swipeDown(LayoutLocator locator) {
        swipeDown(getCoordinates(locator));
    }
    private void swipeDown(HashMap<String, Double> coordinates) {
        int CenterX = coordinates.get("CenterX").intValue();
        int BottomY = coordinates.get("BottomY").intValue();
        int TopY = coordinates.get("TopY").intValue();
        swipe(CenterX, TopY, CenterX, BottomY);
    }

    public void swipeLeft(LayoutLocator locator) {
        HashMap<String, Double> coordinates = getCoordinates(locator);
        int CenterY = coordinates.get("CenterY").intValue();
        int LeftX = coordinates.get("LeftX").intValue();
        int RightX = coordinates.get("RightX").intValue();
        swipe(RightX, CenterY, LeftX, CenterY);
    }

    public void swipeRight(LayoutLocator locator) {
        HashMap<String, Double> coordinates = getCoordinates(locator);
        int CenterY = coordinates.get("CenterY").intValue();
        int LeftX = coordinates.get("LeftX").intValue();
        int RightX = coordinates.get("RightX").intValue();
        swipe(LeftX, CenterY, RightX, CenterY);
    }

    /**
     * Swipe until an element is visible
     * @param locatorToFind
     * @param elementDirection
     */
    public void swipeToElement(LayoutLocator locatorToFind, LayoutLocator locatorToSwipe, String elementDirection) {
        int swipes = 10;
        Boolean elementFound = false;
        for (int i=0; i<swipes; i++) {
            if (isElementVisible(locatorToFind)) {
                elementFound = true;
                break;
            } else {
                if (elementDirection == "down") {
                    swipeUp(locatorToSwipe);
                } else if (elementDirection == "up") {
                    swipeDown(locatorToSwipe);
                } else if (elementDirection == "left") {
                    swipeRight(locatorToSwipe);
                } else if (elementDirection == "right") {
                    swipeLeft(locatorToSwipe);
                } else {
                    fail("Invalid direction value: " + elementDirection);
                }
                sleep(.5);
            }
        }
        if (!elementFound) {
            throw new NotFoundException("Element not found: " + locatorToFind.getElementName());
        }
    }

    /**
     * Scrolls to the desired object
     * @param scrollingArea area of focus where to scroll
     * @param objectName object that needs to be in view located in the scrollingArea
     */
    public void scrollTo(LayoutLocator scrollingArea, String objectName) {
        WebElement objListView = getWebElement(scrollingArea);

        HashMap<String, String> objListScroll = new HashMap<String, String>();
        objListScroll.put("text", objectName);
        objListScroll.put("element", ((RemoteWebElement)objListView).getId());
        driver.executeScript("mobile: scrollTo", objListScroll);
    }

    public String getTimestamp() {
        java.util.Date date= new java.util.Date();
        return String.valueOf(new Timestamp(date.getTime())).replaceAll(" ", "").replaceAll(":","").replaceAll("-","").substring(4,14)+"000";
    }

}
