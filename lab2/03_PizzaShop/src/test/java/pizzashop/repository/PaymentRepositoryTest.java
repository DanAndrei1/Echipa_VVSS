package pizzashop.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    void add() {
        paymentRepository.add(new Payment(0, PaymentType.Cash, 1000.1));
        assertEquals(0, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getTableNumber());
        assertEquals(PaymentType.Cash, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getType());
        assertEquals(1000.1, paymentRepository.getAll().get(paymentRepository.getAll().size()-1).getAmount());
    }
}