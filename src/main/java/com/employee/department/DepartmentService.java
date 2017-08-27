package com.employee.department;

import java.util.List;

import javax.inject.Inject;

import com.example.common.GenericCrudRepository;

public class DepartmentService {

	@Inject
	GenericCrudRepository<Department> repository;

	public List<Department> getDepartments() {
		return repository.getEntities(" from Department dept ");
	}

	public Department getDepartment(int id) {
		return repository.getEntity(id, Department.class);
	}

	public void addDepartment(Department dpt) {
		repository.addEntity(dpt);
	}

	public int updateDepartment(int id, Department dpt) {
		return repository.updateEntity(id, dpt, dpt.getClass());
	}

	public int deleteDepartment(int id) {
		return repository.deleteEntity(id, Department.class);
	}

}
