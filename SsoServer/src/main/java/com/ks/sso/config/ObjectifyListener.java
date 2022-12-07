package com.ks.sso.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.googlecode.objectify.ObjectifyService;
import com.ks.sso.model.Student;
import com.ks.sso.model.Teacher;
import com.ks.sso.model.UserInfo;
import com.ks.sso.model.UserSession;

@WebListener
public class ObjectifyListener implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ObjectifyService.register(Student.class);
    ObjectifyService.register(Teacher.class);
    ObjectifyService.register(UserInfo.class);
    ObjectifyService.register(UserSession.class);

    
  }
  
  
}