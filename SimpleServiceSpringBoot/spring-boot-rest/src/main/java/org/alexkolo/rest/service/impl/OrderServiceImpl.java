package org.alexkolo.rest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alexkolo.rest.exception.EntityNotFoundException;
import org.alexkolo.rest.exception.UpdateStateException;
import org.alexkolo.rest.model.Order;
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

    private final OrderRepository orderRepositoryImpl;
    @Override
    public List<Order> findAll() {
        log.debug("Call findAll in OrderServiceImpl");

        return orderRepositoryImpl.findAll();
    }

    @Override
    public Order findById(Long id) {
        log.debug("Call findById in OrderServiceImpl " + id);

        return orderRepositoryImpl.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                        .format("Order not found! ID: {0}", id)));
    }

    @Override
    public Order save(Order order) {
        log.debug("Call save in OrderServiceImpl " + order);

        return orderRepositoryImpl.save(order);
    }

    @Override
    public Order update(Order order) {
        log.debug("Call update in OrderServiceImpl " + order);

        checkForUpdate(order);
        return orderRepositoryImpl.update(order);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Call deleteById in OrderServiceImpl " + id);

        Order existedOrder = findById(id);
        existedOrder.getClient().removeOrder(id);
        orderRepositoryImpl.deleteById(id);
    }

    @Override
    public void deleteByIdIn(List<Long> ids) {
        log.debug("Call deleteByIdIn in OrderServiceImpl " + ids);

        orderRepositoryImpl.deleteByIdIn(ids);
    }

    private void checkForUpdate(Order order) {
        if (Duration.between(order.getUpdateAt(), Instant.now()).getSeconds() > 5) {
            throw new UpdateStateException("Too late to update Order");
        }
    }

}
