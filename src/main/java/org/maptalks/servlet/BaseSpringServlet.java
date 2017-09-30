package org.maptalks.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import cn.com.seegoo.servletrest.servlets.BaseServlet;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * 项目名：seegoo-jquery-spring-archetype
 * 类   名：BaseSpringServlet.java
 * 作   者：wangjun
 * 日   期：2013-7-1
 * 说   明：继承自seegoo rest框架，提供注入类的spring自动注入，
 * 同时也可以将其它servlet的父级别方法提升到该类中。
 */
public class BaseSpringServlet extends BaseServlet {

	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

}
