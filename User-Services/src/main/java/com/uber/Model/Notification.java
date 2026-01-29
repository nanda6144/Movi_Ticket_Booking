package com.uber.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {


    private HttpStatus paymentStatus;
    private String user_Name;
    private String message;
    private String theatre_location;
    private String user_phoneNumber;
}
