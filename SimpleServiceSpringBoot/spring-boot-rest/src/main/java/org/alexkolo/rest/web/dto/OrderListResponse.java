package org.alexkolo.rest.web.dto;

import lombok.Data;

import java.util.List;
import java.util.ArrayList;

@Data
public class OrderListResponse {

    private List<OrderResponse> orders = new ArrayList<>();

}
