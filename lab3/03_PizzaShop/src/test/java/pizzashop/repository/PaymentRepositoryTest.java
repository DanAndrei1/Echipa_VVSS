package pizzashop.repository;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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

    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @Test
    void testTimeout() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        paymentRepository.add(new Payment(9, PaymentType.Cash, 50));
        assertEquals(9, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getTableNumber());
        assertEquals(50, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getAmount());
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
        paymentRepository.add(new Payment(4, PaymentType.Cash, 1000.01));
        assertEquals(4, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getTableNumber());
        assertEquals(1000.01, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getAmount());
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
                paymentRepository.add(new Payment(-10, PaymentType.Card, 100.5));
                assertEquals(-10, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getTableNumber());
                assertEquals(100.5, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getAmount());
            }

            @Test
            void TestEcp4() {
                paymentRepository.add(new Payment(7, PaymentType.Card, -77));
                assertEquals(7, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getTableNumber());
                assertEquals(-77, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getAmount());
            }
        }

        TestEcp3and4 testEcp3and4 = new TestEcp3and4();
        testEcp3and4.TestEcp3();
        testEcp3and4.TestEcp4();
    }
}