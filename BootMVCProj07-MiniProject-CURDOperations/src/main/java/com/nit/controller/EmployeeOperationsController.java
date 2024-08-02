package com.nit.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nit.model.Employee;
import com.nit.service.IEmployeeMgmtService;

@Controller
public class EmployeeOperationsController {
	@Autowired
	private IEmployeeMgmtService empService;
	@GetMapping("/")
public String showHome() {
	return "home";
}
	@GetMapping("/report")
	public String showEmployeeReport(@PageableDefault(page=0,size=3,sort="job",direction=Sort.Direction.ASC)Pageable pageable,
			                                                   Map<String,Object>map) {
		System.out.println("EmployeeOperationsController.showEmployeeReport()");
		//use service
		Page<Employee> page=empService.getAllEmployeeReportDataByPage(pageable);
		//put the result in model attributes
		map.put("empData", page);
		//return LVN
		return "show_employee_report";
	}
	@GetMapping("/emp_add") //for form launching
	public String showFormForSaveEmployee(@ModelAttribute("emp")Employee emp) {
		//return LVN
		return "register_employee";
	}
	//PRG Implementation-1
	/*	@PostMapping("/emp_add")  //for form submission related to add employee operation
		public String saveEmployee(@ModelAttribute("emp")Employee emp,
				                                                                      Map<String,Object>map) {
			//use service
			String msg=empService.registerEmployee(emp);
			Iterable<Employee> itEmps=empService.getAllEmployees();
			//keep the result in model Attribute 
			map.put("resultMsg",msg);
			map.put("empsList", itEmps);
			//return LVN
			return "redirect:report";
		}*/
	//PRG Implementation with RedirectAttributes along flashAttritube method
	@PostMapping("/emp_add")  //for form submission related to add employee operation
	public String saveEmployee(@PageableDefault(page=0,size=3,sort="job",direction=Sort.Direction.ASC)Pageable pageable,
			@ModelAttribute("emp")Employee emp,RedirectAttributes attrs) {
		System.out.println("EmployeeOperationsController.saveEmployee()");
		//use service
		String msg=empService.registerEmployee(emp);
		Page<Employee> page2=empService.getAllEmployeeReportDataByPage(pageable);
		//keep the result in flashAttribute 
		attrs.addFlashAttribute("resultMsg",msg);
		//return LVN
		return "redirect:report";
	}
	//PRG Implementation using Session object and Session Attributes
	/*@PostMapping("/emp_add")  //for form submission related to add employee operation
	public String saveEmployee(@ModelAttribute("emp")Employee emp,
			                                                                   HttpSession session) {
		System.out.println("EmployeeOperationsController.saveEmployee()");
		//use service
		String msg=empService.registerEmployee(emp);
		Iterable<Employee> itEmps=empService.getAllEmployees();
		//keep the result in sessionAttribute
		session.setAttribute("resultMsg", msg);	
		//return LVN
		return "redirect:report";
	}*/
	@GetMapping("/emp_edit") //for form launching based on edit operation (1-way binding )
	public String showEditEmployeeFormPage(@RequestParam("no")int no,@ModelAttribute("emp")Employee emp) {
		//use service
		Employee emp1=empService.getEmployeeByNo(no);
		//copy data
		BeanUtils.copyProperties(emp1, emp);
		//return LVN
		return "update_employee";
	}
	@PostMapping("/emp_edit") //form submission based on edit operation (2 way binding)
	public String editEmployee(RedirectAttributes attrs,@ModelAttribute("emp")Employee emp) {
		//user service
		String msg=empService.updateEmployee(emp);
		//add the result messages as Flash Attribute
		attrs.addFlashAttribute("resultMsg", msg);
		//return the redirect
		return "redirect:report";
	}
	@GetMapping("/emp_delete")
	public String deleteEmployee(RedirectAttributes attrs,@RequestParam("no")int no) {
		//use service
		String msg=empService.deleteEmployeeById(no);
		//keep the result in flash attribute
		attrs.addFlashAttribute("resultMsg",msg);
		//redirect the request
		return "redirect:report";
	}
}
