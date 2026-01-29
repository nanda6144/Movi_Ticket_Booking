package com.uber.Service;


import com.uber.Model.Notification;
import com.uber.Model.Ticket;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class TicketGeneration {


    private final Logger logger = LoggerFactory.getLogger(TicketGeneration.class);


    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;



    @KafkaListener(topics = "notification-sent", groupId = "Ticket-Services")
    public void ticketDetails(Notification notification){

        ticketDetailsAsynch(notification);

    }


    @Async
    public void ticketDetailsAsynch(Notification notification){

        try {
            Thread.sleep(3000); // ✅ 3-second wait
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }


        logger.info("Sending the Ticket to your WhatsApp number " + " : " + notification.getUser_phoneNumber());
        logger.info("Please Don't share it in public platforms" );

        Ticket ticket = new Ticket();


        if(notification.getPaymentStatus().equals(HttpStatus.ACCEPTED)){

            ticket.setMovie_name(notification.getMovie_name());
            ticket.setMovie_location(notification.getTheatre_location());
            ticket.setMovie_id(notification.getMovie_id());
            ticket.setMovie_theatre_name(notification.getMovie_theatre_name());

            ticket.setDate_And_Time(LocalDateTime.now());

            ticket.setMovie_screen_Number(Math.random());
            ticket.setUser_phoneNumber(notification.getUser_phoneNumber());
            ticket.setUser_Name(notification.getUser_Name());

            logger.info("Notification has recieved by " + ticket.getUser_Name());
            logger.info("Notification Regarding Movie Ticket " + ticket.getMovie_name() + "in" + ticket.getMovie_theatre_name());

            logger.info( "Notification Message     : {}\n" +
                            "Theatre Name    : {}",
                    notification.getMessage(),
                    notification.getTheatre_location());

            kafkaTemplate.send("ticket-details", ticket);

        }
        else{

            logger.info("Your Transaction is not Successfull");
        }


    }
}
