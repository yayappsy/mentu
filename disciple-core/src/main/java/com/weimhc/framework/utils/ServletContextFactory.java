/*
 * CKFinder
 * ========
 * http://cksource.com/ckfinder
 * Copyright (C) 2007-2015, CKSource - Frederico Knabben. All rights reserved.
 *
 * The software, this file and its contents are subject to the CKFinder
 * License. Please read the license.txt file before using, installing, copying,
 * modifying or distribute this file or part of its contents. The contents of
 * this file is part of the Source Code of CKFinder.
 */
package com.weimhc.framework.utils;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

/**
 * Access to servlet context outside from servlet.
 */
@Service
@Lazy(false)
public class ServletContextFactory implements ServletContextAware {

	/**
	 * ServletContext object.
	 */
	private static ServletContext servletContext;

	/**
	 * sets servlet context.
	 *
	 * @param servletContext1
	 *            context
	 */
	@Override
	public void setServletContext(final ServletContext servletContext1) {
		servletContext = servletContext1;
	}

	/**
	 * Returns {@code ServletContext} instance.
	 *
	 * @return current {@code ServletContext} object
	 * @throws Exception
	 *             when {@code ServletContext} is {@code null}.
	 */
	public static ServletContext getServletContext() throws Exception {
		if (servletContext != null) {
			return servletContext;
		} else {
			throw new Exception(
					"Servlet contex is null. Try to restart server.");
		}

	}
}
