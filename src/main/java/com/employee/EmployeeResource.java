package com.employee;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

	@Inject
	EmployeeService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployees() {
		List<Employee> employees = service.getEmployees();
		return Response.ok().entity(employees).build(); 
	}

	@GET
	@Path("/{id}")
	public Response getEmployee(@PathParam("id") int id) {
		Employee emp = service.getEmployee(id);
		if (Objects.isNull(emp)) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok().entity(emp).build();
	}

	@POST
	public Response addEmployee(Employee emp) {
		service.addEmployee(emp);
		return Response.status(Status.CREATED).entity(emp).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateEmployee(@PathParam("id") int id, Employee emp) {
		int count = service.updateEmployee(id, emp);
		if (count == 0) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok().entity(emp).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteEmployee(@PathParam("id") int id) {
		int count = service.deleteEmployee(id);
		if (count == 0) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok().entity(count+ " Record(s) deleted.").build();
	}
}
