package com.projetoFinalIot.projetoFinal.config;

import com.projetoFinalIot.projetoFinal.services.DevProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class ProfileConfig {

    @Autowired
    private DevProfileService devProfileService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String dll;

    @Bean
    public boolean dbInstance(){
        if(dll.equals("create")){
            this.devProfileService.dbInstance();
        }
        return false;
    }
}
