package com.epam.java.rt.lab.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * web
 */
public interface Action {
    ActionResult execute(HttpServletRequest req, HttpServletResponse resp);
}
