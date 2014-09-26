package com.xseillier.springsocial.service;

/**
 * @author xseillier
 * @version 1.0
 */

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;


public class MySocialUserDetailService implements SocialUserDetailsService  {

	private UserDetailsService userDetailsService;

	/**
	 * 
	 * @param aUserDetailsService
	 */
    public MySocialUserDetailService(UserDetailsService aUserDetailsService) {
        
    	this.userDetailsService = aUserDetailsService;
    	
    }
   
    
	@Override
	public SocialUserDetails loadUserByUserId(String aUserId)
			throws UsernameNotFoundException, DataAccessException {
		
		UserDetails oUserDetails = userDetailsService.loadUserByUsername( aUserId );
		return (SocialUserDetails) oUserDetails;
	}

	

}
