package com.xseillier.springsocial.service;

/**
 * @author xseillier
 * @version 1.0
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.xseillier.springsocial.model.MyUserDetails;
import com.xseillier.springsocial.model.RegistrationForm;
import com.xseillier.springsocial.model.Role;
import com.xseillier.springsocial.repository.UserRepository;
import com.xseillier.springsocial.repository.dto.User;




public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String aUsername)
			throws UsernameNotFoundException {
			
		User oUser = userRepository.findByEmail( aUsername );
		
		if( oUser == null )
		{
			throw new UsernameNotFoundException("Opss !");
		}
		
		UserDetails oUserDetails = MyUserDetails.getBuilder()
								  .id( oUser.getId() )
								  .firstName( oUser.getFirstName() )
								  .lastName( oUser.getLastName())
								  .password( oUser.getPassword() )
								  .username( oUser.getEmail() )
								  .role( oUser.getRole() )
								  .socialProvider( oUser.getSignInProvider() )
								  .build();
		
		return oUserDetails;
	}

	public User registerNewUserAccount(RegistrationForm aUserAccountData) {

		User oUser = new User();		
		oUser.setFirstName( aUserAccountData.getFirstName() );
		oUser.setLastName(  aUserAccountData.getLastName() );
		
		if( aUserAccountData.isNormalAccount() )
		{
			oUser.setPassword(  passwordEncoder.encode( aUserAccountData.getPassword() ) );
		}
		
		oUser.setEmail(     aUserAccountData.getEmail() );
		oUser.setRole( Role.ROLE_USER );
		oUser.setsignInProvider( aUserAccountData.getSignInProvider() );
		
		return userRepository.save( oUser );
	}

	
	
	
}
