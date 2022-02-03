package br.com.smashingthosepumpkins.util;

import br.com.smashingthosepumpkins.PokemonTestInfo;
import br.com.smashingthosepumpkins.PokemonTestType;
import br.com.smashingthosepumpkins.core.model.PokemonInfo;
import br.com.smashingthosepumpkins.core.model.PokemonType;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static br.com.smashingthosepumpkins.util.DependencyUtils.openHibernateSession;

/**
 * @author Pablo A. G. Silva Jr. on 31/01/2022
 * @project pokemon-repository-testing
 */
public class RepositorySetup {
    public static void loadTestTypes() {
        Session session = openHibernateSession();
        Transaction tx = session.beginTransaction();
        for (PokemonTestType p : PokemonTestType.values()) {
            session.save(p.asType());
            session.flush();
            session.clear();
        }
        tx.commit();
    }

    public static void loadTestPokemonInfo() {
        Session session = openHibernateSession();
        Transaction tx = session.beginTransaction();
        for (PokemonTestInfo i : PokemonTestInfo.values()) {
            session.save(i.asInfo());
            session.flush();
            session.clear();
        }
        tx.commit();
    }
}
