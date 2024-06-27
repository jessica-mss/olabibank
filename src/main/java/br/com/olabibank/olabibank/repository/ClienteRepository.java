package br.com.olabibank.olabibank.repository;


import br.com.olabibank.olabibank.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    @Query("SELECT COUNT(c) > 0 FROM Cliente c WHERE c.cpf = :cpf")
    boolean existsByCpf(@Param("cpf") String cpf);

//    boolean existsByCPF(String cpf);
}
