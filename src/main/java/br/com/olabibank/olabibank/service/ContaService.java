package br.com.olabibank.olabibank.service;

import br.com.olabibank.olabibank.exception.ClienteException;
import br.com.olabibank.olabibank.exception.ContaException;
import br.com.olabibank.olabibank.model.entity.Cliente;
import br.com.olabibank.olabibank.model.entity.Conta;
import br.com.olabibank.olabibank.model.enums.TipoConta;
import br.com.olabibank.olabibank.repository.ClienteRepository;
import br.com.olabibank.olabibank.repository.ContaRepository;
import br.com.olabibank.olabibank.util.ContaNumeroGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    public Conta create(UUID clienteId, Conta contaRequestBody) {
        Cliente cliente = findClienteByIdOrThrowException(clienteId);
        if (cliente != null) {
            Conta conta = new Conta(cliente);
            conta.setAgencia(contaRequestBody.getAgencia());
            cliente.setConta(conta);
            conta.setCliente(cliente);
            return contaRepository.save(conta);
        }
        throw new ContaException.DuplicateContaException();
    }

    public Conta findById (UUID id){
        return findContaByIdOrThrowException(id);
    }

    public Conta depositar (UUID contaId,double valorDeposito){

        validarValorPositivo(valorDeposito);

        Conta conta = findContaByIdOrThrowException(contaId);
        double novoSaldo = conta.getSaldo() + valorDeposito;
        conta.setSaldo(novoSaldo);
        return contaRepository.save(conta);

    }

    public Conta sacar(UUID contaId, double valorSaque) {

        validarValorPositivo(valorSaque);

        Conta conta = findContaByIdOrThrowException(contaId);

        validarSaldoSuficiente(conta.getSaldo(), valorSaque);

            double novoSaldo = conta.getSaldo() - valorSaque;
            conta.setSaldo(novoSaldo);

            if (conta.getSaquesGratuitos() > 0) {
                conta.setSaquesGratuitos(conta.getSaquesGratuitos() - 1);
            }
            return contaRepository.save(conta);

    }

    public void transferir(UUID contaOrigemId, UUID contaDestinoId, double valorTransferencia) {
        validarValorPositivo(valorTransferencia);

        Conta contaOrigem = findContaByIdOrThrowException(contaOrigemId);
        Conta contaDestino = findContaByIdOrThrowException(contaDestinoId);

        validarSaldoSuficiente(contaOrigem.getSaldo(), valorTransferencia);

        double novoSaldoOrigem = contaOrigem.getSaldo() - valorTransferencia;
        contaOrigem.setSaldo(novoSaldoOrigem);

        double novoSaldoDestino = contaDestino.getSaldo() + valorTransferencia;
        contaDestino.setSaldo(novoSaldoDestino);

        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);
    }

    public Conta pagar(UUID contaId, double valorPagamento) {

        validarValorPositivo(valorPagamento);

        Conta conta = findContaByIdOrThrowException(contaId);

        validarSaldoSuficiente(conta.getSaldo(), valorPagamento);

        double novoSaldo = conta.getSaldo() - valorPagamento;
        conta.setSaldo(novoSaldo);

        return contaRepository.save(conta);
    }

    public double getSaldo(UUID contaId) {
        Conta conta = findContaByIdOrThrowException(contaId);
        return conta.getSaldo();
    }

    public Conta atualizarConta(UUID contaId, Map<String, Object> requestBody) throws IllegalAccessException {
        Conta contaExistente = findContaByIdOrThrowException(contaId);
        List<Field> camposDaModel = List.of(contaExistente.getClass().getDeclaredFields());
        for (Field item : camposDaModel) {
            item.setAccessible(true);
            String itemName = item.getName();

            if (requestBody.containsKey(itemName)) {
                Object requestToUpdate = requestBody.get(itemName);
                item.set(contaExistente, requestToUpdate);
            }
        }
        return contaRepository.save(contaExistente);

    }



    private Cliente findClienteByIdOrThrowException (UUID id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException.ClienteNotFoundException());
    }

    private Conta findContaByIdOrThrowException (UUID id){
        return contaRepository.findById(id)
                .orElseThrow(() -> new ContaException.ContaNotFoundException());
    }

    private void validarValorPositivo(double valor) {
        if (valor <= 0) {
            throw new ContaException.InvalidValueException();
        }
    }

    private void validarSaldoSuficiente(double saldo, double valor) {
        if (saldo < valor) {
            throw new ContaException.InvalidValueException();
        }
    }
}
