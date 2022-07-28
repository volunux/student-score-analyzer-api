package com.tplnt.studentscore.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.tplnt.studentscore.interceptor.RestTemplateHeaderInterceptor;

@Configuration
@ComponentScan("com.tplnt")
@PropertySource(value = { "classpath:application.properties" })
public class TPlntConfig implements WebMvcConfigurer {
	
	private static final String[] ENTITY_PACKAGES_TO_SCAN = new String[] { "com.tplnt" };
	
	@Autowired
	private Environment env;
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.addPathPrefix(env.getProperty("api-base-path"), HandlerTypePredicate.forAnnotation(RestController.class));
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins(env.getProperty("front-end-url")).allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*");
	}
	
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactory.setPackagesToScan(ENTITY_PACKAGES_TO_SCAN);
		entityManagerFactory.setJpaProperties(jpaVendorProperties());
		return entityManagerFactory;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		
		if (org.springframework.util.CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<>();
		}
		interceptors.add(new RestTemplateHeaderInterceptor());
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}
	
	private Properties jpaVendorProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
		properties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
		return properties;		
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return mapper;
	}
}
