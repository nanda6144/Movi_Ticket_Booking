package com.uber.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movies {


    private String movie_id;
    private String movie_name;
    private int no_of_MovieTickets = 10;
    private boolean tickets_Availability = false;
    private String user_name;
    private String user_phoneNumber;
    private String movie_user_id;
    private String theatre_location;


}
