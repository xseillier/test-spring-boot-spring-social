package com.xseillier.springsocial.controller;

/**
 * @author xseillier
 * @version 1.0
 */

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xseillier.springsocial.model.MyUserDetails;

@Controller
public class UserHomeController {

	
	protected static final String VIEW_HOME_PAGE              = "secure/home";
	protected static final String REQUEST_MAPPING_HOME        = "/secure/home";
	protected static final String PARAM_MODEL_USER            = "user";
	protected static final String PARAM_MODEL_URL_IMG_PROFILE = "imageUrl";
	protected static final String PARAM_MODEL_SOCIAL_PROVIDER = "socialProvider";
	
			
	/**
	 * 
	 * @param aPrincipal
	 * @param aModel
	 * @return
	 */
	@RequestMapping( value = REQUEST_MAPPING_HOME, method = RequestMethod.GET )
	public String redirectRequestToRegistrationPage(Principal aPrincipal, Model aModel )
	{	
		
		if( aPrincipal instanceof SocialAuthenticationToken )
		{
			SocialAuthenticationToken oSocialAuthenticationToken = (SocialAuthenticationToken) aPrincipal;
			aModel.addAttribute(PARAM_MODEL_URL_IMG_PROFILE, oSocialAuthenticationToken.getConnection().getImageUrl() );		
			aModel.addAttribute(PARAM_MODEL_USER,            oSocialAuthenticationToken.getConnection().getDisplayName() );
			aModel.addAttribute(PARAM_MODEL_SOCIAL_PROVIDER, oSocialAuthenticationToken.getConnection().getKey().getProviderId() );			
			
		}
		else
		{
			UsernamePasswordAuthenticationToken oSocialAuthenticationToken = (UsernamePasswordAuthenticationToken) aPrincipal;
			MyUserDetails oMyUserDetails = (MyUserDetails) oSocialAuthenticationToken.getPrincipal();
			aModel.addAttribute(PARAM_MODEL_URL_IMG_PROFILE, "" );	
			aModel.addAttribute(PARAM_MODEL_SOCIAL_PROVIDER, "No Social Provider" );		
			aModel.addAttribute(PARAM_MODEL_USER,           oMyUserDetails.getFirstName() + " " + oMyUserDetails.getLastName() );
		}
		
		return VIEW_HOME_PAGE;
	}
	

}
