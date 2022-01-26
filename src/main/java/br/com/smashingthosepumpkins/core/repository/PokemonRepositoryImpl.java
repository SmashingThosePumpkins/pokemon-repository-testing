package br.com.smashingthosepumpkins.core.repository;

import br.com.smashingthosepumpkins.core.model.PokemonEntity;
import br.com.smashingthosepumpkins.infra.dao.PokemonDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.reverse;
import static java.util.Comparator.comparingInt;

/**
 * This layer exists to apply business rules on the Data Access Object query results, such as filtering through results
 * and validating them, for example.
 *
 * @author Pablo A. G. Silva Jr. on 27/01/2022
 * @project pokemon-repository-testing
 */
@Service
public class PokemonRepositoryImpl implements PokemonRepository {
    private final PokemonDAO pokemonDAO;

    @Inject
    public PokemonRepositoryImpl(PokemonDAO pokemonDAO) {
        this.pokemonDAO = pokemonDAO;
    }

    @Override
    public boolean insertPokemon(PokemonEntity... entity) {
        return pokemonDAO.insertPokemon(entity);
    }

    @Override
    public List<PokemonEntity> getAll() {
        return pokemonDAO.getAllEntries();
    }

    @Override
    public List<PokemonEntity> getHighestLevel(int numberOfResults) {
        List<PokemonEntity> entities = pokemonDAO.getAllEntries();
        entities.sort(comparingInt(PokemonEntity::getLevel));
        reverse(entities);

        List<PokemonEntity> result = new ArrayList<>();
        entities.forEach(entity -> {
            if (numberOfResults > (entities).indexOf(entity)) {
                result.add(entity);
            }
        });
        return result;
    }

    @Override
    public List<PokemonEntity> getLowestLevel(int numberOfResults) {
        List<PokemonEntity> entities = pokemonDAO.getAllEntries();
        entities.sort(comparingInt(PokemonEntity::getLevel));

        List<PokemonEntity> result = new ArrayList<>();
        entities.forEach(entity -> {
            if (numberOfResults > (entities).indexOf(entity)) {
                result.add(entity);
            }
        });
        return result;
    }

    @Override
    public PokemonEntity getHighestLevel() {
        return getHighestLevel(1).get(0);
    }

    @Override
    public PokemonEntity getLowestLevel() {
        return getLowestLevel(1).get(0);
    }

    @Override
    public boolean removePokemon(int id) {
        return pokemonDAO.deleteById(id);
    }
}
