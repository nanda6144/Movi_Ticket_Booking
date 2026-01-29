package com.uber.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {


    private HttpStatus paymentStatus;
    private String user_Name;
    private String message;
    private String theatre_location;
    private String movie_name;
    private double movie_screen_Number;
    private String movie_id;
    private LocalDateTime date_And_Time;
    private String user_phoneNumber;
    private String movie_theatre_name;
}
