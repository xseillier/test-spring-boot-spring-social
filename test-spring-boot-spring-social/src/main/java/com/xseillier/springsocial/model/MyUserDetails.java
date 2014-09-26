package com.xseillier.springsocial.model;

/**
 * @author xseillier
 * @version 1.0
 */

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

public class MyUserDetails extends SocialUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 716504004032318367L;
	private Long   mId;
	private String mFirstName;
	private String mLastName;
	private Role   mRole;	

	private SocialProvider mSocialSignInProvider;
	
	/**
	 * 
	 * @param aUsername
	 * @param aPassword
	 * @param aAuthorities
	 */
	public MyUserDetails(String aUsername, String aPassword, Collection<? extends GrantedAuthority> aAuthorities) {
		super(aUsername, aPassword, aAuthorities);
	}

	/**
	 * 
	 * @return
	 */
	public Long getId() {
		return mId;
	}

	/**
	 * 
	 * @param aId
	 */
	public void setId(Long aId) {
		mId = aId;
	}

	/**
	 * 
	 * @return
	 */
	public String getFirstName() {
		return mFirstName;
	}

	/**
	 * 
	 * @param aFirstName
	 */
	public void setFirstName(String aFirstName) {
		mFirstName = aFirstName;
	}


	/**
	 * 
	 * @return
	 */
	public String getLastName() {
		return mLastName;
	}

	/**
	 * 
	 * @param aLastName
	 */
	public void setLastName(String aLastName) {
		mLastName = aLastName;
	}

	/**
	 * 
	 * @return
	 */
	public Role getRole() {
		return mRole;
	}

	/**
	 * 
	 * @param aRole
	 */
	public void setRole(Role aRole) {
		mRole = aRole;
	}

	/**
	 * 
	 * @return
	 */
	public static Builder getBuilder() {
        return new Builder();
    }
	
	/**
	 * 
	 * @return
	 */
	public SocialProvider getSocialSignInProvider() {
		return mSocialSignInProvider;
	}

	/**
	 * 
	 * @param aSocialSignInProvider
	 */
	public void setSocialSignInProvider(SocialProvider aSocialSignInProvider) {
		mSocialSignInProvider = aSocialSignInProvider;
	}

	
	
	public static class Builder{
		
		private Set<GrantedAuthority> mAuthorities;
		
		private Long   id;
		private String firstName;
		private String lastName;
		private Role   role;	
		private String password;
		private String username;
		
		private SocialProvider socialSignInProvider;
		
	    public Builder() {
	        this.mAuthorities = new HashSet<GrantedAuthority>();
	    }
		
	    
	    public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
 
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
 
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
 
        public Builder password(String password) {
            if (password == null) {
                password = "SocialUser";
            }
 
            this.password = password;
            return this;
        }
 
        public Builder role(Role role) {
            this.role = role;
 
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
            this.mAuthorities.add(authority);
 
            return this;
        }
	    
        public Builder socialProvider(SocialProvider socialSignInProvider) {
            this.socialSignInProvider = socialSignInProvider;
            return this;
        }
 
        public Builder username(String username) {
            this.username = username;
            return this;
        }
        
        public MyUserDetails build() {
        	MyUserDetails user = new MyUserDetails(username, password, mAuthorities);
 
            user.mId = id;
            user.mFirstName = firstName;
            user.mLastName = lastName;
            user.mRole = role;
            user.setSocialSignInProvider(socialSignInProvider);
            return user;
        }
	    
	}
	
	
}
