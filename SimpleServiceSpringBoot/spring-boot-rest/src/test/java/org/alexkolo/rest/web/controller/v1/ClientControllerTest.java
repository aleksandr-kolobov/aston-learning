package org.alexkolo.rest.web.controller.v1;

import org.alexkolo.rest.AbstractTestController;
import org.alexkolo.rest.StringTestUtils;
import org.alexkolo.rest.exception.EntityNotFoundException;
import org.alexkolo.rest.mapper.v1.ClientMapper;
import org.alexkolo.rest.model.Client;
import org.alexkolo.rest.model.Order;
import org.alexkolo.rest.service.ClientService;
import org.alexkolo.rest.web.dto.ClientListResponse;
import org.alexkolo.rest.web.dto.ClientResponse;
import org.alexkolo.rest.web.dto.OrderResponse;
import org.alexkolo.rest.web.dto.UpsertClientRequest;

import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

public class ClientControllerTest extends AbstractTestController {

    @MockBean
    private ClientService clientServiceImpl;

    @MockBean
    private ClientMapper clientMapper;

    @Test
    public void whenFindAll_thenReturnAllClients() throws Exception {

        List<Client> clients = new ArrayList<>();
        clients.add(createClient(1L, null));
        Order order = createOrder(1L, 100L, null);
        clients.add(createClient(2L, order));
        List<ClientResponse> clientResponses = new ArrayList<>();
        clientResponses.add(createClientResponse(1L, null));
        OrderResponse orderResponse = createOrderResponse(1L, 100L);
        clientResponses.add(createClientResponse(2L, orderResponse));
        ClientListResponse clientListResponse = new ClientListResponse(clientResponses);

        Mockito.when(clientServiceImpl.findAll())
                .thenReturn(clients);
        Mockito.when(clientMapper.clientListToClientResponseList(clients))
                .thenReturn(clientListResponse);

        String actualResponse = mockMvc.perform(get("/api/v1/clients"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource(
                "response/find_all_clients_response.json");

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);

        Mockito.verify(clientServiceImpl, Mockito.times(1)).findAll();
        Mockito.verify(clientMapper, Mockito.times(1))
                .clientListToClientResponseList(clients);

    }

    @Test
    public void whenGetClientById_thenReturnClientById() throws Exception {

        Client client = createClient(1L, null);
        ClientResponse clientResponse = createClientResponse(1L, null);

        Mockito.when(clientServiceImpl.findById(1L)).thenReturn(client);
        Mockito.when(clientMapper.clientToResponse(client)).thenReturn(clientResponse);

        String actualResponse = mockMvc.perform(get("/api/v1/clients/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource(
                "response/find_client_by_id_response.json");

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);

        Mockito.verify(clientServiceImpl, Mockito.times(1)).findById(1L);
        Mockito.verify(clientMapper, Mockito.times(1))
                .clientToResponse(client);

    }

    @Test
    public void whenCreateClient_thenReturnNewClient() throws Exception {

        Client client = new Client();
        client.setName("Client 1");
        Client createdClient = createClient(1L, null);
        ClientResponse clientResponse = createClientResponse(1L, null);
        UpsertClientRequest request = new UpsertClientRequest("Client 1");

        Mockito.when(clientServiceImpl.save(client)).thenReturn(createdClient);
        Mockito.when(clientMapper.requestToClient(request)).thenReturn(client);
        Mockito.when(clientMapper.clientToResponse(createdClient)).thenReturn(clientResponse);

        String actualResponse = mockMvc.perform(post("/api/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource(
                "response/create_client_response.json");

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);

        Mockito.verify(clientServiceImpl, Mockito.times(1)).save(client);
        Mockito.verify(clientMapper, Mockito.times(1))
                .requestToClient(request);
        Mockito.verify(clientMapper, Mockito.times(1))
                .clientToResponse(createdClient);

    }

    @Test
    public void whenUpdateClientById_thenReturnUpdatedClient() throws Exception {

        UpsertClientRequest request = new UpsertClientRequest("New Client 1");
        Client updatedClient = new Client(1L, "New Client 1", new ArrayList<>());
        ClientResponse clientResponse = new ClientResponse(1L, "New Client 1", new ArrayList<>());

        Mockito.when(clientServiceImpl.update(updatedClient)).thenReturn(updatedClient);
        Mockito.when(clientMapper.requestToClient(1L, request)).thenReturn(updatedClient);
        Mockito.when(clientMapper.clientToResponse(updatedClient)).thenReturn(clientResponse);

        String actualResponse = mockMvc.perform(put("/api/v1/clients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource(
                "response/update_client_response.json");

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);

        Mockito.verify(clientServiceImpl, Mockito.times(1)).update(updatedClient);
        Mockito.verify(clientMapper, Mockito.times(1))
                .requestToClient(1L, request);
        Mockito.verify(clientMapper, Mockito.times(1))
                .clientToResponse(updatedClient);

    }


    @Test
    public void whenDeleteClientById_thenReturnStatus() throws Exception {

        mockMvc.perform(delete("/api/v1/clients/1"))
                .andExpect(status().isOk());

        String expectedResponse = StringTestUtils.readStringFromResource(
                "response/update_client_response.json");

        Mockito.verify(clientServiceImpl, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void whenGetNoExistedClientById_thenReturnError() throws Exception {

        Mockito.when(clientServiceImpl.findById(100L)).thenThrow(
                new EntityNotFoundException("Client not found! ID: 100"));

        String actualResponse = mockMvc.perform(get("/api/v1/clients/100"))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource(
                "response/client_by_id_not_found_response.json");

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);

        Mockito.verify(clientServiceImpl, Mockito.times(1)).findById(100L);

    }




}
