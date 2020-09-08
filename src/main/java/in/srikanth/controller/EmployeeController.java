package in.srikanth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.srikanth.model.Employee;
import in.srikanth.service.IEmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private IEmployeeService service; // HAS-A

	/**
	 * Show register page on enter/register URL(GET)
	 * 
	 */
	// @RequestMapping(value="/register", method=RequestMethod.GET)
	@GetMapping("/register")
	public String showRegister() {
		return "EmployeeRegister";
	}

	/**
	 * This method will read form data as ModelAttribute. performs save() operation
	 * using service that returns ID generated. Construt one Message and send to UI
	 * using Model memory on Entering URL:/Save with TYPE:POST
	 * 
	 */
	// @RequestMapping(value="/save",method=RequestMethod.POST)
	@PostMapping("/actiondone")
	public String saveEmployee(@ModelAttribute Employee employee, Model model) {
		Integer id = service.saveEmployee(employee);
		// String message="Employee'"+id+"'Saved";
		// String message=String.format("Employee '%d' saved", id);
		String message = new StringBuffer().append("Employee ' ").append(id).append(" ' Saved").toString();
		model.addAttribute("message", message);
		return "AfterAction";
	}

	@GetMapping("/all")
	public String showAll(Model model) {
		List<Employee> list = service.getAllEmployees();
		model.addAttribute("list", list);
		return "EmployeeData";
	}

	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam Integer id, Model model) {
		service.deleteEmployee(id);
		model.addAttribute("message", "Employee ' " + id + " ' Deleted");
		model.addAttribute("list", service.getAllEmployees());
		return "AfterAction";
		// return "EmployeeData";
	}

	@GetMapping("/edit")
	public String showEdit(@RequestParam Integer id, Model model) {
		Employee e = service.getOneEmployee(id);
		model.addAttribute("employee", e);
		return "EmployeeEdit";
		
	}

}
