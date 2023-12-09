package org.alexkolo.rest.web.dto;

import lombok.Data;

import java.util.List;
import java.util.ArrayList;

@Data
public class ClientResponse {

    private Long id;

    private String name;

    private List<OrderResponse> orders = new ArrayList<>();

}
