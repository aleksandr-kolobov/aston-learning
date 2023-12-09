package org.alexkolo.rest.repository.impl;

import org.alexkolo.rest.utils.BeanUtils;
import org.alexkolo.rest.model.Client;
import org.alexkolo.rest.model.Order;
import org.alexkolo.rest.repository.ClientRepository;
import org.alexkolo.rest.exception.EntityNotFoundException;

import lombok.extern.slf4j.Slf4j;

import org.alexkolo.rest.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Repository
public class InMemoryClientRepository implements ClientRepository {

    private OrderRepository orderRepositoryImpl;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepositoryImpl) {
        this.orderRepositoryImpl = orderRepositoryImpl;
    }

    private final Map<Long, Client> clients = new ConcurrentHashMap<>();

    private final AtomicLong currentId = new AtomicLong(1);

    @Override
    public List<Client> findAll() {
        log.debug("Call findAll InMemoryClientRepository");

        return new ArrayList<>(clients.values());
    }

    @Override
    public Optional<Client> findById(Long id) {
        log.debug("Call findById InMemoryClientRepository " + id);

        return Optional.ofNullable(clients.get(id));
    }

    @Override
    public Client save(Client client) {
        log.debug("Call save InMemoryClientRepository " + client);

        Long clientId = currentId.getAndIncrement();
        client.setId(clientId);
        clients.put(clientId, client);

        return client;
    }

    @Override
    public Client update(Client client) {
        log.debug("Call update InMemoryClientRepository " + client);

        Long clientId = client.getId();
        Client existedClient = clients.get(clientId);
        if (existedClient == null) {
            throw new EntityNotFoundException(MessageFormat
                    .format("Client not found! ID: {0}", clientId));
        }
        BeanUtils.copyNonNullProperties(client, existedClient);
        existedClient.setId(clientId);
        clients.put(clientId, existedClient);

        return existedClient;
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Call deleteById InMemoryClientRepository " + id);

        Client client = clients.get(id);
        if (client == null) {
            throw new EntityNotFoundException(MessageFormat
                    .format("Client not found! ID: {0}", id));
        }
        orderRepositoryImpl.deleteByIdIn(client.getOrders().stream().map(Order::getId).toList());
        clients.remove(id);
    }

}
