package org.alexkolo.rest.web.controller.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.alexkolo.rest.mapper.v1.OrderMapper;
import org.alexkolo.rest.model.Client;
import org.alexkolo.rest.model.Order;
import org.alexkolo.rest.service.OrderService;
import org.alexkolo.rest.web.dto.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Tag(name = "Order v1", description = "Order API version v1")
public class OrderController {

    private final OrderService orderServiceImpl;

    private final OrderMapper orderMapper;

    @Operation(
            summary = "Get orders",
            description = "Get all orders",
            tags = {"order"}
    )
    @GetMapping
    public ResponseEntity<OrderListResponse> findAll() {
        return ResponseEntity.ok(
                orderMapper.orderListToOrderListResponse(orderServiceImpl.findAll()));
    }

    @Operation(
            summary = "Get order",
            description = "Get order by Id. Return product, cost and clientId",
            tags = {"order", "id"}
    )
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                orderMapper.orderToResponse(orderServiceImpl.findById(id)));
    }

    @Operation(
            summary = "Create order",
            description = "Create order. Return product, cost and clientId",
            tags = {"order"}
    )
    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Valid UpsertOrderRequest request) {

        Order order = orderMapper.requestToOrder(request);
        Client client = new Client();//обходим стековерфлоу
        client.setId(request.getClientId());//обходим стековерфлоу
        order.setClient(client);//обходим стековерфлоу
        return ResponseEntity.ok(
                orderMapper.orderToResponse(orderServiceImpl.save(order)));
    }

    @Operation(
            summary = "Change order",
            description = "Change order by Id. Return product, cost and clientId",
            tags = {"order", "id"}
    )
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable("id") Long orderId,
                                                 @RequestBody @Valid UpsertOrderRequest request) {
        Order updatedOrder = orderServiceImpl
                .update(orderMapper.requestToOrder(orderId, request));

        return ResponseEntity.ok(orderMapper.orderToResponse(updatedOrder));

    }

    @Operation(
            summary = "Delete order",
            description = "Delete order by Id.",
            tags = {"order", "id"}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> delete(@PathVariable Long id) {

        orderServiceImpl.deleteById(id);
        return ResponseEntity.ok(null);
    }

}
