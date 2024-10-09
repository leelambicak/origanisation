package com.fleetenable.organization.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "location")
public class Location {

    @Id
    private String Id;

    private String Address;

    private String city;

    private String state;

    private String  organisationId;


}
