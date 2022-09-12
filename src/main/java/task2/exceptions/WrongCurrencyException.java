package task2.exceptions;

public class WrongCurrencyException extends RuntimeException
{
    public WrongCurrencyException() {
    }

    public WrongCurrencyException(String message) {
        super(message);
    }
}
