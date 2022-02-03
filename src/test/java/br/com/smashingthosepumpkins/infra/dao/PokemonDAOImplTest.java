package br.com.smashingthosepumpkins.infra.dao;

import br.com.smashingthosepumpkins.core.model.PokemonEntity;
import br.com.smashingthosepumpkins.util.PokemonDbUtil;
import br.com.smashingthosepumpkins.util.DependencyUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Random;

import static br.com.smashingthosepumpkins.TestPokemonInfo.*;
import static br.com.smashingthosepumpkins.TestPokemonType.*;
import static br.com.smashingthosepumpkins.util.PokemonUtil.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Pablo A. G. Silva Jr. on 28/01/2022
 * @project pokemon-repository-testing
 */

public class PokemonDAOImplTest {
    private EntityManager entityManager;
    private Session session;

    private PokemonDAO instance;

    @BeforeEach
    public void beforeEach() {
        PokemonDbUtil.clearDatabase();
        PokemonDbUtil.loadTestTypes();
        PokemonDbUtil.loadTestPokemonInfo();
        entityManager = DependencyUtil.createEntityManager();
        session = DependencyUtil.openHibernateSession();
        instance = new PokemonDAOImpl(DependencyUtil.getEntityManagerFactory());
    }

    @AfterEach
    public void afterEach() {
        entityManager.close();
        session.close();
    }

    @Test
    public void shouldGetAllInsertedPokemon() {
        PokemonEntity marowak = buildPokemon("Marowak", MAROWAK);
        PokemonEntity kangaskhan = buildPokemon("Kangaskhan", KANGASKHAN);
        PokemonEntity gengar = buildPokemon("Gengar", GENGAR);
        PokemonEntity zapdos = buildPokemon("Zapdos", ZAPDOS);
        PokemonEntity mew = buildPokemon("Mew", MEW);
        session.beginTransaction();
        session.save(marowak);
        session.save(kangaskhan);
        session.save(gengar);
        session.save(zapdos);
        session.save(mew);
        session.flush();
        session.clear();

        List<PokemonEntity> result = instance.getAllEntries();

        assertTrue(result.contains(marowak));
        assertTrue(result.contains(kangaskhan));
        assertTrue(result.contains(gengar));
        assertTrue(result.contains(zapdos));
        assertTrue(result.contains(mew));
    }

    @Test
    public void shouldInsertPikachu() {
        PokemonEntity pikachu = buildPokemon("Pikachu", PIKACHU);
        instance.insertPokemon(pikachu);

        List<PokemonEntity> result = instance.getAllEntries();
        assertNotNull(result);
        assertNotNull(result.get(0));
        assertEquals("Pikachu", result.get(0).getNickname());
    }

    @Test
    public void shouldInsertAllPokemon() {
        PokemonEntity pikachu = buildPokemon("Pikachu", PIKACHU);
        PokemonEntity vulpix = buildPokemon("vulpix", VULPIX);
        PokemonEntity poliwag = buildPokemon("Poliwag", POLIWAG);
        PokemonEntity eevee = buildPokemon("Eevee", EEVEE);
        instance.insertPokemon(pikachu, vulpix, poliwag, eevee);

        List<PokemonEntity> result = instance.getAllEntries();
        assertNotNull(result);
        assertEquals(4, result.size());

        assertTrue(result.contains(pikachu));
        assertTrue(result.contains(vulpix));
        assertTrue(result.contains(poliwag));
        assertTrue(result.contains(eevee));
    }

    @Test
    public void whenRequestedRockTypePokemonThroughGetByType_ShouldReturnAllRockPokemon_AndNotReturnPinsir() {
        PokemonEntity geodude = buildPokemon("Geodude", GEODUDE);
        PokemonEntity pinsir = buildPokemon("Pinsir", PINSIR);
        PokemonEntity rhyhorn = buildPokemon("Rhyhorn", RHYHORN);
        PokemonEntity rhyhorn2 = buildPokemon("Rhyhorn 2", RHYHORN);
        instance.insertPokemon(rhyhorn, rhyhorn2, pinsir, geodude);

        List<PokemonEntity> result = instance.getByType(ROCK.asType());

        assertNotNull(result);
        assertEquals(3, result.size());

        // All results should have rock type, primary or secondary
        assertTrue(result.stream().anyMatch(pokemon ->
                pokemon.getPokemonInfo().getPrimaryType().equals(ROCK.asType()) ||
                        pokemon.getPokemonInfo().getSecondaryType().equals(ROCK.asType())
        ), "There were non-rock type pokemon inside the result list.");

        assertTrue(result.contains(geodude));
        assertTrue(result.contains(rhyhorn));
        assertTrue(result.contains(rhyhorn2));
        assertFalse(result.contains(pinsir));
    }

    @Test
    public void whenRequestedPoisonTypePokemonThroughGetByPrimaryType_ShouldReturnAllPoisonPokemon_AndNotReturnSecondaries() {
        PokemonEntity ekans = buildPokemon("Ekans", EKANS);
        PokemonEntity venonat = buildPokemon("Venonat", VENONAT);
        PokemonEntity bulbasaur = buildPokemon("Bulbassauro", BULBASAUR);
        PokemonEntity zubat = buildPokemon("zubat", ZUBAT);
        instance.insertPokemon(bulbasaur, zubat, venonat, ekans);

        List<PokemonEntity> result = instance.getByPrimaryType(POISON.asType());

        assertNotNull(result);
        assertEquals(2, result.size());

        assertTrue(result.contains(ekans));
        assertFalse(result.contains(venonat));
        assertFalse(result.contains(bulbasaur));
        assertTrue(result.contains(zubat));
    }

    @Test
    public void whenRequestedPoisonTypePokemonThroughGetBySecondaryType_ShouldReturnAllPoisonPokemon_AndNotReturnPrimaries() {
        PokemonEntity ekans = buildPokemon("Ekans", EKANS);
        PokemonEntity venonat = buildPokemon("Venonat", VENONAT);
        PokemonEntity bulbasaur = buildPokemon("Bulbassauro", BULBASAUR);
        PokemonEntity zubat = buildPokemon("zubat", ZUBAT);
        instance.insertPokemon(bulbasaur, zubat, venonat, ekans);

        List<PokemonEntity> result = instance.getBySecondaryType(POISON.asType());

        assertNotNull(result);
        assertEquals(2, result.size());

        assertFalse(result.contains(ekans));
        assertTrue(result.contains(venonat));
        assertTrue(result.contains(bulbasaur));
        assertFalse(result.contains(zubat));
    }

    @Test
    public void shouldReturnAllInsertedPokemon() {
        PokemonEntity ekans = buildPokemon("Ekans", EKANS);
        PokemonEntity venonat = buildPokemon("Venonat", VENONAT);
        PokemonEntity bulbasaur = buildPokemon("Bulbassauro", BULBASAUR);
        PokemonEntity zubat = buildPokemon("zubat", ZUBAT);
        PokemonEntity pikachu = buildPokemon("Pikachu", PIKACHU);
        PokemonEntity vulpix = buildPokemon("vulpix", VULPIX);
        PokemonEntity poliwag = buildPokemon("Poliwag", POLIWAG);
        PokemonEntity eevee = buildPokemon("Eevee", EEVEE);
        instance.insertPokemon(bulbasaur, zubat, venonat, ekans, pikachu, vulpix, poliwag, eevee);

        List<PokemonEntity> result = instance.getAllEntries();

        assertNotNull(result);
        assertEquals(8, result.size());

        assertTrue(result.contains(ekans));
        assertTrue(result.contains(venonat));
        assertTrue(result.contains(bulbasaur));
        assertTrue(result.contains(zubat));
        assertTrue(result.contains(pikachu));
        assertTrue(result.contains(vulpix));
        assertTrue(result.contains(poliwag));
        assertTrue(result.contains(eevee));
    }

    @Test
    public void shouldDeleteCharmander() {
        PokemonEntity bulbasaur = buildPokemon("Bulbasaur", BULBASAUR);
        PokemonEntity charmander = buildPokemon("Charmander", CHARMANDER);
        PokemonEntity squirtle = buildPokemon("squirtle", SQUIRTLE);
        instance.insertPokemon(bulbasaur, charmander, squirtle);

        boolean result = instance.deleteById(charmander.getId());
        List<PokemonEntity> dbList = instance.getAllEntries();

        assertTrue(result, "No entries were deleted.");
        assertTrue(dbList.contains(bulbasaur));
        assertFalse(dbList.contains(charmander));
        assertTrue(dbList.contains(squirtle));
    }

    @Test
    public void deleteMethodShouldNotBreakWhenSpecifiedPokemonDoesntExist() {
        // * -- table is empty -- * //
        boolean result = instance.deleteById(new Random().nextInt(1000));

        List<PokemonEntity> dbList = instance.getAllEntries();
        assertTrue(dbList.isEmpty());
        assertFalse(result, "Delete method returned true, meaning someting was deleted.");
    }

    @Test
    public void shouldDeleteCharmanderCharmeleonAndCharizard() {
        PokemonEntity bulbasaur = buildPokemon("Bulbasaur", BULBASAUR);
        PokemonEntity ivysaur = buildPokemon("Ivysaur", IVYSAUR);
        PokemonEntity venusaur = buildPokemon("Venusaur", VENUSAUR);
        PokemonEntity charmander = buildPokemon("Charmander", CHARMANDER);
        PokemonEntity charmeleon = buildPokemon("Charmeleon", CHARMELEON);
        PokemonEntity charizard = buildPokemon("charizard", CHARIZARD);
        PokemonEntity squirtle = buildPokemon("squirtle", SQUIRTLE);
        PokemonEntity wartortle = buildPokemon("wartortle", WARTORTLE);
        PokemonEntity blastoise = buildPokemon("blastoise", BLASTOISE);
        instance.insertPokemon(bulbasaur, ivysaur, venusaur, charmander, charmeleon, charizard, squirtle, wartortle, blastoise);

        boolean result = instance.deleteById(charmander.getId(), charmeleon.getId(), charizard.getId());
        List<PokemonEntity> dbList = instance.getAllEntries();

        assertTrue(result, "No entries were deleted.");
        assertTrue(dbList.contains(bulbasaur));
        assertTrue(dbList.contains(ivysaur));
        assertTrue(dbList.contains(venusaur));
        assertFalse(dbList.contains(charmander));
        assertFalse(dbList.contains(charmeleon));
        assertFalse(dbList.contains(charizard));
        assertTrue(dbList.contains(squirtle));
        assertTrue(dbList.contains(wartortle));
        assertTrue(dbList.contains(blastoise));
    }
}














































































































































