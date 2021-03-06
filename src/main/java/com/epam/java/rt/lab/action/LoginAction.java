package com.epam.java.rt.lab.action;

import com.epam.java.rt.lab.servlet.FrontControllerServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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
            actionResult.setProperty("title", "Login");
            actionResult.setProperty("jsp", "login.jsp");
            List<String> errorList = authenticate
                    (String.valueOf(req.getParameter("email")), String.valueOf(req.getParameter("password")));
            if (errorList.size() == 0) {
                logger.info("Authenticate success");
                actionResult.setRedirectURI("/web/home");
            } else {
                logger.info("Authenticate error ({})", errorList);
                actionResult.setProperty("errorList", errorList);
            }
        }
        return actionResult;
    }

    private List<String> authenticate(String email, String password) {
        List<String> errorList = new ArrayList<>();
        if (!"123@123.com".equals(email) || !"123".equals(password)) {
            errorList.add("Authenticate error");
            return errorList;
        }
        return errorList;
    }

}
