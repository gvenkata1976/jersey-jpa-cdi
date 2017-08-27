package com.example.common;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class GenericCrudRepository<T> {
	@Inject
	public EntityManager em;

	@SuppressWarnings("unchecked")
	public List<T> getEntities(String query) {
		List<T> results = null;
		try {
			em.getTransaction().begin();
			results = em.createQuery(query).getResultList();
			em.getTransaction().commit();
		} catch (Exception exp) {
			em.getTransaction().rollback();
			throw exp;
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	public T getEntity(int id, Class<?> entityClass) {
		T result = null;
		try {
			em.getTransaction().begin();
			result = (T) em.find(entityClass, id);
			em.getTransaction().commit();
		} catch (Exception exp) {
			em.getTransaction().rollback();
			throw exp;
		}
		return result;
	}

	public void addEntity(T entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception exp) {
			em.getTransaction().rollback();
			throw exp;
		}
	}

	public int updateEntity(int id, T entity, Class<?> clazz) {
		try {
			em.getTransaction().begin();
			Objects.requireNonNull(em.find(clazz, id));
			em.merge(entity);
			em.getTransaction().commit();
			em.close();
		} catch (Exception exp) {
			em.getTransaction().rollback();
			throw exp;
		}
		return 1;
	}

	public int deleteEntity(int id, Class<?> clazz) {
		try {
			em.getTransaction().begin();
			em.remove(Objects.requireNonNull(em.find(clazz, id)));
			em.getTransaction().commit();
		} catch (Exception exp) {
			em.getTransaction().rollback();
			throw exp;
		}
		return 1;
	}
}
