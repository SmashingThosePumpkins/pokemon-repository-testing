package br.com.smashingthosepumpkins.core.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Pablo A. G. Silva Jr. on 31/01/2022
 * @project pokemon-repository-testing
 */
@Entity
@Table(name = "pokemon_info")
public class PokemonInfo {
    @Id
    @Column(name = "pokedex_id")
    private Integer pokedexId;

    @Column(name = "pokemon_name")
    @Basic
    private String name;

    @ManyToOne
    @JoinColumn(name = "primary_type")
    private PokemonType primaryType;

    @ManyToOne
    @JoinColumn(name = "secondary_type")
    private PokemonType secondaryType;

    public Integer getPokedexId() {
        return pokedexId;
    }

    public void setPokedexId(Integer pokedexId) {
        this.pokedexId = pokedexId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PokemonType getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(PokemonType primaryType) {
        this.primaryType = primaryType;
    }

    public PokemonType getSecondaryType() {
        return secondaryType;
    }

    public void setSecondaryType(PokemonType secondaryType) {
        this.secondaryType = secondaryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonInfo that = (PokemonInfo) o;
        return Objects.equals(pokedexId, that.pokedexId) && Objects.equals(name, that.name) && Objects.equals(primaryType, that.primaryType) && Objects.equals(secondaryType, that.secondaryType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokedexId, name, primaryType, secondaryType);
    }

    @Override
    public String toString() {
        return "[" + pokedexId + "] " + name;
    }
}
