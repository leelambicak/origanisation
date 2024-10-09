package com.fleetenable.organization.repository;

import com.fleetenable.organization.model.Organisation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepo extends MongoRepository<Organisation,String> {


}
