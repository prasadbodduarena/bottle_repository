package com.nit.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nit.model.Employee;

public interface IEmployeeMgmtService {
public Page<Employee> getAllEmployeeReportDataByPage(Pageable pageable);
public String registerEmployee(Employee emp);
public Employee getEmployeeByNo(int eno);
public String updateEmployee(Employee emp);
public String deleteEmployeeById(int eno);
}
