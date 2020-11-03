package bank;

public class IllegalCreditException extends Exception {
    public IllegalCreditException(String message) {
        super(message);
    }
}
