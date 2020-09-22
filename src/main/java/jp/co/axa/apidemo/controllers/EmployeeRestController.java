package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

import javax.persistence.Cacheable;
/**
 * Employee Rest Controller
 * @author rudramki
 *
 */
@RestController
@RequestMapping("/api/v1")
public class EmployeeRestController {

	/**
	 * Employee CRUD Operation Service
	 */
    @Autowired
    private EmployeeService employeeService;
    
    //JSON Object Mapper
    @Autowired
    com.fasterxml.jackson.databind.ObjectMapper mapper;

    /**
     * Constructor with Parameters
     * @param employeeService
     * @param mapper
     */
    public void setEmployeeService(EmployeeService employeeService,com.fasterxml.jackson.databind.ObjectMapper mapper) {
        this.employeeService = employeeService;
        this.mapper = mapper;
    }

    /**
     * Get List of Existing Employees
     * @return List of Stored Employee details
     * @throws JsonProcessingException
     */
    @GetMapping("/employees")
    public String getEmployees() throws JsonProcessingException {
        List<Employee> employees = employeeService.retrieveEmployees();
        if(employees != null && employees.size() > 0) {
        	return mapper.writeValueAsString(employees); 
        } else {
        	return "[]";
        }
    }

    /**
     * 
     * @param employeeId
     * @return Get Employee Detail for the specified ID
     * @throws JsonProcessingException
     */
    @GetMapping("/employees/{employeeId}")
    public String getEmployee(@PathVariable(name="employeeId")Long employeeId) throws JsonProcessingException {
        
    	Employee emp = employeeService.getEmployee(employeeId);
    	if(emp != null) {
    		return mapper.writeValueAsString(emp);
    	} else {
    		return "";
    	}
			
	
    }
    
    /**
     * Save New Employee Details to DB
     * @param employee
     */
    @PostMapping("/employees")
    public void saveEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        System.out.println("Employee Saved Successfully");
    }

    /**
     * Delete the Employee record for the specified ID
     * @param employeeId
     */
    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        employeeService.deleteEmployee(employeeId);
        System.out.println("Employee Deleted Successfully");
    }

    /**
     * Update the Existing Employee Details for the specified ID
     * @param employee
     * @param employeeId
     */
    @PutMapping("/employees/{employeeId}")
    public void updateEmployee(@RequestBody Employee employee,
                               @PathVariable(name="employeeId")Long employeeId){
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null){
            employeeService.updateEmployee(employee);
        }

    }

}
