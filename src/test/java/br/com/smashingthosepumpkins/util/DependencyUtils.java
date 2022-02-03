package br.com.smashingthosepumpkins.util;

import br.com.smashingthosepumpkins.app.ShutdownService;
import br.com.smashingthosepumpkins.di.UnitTestingConfig;
import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManagerFactory;


/**
 * @author Pablo A. G. Silva Jr. on 31/01/2022
 * @project pokemon-repository-testing
 */
public class DependencyUtils {
    public static EntityManagerFactory getEntityManagerFactory() {
        return new AnnotationConfigApplicationContext(UnitTestingConfig.class).getBean(EntityManagerFactory.class);
    }

    public static ShutdownService getShutdownService() {
        return new AnnotationConfigApplicationContext(UnitTestingConfig.class).getBean(ShutdownService.class);
    }

    public static Session openHibernateSession() {
        return getEntityManagerFactory().createEntityManager().unwrap(Session.class);
    }
}
