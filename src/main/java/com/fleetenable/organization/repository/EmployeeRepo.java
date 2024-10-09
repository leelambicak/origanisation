package com.fleetenable.organization.repository;

import com.fleetenable.organization.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee,String> {
    List<Employee> findByLocationId(String locationId);
}
