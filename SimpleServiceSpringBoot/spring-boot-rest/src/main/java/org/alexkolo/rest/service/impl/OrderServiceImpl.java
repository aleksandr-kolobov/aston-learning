package org.alexkolo.rest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alexkolo.rest.exception.EntityNotFoundException;
import org.alexkolo.rest.exception.UpdateStateException;
import org.alexkolo.rest.model.Client;
import org.alexkolo.rest.model.Order;
import org.alexkolo.rest.repository.ClientRepository;
import org.alexkolo.rest.repository.OrderRepository;
import org.alexkolo.rest.service.OrderService;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ClientRepository clientRepositoryImpl;

    private final OrderRepository orderRepositoryImpl;

    @Override
    public List<Order> findAll() {
        log.debug("Call findAll in OrderServiceImpl");

        return orderRepositoryImpl.findAll();
    }

    @Override
    public Order findById(Long id) {
        log.debug("Call findById in OrderServiceImpl " + id);

        return getOrder(id);
    }

    @Override
    public Order save(Order order) {
        log.debug("Call save in OrderServiceImpl " + order);

        Long clientId = order.getClient().getId();
        Client client = getClient(clientId);
        order = orderRepositoryImpl.save(order);//опасность цикличности
        client.addOrder(order);                //опасность цикличности
        order.setClient(client);               //опасность цикличности
        return order;
    }

    @Override
    public Order update(Order newOrder) {
        log.debug("Call update in OrderServiceImpl " + newOrder);

        Long id = newOrder.getId();
        Order existedOrder = getOrder(id);
        checkForUpdate(existedOrder);

        //BeanUtils.copyNonNullProperties(order, existedOrder);//тут все делаю
        long newOrderClientId = newOrder.getClient().getId();
        long existedOrderClientId = existedOrder.getClient().getId();
        if (newOrderClientId != existedOrderClientId) {
            Client newClient = getClient(newOrder.getClient().getId());
            Client existedClient = getClient(newOrder.getClient().getId());
            existedClient.removeOrder(id);
            newClient.addOrder(existedOrder);
        }
        existedOrder.setProduct(newOrder.getProduct());
        existedOrder.setCost(newOrder.getCost());
        existedOrder.setUpdateAt(Instant.now());

        return orderRepositoryImpl.update(existedOrder);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Call deleteById in OrderServiceImpl " + id);

        Order order = getOrder(id);
        Client client = getClient(order.getClient().getId());
        client.removeOrder(id);

        orderRepositoryImpl.deleteById(id);
    }

    @Override
    public void deleteByIdIn(List<Long> ids) {
        log.debug("Call deleteByIdIn in OrderServiceImpl " + ids);

        orderRepositoryImpl.deleteByIdIn(ids);
    }

    private void checkForUpdate(Order order) {
        if (Duration.between(order.getUpdateAt(), Instant.now()).getSeconds() > 120) {
            throw new UpdateStateException("Too late to update Order");
        }
    }

    private Order getOrder(Long id) {

        return orderRepositoryImpl.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                        .format("Order not found! ID: {0}", id)));
    }

    private Client getClient(Long id) {

        return clientRepositoryImpl.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                        .format("Client not found! ID: {0}", id)));
    }

}
