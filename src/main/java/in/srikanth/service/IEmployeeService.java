package in.srikanth.service;

import java.util.List;

import in.srikanth.model.Employee;

public interface IEmployeeService {
	/**
	 * This method reads Form Data as Model Class
	 * 
	 * @param e indicates ModelAttribute
	 * @return Integer PK generated after save
	 */
	public Integer saveEmployee(Employee e);

	/**
	 * This method gets all records from db in List
	 */
	public List<Employee> getAllEmployees();

	public void deleteEmployee(Integer id);

	public Employee getOneEmployee(Integer id);
}
