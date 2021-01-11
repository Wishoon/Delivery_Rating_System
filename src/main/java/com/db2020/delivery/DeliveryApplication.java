package com.db2020.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.Locale;

@SpringBootApplication
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002", "http://192.168.64.4:3000", "http://192.168.64.4:3002"}, allowCredentials = "true")
public class DeliveryApplication implements WebMvcConfigurer{

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    public static void main(String[] args) {
        SpringApplication.run(DeliveryApplication.class, args);
    }

    /*
     * 세션 및 쿠키 Locale 설정
     */
    @Bean
    public LocaleResolver localeResolver() {
//		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREA);
        return localeResolver;
    }
    /*
     * 크로스 도메인
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000", "http://localhost:3002")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers")
//                .exposedHeaders("Authorization")
//                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
//                .allowCredentials(true).maxAge(10);
//    }
}
