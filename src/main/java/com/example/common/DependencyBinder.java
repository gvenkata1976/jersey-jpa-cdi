package com.example.common;

import static com.example.common.Constants.PERSISTENT_UNIT;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;

import com.employee.Employee;
import com.employee.EmployeeService;
import com.employee.department.Department;
import com.employee.department.DepartmentService;
import com.persistence.config.EntityManagerFactoryProvider;
import com.persistence.config.EntityManagerProvider;

public class DependencyBinder extends AbstractBinder {
	private static final String ACTIWEB_PU = "actiweb";

	@Override
	protected void configure() {
		bind(ACTIWEB_PU).to(String.class).named(PERSISTENT_UNIT);
		bindFactory(EntityManagerFactoryProvider.class).to(EntityManagerFactory.class).in(Singleton.class);
		bindFactory(EntityManagerProvider.class).to(EntityManager.class).in(RequestScoped.class);
		bind(EmployeeService.class).to(EmployeeService.class).in(RequestScoped.class);
		bind(DepartmentService.class).to(DepartmentService.class).in(RequestScoped.class);

		TypeLiteral<GenericCrudRepository<Employee>> employeeRepository = new TypeLiteral<GenericCrudRepository<Employee>>() {
		};
		bind(GenericCrudRepository.class).to(employeeRepository).in(RequestScoped.class); 
		
		TypeLiteral<GenericCrudRepository<Department>> departmentRepository = new TypeLiteral<GenericCrudRepository<Department>>() {
		};
		bind(GenericCrudRepository.class).to(departmentRepository).in(RequestScoped.class); 
	}
}
