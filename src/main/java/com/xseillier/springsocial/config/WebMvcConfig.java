package com.xseillier.springsocial.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
 
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter{
 
	
	protected static final String VIEW_HOME_PAGE        = "auth/login";
	protected static final String REQUEST_MAPPING_LOGIN = "/login";	
	protected static final String REQUEST_MAPPING_INDEX = "/";
	
	
	/**
	 * 
	 */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer aConfigurer) {
        aConfigurer.enable();
    }
  
    /**
     * 
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver oViewResolver = new InternalResourceViewResolver();
 
        oViewResolver.setViewClass(JstlView.class);
        oViewResolver.setPrefix("/WEB-INF/views/");
        oViewResolver.setSuffix(".jsp");
 
        return oViewResolver;
    }
    
    /**
     * 
     */
    @Override
	public void addViewControllers(ViewControllerRegistry aRegistry) {
	    aRegistry.addViewController( REQUEST_MAPPING_LOGIN  ).setViewName( VIEW_HOME_PAGE );
	    aRegistry.addViewController( REQUEST_MAPPING_INDEX ).setViewName( VIEW_HOME_PAGE );
	}
   

    
    /**
     * add locale change interceptor
     */
    @Override
    public void addInterceptors(InterceptorRegistry aRegistry) {
            aRegistry.addInterceptor(localeChangeInterceptor());
    }

    
    /**
     * define param for language
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
            LocaleChangeInterceptor oLocaleChangeInterceptor = new LocaleChangeInterceptor();
            oLocaleChangeInterceptor.setParamName("lang");
            return oLocaleChangeInterceptor;
    }
    
    /**
     * define default language
     * @return
     */
    @Bean(name = "localeResolver")
    public CookieLocaleResolver localeResolver() {
            CookieLocaleResolver oLocaleResolver = new CookieLocaleResolver();
            Locale defaultLocale = new Locale("en");
            oLocaleResolver.setDefaultLocale(defaultLocale);
            return oLocaleResolver;
    }

    
    /**
     * define bundle path an name
     * @return
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
            ReloadableResourceBundleMessageSource oMessageSource = new ReloadableResourceBundleMessageSource();
            oMessageSource.setBasename("classpath:i18n/messages");
            oMessageSource.setCacheSeconds(10); //reload messages every 10 seconds
            return oMessageSource;
    }
        
}