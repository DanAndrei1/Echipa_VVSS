package pizzashop.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PizzaServiceTest {

    @Test
    void testGetAllPizzasPayments() {

        PizzaService pizzaService = Mockito.mock(PizzaService.class);
        when(pizzaService.getMenuData()).thenReturn(null);
        doNothing().when(pizzaService).addPayment(1, PaymentType.Cash, 50);
        when(pizzaService.getTotalAmount(PaymentType.Cash)).thenReturn(50.0);

        assertEquals(50.0, pizzaService.getTotalAmount(PaymentType.Cash));
    }

    @Test
    void testGetMenuData() {
        PizzaService pizzaService = Mockito.mock(PizzaService.class);
        when(pizzaService.getMenuData()).thenReturn(null);
        doNothing().when(pizzaService).addPayment(1, PaymentType.Cash, 50);
        when(pizzaService.getPayments()).thenReturn(List.of(new Payment(1, PaymentType.Cash, 50)));
    }
}