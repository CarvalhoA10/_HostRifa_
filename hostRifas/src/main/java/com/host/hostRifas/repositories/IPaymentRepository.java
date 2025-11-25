package com.host.hostRifas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.host.hostRifas.models.payment.PaymentModel;

@Repository
public interface IPaymentRepository extends JpaRepository<PaymentModel, Long>{
    
}
