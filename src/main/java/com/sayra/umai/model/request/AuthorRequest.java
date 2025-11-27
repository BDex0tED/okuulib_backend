package com.sayra.umai.model.request;

import lombok.Data;

import java.net.URL;
import java.util.List;
@Data
public class AuthorRequest {
    private String photo;
    private String wiki;
    private String name;
    private String bio;
    private String dateOfBirth;

    private List<Long> workIds;


}
