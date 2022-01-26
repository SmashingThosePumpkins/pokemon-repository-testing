package br.com.smashingthosepumpkins.infra.dao;

import br.com.smashingthosepumpkins.core.model.PokemonEntity;
import br.com.smashingthosepumpkins.core.model.PokemonType;

import java.util.List;

/**
 * @author Pablo A. G. Silva Jr. on 27/01/2022
 * @project pokemon-repository-testing
 */
public interface PokemonDAO {
    boolean insertPokemon(PokemonEntity... entities);
    List<PokemonEntity> getByType(PokemonType type);
    List<PokemonEntity> getByPrimaryType(PokemonType type);
    List<PokemonEntity> getBySecondaryType(PokemonType type);
    List<PokemonEntity> getAllEntries();
    boolean deleteById(int... pokemonEntityId);
}
