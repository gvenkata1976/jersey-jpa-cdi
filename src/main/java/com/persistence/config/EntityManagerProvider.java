package com.persistence.config;

import java.io.Closeable;
import java.util.Objects;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.server.CloseableService;

public class EntityManagerProvider implements Factory<EntityManager> {
	private final CloseableService closeableService;
	EntityManagerFactory emf;

	@Inject
	public EntityManagerProvider(EntityManagerFactory emf, CloseableService closeableService) {
		this.closeableService = Objects.requireNonNull(closeableService);
		this.emf = emf;
	}

	@Override
	public EntityManager provide() {
		final EntityManager em = emf.createEntityManager();
		closeableService.add(new Closeable() {
			public final void close() {
				em.close();
			}
		});
		return em;
	}

	@Override
	public void dispose(EntityManager em) {
		if (Objects.nonNull(em) && em.isOpen()) {
			em.close();
		}
	}

}