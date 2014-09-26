package com.xseillier.springsocial.controller;

/**
 * @author xseillier
 * @version 1.0
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignUpController {

	protected static final String REDIRECT_TO_VIEW_REGISTER_PAGE = "redirect:/user/register";
	protected static final String REQUEST_MAPPING_SIGNUP         = "/signup";
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping( value = REQUEST_MAPPING_SIGNUP, method = RequestMethod.GET )
	public String redirectRequestToRegistrationPage()
	{
		return REDIRECT_TO_VIEW_REGISTER_PAGE;
	}
}
