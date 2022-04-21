package com.wp.project.beautysalon.repository;

import com.wp.project.beautysalon.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
