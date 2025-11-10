package com.sayra.umai.model.request;

import lombok.Data;

import java.net.URL;

@Data
public class AuthorRequest {
    private URL photo;
    private URL wiki;
    private String name;
    private String bio;
    private String dateOfBirth;


}
