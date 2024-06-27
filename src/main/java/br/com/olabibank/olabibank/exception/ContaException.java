package br.com.olabibank.olabibank.exception;

public class ContaException extends RuntimeException {

    public ContaException(String message) {
        super(message);
    }

    public static class DuplicateContaException extends ContaException{
        public DuplicateContaException(){
            super("Cliente já tem uma conta cadastrada");
        }
    }

    public static class ContaNotFoundException extends ContaException {
        public ContaNotFoundException() {
            super("Conta não encontrada");
        }
    }

    public static class InvalidValueException extends ContaException {
        public InvalidValueException() {
            super("O valor deve ser maior que zero.");
        }
    }
}
