package com.tpi.notificaciones.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsService {
    @Value("${twilio.phone.from}")
    private String fromPhoneNumber;

    @Value("${tpi-agencia.microservicio-notificaciones.recipients}")
    private String recipientPhoneNumbers;

    // Inicializar Twilio en el constructor
    public TwilioSmsService(@Value("${twilio.account.sid}") String accountSid,
                            @Value("${twilio.auth.token}") String authToken) {
        Twilio.init(accountSid, authToken);
    }

    public void sendSmsToMultipleRecipients(String message) {
        String[] recipients = recipientPhoneNumbers.split(",");
        for (String to : recipients) {
            sendSms(to, message);
        }
    }

    private void sendSms(String to, String message) {
        try {
            Message sms = Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(fromPhoneNumber),
                    message
            ).create();
            System.out.println("Mensaje enviado a " + to + ", SID: " + sms.getSid());
        } catch (Exception e) {
            System.err.println("Error enviando el mensaje a " + to + ": " + e.getMessage());
        }
    }
}
