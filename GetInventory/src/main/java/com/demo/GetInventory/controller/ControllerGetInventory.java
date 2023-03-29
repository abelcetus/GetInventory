package com.demo.GetInventory.controller;

import com.demo.GetInventory.dto.DescriptionInventoryDTO;
import com.demo.GetInventory.dto.NotFoundDTO;
import com.demo.GetInventory.dto.TransferDTO;
import com.demo.GetInventory.service.ServiceGetInventory;
import com.demo.GetInventory.util.ResponseEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ControllerGetInventory {

    @Autowired
    ServiceGetInventory serviceGetInventory;

    DescriptionInventoryDTO descriptionInventoryDTO;
    @GetMapping("/getinventory/{idInventory}")
    public ResponseEntity getInventory(@PathVariable("idInventory") String idInventory) throws JsonProcessingException {
        TransferDTO transferDTO;
        NotFoundDTO nmotFoundDTO;
        log.info("idInventory = "+idInventory);
        if (idInventory != null){
            transferDTO = serviceGetInventory.getInventory(idInventory);
            if (transferDTO.getMessage() == ResponseEnum.RESPONSE_OK.getMessage()){
                return  new ResponseEntity<>(transferDTO.getDescriptionInventoryDTO(),HttpStatus.OK);
            }else {
                log.info(ResponseEnum.RESPONSE_NOT_FOUND.getMessage());
                //return ResponseEntity.badRequest().body(transferDTO.getNotFoundDTO());
                return  new ResponseEntity<String>(transferDTO.getNotFoundDTO(), HttpStatus.NOT_FOUND);
            }

        }else{
            log.info(ResponseEnum.RESPONSE_ERROR.getMessage());
            return  new ResponseEntity(ResponseEnum.RESPONSE_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
