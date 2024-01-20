package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee_details")
public class Employee {

    @Id
    @GeneratedValue

    @Column(name = "employee_id")
    private int employeeId;

    @Pattern(regexp = "[A-Za-z]*", message="Employee name should not contain special characters")
    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_address")
    private String employeeAddress;

    @Column(name = "employee_code")
    private long employeeCode;

    @Column(name = "employee_contact")
    private long employeeContact;

    @Column(name = "employee_email")
    private String employeeEmailId;

    @Column(name = "employee_pass")
    private String employeePassword;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date employeeDOB;

    @DecimalMin(value="1971", message="Employee should not be more than 50 years old")
    private int employeeDOBYear;

    private int departmentId;
}
