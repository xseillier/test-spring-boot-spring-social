package com.xseillier.springsocial.model;

/**
 * @author xseillier
 * @version 1.0
 */

public class RegistrationForm {

	
	private String mEmail;
   
    private String mFirstName;

    private String mLastName;

    private String mPassword;

    private String mPasswordVerification;

    private SocialProvider mSignInProvider;
    
    public RegistrationForm()
    {	
    }
    
	public String getEmail() {
		return mEmail;
	}

	public void setEmail(String aEmail) {
		mEmail = aEmail;
	}

	public String getFirstName() {
		return mFirstName;
	}

	public void setFirstName(String aFirstName) {
		this.mFirstName = aFirstName;
	}

	public String getLastName() {
		return mLastName;
	}

	public void setLastName(String aLastName) {
		mLastName = aLastName;
	}

	public String getPassword() {
		return mPassword;
	}

	public void setPassword(String aPassword) {
		mPassword = aPassword;
	}

	public String getPasswordVerification() {
		return mPasswordVerification;
	}

	public void setPasswordVerification(String aPasswordVerification) {
		mPasswordVerification = aPasswordVerification;
	}

	public SocialProvider getSignInProvider() {
		return mSignInProvider;
	}

	public void setSignInProvider(SocialProvider aSignInProvider) {
		this.mSignInProvider = aSignInProvider;
	}

	public boolean isNormalAccount() {
		return ( mSignInProvider == null || "".equals( mSignInProvider ) );
	}
}
