package com.mes.dao;

import com.mes.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.sql.SQLException;

public class JpaDao<E> {
    protected EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

    public E create(E entity) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(entity);
            //entityManager.flush();
            //entityManager.refresh(entity);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }
        return entity;
    }

    public E update(E entity){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }
        return entity;
    }

    public E find(Class<E> type, Object id) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        E entity = entityManager.find(type, id);
        if (entity == null) {
            return null;
        }

        return entity;
    }

    public void delete(Class<E> type,Object id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        Object references = entityManager.getReference(type,id);

        transaction.begin();
        entityManager.remove(references);
        transaction.commit();
    }




}
