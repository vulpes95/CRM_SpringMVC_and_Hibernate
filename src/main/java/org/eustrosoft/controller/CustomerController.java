package org.eustrosoft.controller;

import java.util.List;

import org.eustrosoft.entity.Customer;
import org.eustrosoft.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	//@GetMapping("/list") изменений не будет
	@RequestMapping("list")
	public String listCustomers(Model model) {
		List<Customer> customers = customerService.getCustomers();
		model.addAttribute("customers", customers);
		return "list_customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer",customer);
		return "customer_form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.saveCustomer(customer);
		System.out.println(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, 
									Model model) {
		Customer loadCustomer = customerService.getCustomer(id);
		model.addAttribute("customer", loadCustomer);
		return "customer_form";

	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int id) {
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}
	
	@PostMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
	                                    Model theModel) {
        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
        theModel.addAttribute("customers", theCustomers);
        return "list_customers";        
    }
}
