package br.com.smashingthosepumpkins.infra.dao;


import br.com.smashingthosepumpkins.core.model.PokemonEntity;
import br.com.smashingthosepumpkins.core.model.PokemonType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * @author Pablo A. G. Silva Jr. on 27/01/2022
 * @project pokemon-repository-testing
 */
@Service
public class PokemonDAOImpl implements PokemonDAO {
    private final EntityManagerFactory emf;

    @Inject
    public PokemonDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public boolean insertPokemon(PokemonEntity... entities) {
        EntityManager em = emf.createEntityManager();
        try (Session session = em.unwrap(Session.class)) {
            Transaction tx = session.beginTransaction();
            for (PokemonEntity p : entities) {
                session.save(p);
                session.flush();
                session.clear();
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public List<PokemonEntity> getByType(PokemonType type) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("FROM PokemonEntity e " +
                        "WHERE e.pokemonInfo.primaryType = :type " +
                        "OR e.pokemonInfo.secondaryType = :type", PokemonEntity.class)
                .setParameter("type", type)
                .getResultList();
    }

    @Override
    public List<PokemonEntity> getByPrimaryType(PokemonType type) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("FROM PokemonEntity e " +
                        "WHERE e.pokemonInfo.primaryType = :type", PokemonEntity.class)
                .setParameter("type", type)
                .getResultList();
    }

    @Override
    public List<PokemonEntity> getBySecondaryType(PokemonType type) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("FROM PokemonEntity e " +
                        "WHERE e.pokemonInfo.secondaryType = :type", PokemonEntity.class)
                .setParameter("type", type)
                .getResultList();
    }

    @Override
    public List<PokemonEntity> getAllEntries() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("FROM PokemonEntity e", PokemonEntity.class)
                .getResultList();
    }

    @Override
    public boolean deleteById(int... entityIds) {
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        int n = 0;
        for (int id : entityIds) {
            session.beginTransaction();
            n += session.createQuery("DELETE FROM PokemonEntity e " +
                            "WHERE e.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
        session.close();

        return n > 0;
    }
}
