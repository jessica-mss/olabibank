package br.com.olabibank.olabibank.controller;

import br.com.olabibank.olabibank.exception.ClienteException;
import br.com.olabibank.olabibank.model.entity.Cliente;
import br.com.olabibank.olabibank.repository.ClienteRepository;
import br.com.olabibank.olabibank.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        log.info("Executing getAllClientes");

        List<Cliente> getAllClientes = clienteService.findAll();
        return new ResponseEntity<>(getAllClientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClienteById(@PathVariable UUID id) {
        log.info("Executing getClienteById");

        try {
            Cliente cliente = clienteService.findById(id);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (ClienteException.ClienteNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<Object> createCliente(@RequestBody Cliente clienteRequestBody) {
        log.info("Executing createCliente");

        try {
            clienteService.create(clienteRequestBody);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ClienteException.DuplicateClienteException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (ClienteException.InvalidDataException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ClienteException.ClienteNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable("id") UUID id, @RequestBody Map<String, Object> requestBody) {
        try {
            clienteService.update(id, requestBody);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ClienteException.ClienteNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ClienteException.InvalidDataException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (IllegalAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
