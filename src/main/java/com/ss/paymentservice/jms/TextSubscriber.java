package com.ss.paymentservice.jms;

import com.ss.paymentservice.config.ActiveMQConfiguration;
import com.ss.paymentservice.entity.Payment;
import com.ss.paymentservice.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class TextSubscriber {

    private static final Logger log = LoggerFactory.getLogger(TextSubscriber.class);

    @Autowired
    private PaymentService paymentService;
    @JmsListener(destination = ActiveMQConfiguration.TEXT_TOPIC)
    public void receiveText(TextMessage textMessage) throws JMSException {
        log.info("Received booking details from Booking Service: " + textMessage.getText());

        Long bookingNumber = Long.valueOf(textMessage.getText().split("Booking Number created and waiting for payment ")[1]);

        Payment payment = new Payment();
        payment.setBookingNumber(bookingNumber);
        payment.setPaymentStatus("pending");
        paymentService.addPayment(payment);
        log.info("Payment created and status updated to pending for Booking Number " + bookingNumber);
    }

}
