package com.persistence.config;

import static com.example.common.Constants.PERSISTENT_UNIT;

import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.glassfish.hk2.api.Factory;

public class EntityManagerFactoryProvider implements Factory<EntityManagerFactory> {

	private final EntityManagerFactory emf;

	@Inject
	public EntityManagerFactoryProvider(@Named(PERSISTENT_UNIT) String persistentUnit) {
		emf = Persistence.createEntityManagerFactory(persistentUnit);
	}

	@Override
	public EntityManagerFactory provide() {
		return emf;
	}

	@Override
	public void dispose(EntityManagerFactory instance) {
		if (Objects.nonNull(instance) && instance.isOpen()) {
			instance.close();
		}
	}

}