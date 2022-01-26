package br.com.smashingthosepumpkins.util;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static br.com.smashingthosepumpkins.util.SpringUtil.getContext;

/**
 * @author Pablo A. G. Silva Jr. on 31/01/2022
 * @project pokemon-repository-testing
 */
public class DependencyUtil {
    private static EntityManagerFactory emf;

    public static EntityManager createEntityManager() {
        if (emf != null) {
            return emf.createEntityManager();
        }
        emf = getEntityManagerFactory();
        return createEntityManager();
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return getContext().getBean(EntityManagerFactory.class);
    }

    public static Session openHibernateSession() {
        return createEntityManager().unwrap(Session.class);
    }
}
