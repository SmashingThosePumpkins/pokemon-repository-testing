package br.com.smashingthosepumpkins.util;

import br.com.smashingthosepumpkins.PokemonTestInfo;
import br.com.smashingthosepumpkins.core.model.PokemonEntity;

/**
 * @author Pablo A. G. Silva Jr. on 01/02/2022
 * @project pokemon-repository-testing
 */
public class RepositoryDataBuilder {
    public static PokemonEntity buildPokemon(String nickname, PokemonTestInfo profile, int level, boolean isPoisoned, boolean isParalyzed, boolean isAsleep) {
        PokemonEntity e = new PokemonEntity();
        e.setNickname(nickname);
        e.setPoisoned(isPoisoned);
        e.setParalyzed(isParalyzed);
        e.setAsleep(isAsleep);
        e.setLevel(level);
        e.setPokemonInfo(profile.asInfo());
        return e;
    }
}
