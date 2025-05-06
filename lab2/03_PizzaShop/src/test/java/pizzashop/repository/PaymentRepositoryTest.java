package pizzashop.repository;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    @Disabled
    void testOnePlusOne() {
        assertEquals(2, 1+1);
    }

    @Test
    void testAddPayment() {
        PaymentRepository mockPaymentRepository = mock(PaymentRepository.class);
        Payment payment = new Payment(1, PaymentType.Cash, 50);
        doNothing().when(mockPaymentRepository).add(payment);

        assertEquals(50, mockPaymentRepository.getAll().get(mockPaymentRepository.getAll().size()-1).getAmount());
    }

    @Test
    void testGetPayment() {
        PaymentRepository mockPaymentRepository = mock(PaymentRepository.class);
        Payment payment = new Payment(1, PaymentType.Cash, 50);
        doNothing().when(mockPaymentRepository).add(payment);

        assertEquals(50, mockPaymentRepository.getAll().get(mockPaymentRepository.getAll().size()-1).getAmount());
    }

    @DisplayName("First BVA test")
    @Test
    @Tag("BVA")
    void TestBva1()
    {
        paymentRepository.add(new Payment(4,PaymentType.Card,50));
        assertEquals(4, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getTableNumber());
        assertEquals(50, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getAmount());
    }

    @Test
    @Tag("BVA")
    void TestBva2() {
        assertThrows(Exception.class, () -> paymentRepository.add(new Payment(9, PaymentType.Cash, 50)));
    }

    @Test
    @Tag("BVA")
    void TestBva3() {
        paymentRepository.add(new Payment(4, PaymentType.Cash, 50));
        assertEquals(4, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getTableNumber());
        assertEquals(50, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getAmount());
    }

    @Test
    @Tag("BVA")
    void TestBva4() {
         assertThrows(Exception.class, () -> paymentRepository.add(new Payment(4, PaymentType.Cash, 1000.01)));
    }

    @Test
    @Tag("ECP")
    void TestEcp1() {
        paymentRepository.add(new Payment(1,PaymentType.Cash,22.2));
        assertEquals(1, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getTableNumber());
        assertEquals(22.2, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getAmount());
    }

    @Test
    @Tag("ECP")
    void TestEcp2() {
        paymentRepository.add(new Payment(4, PaymentType.Card, 100.5));
        assertEquals(4, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getTableNumber());
        assertEquals(100.5, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getAmount());
    }

    @Test
    @Tag("ECP")
    void TestEcp3And4() {

        @Nested
        class TestEcp3and4 {
            @Test
            void TestEcp3() {
                assertThrows(Exception.class, () -> paymentRepository.add(new Payment(9, PaymentType.Cash, 50)));
            }

            @Test
            void TestEcp4() {
                assertThrows(Exception.class, () -> paymentRepository.add(new Payment(4, PaymentType.Card, 1000.01)));
            }
        }

        TestEcp3and4 testEcp3and4 = new TestEcp3and4();
        testEcp3and4.TestEcp3();
        testEcp3and4.TestEcp4();
    }
}