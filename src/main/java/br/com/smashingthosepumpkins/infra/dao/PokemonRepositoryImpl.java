package br.com.smashingthosepumpkins.infra.dao;


import br.com.smashingthosepumpkins.app.ShutdownService;
import br.com.smashingthosepumpkins.core.model.PokemonEntity;
import br.com.smashingthosepumpkins.core.model.PokemonType;
import br.com.smashingthosepumpkins.core.repository.PokemonRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pablo A. G. Silva Jr. on 27/01/2022
 * @project pokemon-repository-testing
 */
@Service
public class PokemonRepositoryImpl implements PokemonRepository {
    private final EntityManagerFactory emf;
    private final ShutdownService ss;

    @Inject
    public PokemonRepositoryImpl(EntityManagerFactory emf,
                                 ShutdownService ss) {
        this.emf = emf;
        this.ss = ss;
    }

    @Override
    public void insertPokemon(PokemonEntity p) {
        Session session = null;
        try {
            session = emf.createEntityManager().unwrap(Session.class);
            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
            session.clear();
        } catch (IllegalArgumentException ignored) {
        } catch (PersistenceException ex) {
            ss.shutdown(ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<PokemonEntity> findByType(PokemonType type) {
        List<PokemonEntity> list = new ArrayList<>();
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            list = em.createQuery("FROM PokemonEntity e " +
                            "WHERE e.pokemonInfo.primaryType = :type " +
                            "OR e.pokemonInfo.secondaryType = :type", PokemonEntity.class)
                    .setParameter("type", type)
                    .getResultList();
        } catch (PersistenceException ex) {
            ss.shutdown(ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return list;
    }

    @Override
    public List<PokemonEntity> findByPrimaryType(PokemonType type) {
        List<PokemonEntity> list = new ArrayList<>();
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            list = em.createQuery("FROM PokemonEntity e " +
                            "WHERE e.pokemonInfo.primaryType = :type", PokemonEntity.class)
                    .setParameter("type", type)
                    .getResultList();
        } catch (PersistenceException ex) {
            ss.shutdown(ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return list;
    }

    @Override
    public List<PokemonEntity> findBySecondaryType(PokemonType type) {
        List<PokemonEntity> list = new ArrayList<>();
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            list = em.createQuery("FROM PokemonEntity e " +
                            "WHERE e.pokemonInfo.secondaryType = :type", PokemonEntity.class)
                    .setParameter("type", type)
                    .getResultList();
        } catch (PersistenceException ex) {
            ss.shutdown(ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return list;
    }

    @Override
    public List<PokemonEntity> findAllEntries() {
        List<PokemonEntity> list = new ArrayList<>();
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.createQuery("FROM PokemonEntity e", PokemonEntity.class)
                    .getResultList();
        } catch (PersistenceException ex) {
            ss.shutdown(ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return list;
    }

    @Override
    public void deleteById(int entityId) {
        Session session = null;
        try {
            session = emf.createEntityManager().unwrap(Session.class);
            session.beginTransaction();
            session.createQuery("DELETE FROM PokemonEntity e " +
                            "WHERE e.id = :id")
                    .setParameter("id", entityId)
                    .executeUpdate();
            session.getTransaction().commit();
            session.clear();
        } catch (PersistenceException ex) {
            ss.shutdown(ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
