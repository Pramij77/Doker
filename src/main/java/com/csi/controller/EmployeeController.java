package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.model.Response;
import com.csi.repo.EmployeeRepository;
import com.csi.service.EmployeeServiceImpl;
import com.csi.vo.RestTemplateVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/signup")
    public Response signUp(@Valid @RequestBody Employee employee){
        employeeService.signUp(employee);
        return new Response(employee.getEmployeeId()+ "inserted", Boolean.TRUE) ;
    }

    @GetMapping("/getalldata")
    public List<Employee> getAllData(){
        return employeeService.getAllData();
    }


    @GetMapping("/signin/{employeeEmailId}/{employeePassword}")
    public boolean employeeSignIn(@PathVariable String employeeEmailId, @PathVariable String employeePassword ){
        return employeeService.employeeSignIn(employeeEmailId,employeePassword);
    }

    @GetMapping("/getdatabyid/{employeeId}")
    public RestTemplateVo restTemplateVo(@PathVariable int employeeId){
        return employeeService.getRestTemplateVo(employeeId);
    }

    @GetMapping("/getdatabyname/{employeeName}")
    public ResponseEntity<Employee> getDataByName(@PathVariable String employeeName){
        return employeeService.getDataByName(employeeName);
    }


    @PutMapping("/updatedata/{employeeId}")
    public Employee updateData(@PathVariable int employeeId,@RequestBody Employee employee) throws RecordNotFoundException{
        log.info("****************Trying To Update The Data For Employee Id :- "+employeeId);
        Employee employee1= employeeRepository.findById(employeeId).orElseThrow(()->new RecordNotFoundException("Employee Id is Not Available"));
        log.info("*********Go Ahead for updating the update data :"+employee1.getEmployeeId());
        employee1.setEmployeeId(employeeId);
        employee1.setEmployeeName(employee.getEmployeeName());
        employee1.setEmployeeAddress(employee.getEmployeeAddress());
        employee1.setEmployeeCode(employee.getEmployeeCode());
        employee1.setEmployeeContact(employee.getEmployeeContact());
        employee1.setEmployeeDOB(employee.getEmployeeDOB());
        employee1.setEmployeeEmailId(employee.getEmployeeEmailId());
        employee1.setEmployeePassword(employee.getEmployeePassword());



        return employeeService.updateData(employee);
    }


    @DeleteMapping("/deletedata/{employeeId}")
    public ResponseEntity<String> deleteData(@PathVariable int employeeId){
        employeeService.deleteData(employeeId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }





















}
