package br.com.smashingthosepumpkins.infra.dao;

import br.com.smashingthosepumpkins.core.model.PokemonEntity;
import br.com.smashingthosepumpkins.core.model.PokemonType;
import br.com.smashingthosepumpkins.core.repository.PokemonRepository;
import br.com.smashingthosepumpkins.util.DependencyUtils;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Random;

import static br.com.smashingthosepumpkins.PokemonTestInfo.*;
import static br.com.smashingthosepumpkins.PokemonTestType.ROCK;
import static br.com.smashingthosepumpkins.util.DatabaseCleaner.clearDatabase;
import static br.com.smashingthosepumpkins.util.RepositoryAssertionUtils.pokemonListContainsAll;
import static br.com.smashingthosepumpkins.util.RepositoryInserter.insert;
import static br.com.smashingthosepumpkins.util.RepositorySetup.loadTestPokemonInfo;
import static br.com.smashingthosepumpkins.util.RepositorySetup.loadTestTypes;
import static br.com.smashingthosepumpkins.util.RepositoryDataBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Pablo A. G. Silva Jr. on 28/01/2022
 * @project pokemon-repository-testing
 */

public class PokemonRepositoryTest {
    private PokemonRepository instance;

    @BeforeEach
    public void beforeEach() {
        clearDatabase();
        loadTestTypes();
        loadTestPokemonInfo();
        instance = new PokemonRepositoryImpl(DependencyUtils.getEntityManagerFactory(),
                DependencyUtils.getShutdownService());
    }

    /**
     * @see PokemonRepository#findAllEntries()
     */
    @Test
    public void whenMultiplePokemonAreInTheDatabase_shouldGetAllThree() {
        insert(buildPokemon("Bulbasaur", BULBASAUR, 5, false, false, false),
                buildPokemon("Charmander", CHARMANDER, 5, false, false, false),
                buildPokemon("Squirtle", SQUIRTLE, 5, false, false, false));

        List<PokemonEntity> result = instance.findAllEntries();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(pokemonListContainsAll(result, BULBASAUR, CHARMANDER, SQUIRTLE));
    }

    /**
     * @see PokemonRepository#findAllEntries()
     */
    @Test
    public void whenOnePokemonIsInTheDatabase_methodShouldReturnIt() {
        insert(buildPokemon("Bulbasaur", BULBASAUR, 5, false, false, false));

        List<PokemonEntity> result = instance.findAllEntries();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(pokemonListContainsAll(result, BULBASAUR));
    }

    /**
     * @see PokemonRepository#findAllEntries()
     */
    @Test
    public void whenNoPokemonAreInTheDatabase_methodShouldReturnEmptyList() {
        List<PokemonEntity> result = instance.findAllEntries();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * @see PokemonRepository#insertPokemon(PokemonEntity)
     */
    @Test
    public void whenOnePokemonIsInserted_ShouldBeInDatabase() {
        instance.insertPokemon(buildPokemon("Pikachu", PIKACHU, 5, false, false, false));

        List<PokemonEntity> result = instance.findAllEntries();
        assertEquals(1, result.size());
        assertTrue(pokemonListContainsAll(result, PIKACHU));
    }

    /**
     * @see PokemonRepository#insertPokemon(PokemonEntity)
     */
    @Test
    public void whenNullPokemonIsInserted_ShouldNotBreak_andNotBeInDatabase() {
        instance.insertPokemon(null);

        List<PokemonEntity> result = instance.findAllEntries();
        assertTrue(result.isEmpty());
    }

    /**
     * @see PokemonRepository#insertPokemon(PokemonEntity)
     */
    @Test
    public void whenTwoSimilarPokemonAreInserted_BothShouldBeInDatabase() {
        instance.insertPokemon(buildPokemon("Pikachu", PIKACHU, 5, false, false, false));
        instance.insertPokemon(buildPokemon("pikachu", PIKACHU, 5, false, false, false));

        List<PokemonEntity> result = instance.findAllEntries();
        assertEquals(2, result.size());
        assertNotEquals(result.get(0), result.get(1));
    }

    /**
     * @see PokemonRepository#findByType(PokemonType)
     */
    @Test
    public void findByTypeScenario1_twoMatchingTypePokemonAndOneNotMatching() {
        insert(buildPokemon("Geodude", GEODUDE, 5, false, false, false),
                buildPokemon("Pinsir", PINSIR, 5, false, false, false),
                buildPokemon("Rhyhorn", RHYHORN, 5, false, false, false));

        List<PokemonEntity> result = instance.findByType(ROCK.asType());

        assertEquals(2, result.size());
        assertTrue(pokemonListContainsAll(result, GEODUDE, RHYHORN));
    }

    /**
     * @see PokemonRepository#findByType(PokemonType)
     */
    @Test
    public void findByTypeScenario2_noneMatch() {
        insert(buildPokemon("Kangaskhan", KANGASKHAN, 5, false, false, false),
                buildPokemon("Pinsir", PINSIR, 5, false, false, false));

        List<PokemonEntity> result = instance.findByType(ROCK.asType());

        assertTrue(result.isEmpty());
    }

    /**
     * @see PokemonRepository#findByType(PokemonType)
     */
    @Test
    public void findByTypeScenario3_oneMatchingTypePokemonAndTwoNotMatching() {
        insert(buildPokemon("Golem", GOLEM, 5, false, false, false),
                buildPokemon("Kangaskhan", KANGASKHAN, 5, false, false, false),
                buildPokemon("Pinsir", PINSIR, 5, false, false, false));

        List<PokemonEntity> result = instance.findByType(ROCK.asType());

        assertEquals(1, result.size());
        assertTrue(pokemonListContainsAll(result, GOLEM));
    }

    /**
     * @see PokemonRepository#findByType(PokemonType)
     */
    @Test
    public void findByTypeScenario4_onlyOneMatchingTypePokemonInRepo() {
        insert(buildPokemon("Golem", GOLEM, 5, false, false, false));

        List<PokemonEntity> result = instance.findByType(ROCK.asType());

        assertEquals(1, result.size());
        assertTrue(pokemonListContainsAll(result, GOLEM));
    }

    /**
     * @see PokemonRepository#findByType(PokemonType)
     */
    @Test
    public void findByTypeScenario5_onlyTwoMatchingTypePokemonInRepo() {
        insert(buildPokemon("Golem", GOLEM, 5, false, false, false),
                buildPokemon("Rhyhorn", RHYHORN, 5, false, false, false));

        List<PokemonEntity> result = instance.findByType(ROCK.asType());

        assertEquals(2, result.size());
        assertTrue(pokemonListContainsAll(result, GOLEM, RHYHORN));
    }

    /**
     * @see PokemonRepository#findByType(PokemonType)
     */
    @Test
    public void findByTypeScenario6_emptyDb() {
        List<PokemonEntity> result = instance.findByType(ROCK.asType());

        assertTrue(result.isEmpty());
    }

    /**
     * @see PokemonRepository#findByPrimaryType(PokemonType)
     */
    @Test
    public void findByPrimaryTypeScenario1_oneMatchingTypeAndOneNotMatching() {
        insert(buildPokemon("Golem", GOLEM, 5, false, false, false),        // Rock, Ground
                buildPokemon("Rhyhorn", RHYHORN, 5, false, false, false));  // Ground, Rock

        List<PokemonEntity> result = instance.findByPrimaryType(ROCK.asType());

        assertEquals(1, result.size());
        assertTrue(pokemonListContainsAll(result, GOLEM));
    }

    /**
     * @see PokemonRepository#findByPrimaryType(PokemonType)
     */
    @Test
    public void findByPrimaryTypeScenario2_onlyOneMatchingType() {
        insert(buildPokemon("Golem", GOLEM, 5, false, false, false));       // Rock, Ground

        List<PokemonEntity> result = instance.findByPrimaryType(ROCK.asType());

        assertEquals(1, result.size());
        assertTrue(pokemonListContainsAll(result, GOLEM));
    }

    /**
     * @see PokemonRepository#findByPrimaryType(PokemonType)
     */
    @Test
    public void findByPrimaryTypeScenario3_twoMatchingTypes() {
        insert(buildPokemon("Golem", GOLEM, 5, false, false, false),        // Rock, Ground
                buildPokemon("Onix", ONIX, 5, false, false, false));        // Rock, Ground

        List<PokemonEntity> result = instance.findByPrimaryType(ROCK.asType());

        assertEquals(2, result.size());
        assertTrue(pokemonListContainsAll(result, GOLEM, ONIX));
    }

    /**
     * @see PokemonRepository#findByPrimaryType(PokemonType)
     */
    @Test
    public void findByPrimaryTypeScenario4_noneMatch() {
        insert(buildPokemon("Rhyhorn", RHYHORN, 5, false, false, false));   // Ground, Rock

        List<PokemonEntity> result = instance.findByPrimaryType(ROCK.asType());

        assertTrue(result.isEmpty());
    }

    /**
     * @see PokemonRepository#findByPrimaryType(PokemonType)
     */
    @Test
    public void findByPrimaryTypeScenario5_emptyDb() {
        List<PokemonEntity> result = instance.findByPrimaryType(ROCK.asType());

        assertTrue(result.isEmpty());
    }

    /**
     * @see PokemonRepository#findBySecondaryType(PokemonType)
     */
    @Test
    public void findBySecondaryTypeScenario1_oneMatchingTypeAndOneNotMatching() {
        insert(buildPokemon("Golem", GOLEM, 5, false, false, false),        // Rock, Ground
                buildPokemon("Rhyhorn", RHYHORN, 5, false, false, false));  // Ground, Rock

        List<PokemonEntity> result = instance.findBySecondaryType(ROCK.asType());

        assertEquals(1, result.size());
        assertTrue(pokemonListContainsAll(result, RHYHORN));
    }

    /**
     * @see PokemonRepository#findBySecondaryType(PokemonType)
     */
    @Test
    public void findBySecondaryTypeScenario2_onlyOneMatchingType() {
        insert(buildPokemon("Rhyhorn", RHYHORN, 5, false, false, false));       // Ground, Rock

        List<PokemonEntity> result = instance.findBySecondaryType(ROCK.asType());

        assertEquals(1, result.size());
        assertTrue(pokemonListContainsAll(result, RHYHORN));
    }

    /**
     * @see PokemonRepository#findBySecondaryType(PokemonType)
     */
    @Test
    public void findBySecondaryTypeScenario3_twoMatchingTypes() {
        insert(buildPokemon("Rhyhorn", RHYHORN, 5, false, false, false),        // Ground, Rock
                buildPokemon("Rhydon", RHYDON, 5, false, false, false));        // Ground, Rock

        List<PokemonEntity> result = instance.findBySecondaryType(ROCK.asType());

        assertEquals(2, result.size());
        assertTrue(pokemonListContainsAll(result, RHYHORN, RHYDON));
    }

    /**
     * @see PokemonRepository#findBySecondaryType(PokemonType)
     */
    @Test
    public void findBySecondaryTypeScenario4_noneMatch() {
        insert(buildPokemon("Golem", GOLEM, 5, false, false, false));   // Rock, Ground

        List<PokemonEntity> result = instance.findBySecondaryType(ROCK.asType());

        assertTrue(result.isEmpty());
    }

    /**
     * @see PokemonRepository#findBySecondaryType(PokemonType)
     */
    @Test
    public void findBySecondaryTypeScenario5_emptyDb() {
        List<PokemonEntity> result = instance.findBySecondaryType(ROCK.asType());

        assertTrue(result.isEmpty());
    }

    /**
     * @see PokemonRepository#deleteById(int)
     */
    @Test
    public void deleteMethodShouldDeleteRepositoryPokemon() {
        PokemonEntity pikachu = buildPokemon("pikachu", PIKACHU, 5, false, false, false);
        insert(pikachu);

        instance.deleteById(pikachu.getId());

        List<PokemonEntity> result = instance.findAllEntries();
        assertTrue(result.isEmpty());
    }

    /**
     * @see PokemonRepository#deleteById(int)
     */
    @Test
    public void whenDeleteMethodIsCalled_ShouldOnlyDeleteDesiredPokemon() {
        PokemonEntity pikachu = buildPokemon("pikachu", PIKACHU, 5, false, false, false);
        insert(pikachu, buildPokemon("raichu", RAICHU, 5, false, false, false));

        instance.deleteById(pikachu.getId());

        List<PokemonEntity> result = instance.findAllEntries();
        assertEquals(1, result.size());
        assertTrue(pokemonListContainsAll(result, RAICHU));
    }

    /**
     * @see PokemonRepository#deleteById(int)
     */
    @Test
    public void whenNonExistentPokemonIsRequestedForDeletion_ShouldNotBreak() {
        instance.deleteById(new Random().nextInt(1000));
    }
}