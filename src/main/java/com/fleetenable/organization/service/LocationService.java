package com.fleetenable.organization.service;



import com.fleetenable.organization.model.Employee;
import com.fleetenable.organization.model.Location;
import com.fleetenable.organization.repository.LocationRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
@Autowired
LocationRepo locationRepo;

@Autowired
private ModelMapper modelMapper;

    public Location createLocation(Location location){
        return locationRepo.save(location);
    }


    public List<Location>  getAll(){
        return locationRepo.findAll();
    }
    public Optional<Location> getLocationById(String Id) {
        return locationRepo.findById(Id);
    }
//    public Location getLocationById(String id){
//        Optional<Location> location = locationRepo.findById(id);
//
//        return location.orElse(null);
//    }
//public Location getLocationById(String id) {
//    Optional<Location> location = locationRepo.findById(id);
//    return location.orElse(null);
//}

    public void deleteAll(){
        locationRepo.deleteAll();
    }
//
//  public String deleteLocationById(String id){
//        Optional<Location> location = locationRepo.findById(id);
//        if(location.isPresent()){
//            locationRepo.deleteById(id);
//            return "Location with Id:" +id+ "deleted succesfully";
//        }else{
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Location not found");
//        }
//  }

    public String deleteLocationById(String id) {
        Logger logger = LoggerFactory.getLogger(LocationService.class);
        logger.info("Attempting to delete location with id: {}", id);

        Optional<Location> location = locationRepo.findById(id);
        if (location.isPresent()) {
            locationRepo.deleteById(id);
            logger.info("Location with id {} deleted successfully", id);
            return "Location with Id: " + id + " deleted successfully";
        } else {
            logger.error("Location with id {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        }
    }
    public Location updateLocationById(String id, Location location) {
        Optional<Location> locationOptional = locationRepo.findById(id);
        if (locationOptional.isPresent()) {
            Location existingLocation = locationOptional.get();

            // Update non-null fields
            if (location.getAddress() != null) {
                existingLocation.setAddress(location.getAddress());
            }
            if (location.getCity() != null) {
                existingLocation.setCity(location.getCity());
            }
            if (location.getState() != null) {
                existingLocation.setState(location.getState());
            }
            if (location.getOrganisationId() != null) {
                existingLocation.setOrganisationId(location.getOrganisationId());
            }

            // Save the updated location
            return locationRepo.save(existingLocation);
        } else {
            throw new RuntimeException("Location not found with id: " + id);
        }
    }

    public List<Location> getLocationsByOrganisation(String organisationId) {
       return locationRepo.findByOrganisationId(organisationId);

    }

}
