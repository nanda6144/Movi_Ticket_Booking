package com.uber.Service;


import com.uber.Model.Movies;
import com.uber.Model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {


    private final Logger logger = LoggerFactory.getLogger(MovieService.class);


    @Autowired
    private KafkaTemplate<String, Movies> kafkaTemplate;





    @KafkaListener(topics = "booking-movie", groupId = "Movie_Service")
    public void movieDetails(User user){

        movieDetailsAsynch(user);

    }


    @Async
    public void movieDetailsAsynch(User user){

        try {
            Thread.sleep(3000); // ✅ 3-second wait
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        logger.info("Checking the Availability of your searched Movie");

        Movies movies = new Movies();


        movies.setMovie_name("Khaleja");



        if(movies.getMovie_name().equals(user.getMovie_name())){

            movies.setMovie_id(user.getMovie_id());


            logger.info("Yeah We found your searched Movie " + user.getMovie_name());
            logger.info("Checking of Tickets Availability");

            if(user.getNo_of_movieTickets() <= movies.getNo_of_MovieTickets()){

                movies.setTickets_Availability(true);
                movies.setUser_name(user.getUser_name());
                movies.setMovie_user_id(user.getUser_id());
                movies.setTheatre_location(user.getMovie_location());
                movies.setUser_phoneNumber(user.getUser_phoneNumber());
                logger.info("The Requested no. Of Tickets are available... Shortly will be moved to Payment Page");
                logger.info(user.getUser_name() + " We have processed the Movie tickets for " + user.getMovie_name());
                logger.info("Moving to Payment Page");

                kafkaTemplate.send("movie-found", movies);

            }
            else{

                logger.info("Sorry The Requested No.of Tickets are not Available for the " + user.getMovie_name());
            }


        }
        else {

            logger.info(" Sorry the Request Movie is not available Please enter the Different Movie");
        }

    }
}
