package org.alexkolo.rest.repository.impl;

import org.alexkolo.rest.utils.BeanUtils;
import org.alexkolo.rest.model.Client;
import org.alexkolo.rest.model.Order;
import org.alexkolo.rest.repository.ClientRepository;
import org.alexkolo.rest.repository.OrderRepository;
import org.alexkolo.rest.exception.EntityNotFoundException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private ClientRepository clientRepositoryImpl;

    @Autowired
    public void setClientRepository(ClientRepository clientRepositoryImpl) {
        this.clientRepositoryImpl = clientRepositoryImpl;
    }

    private final Map<Long, Order> orders = new ConcurrentHashMap<>();

    private final AtomicLong currentId = new AtomicLong(1);

    @Override
    public List<Order> findAll() {
        log.debug("Call findAll InMemoryOrderRepository");

        return new ArrayList<>(orders.values());
    }

    @Override
    public Optional<Order> findById(Long id) {
        log.debug("Call findById InMemoryOrderRepository " + id);

        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public Order save(Order order) {
        log.debug("Call save InMemoryTaskRepository " + order);

        Long orderId = currentId.getAndIncrement();
        Long clientId = order.getClient().getId();
        Client client = clientRepositoryImpl.findById(clientId)
                    .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                                .format("Client not found! ID: {0}", clientId)));
        order.setClient(client);
        order.setId(orderId);
        Instant now = Instant.now();
        order.setCreateAt(now);
        order.setUpdateAt(now);
        orders.put(orderId, order);
        client.addOrder(order);
        clientRepositoryImpl.update(client);

        return order;
    }

    @Override
    public Order update(Order order) {
        log.debug("Call update InMemoryOrderRepository " + order);

        Long orderId = order.getId();
        Order existedOrder = orders.get(orderId);
        if (existedOrder == null) {
            throw new EntityNotFoundException(MessageFormat
                    .format("Order not found! ID: {0}", orderId));
        }
        BeanUtils.copyNonNullProperties(order, existedOrder);
        existedOrder.setId(orderId);
        existedOrder.setUpdateAt(Instant.now());
        orders.put(orderId, existedOrder);

        return existedOrder;
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Call deleteById InMemoryOrderRepository " + id);

        orders.remove(id);
    }

    @Override
    public void deleteByIdIn(List<Long> ids) {
        log.debug("Call deleteByIdIn InMemoryOrderRepository " + ids);

        ids.forEach(orders::remove);
    }

}
