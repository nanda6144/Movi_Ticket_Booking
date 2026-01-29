package com.uber.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPayment {



    private HttpStatus paymetStatus;
    private String paymentType;
    private String payment_id;
    private String payment_person;
    private String movieticket_id;
    private String theatre_location;
    private String movie_name;
    private String payment_phoneNumber;
    private String user_phoneNumber;
}
