package com.xseillier.springsocial.controller;


/**
 * @author xseillier
 * @version 1.0
 */

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.xseillier.springsocial.model.RegistrationForm;
import com.xseillier.springsocial.model.SocialProvider;
import com.xseillier.springsocial.repository.dto.User;
import com.xseillier.springsocial.service.MyUserDetailService;
import com.xseillier.springsocial.util.SecurityUtil;



@Controller
public class RegistrationController {

	
	@Autowired
	MyUserDetailService myUserDetailService;
	
	protected static final String VIEW_REGISTRATION_PAGE     = "auth/registrationForm";
	protected static final String REDIRECT_TO_VIEW_HOME_PAGE = "redirect:/secure/home";
	protected static final String REQUEST_MAPPING_REGISTER   = "/user/register";
	
	
	    /**
	     * 
	     * @param aUserAccount
	     * @param aResult
	     * @param aRequest
	     * @param aModel
	     * @return
	     */
	 	@RequestMapping(value = REQUEST_MAPPING_REGISTER, method = RequestMethod.GET )
	    public String showRegistrationForm(@ModelAttribute("user")  RegistrationForm aUserAccount,
	    		BindingResult aResult, 
	    		WebRequest aRequest, Model aModel) {
	       
		 Connection<?> oConnection = new ProviderSignInUtils().getConnectionFromSession( aRequest );
	        
	        if( oConnection != null )
	        {
	        	RegistrationForm registration = createRegistrationDTO( oConnection );
	 	        aModel.addAttribute("user", registration); 	
	        }
	       	 
	        return VIEW_REGISTRATION_PAGE;
	    }
	 
	 
	    /**
	     * 
	     * @param aUserAccountData
	     * @param aResult
	     * @param aRequest
	     * @return
	     */
	 	@RequestMapping(value = REQUEST_MAPPING_REGISTER, method = RequestMethod.POST)
	    public String registerUserAccount(@Valid @ModelAttribute("user") RegistrationForm aUserAccountData,
	                                      BindingResult aResult,
	                                      WebRequest aRequest) {
		 	 
		 if (aResult.hasErrors()) {
	            return VIEW_REGISTRATION_PAGE;
	        }
		 User oUser = myUserDetailService.registerNewUserAccount( aUserAccountData );	
		 
		 if( SecurityUtil.isSocialLogin( aRequest ) )
		 {
			 SecurityUtil.logInSocialUser( aRequest );
			 new ProviderSignInUtils().doPostSignUp(oUser.getEmail(), aRequest);
		 }
		 else
		 {
			 SecurityUtil.logInUser( oUser );
		 }
		 return REDIRECT_TO_VIEW_HOME_PAGE;
	 }
	 
	 
	 /**
	  * 
	  * @param oConnection
	  * @return
	  */
	 private RegistrationForm createRegistrationDTO(Connection<?> oConnection) {
	        RegistrationForm oRegistrationForm = new RegistrationForm();
	 
	        if (oConnection != null) {
	            UserProfile oSocialMediaProfile = oConnection.fetchUserProfile();
	            oRegistrationForm.setEmail(oSocialMediaProfile.getEmail());
	            oRegistrationForm.setFirstName(oSocialMediaProfile.getFirstName());
	            oRegistrationForm.setLastName(oSocialMediaProfile.getLastName());  
	            ConnectionKey providerKey = oConnection.getKey();
	            oRegistrationForm.setSignInProvider(SocialProvider.valueOf(providerKey.getProviderId().toUpperCase()));
	        }	 
	        return oRegistrationForm;
	}
}
