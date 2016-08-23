package com.epam.java.rt.lab.action;

import java.util.HashMap;
import java.util.Map;

/**
 * web
 */
public class ActionResult {
    private String redirectURI = null;
    private Map<String, Object> propertyMap;

    ActionResult() {
        this.propertyMap = new HashMap<String, Object>();
    }

    public String getRedirectURI() { return redirectURI; }

    public void setRedirectURI(String redirectURI) { this.redirectURI = redirectURI; }

    public Object getProperty(String name) {
        return propertyMap.get(name);
    }

    public void setProperty(String name, Object object) {
        this.propertyMap.put(name, object);
    }

    public boolean isPropertyExist(String name) {
        return this.propertyMap.containsKey(name);
    }

}
