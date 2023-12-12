package org.alexkolo.rest.web.controller.v1;

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
public class OrderController {

    private final OrderService orderServiceImpl;

    private final OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<OrderListResponse> findAll() {
        return ResponseEntity.ok(
                orderMapper.orderListToOrderListResponse(orderServiceImpl.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                orderMapper.orderToResponse(orderServiceImpl.findById(id)));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Valid UpsertOrderRequest request) {

        Order order = orderMapper.requestToOrder(request);
        Client client = new Client();//обходим стековерфлоу
        client.setId(request.getClientId());//обходим стековерфлоу
        order.setClient(client);//обходим стековерфлоу
        return ResponseEntity.ok(
                orderMapper.orderToResponse(orderServiceImpl.save(order)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable("id") Long orderId,
                                                 @RequestBody @Valid UpsertOrderRequest request) {
        Order updatedOrder = orderServiceImpl
                .update(orderMapper.requestToOrder(orderId, request));

        return ResponseEntity.ok(orderMapper.orderToResponse(updatedOrder));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> delete(@PathVariable Long id) {

        orderServiceImpl.deleteById(id);
        return ResponseEntity.ok(null);
    }

}
