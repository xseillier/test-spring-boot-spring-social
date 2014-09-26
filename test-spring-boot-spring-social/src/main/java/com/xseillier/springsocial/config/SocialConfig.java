package com.xseillier.springsocial.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

@Configuration
@EnableSocial
public class SocialConfig  implements SocialConfigurer {

	  @Autowired
	    private DataSource dataSource;
	 
	    @Override
	    public void addConnectionFactories(ConnectionFactoryConfigurer aCfConfig, Environment aEnv) {
	        aCfConfig.addConnectionFactory(new TwitterConnectionFactory(
	                aEnv.getProperty("twitter.consumer.key"),
	                aEnv.getProperty("twitter.consumer.secret")
	        ));
	        aCfConfig.addConnectionFactory(new FacebookConnectionFactory(
	                aEnv.getProperty("facebook.app.id"),
	                aEnv.getProperty("facebook.app.secret")
	        ));
	        aCfConfig.addConnectionFactory(new LinkedInConnectionFactory(
	                aEnv.getProperty("linkedin.app.id"),
	                aEnv.getProperty("linkedin.app.secret")
	        ));
	    }
	 
	    @Override
	    public UserIdSource getUserIdSource() {
	        return new AuthenticationNameUserIdSource();
	    }
	 
	    @Override
	    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator aConnectionFactoryLocator) {
	        return new JdbcUsersConnectionRepository(
	                dataSource,
	                aConnectionFactoryLocator,
	                Encryptors.noOpText()
	        );
	    }
	 
	    @Bean
	    public ConnectController connectController(ConnectionFactoryLocator aConnectionFactoryLocator, ConnectionRepository aConnectionRepository) {
	        return new ConnectController(aConnectionFactoryLocator, aConnectionRepository);
	    }
}
