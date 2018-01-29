package com.answer.yx.listener;

import com.answer.yx.dao.Impl.ThreadDaoStart;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ThreadDaoListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Listener启动");
        ThreadDaoStart.run();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Listener销毁");
        ThreadDaoStart.close();
    }
}
