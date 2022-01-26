package br.com.smashingthosepumpkins.util;

import br.com.smashingthosepumpkins.TestPokemonInfo;
import br.com.smashingthosepumpkins.TestPokemonType;
import br.com.smashingthosepumpkins.core.model.PokemonEntity;
import br.com.smashingthosepumpkins.core.model.PokemonInfo;
import br.com.smashingthosepumpkins.core.model.PokemonType;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Pablo A. G. Silva Jr. on 31/01/2022
 * @project pokemon-repository-testing
 */
public class PokemonDbUtil {
    public static void loadTestTypes() {
        Session session = DependencyUtil.openHibernateSession();
        Transaction tx = session.beginTransaction();
        for (TestPokemonType p : TestPokemonType.values()) {
            PokemonType t = new PokemonType();
            t.setName(p.name());
            t.setId(p.asType().getId());
            session.save(t);
            session.flush();
            session.clear();
        }
        tx.commit();
    }

    public static void loadTestPokemonInfo() {
        Session session = DependencyUtil.openHibernateSession();
        Transaction tx = session.beginTransaction();
        for (TestPokemonInfo i : TestPokemonInfo.values()) {
            PokemonInfo p = new PokemonInfo();
            p.setPokedexId(i.getPokedexId());

            PokemonType t1 = new PokemonType();
            t1.setName(i.getPrimaryType().name());
            t1.setId(i.getPrimaryType().asType().getId());
            p.setPrimaryType(t1);

                PokemonType t2 = new PokemonType();
                t2.setName(i.getSecondaryType().name());
                t2.setId(i.getSecondaryType().asType().getId());
                p.setSecondaryType(t2);

            p.setName(i.name());

            session.save(p);
            session.flush();
            session.clear();
        }
        tx.commit();
    }

    public static void clearDatabase() {
        clearTable(PokemonInfo.class,
                PokemonEntity.class,
                PokemonType.class);
    }

    private static void clearTable(Class... classes) {
        for (Class c : classes) {
            Session session = DependencyUtil.openHibernateSession();
            session.beginTransaction();
            session.createQuery("DELETE FROM " + c.getSimpleName())
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }
}
