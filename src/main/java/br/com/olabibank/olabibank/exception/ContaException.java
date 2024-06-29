package br.com.olabibank.olabibank.exception;

public class ContaException extends RuntimeException {

    public ContaException(String message) {
        super(message);
    }

    public static class ContasDuplicadasException extends ContaException{
        public ContasDuplicadasException(){
            super("Cliente já tem uma conta cadastrada");
        }
    }

    public static class ContaNaoEncontradaException extends ContaException {
        public ContaNaoEncontradaException() {
            super("Conta não encontrada");
        }
    }

    public static class ValorInvalidoException extends ContaException {
        public ValorInvalidoException() {
            super("O valor deve ser maior que zero.");
        }
    }

    public static class SaldoInsuficienteException extends ContaException {
        public SaldoInsuficienteException() {
            super("O saldo em conta é insuficiente para esta transação");
        }
    }
}
