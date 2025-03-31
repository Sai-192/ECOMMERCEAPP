package com.srikanth.paymentManagment.service;

import com.srikanth.paymentManagment.model.Payment;
import com.srikanth.paymentManagment.repository.paymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final paymentRepository repository;

    public PaymentService(paymentRepository repository) {
        this.repository = repository;
    }

    public List<Payment> getAll() {
        return repository.findAll();
    }

    public Payment getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Payment create(Payment payment) {
        return repository.save(payment);
    }

    public Payment update(Long id, Payment updated) {
        return repository.findById(id).map(p -> {
            p.setOrderId(updated.getOrderId());
            p.setMethod(updated.getMethod());
            p.setAmount(updated.getAmount());
            p.setStatus(updated.getStatus());
            return repository.save(p);
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
