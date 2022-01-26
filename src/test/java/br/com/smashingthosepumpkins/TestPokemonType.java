package br.com.smashingthosepumpkins;

import br.com.smashingthosepumpkins.core.model.PokemonType;

/**
 * @author Pablo A. G. Silva Jr. on 31/01/2022
 * @project pokemon-repository-testing
 */
public enum TestPokemonType {
    NORMAL(1),
    FIRE(2),
    WATER(3),
    GRASS(4),
    ELECTRIC(5),
    ICE(6),
    FIGHTING(7),
    POISON(8),
    GROUND(9),
    FLYING(10),
    PSYCHIC(11),
    BUG(12),
    ROCK(13),
    GHOST(14),
    DARK(15),
    DRAGON(16),
    STEEL(17),
    FAIRY(18),
    UNKNOWN(100);

    private final int id;

    TestPokemonType(int id) {
        this.id = id;
    }

    public PokemonType asType() {
        PokemonType type = new PokemonType();
        type.setName(name());
        type.setId(id);
        return type;
    }
}
