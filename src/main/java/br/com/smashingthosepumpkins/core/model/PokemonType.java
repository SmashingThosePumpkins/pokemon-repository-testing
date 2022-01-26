package br.com.smashingthosepumpkins.core.model;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Pablo A. G. Silva Jr. on 31/01/2022
 * @project pokemon-repository-testing
 */
@Entity
@Table(name = "pokemon_type")
public class PokemonType {
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonType type = (PokemonType) o;
        return Objects.equals(id, type.id) && Objects.equals(name, type.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
