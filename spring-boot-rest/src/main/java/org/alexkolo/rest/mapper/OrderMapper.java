package org.alexkolo.rest.mapper;

import lombok.RequiredArgsConstructor;
import org.alexkolo.rest.model.Order;
import org.alexkolo.rest.service.ClientService;
import org.alexkolo.rest.model.dto.OrderListResponse;
import org.alexkolo.rest.model.dto.OrderResponse;
import org.alexkolo.rest.model.dto.UpsertOrderRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final ClientService clientServiceImpl;

    public Order requestToOrder(UpsertOrderRequest request) {
        Order order = new Order();
        order.setProduct(request.getProduct());
        order.setCost(request.getCost());
        order.setClient(clientServiceImpl.findById(request.getClientId()));

        return order;
    }

    public Order requestToOrder(Long orderId, UpsertOrderRequest request) {
        Order order = requestToOrder(request);
        order.setId(orderId);

        return order;
    }

    public OrderResponse orderToResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId(order.getId());
        orderResponse.setCost(order.getCost());
        orderResponse.setProduct(order.getProduct());

        return orderResponse;
    }

    public List<OrderResponse> orderListToResponseList(List<Order> orders) {
        return orders.stream().map(this::orderToResponse).toList();
    }

    public OrderListResponse orderListToOrderListResponse(List<Order> orders) {
        OrderListResponse response = new OrderListResponse();
        response.setOrders(orderListToResponseList(orders));

        return response;
    }

}
