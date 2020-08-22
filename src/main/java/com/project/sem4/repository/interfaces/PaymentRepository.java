package com.project.sem4.repository.interfaces;

import com.project.sem4.model.Payment;

import java.util.List;

public interface PaymentRepository {
    public List<Payment> getAllPayment();
    public Boolean insertPayment(Payment payment);
    public Payment findPaymentById(Integer id);
    public Boolean updatePayment(Payment payment);
    public Boolean deletePayment(Integer id);
}
