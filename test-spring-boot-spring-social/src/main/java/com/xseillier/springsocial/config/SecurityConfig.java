package com.xseillier.springsocial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import com.xseillier.springsocial.service.MySocialUserDetailService;
import com.xseillier.springsocial.service.MyUserDetailService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Override
	    public void configure(WebSecurity aWeb) throws Exception {
	        aWeb
	                //Spring Security ignores request to static resources such as CSS or JS files.
	                .ignoring()
	                    .antMatchers("/static/**");

	    }
	@Override
    protected void configure(HttpSecurity aHttp) throws Exception {
			
		aHttp
        //Configures form login
        .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login/authenticate")
            .failureUrl("/login?error=bad_credentials")
        //Configures the logout function
        .and()
            .logout()      
             .deleteCookies("JSESSIONID")
             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
             .logoutSuccessUrl("/login")
        //Configures url based authorization
        .and()
            .authorizeRequests()
                //Anyone can access the urls
                .antMatchers(
                        "/auth/**",
                        "/login",
                        "/signup/**",
                        "/user/register/**"
                ).permitAll()
                //The rest of the our application is protected.
                .antMatchers("/secure/**").hasRole("USER")
        //Adds the SocialAuthenticationFilter to Spring Security's filter chain.
        .and()
            .apply( getSpringSocialConfigurer() );
    }
 
	
	private SpringSocialConfigurer getSpringSocialConfigurer() {
        SpringSocialConfigurer aConfig = new SpringSocialConfigurer();
        aConfig.alwaysUsePostLoginUrl(true);
        aConfig.postLoginUrl("/secure/home");
        return aConfig;
	}
	
	 /**
     * 
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
           	
    	auth.userDetailsService(userDetailsService() )
            .passwordEncoder(passwordEncoder() );
    }
	
	/**
	 * 
	 * @return
	 */
    @Bean
    public SocialUserDetailsService socialUserDetailsService() {
        return new MySocialUserDetailService( userDetailsService() );
    }
	 
    /**
     * 
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailService();
    }
	       
    /**
     * 
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
   
}
