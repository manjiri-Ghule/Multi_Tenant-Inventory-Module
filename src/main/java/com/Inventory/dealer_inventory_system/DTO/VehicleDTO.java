package com.Inventory.dealer_inventory_system.DTO;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.*;

@Getter @Setter
public class VehicleDTO {

    @SuppressWarnings("unused")
	private UUID id;
    @SuppressWarnings("unused")
	private String model;
    @SuppressWarnings("unused")
	private BigDecimal price;
    @SuppressWarnings("unused")
	private String status;
    @SuppressWarnings("unused")
	private Object dealerId;
	
}