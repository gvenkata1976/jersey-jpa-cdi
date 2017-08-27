package com.employee.department;

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

@Path("/departments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentResource {

	@Inject
	DepartmentService service;

	@GET
	public List<Department> getDepartments() {
		List<Department> departments = service.getDepartments();
		return departments ;
	}
 
	@GET
	@Path("/{id}")
	public Response getDepartment(@PathParam("id") int id) {
 		Department dpt = service.getDepartment(id);
		if (Objects.isNull(dpt)) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok().entity(dpt).build();
	}
	
	@POST
	public Response addDepartment(Department dpt) {
 		service.addDepartment(dpt);
		return Response.status(Status.CREATED).entity(dpt).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateDepartment(@PathParam("id") int id, Department emp) {
 		int count = service.updateDepartment(id, emp);
		if (count == 0) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteDepartment(@PathParam("id") int id) {
 		int count = service.deleteDepartment(id);
		if (count == 0) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok().build();
	}
}
