package com.mail.sender.configurations;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class AppConfig {
//	implements WebMvcConfigurer
//	 @Override
//	  public void addCorsMappings(CorsRegistry registry) {
//	       registry.addMapping("/**")
//	       .allowedOrigins("https://finytive-lyn4.vercel.app")
//	       .allowedMethods("GET", "POST", "PUT", "DELETE")
//	       .allowCredentials(true);
//	  }
	
	@Value("${spring.mail.username}")
	private String to;
	
	@Value("${spring.mail.password}")
	private String pass;
	
	@Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setUsername(to);
        sender.setPassword(pass);

        Properties props = sender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return sender;
    }
}
