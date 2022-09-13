package task2.exceptions;

public class WrongFromCurrencyException extends RuntimeException
{
    public WrongFromCurrencyException() {
    }

    public WrongFromCurrencyException(String message) {
        super(message);
    }
}
