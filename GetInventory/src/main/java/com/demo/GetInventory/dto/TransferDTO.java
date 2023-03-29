package com.demo.GetInventory.dto;

import lombok.Data;
import java.util.List;
@Data
public class TransferDTO {

    private String message;
    private  DescriptionInventoryDTO  descriptionInventoryDTO;
    private String notFoundDTO;

}
