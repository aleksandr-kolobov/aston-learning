package org.alexkolo.rest.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

    private Long id;

    private String name;

    private List<OrderResponse> orders;

}
