package com.example.labxspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurOAUTH2Github {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests().anyRequest().authenticated().and().oauth2Login();
        return http.build();
    }

//    @Bean
//    public ClientRegistrationRepository clientrepo (){
//        ClientRegistration cliReg = clientRegistration();
//        return new InMemoryClientRegistrationRepository(cliReg);
//    }
//
//    private ClientRegistration clientRegistration() {
//        return CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("cc6ad9be6fbfb68f17b4")
//                .clientSecret("54831dd789a77a923f98355ffbda302ca7aae843").build();
//    }
}
