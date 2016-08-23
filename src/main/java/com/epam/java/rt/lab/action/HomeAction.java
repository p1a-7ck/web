package com.epam.java.rt.lab.action;

import com.epam.java.rt.lab.servlet.FrontControllerServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * web
 */
@ServletAction()
public class HomeAction implements Action {
    private static final Logger logger = LoggerFactory.getLogger(FrontControllerServlet.class);

    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("Home action");
        ActionResult actionResult = new ActionResult();
        actionResult.setProperty("title", "Home");
        actionResult.setProperty("navs", "_navs.jsp");
        actionResult.setProperty("jsp", "home.jsp");
        return actionResult;
    }

}
