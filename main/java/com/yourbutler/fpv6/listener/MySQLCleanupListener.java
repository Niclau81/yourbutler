package com.yourbutler.fpv6.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

public class MySQLCleanupListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        AbandonedConnectionCleanupThread.checkedShutdown();
    }
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // No action needed on context initialization
    }
}
