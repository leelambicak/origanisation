package com.fleetenable.organization.controller;


import com.fleetenable.organization.model.Organisation;
import com.fleetenable.organization.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("organisation")
public class OrganisationController {
     @Autowired
    OrganisationService organisationService;

     @PostMapping("create")
     public Organisation createOrg(Organisation organisation){
         return organisationService.createOrg(organisation);
     }

     @GetMapping("all")
     public ResponseEntity<List<Organisation>>  getAll(){
         return ResponseEntity.ok(organisationService.getAll());
     }

     @DeleteMapping("delete-all")
     public ResponseEntity<String> deleteAll(){
         organisationService.deleteAll();
         return ResponseEntity.ok("All Organisations successfully deleted");
     }

    @DeleteMapping("/delete/{id}")

    public ResponseEntity<String> deleteOrganisation(@PathVariable("id") String id) {
        try {
            String message = organisationService.deleteOrganisationById(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), HttpStatus.NOT_FOUND);
        }
    }

     @GetMapping("get-by-id/{id}")
     public ResponseEntity<?> getById(@PathVariable String id){
         Organisation organisation = organisationService.getById(id);
         return organisation !=null ? ResponseEntity.ok(organisation) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Organisation not found");
     }

    @PutMapping("update/{id}")
    public ResponseEntity<Organisation> updateOrganisationById(
            @PathVariable("id") String id,
            @RequestBody Organisation organisation) {

        try {
            Organisation updatedOrganisation = organisationService.updateOrganisationById(id, organisation);
            return new ResponseEntity<>(updatedOrganisation, HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}