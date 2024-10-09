package com.fleetenable.organization.repository;

import com.fleetenable.organization.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepo extends MongoRepository<Location,String> {
    List<Location> findByOrganisationId(String organisationId);
}
