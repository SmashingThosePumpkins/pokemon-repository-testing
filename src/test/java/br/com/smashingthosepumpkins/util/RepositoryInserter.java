package br.com.smashingthosepumpkins.util;

import br.com.smashingthosepumpkins.core.model.PokemonEntity;
import org.hibernate.Session;

import static br.com.smashingthosepumpkins.util.DependencyUtils.openHibernateSession;

/**
 * @author Pablo A. G. Silva Jr. on 03/02/2022
 * @project pokemon-repository-testing
 */
public class RepositoryInserter {
    public static void insert(PokemonEntity... es) {
        Session session = openHibernateSession();
        session.beginTransaction();
        for (PokemonEntity e : es) {
            session.save(e);
        }
        session.getTransaction().commit();
        session.clear();
        session.close();
    }
}
