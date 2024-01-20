package com.csi.dao;

import com.csi.model.Employee;
import com.csi.repo.EmployeeRepository;
import com.csi.vo.Department;
import com.csi.vo.RestTemplateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class EmployeeDaoImpl {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RestTemplate restTemplate;

    public Employee signUp(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllData() {
        return employeeRepository.findAll();
    }

    public boolean employeeSignIn(String employeeEmailId, String employeePassword) {
        boolean flag= false;
        List<Employee> employees= employeeRepository.findAll();
        for (Employee employee : employees){
            if (employee.getEmployeeEmailId().equals(employeeEmailId) && employee.getEmployeePassword().equals(employeePassword)){
                flag=true;
            }
        } return flag;

    }

    public RestTemplateVo getRestTemplateVo(int employeeId) {
        RestTemplateVo restTemplateVo= new RestTemplateVo();
        Employee employee= employeeRepository.findByEmployeeId(employeeId);
        Department department= restTemplate.getForObject("http://departmentservice/departments/getdatabyid/"+employee.getDepartmentId(), Department.class);
        restTemplateVo.setEmployee(employee);
        restTemplateVo.setDepartment(department);
        return restTemplateVo;
    }

    public ResponseEntity<Employee> getDataByName(String employeeName) {
        return employeeRepository.findByEmployeeName(employeeName);
    }

    public Employee updateData(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteData(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
