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
            String[] errorArray = authenticate
                    (String.valueOf(req.getAttribute("email")), String.valueOf(req.getAttribute("email")));
            if (errorArray == null) {
                logger.info("Authenticate success");
                actionResult.setRedirectURI("/web/home");
            } else {
                logger.info("Authenticate error ({})", errorArray);
                actionResult.setProperty("errorArray", errorArray);
            }
        }
        return actionResult;
    }

    private String[] authenticate(String email, String password) {
        if (!"123@123.com".equals(email) || !"123".equals(password)) {
            String[] errorArray = {"Authenticate error"};
            return errorArray;
        }
        return null;
    }

}
