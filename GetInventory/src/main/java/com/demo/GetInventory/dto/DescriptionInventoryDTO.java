package com.demo.GetInventory.dto;

import lombok.Data;

@Data
public class DescriptionInventoryDTO {
    private String serialNumber;
    private String status;
    private String inventoryType;
    private int inventoryId;
    private String resourceId;
    private int resourceInternalId;
    private int quantity;
    private String ITEM_NUMBER;
    private String inventory_model;
    private String part_item_desc;
}
