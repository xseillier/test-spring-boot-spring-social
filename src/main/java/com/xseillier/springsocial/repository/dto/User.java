package com.xseillier.springsocial.repository.dto;

/**
 * @author xseillier
 * @version 1.0
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xseillier.springsocial.model.Role;
import com.xseillier.springsocial.model.SocialProvider;

@Entity
@Table( name = "myuser")
public class User {

	@Id
	@GeneratedValue( strategy =  GenerationType.AUTO)
	@Column(name = "id")
	private Long   id;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "password")
	private String password;
	     
	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;
    
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;

	@Enumerated(EnumType.STRING)
	@Column(name = "signInProvider")
	private SocialProvider signInProvider;
  	
	 /**
	  * 
	  */
    public User()
    {}
    
    /**
     * 
     * @return
     */
    public Long getId() {
		return id;
	}
    
    /**
     * 
     * @param id
     */
	public void setId(Long aId) {
		id = aId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String aFirstName) {
		firstName = aFirstName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String aLastName) {
		lastName = aLastName;
	}
	
	/**
	 * 
	 * @return
	 */
	public Role getRole() {
		return role;
	}
	
	/**
	 * 
	 * @param role
	 */
	public void setRole(Role aRole) {
		role = aRole;
	}

	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String aPassword) {
		password = aPassword;
	}
	
	/**
	 * 
	 * @return
	 */
	public SocialProvider getSignInProvider() {
		return signInProvider;
	}

	/**
	 * 
	 * @param signInProvider
	 */
	public void setsignInProvider(SocialProvider aSignInProvider) {
		signInProvider = aSignInProvider;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String aEmail) {
		email = aEmail;
	}

	/**
	 * 
	 * @param signInProvider
	 */
	public void setSignInProvider(SocialProvider aSignInProvider) {
		signInProvider = aSignInProvider;
	}
}
