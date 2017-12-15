package com.example.project;

import javax.faces.annotation.FacesConfig;
import javax.faces.push.PushContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@FacesConfig
@WebListener
public class ApplicationConfig implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		event.getServletContext().setInitParameter(PushContext.ENABLE_WEBSOCKET_ENDPOINT_PARAM_NAME, "true");
	}

}
