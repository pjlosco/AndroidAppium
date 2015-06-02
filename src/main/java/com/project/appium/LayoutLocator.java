package com.project.appium;

public class LayoutLocator {

    String elementName;
    String xpath;
    String label;
    String className;
    String contentDescription;
    String androidSelector;
    String id;

    public LayoutLocator(String elementName) {
        this.elementName = elementName;
    }

    public LayoutLocator(String elementName, String xpath, String label) {
        this.elementName = elementName;
        this.xpath = xpath;
        this.label = label;
    }

    public boolean hasLocatorSupplied() {
        if (this.xpath != null && !this.xpath.isEmpty()) {
            return true;
        }
        if (this.label != null && !this.label.isEmpty()) {
            return true;
        }
        if (this.className != null && !this.className.isEmpty()) {
            return true;
        }
        if (this.contentDescription != null && !this.contentDescription.isEmpty()) {
            return true;
        }
        if (this.androidSelector != null && !this.androidSelector.isEmpty()) {
            return true;
        }
        if (this.id != null && !this.id.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * An abstract representation of a path to an element
     * Should only be used when no label, name, or identifier is not present and view is not expected to change
     * @return the xpath of the element
     */
    public String getXpath() {
        return xpath;
    }

    /**
     * The name in reference to the element that is defined by the tester for reference purposes
     * @return the name of the element
     */
    public String getElementName() {
        return elementName;
    }

    /**
     * The text or label of an element
     * @return returns text, label,
     */
    public String getLabel() {
        return label;
    }

    /**
     * Return class name
     * @return
     */
    public String getClassName() { return className; }

    /**
     * Return UI Selector for an Android app element using the content description
     * @return
     */
    public String getContentDescription() {
        return "new UiSelector().description(\"" + contentDescription + "\")";
    }

    /**
     * Return UI Selector for an Android app element using the UiSelector class
     * @return
     */
    public String getAndroidSelector() {
        return "new UiSelector()." + androidSelector;
    }

    public String getId() {
        return id;
    }

    public LayoutLocator setXpath(String xpath) {
        this.xpath = xpath;
        return this;
    }

    public LayoutLocator setLabel(String label) {
        this.label = label;
        return this;
    }

    public LayoutLocator setClassName(String className) {
        this.className = className;
        return this;
    }

    public LayoutLocator setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
        return this;
    }

    public LayoutLocator setAndroidSelector(String androidSelector) {
        this.androidSelector = androidSelector;
        return this;
    }

    public LayoutLocator setId(String id) {
        this.id = id;
        return this;
    }
}
