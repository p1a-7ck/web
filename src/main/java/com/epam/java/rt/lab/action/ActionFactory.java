package com.epam.java.rt.lab.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * web
 */
public class ActionFactory {
    private static final Logger logger = LoggerFactory.getLogger(ActionFactory.class);
    private final Map<String, Action> actionMap = new HashMap<String, Action>();

    public ActionFactory() throws ActionException {
        logger.info("Action factory initiated");
    }

    private void initFactory() throws ActionException {
        try {
            String actionPackageName = ActionFactory.class.getPackage().getName();
            String actionPackagePath = actionPackageName.replace(".", "/");
            Enumeration<URL> actionPackageResource = ActionFactory.class.getClassLoader().getResources("Action");
            String file;
            String className;
            String actionName;
            Class<?> actionClass;
            while (actionPackageResource.hasMoreElements()) {
                file = actionPackageResource.nextElement().getFile();
                logger.info("file = '{}'", file);
                if (file.endsWith(".class")) {
                    className = actionPackageName.concat(".").concat(file.substring(0, file.length() - 6));
                    actionClass = Class.forName(className);
                    if (actionClass.getAnnotation(ServletAction.class) != null) {
                        actionName = actionClass.getSimpleName().toLowerCase();
                        this.actionMap.put(actionName.substring(0, actionName.length() - 6), (Action) actionClass.newInstance());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            logger.error("Class not found", e);
            throw new ActionException(e.getMessage());
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("Class instantiation or access error", e);
            throw new ActionException(e.getMessage());
        } catch (IOException e) {
            logger.error("Package resource error", e);
            throw new ActionException(e.getMessage());
        }
    }

    private Action getAndPutAction(String path) throws ActionException {
        try {
            logger.info("Trying to add action '{}'", path);
            String actionPackage = ActionFactory.class.getPackage().getName();
            Class<?> actionClass = Class.forName(actionPackage.concat(".")
                    .concat(path.substring(1, 2).toUpperCase().concat(path.substring(2)).concat("Action")));
            if (actionClass.getAnnotation(ServletAction.class) != null) {
                Action actionObject = (Action) actionClass.newInstance();
                this.actionMap.put(path, actionObject);
                return actionObject;
            }
            throw new ActionException("Action not found");
        } catch (ClassNotFoundException e) {
            logger.error("Action class not found", e);
            throw new ActionException(e.getMessage());
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("Action class instantiating and access error", e);
            throw new ActionException(e.getMessage());
        }
    }

    public Action getAction(HttpServletRequest req) throws ActionException {
        Action action = this.actionMap.get(req.getPathInfo());
        if (action != null) return action;
        return getAndPutAction(req.getPathInfo());
    }
}