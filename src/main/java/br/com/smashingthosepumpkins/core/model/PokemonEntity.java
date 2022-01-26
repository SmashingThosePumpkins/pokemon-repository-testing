package br.com.smashingthosepumpkins.core.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Pablo A. G. Silva Jr. on 31/01/2022
 * @project pokemon-repository-testing
 */
@Entity
@Table(name = "pokemon_entity")
public class PokemonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pokedex_id")
    private PokemonInfo pokemonInfo;

    @Basic
    @Column(name = "nickname")
    private String nickname;

    @Basic
    @Column(name = "level")
    private Integer level;

    @Basic
    @Column(name = "is_poisoned")
    private Boolean isPoisoned;

    @Basic
    @Column(name = "is_paralyzed")
    private Boolean isParalyzed;

    @Basic
    @Column(name = "is_asleep")
    private Boolean isAsleep;

    public Integer getId() {
        return id;
    }

    public PokemonInfo getPokemonInfo() {
        return pokemonInfo;
    }

    public void setPokemonInfo(PokemonInfo pokemonInfo) {
        this.pokemonInfo = pokemonInfo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getPoisoned() {
        return isPoisoned;
    }

    public void setPoisoned(Boolean poisoned) {
        isPoisoned = poisoned;
    }

    public Boolean getParalyzed() {
        return isParalyzed;
    }

    public void setParalyzed(Boolean paralyzed) {
        isParalyzed = paralyzed;
    }

    public Boolean getAsleep() {
        return isAsleep;
    }

    public void setAsleep(Boolean asleep) {
        isAsleep = asleep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonEntity entity = (PokemonEntity) o;
        return Objects.equals(id, entity.id) && Objects.equals(pokemonInfo, entity.pokemonInfo) && Objects.equals(nickname, entity.nickname) && Objects.equals(level, entity.level) && Objects.equals(isPoisoned, entity.isPoisoned) && Objects.equals(isParalyzed, entity.isParalyzed) && Objects.equals(isAsleep, entity.isAsleep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pokemonInfo, nickname, level, isPoisoned, isParalyzed, isAsleep);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (level != null) {
            builder.append("Level ").append(level);
        }
        builder.append(" ").append(pokemonInfo.getName());
        if (nickname != null) {
            builder.append(" nicknamed ").append(nickname);
        }
        return builder.toString();
    }
}
