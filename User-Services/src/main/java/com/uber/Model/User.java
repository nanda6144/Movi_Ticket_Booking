package com.uber.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    private String user_id;
    private String user_name;
    private String user_phoneNumber;
    private String movie_id;
    private String movie_name;
    private int no_of_movieTickets;
    private String movie_location;


}
