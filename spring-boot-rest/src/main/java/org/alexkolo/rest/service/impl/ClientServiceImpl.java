package org.alexkolo.rest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alexkolo.rest.exception.EntityNotFoundException;
import org.alexkolo.rest.model.Client;
import org.alexkolo.rest.model.Order;
import org.alexkolo.rest.repository.ClientRepository;
import org.alexkolo.rest.repository.OrderRepository;
import org.alexkolo.rest.service.ClientService;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepositoryImpl;

    private final OrderRepository orderRepositoryImpl;

    @Override
    public List<Client> findAll() {
        log.debug("Call findAll in ClientServiceImpl");

        return clientRepositoryImpl.findAll();
    }

    @Override
    public Client findById(Long id) {
        log.debug("Call findById in ClientServiceImpl " + id);

        return getClient(id);
    }

    @Override
    public Client save(Client client) {
        log.debug("Call save in ClientServiceImpl " + client);

        return clientRepositoryImpl.save(client);
    }

    @Override
    public Client update(Client client) {
        log.debug("Call update in ClientServiceImpl " + client);

        Long id = client.getId();
        Client existedClient = getClient(id);
        existedClient.setName(client.getName());

        return clientRepositoryImpl.update(existedClient);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Call deleteById in ClientServiceImpl " + id);

        Client client = getClient(id);

        orderRepositoryImpl.deleteByIdIn(client.getOrders().stream().map(Order::getId).toList());

        clientRepositoryImpl.deleteById(id);
    }

    private Client getClient(Long id) {

        return clientRepositoryImpl.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                        .format("Client not found! ID: {0}", id)));
    }

}
