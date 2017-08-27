package com.employee;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

public class EmployeeResourceTest {

	private static final String EMPLOYEES_URL = "http://localhost:8080/jersey-crud/rest/employees";

	@Test
	public void testEmployees() throws Exception {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(EMPLOYEES_URL);
		String response = webTarget.request().get(String.class);
		System.out.println(response);
	}
}