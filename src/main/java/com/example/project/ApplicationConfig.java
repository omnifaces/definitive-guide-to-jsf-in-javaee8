package com.example.project;

import javax.faces.annotation.FacesConfig;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.example.project.model.Product;
import com.example.project.service.ProductService;

@FacesConfig
@WebListener
public class ApplicationConfig implements ServletContextListener {

	@Inject
	private ProductService productService;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		enableWebsocketEndpoint(event.getServletContext());
		createTestProducts();
	}

	private void enableWebsocketEndpoint(ServletContext context) {
		context.setInitParameter(PushContext.ENABLE_WEBSOCKET_ENDPOINT_PARAM_NAME, "true");
	}

	private void createTestProducts() {
		productService.create(Product.create("One", "The first product"));
		productService.create(Product.create("Two", "The second product"));
		productService.create(Product.create("Three", "The third product"));
	}

}
