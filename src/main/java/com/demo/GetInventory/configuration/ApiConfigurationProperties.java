package com.demo.GetInventory.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@EnableConfigurationProperties
@ConfigurationProperties(prefix ="service.rest.puntonet")
@Component
public class ApiConfigurationProperties {

    //Data Microservice
    private  String endPointInventory;
    private String userName;
    private String password;


}
