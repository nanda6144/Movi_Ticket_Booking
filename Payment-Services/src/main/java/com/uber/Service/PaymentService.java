package com.uber.Service;

import com.uber.Model.Movies;
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

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {



    private final Logger logger = LoggerFactory.getLogger(PaymentService.class);



    @Autowired
    private KafkaTemplate<String, UserPayment> kafkaTemplate;


    @KafkaListener(topics = "movie-found", groupId = "Payment_Service")
    public void paymentDetails(Movies movies) {

        paymentDetailsAsynch(movies);

    }

    @Async
    public void paymentDetailsAsynch(Movies movies){


        try {
            Thread.sleep(3000); // ✅ 3-second wait
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        logger.info("Welcome to Payment Page " + movies.getUser_name());

        UserPayment payment = new UserPayment();

        payment.setPayment_id(movies.getMovie_user_id());
        payment.setPayment_phoneNumber(movies.getUser_phoneNumber());
        payment.setPaymentType("UPI PAYMENT");

        logger.info(movies.getUser_name() + " you have Chosen the UPI  payment Type ");
        logger.info("Enter the Payment details");
        logger.info("The Payment UPI ID is " + " : " + payment.getPayment_phoneNumber());
        logger.info("Please Wait Payment details are Validating");



        if(payment.getPayment_id().equals(movies.getMovie_user_id())){

            payment.setPayment_person(movies.getUser_name());

            logger.info("Payment Details has been validated SuccessFully.........");

            logger.info(" Please Wait Payment is Processing. . . . .");
            payment.setPaymetStatus(HttpStatus.ACCEPTED);

            logger.info(movies.getUser_name() + " Your have done your Payment Successfully through " + payment.getPaymentType());
            logger.info(movies.getUser_name() + " ThankYOU. . . ");

            payment.setMovieticket_id(UUID.randomUUID().toString());
            payment.setTheatre_location(movies.getTheatre_location());
            payment.setMovie_name(movies.getMovie_name());
            payment.setUser_phoneNumber(movies.getUser_phoneNumber());


            kafkaTemplate.send("paymentTOPIC", payment);


        }
        else{

            logger.info(movies.getUser_name() + " The Entered Payment Details are Invalid");

        }





    }
}
