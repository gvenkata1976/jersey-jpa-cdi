package com.rest.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import com.example.common.DependencyBinder;

@ApplicationPath("/")
public class JerseyApplication extends ResourceConfig {
	public JerseyApplication() {
		System.out.println("JERSEY BINDING..");
		register(new DependencyBinder());
	}
}
