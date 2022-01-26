package br.com.smashingthosepumpkins.core.repository;

import br.com.smashingthosepumpkins.core.model.PokemonEntity;

import java.util.List;

/**
 * @author Pablo A. G. Silva Jr. on 27/01/2022
 * @project pokemon-repository-testing
 */
public interface PokemonRepository {
    boolean insertPokemon(PokemonEntity... entity);

    List<PokemonEntity> getHighestLevel(int numberOfResults);
    PokemonEntity getHighestLevel();
    List<PokemonEntity> getLowestLevel(int numberOfResults);
    PokemonEntity getLowestLevel();

    List<PokemonEntity> getAll();

    boolean removePokemon(int id);
}
