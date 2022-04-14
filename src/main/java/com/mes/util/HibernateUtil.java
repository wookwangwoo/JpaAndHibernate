package com.mes.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static EntityManagerFactory entityManagerFactory;

    static {
        if(entityManagerFactory == null){
            try {
                entityManagerFactory = Persistence.createEntityManagerFactory("Teamyung");
            }catch (IllegalStateException e){
                e.printStackTrace();
                HibernateUtil.close();
            }
        }
    }

    public static EntityManagerFactory open(){
        return entityManagerFactory;
    }

    public static EntityManagerFactory getEntityManagerFactory(){
        return entityManagerFactory;
    }

    public static void close() {

        try {
            if (entityManagerFactory instanceof EntityManagerFactory) {
                entityManagerFactory.close();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
            System.out.println("Connection Close error");
        }
    }


}
