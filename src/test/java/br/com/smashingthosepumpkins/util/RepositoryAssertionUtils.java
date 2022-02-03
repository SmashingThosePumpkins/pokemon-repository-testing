package br.com.smashingthosepumpkins.util;

import br.com.smashingthosepumpkins.PokemonTestInfo;
import br.com.smashingthosepumpkins.PokemonTestType;
import br.com.smashingthosepumpkins.core.model.PokemonEntity;

import java.util.List;

/**
 * @author Pablo A. G. Silva Jr. on 04/02/2022
 * @project pokemon-repository-testing
 */
public class RepositoryAssertionUtils {
    public static boolean pokemonListContainsAll(List<PokemonEntity> list, PokemonEntity... entities) {
        for (PokemonEntity e : entities) {
            if (!list.contains(e)) {
                return false;
            }
        }
        return true;
    }

    public static boolean pokemonListContainsAll(List<PokemonEntity> list, PokemonTestInfo... entities) {
        for (PokemonTestInfo e : entities) {
            if (list.stream().noneMatch(l -> l.getPokemonInfo().equals(e.asInfo()))) {
                return false;
            }
        }
        return true;
    }
}
