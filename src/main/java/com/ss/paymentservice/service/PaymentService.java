package com.ss.paymentservice.service;

import com.ss.paymentservice.entity.Payment;
import com.ss.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;


    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        return paymentOptional.orElse(null);
    }

    public Payment getPaymentByBookingNumber(Long bookingNumber) {
        Optional<Payment> paymentOptional = paymentRepository.findByBookingNumber(bookingNumber);
        return paymentOptional.orElse(null);
    }
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Long id, Payment payment) {
        Optional<Payment> existingPaymentOptional = paymentRepository.findById(id);
        if (existingPaymentOptional.isPresent()) {
            Payment existingPayment = existingPaymentOptional.get();
            existingPayment.setPaymentAmount(payment.getPaymentAmount());
            existingPayment.setBookingNumber(payment.getBookingNumber());
            existingPayment.setPaymentDate(payment.getPaymentDate());
            return paymentRepository.save(existingPayment);
        }
        return null;
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
