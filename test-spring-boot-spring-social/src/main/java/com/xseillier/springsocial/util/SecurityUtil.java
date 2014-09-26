package com.xseillier.springsocial.util;

/**
 * @author xseillier
 * @version 1.0
 */

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.web.context.request.WebRequest;

import com.xseillier.springsocial.model.MyUserDetails;
import com.xseillier.springsocial.repository.dto.User;

public class SecurityUtil {

	
	
	/*
	 * 
	 */
	public static void logInUser(User aUser) {
        MyUserDetails userDetails = MyUserDetails.getBuilder()
                .firstName(aUser.getFirstName())
                .id(aUser.getId())
                .lastName(aUser.getLastName())
                .password(aUser.getPassword())
                .role(aUser.getRole())
                .socialProvider(aUser.getSignInProvider())
                .username(aUser.getEmail())
                .build();
 
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
	
	/**
	 * 
	 * @param aRequest
	 */
	public static void logInSocialUser(WebRequest aRequest) {
		Connection<?> oConnection = new ProviderSignInUtils().getConnectionFromSession( aRequest );
		
		Authentication authentication = new SocialAuthenticationToken( oConnection, null );
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	/**
	 * 
	 * @param aRequest
	 * @return
	 */
	public static boolean isSocialLogin(WebRequest aRequest) {		
		return ( new ProviderSignInUtils().getConnectionFromSession( aRequest ) != null )?true:false;	
	}
}
