package com.demo.GetInventory.service;

import com.demo.GetInventory.configuration.ApiConfigurationProperties;
import com.demo.GetInventory.dto.DescriptionInventoryDTO;
import com.demo.GetInventory.dto.NotFoundDTO;
import com.demo.GetInventory.dto.TransferDTO;
import com.demo.GetInventory.util.ResponseEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
@Slf4j
public class ServiceGetInventory {

    @Autowired
    private ApiConfigurationProperties apiConfigurationProperties;

    private String endPoint;
    private ObjectMapper objectMapper;
    public TransferDTO getInventory(String idInventory) {

        TransferDTO transferDTO = new TransferDTO();
        DescriptionInventoryDTO descriptionInventoryDTO;
        NotFoundDTO notFoundDTO;

        String credencialesStringBase64;
        credencialesStringBase64 = getEncodeBase64InString(apiConfigurationProperties.getUserName(), apiConfigurationProperties.getPassword());
        log.info("Credenciales: " + credencialesStringBase64);
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Basic " + credencialesStringBase64);
        HttpEntity<String> requestHeader = new HttpEntity<String>(header);
        RestTemplate restTemplate = new RestTemplate();
        endPoint = apiConfigurationProperties.getEndPointInventory().replaceAll("INVENTORY_ID",idInventory);
        log.info("End Point "+endPoint);
        try{
            ResponseEntity<DescriptionInventoryDTO> descriptionInventoryDTOResponseEntity =  restTemplate.exchange(endPoint,HttpMethod.GET,requestHeader,DescriptionInventoryDTO.class);

            descriptionInventoryDTO = descriptionInventoryDTOResponseEntity.getBody();
            transferDTO.setDescriptionInventoryDTO(descriptionInventoryDTO);
            transferDTO.setMessage(ResponseEnum.RESPONSE_OK.getMessage());
            return  transferDTO;
        } catch (HttpClientErrorException e) {
            log.info(e.getResponseBodyAsString());
            transferDTO.setNotFoundDTO(e.getResponseBodyAsString());
            return  transferDTO;
        }
    }


    public static String getEncodeBase64InString(String userName, String password){
        String credenciales;
        byte[] credencialesByte;
        byte[] byteBase64;
        String credencialesStringBase64;

        if (userName != null && password != null){
            credenciales = userName + ":" + password;
            credencialesByte = credenciales.getBytes();
            byteBase64 = Base64.getEncoder().encode(credencialesByte);
            credencialesStringBase64 = new String(byteBase64);
        }else {
            credencialesStringBase64 = ResponseEnum.RESPONSE_ERROR_CREDENTIAL.getMessage();
        }

        return  credencialesStringBase64;

    }

}
