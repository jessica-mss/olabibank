package br.com.olabibank.olabibank.service;

import br.com.olabibank.olabibank.exception.ClienteException;
import br.com.olabibank.olabibank.model.entity.Cliente;
import br.com.olabibank.olabibank.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(UUID id) {
        return findByIdOrThrowException(id);
    }

    public Cliente create(Cliente clienteRequestBody) {
        boolean clienteExiste = clienteRepository.existsByCpf(clienteRequestBody.getCpf());
        if (clienteExiste) {
            throw new ClienteException.DuplicateClienteException();
        }
        if (verifyNameAndCPF(clienteRequestBody)) {
            return clienteRepository.save(clienteRequestBody);
        }
        throw new ClienteException.InvalidDataException();
    }

    public Cliente update(UUID id, Map<String, Object> requestBody) throws IllegalAccessException {
        Cliente cliente = this.findById(id);

        List<Field> camposDaModel = List.of(Cliente.class.getDeclaredFields());

        for (Field campo : camposDaModel) {
            campo.setAccessible(true);
            String nomeCampo = campo.getName();

            if (requestBody.containsKey(nomeCampo)) {
                if (nomeCampo.equals("cpf")) {
                    throw new ClienteException.InvalidDataException();
                }
                Object atualizacaoRequest = requestBody.get(nomeCampo);

                if (campo.getType().isAssignableFrom(atualizacaoRequest.getClass())) {
                    campo.set(cliente, atualizacaoRequest);
                } else {
                    throw new ClienteException.InvalidDataException();
                }
            }
        }

        return clienteRepository.save(cliente);
    }



    private Cliente findByIdOrThrowException(UUID id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException.ClienteNotFoundException());
    }

    private boolean verifyNameAndCPF(Cliente cliente) {
        return cliente.getNome() != null && !cliente.getNome().trim().isEmpty() &&
                cliente.getCpf() != null && !cliente.getCpf().trim().isEmpty();
    }
}
