package br.com.smashingthosepumpkins.util;

import br.com.smashingthosepumpkins.TestPokemonInfo;
import br.com.smashingthosepumpkins.core.model.PokemonEntity;

import java.util.List;

import static br.com.smashingthosepumpkins.TestPokemonInfo.BULBASAUR;

/**
 * @author Pablo A. G. Silva Jr. on 01/02/2022
 * @project pokemon-repository-testing
 */
public class PokemonUtil {
    public static PokemonEntity getBasicPokemon(){
        PokemonEntity e = new PokemonEntity();
        e.setNickname("");
        e.setPoisoned(false);
        e.setParalyzed(false);
        e.setAsleep(false);
        e.setLevel(5);
        e.setPokemonInfo(BULBASAUR.asInfo());
        return e;
    }

    public static PokemonEntity buildPokemon(String nickname, TestPokemonInfo profile) {
        PokemonEntity e = getBasicPokemon();
        e.setNickname(nickname);
        e.setPokemonInfo(profile.asInfo());
        return e;
    }

    public static PokemonEntity buildPokemon(String nickname, TestPokemonInfo profile, int level) {
        PokemonEntity e = getBasicPokemon();
        e.setNickname(nickname);
        e.setPokemonInfo(profile.asInfo());
        e.setLevel(level);
        return e;
    }
}
