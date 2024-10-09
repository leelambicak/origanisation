package com.fleetenable.organization.service;

import com.fleetenable.organization.model.Employee;
import com.fleetenable.organization.model.Organisation;
import com.fleetenable.organization.repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

 public Employee createEmp(Employee emp){
     return employeeRepo.save(emp);
 }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(String id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        return employee.orElse(null);
    }


    public Employee updateEmployeeById(String id, Employee employee) {
        Optional<Employee> employeeOptional = employeeRepo.findById(id);
        if (employeeOptional.isPresent()) {
            Employee existingEmployee = employeeOptional.get();

            // Update non-null fields
            if (employee.getName() != null) {
                existingEmployee.setName(employee.getName());
            }
            if (employee.getDep() != null) {
                existingEmployee.setDep(employee.getDep());
            }
            if (employee.getHireDate() != null) {
                existingEmployee.setHireDate(employee.getHireDate());
            }
            if (employee.getSalary() != null) {
                existingEmployee.setSalary(employee.getSalary());
            }
            if (employee.getLocationId() != null) {
                existingEmployee.setLocationId(employee.getLocationId());
            }
            if (employee.getOrganisationid() != null) {
                existingEmployee.setOrganisationid(employee.getOrganisationid());
            }

            // Save the updated employee
            return employeeRepo.save(existingEmployee);
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }


public String deleteEmployeeById(String id){
     Optional<Employee> employee = employeeRepo.findById(id);
     if(employee.isPresent()){
         employeeRepo.deleteById(id);
         return "Employee with Id:" +id +"deleted successfully";
     }
     else{
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found");
     }
}

    public void deleteAllEmployees() {
        employeeRepo.deleteAll();
    }


    public List<Employee> getEmployeesByLocation(String locationId) {
        return employeeRepo.findByLocationId(locationId);
    }

}
