package com.nit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nit.model.Employee;
import com.nit.repository.IEmployeeRepository;

@Service("empService")
public class EmployeeMgmtServiceImpl implements IEmployeeMgmtService{
	@Autowired
private IEmployeeRepository empRepo;
	@Override
	public Page<Employee> getAllEmployeeReportDataByPage(Pageable pageable) {
		//get employees
		return empRepo.findAll(pageable);
	}
	@Override
	public String registerEmployee(Employee emp) {
	
		return "employee is saved with id value::"+empRepo.save(emp).getEmpno();
	}
	@Override
	public Employee getEmployeeByNo(int eno) {
	Employee emp=empRepo.findById(eno).orElseThrow(()->new IllegalArgumentException("invali id ! check it out my friend!"));
		return emp;
	}
	@Override
	public String updateEmployee(Employee emp) {
		return "Employee is updated with having id value::"+empRepo.save(emp).getEmpno();
	}
	@Override
	public String deleteEmployeeById(int eno) {	
		empRepo.deleteById(eno);
		return eno+":: Employee id got deleted! ";
	}

}
