package com.srikanth.paymentManagment.repository;

import com.srikanth.paymentManagment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface paymentRepository extends JpaRepository<Payment, Long> {
}
