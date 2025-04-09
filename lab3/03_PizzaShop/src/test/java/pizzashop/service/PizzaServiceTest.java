package pizzashop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PizzaServiceTest {

    private PizzaService service;
    private PaymentRepository testPayRepo;

    @BeforeEach
    void setUp() {
        testPayRepo = new PaymentRepository() {
            private final List<Payment> payments = new ArrayList<>();

            @Override
            public void add(Payment payment) {
                payments.add(payment);
            }

            @Override
            public List<Payment> getAll() {
                return payments;
            }
        };

        service = new PizzaService(null, testPayRepo);
    }

    @Test
    void getTotalAmount_validCardPayment() throws Exception {
        service.addPayment(1, PaymentType.Card, 34.5);
        double total = service.getTotalAmount(PaymentType.Card);
        assertEquals(34.5, total, 0.001, "Totalul pentru plata cu Card ar trebui să fie 34.5 lei");
    }

    @Test
    void getTotalAmount_validCashPayment() throws Exception {
        service.addPayment(2, PaymentType.Cash, 24.0); // 2 pizza x 12 lei
        double total = service.getTotalAmount(PaymentType.Cash);
        assertEquals(24.0, total, 0.001, "Totalul pentru plata cu Cash ar trebui să fie 24.0 lei");
    }

    @Test
    void getTotalAmount_invalidPaymentType() {
        Exception exception = assertThrows(Exception.class, () -> {
            String x = "CRYPTO";
            PaymentType type = x.equals("Card") ? PaymentType.Card :
                    x.equals("Cash") ? PaymentType.Cash : null;
            service.getTotalAmount(type);
        });

        assertEquals("PaymentType error", exception.getMessage());
    }

    @Test
    void getTotalAmount_invalidType_throwsException() {
        assertThrows(Exception.class, () -> {
            service.getTotalAmount(null); // simulăm un tip neacceptat
        });
    }

    @Test
    void getTotalAmount_listNull_returnsZero() throws Exception {
        // Modificăm PaymentRepository ca să returneze null
        service = new PizzaService(null, new PaymentRepository() {
            @Override public void add(Payment p) {}
            @Override public List<Payment> getAll() { return null; }
        });
        assertEquals(0.0, service.getTotalAmount(PaymentType.Card), 0.001);
    }

    @Test
    void getTotalAmount_listEmpty_returnsZero() throws Exception {
        assertEquals(0.0, service.getTotalAmount(PaymentType.Cash), 0.001);
    }

    @Test
    void getTotalAmount_noMatchingType_returnsZero() throws Exception {
        service.addPayment(1, PaymentType.Cash, 10.0);
        service.addPayment(2, PaymentType.Cash, 20.0);
        assertEquals(0.0, service.getTotalAmount(PaymentType.Card), 0.001);
    }

    @Test
    void getTotalAmount_multipleMatches_returnsCorrectSum() throws Exception {
        service.addPayment(1, PaymentType.Cash, 12.0);
        service.addPayment(2, PaymentType.Card, 25.0);
        service.addPayment(3, PaymentType.Cash, 3.0);
        assertEquals(15.0, service.getTotalAmount(PaymentType.Cash), 0.001);
    }

}
