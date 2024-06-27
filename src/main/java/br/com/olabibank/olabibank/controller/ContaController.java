package br.com.olabibank.olabibank.controller;

import br.com.olabibank.olabibank.exception.ContaException;
import br.com.olabibank.olabibank.model.entity.Conta;
import br.com.olabibank.olabibank.service.ClienteService;
import br.com.olabibank.olabibank.service.ContaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    private static final Logger log = LoggerFactory.getLogger(ContaController.class);

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/{clienteId}/criar")
    public ResponseEntity<Conta> createConta(@PathVariable UUID clienteId, @RequestBody Conta contaRequestBody) {
        log.info("Executing createConta");

        try {
            contaService.create(clienteId, contaRequestBody);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ContaException.DuplicateContaException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getContaById(@PathVariable UUID id) {
        log.info("Executing getContaById");

        try {
            Conta conta = contaService.findById(id);
            return new ResponseEntity<>(conta, HttpStatus.OK);
        } catch (ContaException.ContaNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/depositar")
    public ResponseEntity<Object> depositar(@RequestParam UUID contaId, @RequestParam double valorDeposito) {
        log.info("Executing depositar");

        try {
            Conta conta = contaService.depositar(contaId, valorDeposito);
            return new ResponseEntity<>(conta, HttpStatus.OK);
        } catch (ContaException.ContaNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ContaException.InvalidValueException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sacar")
    public ResponseEntity<Object> sacar(@RequestParam UUID contaId, @RequestParam double valorSaque) {
        log.info("Executing sacar");

        try {
            contaService.sacar(contaId, valorSaque);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ContaException.ContaNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ContaException.InvalidValueException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/transferir")
    public ResponseEntity<Object> transferir(@RequestParam UUID contaOrigemId, @RequestParam UUID contaDestinoId, @RequestParam double valorTransferencia) {
        log.info("Executing transferir");

        try {
            contaService.transferir(contaOrigemId, contaDestinoId, valorTransferencia);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ContaException.ContaNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ContaException.InvalidValueException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/pagar")
    public ResponseEntity<Object> pagar(@RequestParam UUID contaId, @RequestParam double valorPagamento) {
        log.info("Executing pagar");

        try {
            contaService.pagar(contaId, valorPagamento);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ContaException.ContaNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ContaException.InvalidValueException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/saldo/{contaId}")
    public ResponseEntity<Object> getSaldo(@PathVariable UUID contaId) {
        log.info("Executing getSaldo");

        try {
            double saldo = contaService.getSaldo(contaId);
            return new ResponseEntity<>(saldo, HttpStatus.OK);
        } catch (ContaException.ContaNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{contaId}")
    public ResponseEntity<Object> atualizarConta(@PathVariable UUID contaId, @RequestBody Map<String, Object> requestBody) {
        log.info("Executing atualizarConta");

        try {
            contaService.atualizarConta(contaId, requestBody);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ContaException.ContaNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
