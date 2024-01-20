package com.csi.service;

import com.csi.dao.EmployeeDaoImpl;
import com.csi.model.Employee;
import com.csi.vo.RestTemplateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl {

    @Autowired
    EmployeeDaoImpl employeeDao;

    public Employee signUp(Employee employee) {
        return employeeDao.signUp(employee);
    }

    public List<Employee> getAllData() {
        return employeeDao.getAllData();
    }

    public boolean employeeSignIn(String employeeEmailId, String employeePassword) {
        return employeeDao.employeeSignIn(employeeEmailId,employeePassword);
    }

    public RestTemplateVo getRestTemplateVo(int employeeId) {
        return employeeDao.getRestTemplateVo(employeeId);
    }

    public ResponseEntity<Employee> getDataByName(String employeeName) {
        return employeeDao.getDataByName(employeeName);
    }

    public Employee updateData(Employee employee) {
        return employeeDao.updateData(employee);
    }

    public void deleteData(int employeeId) {
        employeeDao.deleteData(employeeId);
    }
}
