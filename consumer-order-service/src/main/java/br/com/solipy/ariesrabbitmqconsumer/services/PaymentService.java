package br.com.solipy.ariesrabbitmqconsumer.services;

import br.com.solipy.ariesrabbitmqconsumer.models.Payment;
import br.com.solipy.ariesrabbitmqconsumer.repositories.PaymentRepository;
import br.com.solipy.ariesrabbitmqconsumer.util.Status;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional
    public String registerPayment(String token, BigDecimal total) {
        Optional<Payment> optionalPayment = paymentRepository.findByPaymentToken(token);
        if(optionalPayment.isPresent()) {
            return optionalPayment.get().getPaymentToken();
        }

        Payment payment = Payment.builder()
                .setPaymentToken(token)
                .setTotal(total)
                .setStatus(Status.PEDENT)
                .build();
        Payment paymentPersisted = paymentRepository.save(payment);
        return paymentPersisted.getPaymentToken();
    }

    @Transactional
    public Map<String, Boolean> finishPayment(String token) {
        Integer status = paymentRepository.updateStatusByToken(token, Status.FINISH);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", status > 0);
        return map;
    }

}
