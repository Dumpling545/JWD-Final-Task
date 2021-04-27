package by.tc.task05.controller;

import by.tc.task05.dao.connectionpool.ConnectionPool;
import by.tc.task05.dao.connectionpool.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ServletStartAndShutdownListener implements ServletContextListener {
    private static final String DB_BUNDLE = "by.tc.task05.bundle.db";
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance().init(DB_BUNDLE);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().closeConnections();
    }
}
