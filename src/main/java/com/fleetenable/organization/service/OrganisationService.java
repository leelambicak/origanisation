package com.fleetenable.organization.service;


import com.fleetenable.organization.model.Organisation;
import com.fleetenable.organization.repository.OrganisationRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OrganisationService {

    @Autowired
    OrganisationRepo organisationRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Organisation createOrg(Organisation organisation){
        return organisationRepo.save(organisation);
    }

    public List<Organisation> getAll(){
        return organisationRepo.findAll();
    }

    public Organisation getById(String id){
        Optional<Organisation> organisation = organisationRepo.findById(id);
        return organisation.orElse(null);
    }

    public void deleteAll(){
        organisationRepo.deleteAll();
    }

    public String deleteOrganisationById(String id) {
        Optional<Organisation> organisation = organisationRepo.findById(id);
        if (organisation.isPresent()) {
            organisationRepo.deleteById(id);
            return "Organisation with ID : " + id + " is deleted successfully";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organisation not found");
        }
    }

    public Organisation updateOrganisationById(String id, Organisation organisation) {
        Optional<Organisation> organisationOptional = organisationRepo.findById(id);
        if (organisationOptional.isPresent()) {
            Organisation existingOrganisation = organisationOptional.get();

            // Update non-null fields
            if (organisation.getName() != null) {
                existingOrganisation.setName(organisation.getName());
            }

            // Save the updated organisation
            return organisationRepo.save(existingOrganisation);
        } else {
            throw new RuntimeException("Organisation not found with id: " + id);
        }
    }

}
