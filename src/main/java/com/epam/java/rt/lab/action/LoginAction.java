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
public class LoginAction implements Action {
    private static final Logger logger = LoggerFactory.getLogger(FrontControllerServlet.class);

    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        ActionResult actionResult = new ActionResult();
        if (req.getMethod().equals("GET")) {
            logger.info("Login action (GET)");
            actionResult.setProperty("title", "Login");
            actionResult.setProperty("jsp", "login.jsp");
        } else if (req.getMethod().equals("POST")) {
            logger.info("Login action (POST)");
            actionResult.setRedirectURI("/web/home");
        }
        return actionResult;
    }

}
