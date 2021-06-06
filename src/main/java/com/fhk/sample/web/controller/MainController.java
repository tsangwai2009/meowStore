package com.fhk.sample.web.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainController 
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String std(final HttpServletRequest httpRequest, final Locale locale, final ModelMap model, final HttpSession session) throws IOException 
	{
		final String APPLICATION = "std";

		model.put("app", APPLICATION);
		model.put("locale", locale);
		model.put("contextPath", httpRequest.getContextPath());
		return APPLICATION;
	}

	@RequestMapping(value = "/sandbox")
	public String sandbox(final HttpServletRequest httpRequest, final Locale locale, final ModelMap model) {
		return "sandbox";
	}
	
	
	
}
