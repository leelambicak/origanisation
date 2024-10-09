package com.fleetenable.organization.controller;


import com.fleetenable.organization.model.Employee;
import com.fleetenable.organization.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @PostMapping("create")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmp(employee);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
    }


//    @PutMapping("/update-by-id/{id}")
//    public ResponseEntity<String> updateEmployeeById(@PathVariable String id, @Valid @RequestBody Employee employee) {
//        Employee updatedEmployee = employeeService.updateEmployeeById(id, employee);
//        if (updatedEmployee != null) {
//            return ResponseEntity.ok("Employee updated successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
//        }
//    }
@PutMapping("update-by-id/{id}")
public ResponseEntity<Employee> updateEmployeeById(@PathVariable("id") String id, @RequestBody Employee employee) {

    try {
        Employee updatedEmployee = employeeService.updateEmployeeById(id, employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.ACCEPTED);
    } catch (RuntimeException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

@DeleteMapping("delete/{id}")
public ResponseEntity<String> deleteEmployee(@PathVariable String id){
        try{
            String message = employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(message,HttpStatus.OK);
        }
        catch(ResponseStatusException e) {
            return  new ResponseEntity<>(e.getReason(),HttpStatus.NOT_FOUND);

    }
}

//    @DeleteMapping("/delete-by-id/{id}")
//    public ResponseEntity<String> deleteEmployeeById(@PathVariable String id) {
//        boolean deleted = employeeService.deleteEmployeeById(id);
//        if (deleted) {
//            return ResponseEntity.ok("Employee deleted successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
//        }
//    }

    public ResponseEntity<?> deleteEmployeeById(@PathVariable String id){
        employeeService.deleteEmployeeById(id);
        return  ResponseEntity.ok("Deleted successfully");

        }


    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllEmployees() {
        employeeService.deleteAllEmployees();
        return ResponseEntity.ok("All employees deleted successfully");
    }

    @GetMapping("/get-employee-by-loc/{locationId}")
    public ResponseEntity<?> getEmployeesByLocation(@PathVariable String locationId) {
        List<Employee> employees = employeeService.getEmployeesByLocation(locationId);
        if (!employees.isEmpty()) {
            return ResponseEntity.ok(employees);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employees found at this location");
        }
    }
}


