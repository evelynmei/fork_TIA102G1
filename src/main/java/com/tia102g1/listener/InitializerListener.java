package com.tia102g1.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tia102g1.HibernateUtil;

public class InitializerListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("context started");
		HibernateUtil.getSessionFactory();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("context ended");
		HibernateUtil.shutdown();
	}	

}
