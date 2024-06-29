package br.com.olabibank.olabibank.repository;

import br.com.olabibank.olabibank.model.entity.Cliente;
import br.com.olabibank.olabibank.model.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContaRepository extends JpaRepository<Conta, UUID> {

}
