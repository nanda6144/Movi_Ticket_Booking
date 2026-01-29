package com.uber.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {



    private String movie_name;
    private String movie_location;
    private String movie_theatre_name;
    private double movie_screen_Number;
    private String movie_id;
    private LocalDateTime date_And_Time;
    private String user_phoneNumber;
    private String user_Name;
}
