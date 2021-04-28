package by.tc.task05.controller;

import by.tc.task05.dao.connectionpool.ConnectionPool;
import by.tc.task05.dao.connectionpool.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ServletStartAndShutdownListener implements ServletContextListener {
    private static Logger logger = LogManager.getLogger();
    private static final String DB_BUNDLE = "by.tc.task05.bundle.db";
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance().init(DB_BUNDLE);
        logger.info("Servlet Context initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().closeConnections();
        logger.info("Servlet Context destroyed");
    }
}
