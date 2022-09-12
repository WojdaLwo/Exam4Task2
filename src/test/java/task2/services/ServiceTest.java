package task2.services;

import org.junit.Before;
import org.junit.Test;
import task2.exceptions.WrongCurrencyException;

import java.io.IOException;

import static org.junit.Assert.*;

public class ServiceTest {

    private Service service;

    @Before
    public void init() {
        service = new Service();
    }

    @Test(expected = WrongCurrencyException.class)
    public void shouldThrowWrongCurrenctException() throws IOException {
        service.exchange("ABC", "PLN", 1000);
    }

    @Test(expected = WrongCurrencyException.class)
    public void shouldThrowWrongCurrenctException2() throws IOException {
        service.exchange("PLN", "ABC", 1000);
    }

    @Test
    public void shouldReturnCorrectValueOfExchangedMoney() throws IOException {
        assertEquals(48.6, service.exchange("CHF", "PLN", 1000), 0.1);
    }

}