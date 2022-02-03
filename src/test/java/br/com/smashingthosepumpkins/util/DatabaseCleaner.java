package br.com.smashingthosepumpkins.util;

import br.com.smashingthosepumpkins.core.model.PokemonEntity;
import br.com.smashingthosepumpkins.core.model.PokemonInfo;
import br.com.smashingthosepumpkins.core.model.PokemonType;
import org.hibernate.Session;

import static br.com.smashingthosepumpkins.util.DependencyUtils.openHibernateSession;

/**
 * @author Pablo A. G. Silva Jr. on 03/02/2022
 * @project pokemon-repository-testing
 */
public class DatabaseCleaner {
    public static void clearDatabase() {
        clearTable(PokemonInfo.class,
                PokemonEntity.class,
                PokemonType.class);
    }

    private static void clearTable(Class... classes) {
        for (Class c : classes) {
            Session session = openHibernateSession();
            session.beginTransaction();
            session.createQuery("DELETE FROM " + c.getSimpleName())
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }
}
