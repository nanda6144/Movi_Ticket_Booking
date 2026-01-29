package com.uber.Service;

import com.uber.Model.Notification;
import com.uber.Model.UserPayment;
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
public class NotificationService {


    private final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;



    @KafkaListener(topics = "paymentTOPIC", groupId = "notificationGROUP")
    public void consumePayment(UserPayment userPayment){

        consumePaymentAsynch(userPayment);
    }


    @Async
    public void consumePaymentAsynch(UserPayment userPayment){


        try {
            Thread.sleep(3000); // ✅ 3-second wait
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        Notification notification = new Notification();

        if(userPayment.getPaymetStatus().equals(HttpStatus.ACCEPTED)){

            notification.setPaymentStatus(HttpStatus.ACCEPTED);
            notification.setUser_Name(userPayment.getPayment_person());
            notification.setMessage("Your ticket for the Movie : " + userPayment.getMovie_name() + ", " + "Successfully booked. Shortly you will be recieving Ticket Details to your " +" : " + userPayment.getUser_phoneNumber());
            notification.setTheatre_location(userPayment.getTheatre_location());
            notification.setUser_phoneNumber(userPayment.getUser_phoneNumber());
            notification.setMovie_id(userPayment.getMovieticket_id());
            notification.setDate_And_Time(LocalDateTime.now());
            notification.setMovie_screen_Number(Math.random());
            notification.setMovie_theatre_name("Bramaramba MalliKarjuna 35MM");
            notification.setMovie_name(userPayment.getMovie_name());

            logger.info(notification.getUser_Name() + " The Recent purchase regarding " +notification.getMovie_name() +" Movie has been Successfully Booked");
            logger.info(notification.getUser_Name() + " You Will be recieving Ticket details to you shortly");


            kafkaTemplate.send("notification-sent", notification);
        }
        else{

            logger.info("Your Payment has Declined");
        }



    }
}
