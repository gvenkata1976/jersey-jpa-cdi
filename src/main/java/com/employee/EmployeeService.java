package com.employee;

import java.util.List;

import javax.inject.Inject;

import com.example.common.GenericCrudRepository;

public class EmployeeService {

	@Inject
	GenericCrudRepository<Employee> repository;

	public List<Employee> getEmployees() {
		return repository.getEntities(" from Employee emp ");
	}

	public Employee getEmployee(int id) {
		return repository.getEntity(id, Employee.class);
	}

	public void addEmployee(Employee emp) {
		repository.addEntity(emp);
	}

	public int updateEmployee(int id, Employee emp) {
		return repository.updateEntity(id, emp, emp.getClass());
	}

	public int deleteEmployee(int id) {
		return repository.deleteEntity(id, Employee.class);
	}

}
