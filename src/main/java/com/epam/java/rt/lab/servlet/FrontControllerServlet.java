package com.epam.java.rt.lab.servlet;

import com.epam.java.rt.lab.action.ActionException;
import com.epam.java.rt.lab.action.ActionFactory;
import com.epam.java.rt.lab.action.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * web
 */
@WebServlet(name = "Service", urlPatterns = "/web/*")
public class FrontControllerServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(FrontControllerServlet.class);
    private static ActionFactory actionFactory = new ActionFactory();

    public FrontControllerServlet() throws ActionException {
        logger.info("Front controller servlet initiated");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("Front controller servlet servicing request '{}'", req.getMethod().concat(req.getPathInfo()));
        try {
            if (!req.getPathInfo().startsWith("/static/")) {
                ActionResult actionResult = FrontControllerServlet.actionFactory.getAction(req).execute(req, resp);
                if (actionResult.getRedirectURI() == null) {
                    req.setAttribute("result", actionResult);
                    req.getRequestDispatcher("/WEB-INF/jsp/_template.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect(actionResult.getRedirectURI());
                }
            } else {
                req.getRequestDispatcher("/WEB-INF".concat(req.getPathInfo())).forward(req, resp);
            }
        } catch (ActionException e) {
            throw new ServletException(e.getMessage());
        }
    }
}
