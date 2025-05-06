package pizzashop.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentTest {

    @Test
    void testPaymentTableNumber() {
        Payment payment = new Payment(1, PaymentType.Cash, 50);
        assertEquals(1, payment.getTableNumber());
    }

    @Test
    void testPaymentType() {
        Payment payment = new Payment(1, PaymentType.Cash, 50);
        assertEquals(PaymentType.Cash, payment.getType());
    }

    @Test
    void testPaymentAmount() {
        Payment payment = new Payment(1, PaymentType.Cash, 50);
        assertEquals(50, payment.getAmount());
    }
}