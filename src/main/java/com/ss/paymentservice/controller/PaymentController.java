package com.ss.paymentservice.controller;

import com.ss.paymentservice.entity.Payment;
import com.ss.paymentservice.jms.PublisherTextService;
import com.ss.paymentservice.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    @Autowired
    private PublisherTextService publisherTextService;
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/bookingNumber/{bookingNumber}")
    public Payment getPaymentByBookingNumber(@PathVariable Long bookingNumber) {
        return paymentService.getPaymentByBookingNumber(bookingNumber);
    }

    @PostMapping
    public Payment addPayment(@RequestBody Payment payment) {
        return paymentService.addPayment(payment);
    }

    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        payment.setPaymentStatus("done");
        return paymentService.updatePayment(id, payment);
    }

    @PutMapping("/bookingNumber/{bookingNumber}")
    public Payment updatePaymentByBookingNumber(@PathVariable Long bookingNumber, @RequestBody Payment payment) {
        log.info("Updating payment by Bookinmg Number " + bookingNumber);
        Payment p =paymentService.updatePaymentByBookingNumber(bookingNumber, payment);
        publisherTextService.publishText("Payment done for Booking Number "+ p.getBookingNumber());
        return p;
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
}
