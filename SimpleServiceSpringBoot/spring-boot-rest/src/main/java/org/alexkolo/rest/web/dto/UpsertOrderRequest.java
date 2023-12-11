package org.alexkolo.rest.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertOrderRequest {

    private String product;

    private BigDecimal cost;

    private Long clientId;

}
