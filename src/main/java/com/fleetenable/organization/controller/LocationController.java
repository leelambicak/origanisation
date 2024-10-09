package com.fleetenable.organization.controller;


import com.fleetenable.organization.model.Location;
import com.fleetenable.organization.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("location")
public class LocationController {
@Autowired
    LocationService locationService;

    @PostMapping("create")
    public Location createLocation(Location location){
        return locationService.createLocation(location);
    }

    @GetMapping("get-all")
    public ResponseEntity<List<Location>> getAll(){
       return ResponseEntity.ok(locationService.getAll());
    }

    @GetMapping("get-by-id/{Id}")
    public ResponseEntity<Location> getLocationById(@PathVariable String Id) {
        return locationService.getLocationById(Id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The location not found"));
    }

    @DeleteMapping("delete-all")
    public ResponseEntity<String> deleteAll(){
         locationService.deleteAll();
        return ResponseEntity.ok("All Locations deleted successfully");
    }

@DeleteMapping("delete/{id}")
 public ResponseEntity<String> deleteById(@PathVariable String id){
        try{
            String message = locationService.deleteLocationById(id);
            return new ResponseEntity<>(message,HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),HttpStatus.NOT_FOUND);
        }

 }

    @PutMapping("update/{id}")
    public ResponseEntity<Location> updateLocationById(@PathVariable("id") String id, @RequestBody Location location) {
        try {
            Location updatedLocation = locationService.updateLocationById(id, location);
            return new ResponseEntity<>(updatedLocation, HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-loc-by-org/{organisationId}")
    public List<Location> getLocationByOrganisation(@PathVariable String organisationId) {
        return locationService.getLocationsByOrganisation(organisationId);
    }
}
