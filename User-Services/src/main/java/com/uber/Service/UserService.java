package com.uber.Service;

import com.uber.Model.Notification;
import com.uber.Model.Ticket;
import com.uber.Model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {



    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;


    public User bookingMovie(User user){

        User newuser = new User();

        newuser.setUser_id(UUID.randomUUID().toString());
        newuser.setUser_name(user.getUser_name());
        newuser.setUser_phoneNumber(user.getUser_phoneNumber());
        newuser.setMovie_id(UUID.randomUUID().toString());
        newuser.setMovie_name(user.getMovie_name());
        newuser.setNo_of_movieTickets(user.getNo_of_movieTickets());
        newuser.setMovie_location("Hyderabad");

        kafkaTemplate.send("booking-movie", newuser);


        return newuser;
    }



    @KafkaListener(topics = "ticket-details", groupId = "user_service")
    public void movieticketdetails(Ticket ticket){
        movieticketdetailsAsynch(ticket);
    }

    @Async
    public void movieticketdetailsAsynch(Ticket ticket){


        try {
            Thread.sleep(3000); // ✅ 3-second wait
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        if(ticket != null){

            logger.info("Movie ticket Has been Booked");
            logger.info(    "Movie Name      : {}\n" +
                            "Movie ID        : {}\n" +
                            "Location        : {}\n" +
                            "Date & Time     : {}\n" +
                            "Screen Number   : {}\n" +
                            "Theatre Name       : {}\n" +
                            "User Name    : {}",
                    ticket.getMovie_name(),
                    ticket.getMovie_id(),
                    ticket.getMovie_location(),
                    ticket.getDate_And_Time(),
                    ticket.getMovie_screen_Number(),
                    ticket.getMovie_theatre_name(),
                    ticket.getUser_Name());

        }
        else{

            logger.info("Sorry Your Movie Ticket details has been not booked... Due to Internal Server Error");
        }



    }



}
