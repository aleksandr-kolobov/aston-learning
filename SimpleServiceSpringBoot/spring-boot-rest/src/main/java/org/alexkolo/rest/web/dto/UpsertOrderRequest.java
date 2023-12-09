package org.alexkolo.rest.web.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpsertOrderRequest {

    private String product;

    private BigDecimal cost;

    private Long clientId;

}
