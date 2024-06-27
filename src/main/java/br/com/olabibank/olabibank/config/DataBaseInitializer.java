package br.com.olabibank.olabibank.config;

import br.com.olabibank.olabibank.model.entity.Cliente;
import br.com.olabibank.olabibank.model.value.Endereco;
import br.com.olabibank.olabibank.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration

public class DataBaseInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataBaseInitializer.class);

//    private ClienteRepository repository;
//
//    public DataBaseInitializer(ClienteRepository repository) {
//        this.repository = repository;
//    }
//
//    static Endereco endereco1 = new Endereco("Rua das Flores", "123", "Cidade A", "Estado A", "12345-678");
//    static Endereco endereco2 = new Endereco("Avenida Central", "456", "Cidade B", "Estado B", "23456-789");
//    static Endereco endereco3 = new Endereco("Praça da Liberdade", "789", "Cidade C", "Estado C", "34567-890");
//    static Endereco endereco4 = new Endereco("Rua Nova", "101", "Cidade D", "Estado D", "45678-901");
//    static Endereco endereco5 = new Endereco("Avenida Paulista", "202", "Cidade E", "Estado E", "56789-012");
//
//    public static final List<Cliente> clientes = List.of(
//            new Cliente("João Silva", "123.456.789-00", "joao.silva@example.com", endereco1, 2000),
//            new Cliente("Maria Oliveira", "234.567.890-01", "maria.oliveira@example.com", endereco2, 6000),
//            new Cliente("Pedro Santos", "345.678.901-02", "pedro.santos@example.com", endereco3, 7000),
//            new Cliente("Ana Costa", "456.789.012-03", "ana.costa@example.com", endereco4, 8000),
//            new Cliente("Carlos Lima", "567.890.123-04", "carlos.lima@example.com", endereco5, 1500)
//    );


    @Override
    public void run(String... args) throws Exception {
//        repository.saveAll(clientes);
        log.info("Initialized database");
    }
}
