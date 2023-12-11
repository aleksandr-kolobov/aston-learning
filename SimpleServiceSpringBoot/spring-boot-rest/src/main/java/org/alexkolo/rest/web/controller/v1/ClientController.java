package org.alexkolo.rest.web.controller.v1;

import lombok.RequiredArgsConstructor;

import org.alexkolo.rest.mapper.v1.ClientMapper;
import org.alexkolo.rest.model.Client;
import org.alexkolo.rest.service.ClientService;
import org.alexkolo.rest.web.dto.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientServiceImpl;

    private final ClientMapper clientMapper;

    @GetMapping
    public ResponseEntity<ClientListResponse> findAll() {
        return ResponseEntity.ok(
                clientMapper.clientListToClientResponseList(clientServiceImpl.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                clientMapper.clientToResponse(clientServiceImpl.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create(@RequestBody UpsertClientRequest request) {
        Client newClient = clientServiceImpl.save(clientMapper.requestToClient(request));
        return ResponseEntity.ok(
                clientMapper.clientToResponse(newClient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable("id") Long clientId,
                                                 @RequestBody UpsertClientRequest request) {
        Client updatedClient = clientServiceImpl
                .update(clientMapper.requestToClient(clientId, request));

        return ResponseEntity.ok(
                clientMapper.clientToResponse(updatedClient));
    }

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
