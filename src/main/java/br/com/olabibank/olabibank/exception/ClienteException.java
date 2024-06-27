package br.com.olabibank.olabibank.exception;

public class ClienteException extends RuntimeException {

    public ClienteException(String message) {
        super(message);
    }

    public static class DuplicateClienteException extends ClienteException{
        public DuplicateClienteException(){
            super("Cliente já cadastrado");
        }
    }

    public static class ClienteNotFoundException extends ClienteException {
        public ClienteNotFoundException() {
            super("Cliente não encontrado");
        }
    }

    public static class InvalidDataException extends ClienteException {
        public InvalidDataException() {
            super("Dados inputados não compatíveis com os tipos solicitados");
        }
    }
}
