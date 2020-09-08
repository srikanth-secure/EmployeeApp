package in.srikanth.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.srikanth.exception.EmployeeNotFoundException;
import in.srikanth.model.Employee;
import in.srikanth.repo.EmployeeRepository;
import in.srikanth.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private EmployeeRepository repo;

	@Override
	public Integer saveEmployee(Employee e) {
		// after save(obj) same object is returned with ID effected
		e = repo.save(e);
		return e.getId();
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> list = repo.findAll();
		// Interface ob = (method params) -> {method body;}
		// list.sort((e1, e2) -> e1.getId() - e2.getId());
		// return list;

		list.sort(new Comparator<Employee>() {
			@Override
			public int compare(Employee e1, Employee e2) {
				return e1.getId() - e2.getId();
				// return e1.getId().compareTo(e2.getId());
			}
		});
		return list;
	}

	@Override
	public void deleteEmployee(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Employee getOneEmployee(Integer id) {
		/*
		 * Employee e = repo.findById(id) .orElseThrow(() -> new
		 * EmployeeNotFoundException("Employee '" + id + " ' not Exist")); return e;
		 */
		Optional<Employee> opt = repo.findById(id);
		if (opt.isPresent())
			return opt.get();
		else
			throw new EmployeeNotFoundException("Employee '" + id + " ' not Exist");
	}
}
