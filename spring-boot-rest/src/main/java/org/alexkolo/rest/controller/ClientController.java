package org.alexkolo.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.alexkolo.rest.mapper.ClientMapper;
import org.alexkolo.rest.model.Client;
import org.alexkolo.rest.model.dto.ClientListResponse;
import org.alexkolo.rest.model.dto.ClientResponse;
import org.alexkolo.rest.model.dto.UpsertClientRequest;
import org.alexkolo.rest.service.ClientService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
@Tag(name = "Client v1", description = "Client API version v1")
public class ClientController {

    private final ClientService clientServiceImpl;

    private final ClientMapper clientMapper;

    @Operation(
            summary = "Get clients",
            description = "Get all clients",
            tags = {"client"}
    )
    @GetMapping
    public ResponseEntity<ClientListResponse> findAll() {
        return ResponseEntity.ok(
                clientMapper.clientListToClientResponseList(clientServiceImpl.findAll()));
    }


    @Operation(
            summary = "Get client",
            description = "Get client by Id. Return id, name and list of orders",
            tags = {"client", "id"}
    )
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                clientMapper.clientToResponse(clientServiceImpl.findById(id)));
    }

    @Operation(
            summary = "Create client",
            description = "Create client by name. Return id , name and empty list of orders",
            tags = {"client"}
    )
    @PostMapping
    public ResponseEntity<ClientResponse> create(@RequestBody @Valid UpsertClientRequest request) {
        Client newClient = clientServiceImpl.save(clientMapper.requestToClient(request));
        return ResponseEntity.ok(
                clientMapper.clientToResponse(newClient));
    }

    @Operation(
            summary = "Change client",
            description = "Change client name by Id. Return id, name and list or orders",
            tags = {"client", "id"}
    )
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable("id") Long clientId,
                                                 @RequestBody @Valid UpsertClientRequest request) {
        Client updatedClient = clientServiceImpl
                .update(clientMapper.requestToClient(clientId, request));

        return ResponseEntity.ok(
                clientMapper.clientToResponse(updatedClient));
    }

    @Operation(
            summary = "Delete client",
            description = "Delete client by Id",
            tags = {"client", "id"}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientServiceImpl.deleteById(id);

        return ResponseEntity.ok(null);
    }

/*  сработает только для этого контроллера!
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> notFoundHandler(EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }*/

}
