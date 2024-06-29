package br.com.olabibank.olabibank.model.entity;


import br.com.olabibank.olabibank.model.enums.TipoConta;
import br.com.olabibank.olabibank.util.ContaNumeroGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import java.util.UUID;

@Entity
public class Conta {

    @Id
    @UuidGenerator
    private UUID id;

    private TipoConta tipo;
    private String agencia;
    private String numeroConta;
    private double saldo;
    private int saquesGratuitos;

    @OneToOne
    @JoinColumn(name = "clienteId")
    @JsonBackReference
    @JsonPropertyOrder({ "id", "tipo", "agencia", "numeroConta", "saldo", "saquesGratuitos", "cliente" })
    private Cliente cliente;

    public Conta() {}

    public Conta(TipoConta tipo, String agencia, String numeroConta, double saldo, int saquesGratuitos, Cliente cliente) {
        this.tipo = tipo;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.saquesGratuitos = saquesGratuitos;
        this.cliente = cliente;
    }

    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.tipo = cliente.getRendaSalarial() >= 3500 ? TipoConta.CORRENTE : TipoConta.PAGAMENTO;
        this.numeroConta = ContaNumeroGenerator.gerarNumeroConta();
        this.saldo = 0;
        this.saquesGratuitos = 4;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public TipoConta getTipo() {
        return tipo;
    }

    public void setTipo(TipoConta tipo) {
        this.tipo = tipo;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getSaquesGratuitos() {
        return saquesGratuitos;
    }

    public void setSaquesGratuitos(int saquesGratuitos) {
        this.saquesGratuitos = saquesGratuitos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
