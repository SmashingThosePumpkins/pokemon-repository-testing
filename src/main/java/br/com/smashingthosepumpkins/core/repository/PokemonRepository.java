package br.com.smashingthosepumpkins.core.repository;

import br.com.smashingthosepumpkins.core.model.PokemonEntity;
import br.com.smashingthosepumpkins.core.model.PokemonType;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * @author Pablo A. G. Silva Jr. on 27/01/2022
 * @project pokemon-repository-testing
 */
public interface PokemonRepository {
    /**
     * @throws PersistenceException If the entity manager throws this exception, it means that the query timed out.
     *                              When the database is down, the method calls the shutdown service to finalize the application.
     */
    void insertPokemon(PokemonEntity p);

    /**
     * @return Returns a list of pokémon entities that have the desired type, either primarily or secondarily. In case
     * there are no entities with a matching type, it will return an empty list.
     * @throws PersistenceException If the entity manager throws this exception, it means that the query timed out.
     *                              When the database is down, the method calls the shutdown service to finalize the application.
     */
    List<PokemonEntity> findByType(PokemonType type);

    /**
     * @return Returns a list of pokémon entities that have the desired type primarily. In case
     * there are no entities with a matching type, it will return an empty list.
     * @throws PersistenceException If the entity manager throws this exception, it means that the query timed out.
     *                              When the database is down, the method calls the shutdown service to finalize the application.
     */
    List<PokemonEntity> findByPrimaryType(PokemonType type);

    /**
     * @return Returns a list of pokémon entities that have the desired type secondarily. In case
     * there are no entities with a matching type, it will return an empty list.
     * @throws PersistenceException If the entity manager throws this exception, it means that the query timed out.
     *                              When the database is down, the method calls the shutdown service to finalize the application.
     */
    List<PokemonEntity> findBySecondaryType(PokemonType type);

    /**
     * @return Returns a list of all pokémon entities in the database. In case the table is empty, it will return an empty list.
     * @throws PersistenceException If the entity manager throws this exception, it means that the query timed out.
     *                              When the database is down, the method calls the shutdown service to finalize the application.
     */
    List<PokemonEntity> findAllEntries();

    /**
     * Deletes a pokémon from the database.
     *
     * @throws PersistenceException If the entity manager throws this exception, it means that the query timed out.
     *                              When the database is down, the method calls the shutdown service to finalize the application.
     */
    void deleteById(int entityId);
}
