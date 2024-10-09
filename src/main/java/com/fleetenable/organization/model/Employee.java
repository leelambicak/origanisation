package com.fleetenable.organization.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "employee")
public class Employee {

    @Id
    private String id;

    private String name;

    private String dep;

    private Date hireDate;

    private Long salary;

    private String locationId;

    private String organisationid;
}
