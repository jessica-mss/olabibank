package br.com.olabibank.olabibank.model.entity;

import br.com.olabibank.olabibank.model.value.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import java.util.UUID;

@Entity
@Table(
        name = "cliente",
        uniqueConstraints = @UniqueConstraint(columnNames = "cpf")
)
@JsonPropertyOrder({ "clienteId", "nome", "cpf", "email", "endereco", "rendaSalarial", "conta" })
public class Cliente {

    @Id
    @UuidGenerator
    private UUID clienteId;

    private String nome;

    @Column(nullable = false)
    private String cpf;
    private String email;

    @Embedded
    private Endereco endereco;
    private double rendaSalarial;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"nome", "cpf", "email", "endereco", "rendaSalarial", "conta"})
    private Conta conta;

    public Cliente() {}

    public Cliente(String nome, String cpf, String email, Endereco endereco, double rendaSalarial, Conta conta) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
        this.rendaSalarial = rendaSalarial;
        this.conta = conta;
    }

    public Cliente(String nome, String cpf, String email, Endereco endereco, double rendaSalarial) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
        this.rendaSalarial = rendaSalarial;
    }


    public UUID getId() {
        return clienteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public double getRendaSalarial() {
        return rendaSalarial;
    }

    public void setRendaSalarial(double rendaSalarial) {
        this.rendaSalarial = rendaSalarial;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
