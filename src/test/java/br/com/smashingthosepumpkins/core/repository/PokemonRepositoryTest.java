package br.com.smashingthosepumpkins.core.repository;

import br.com.smashingthosepumpkins.core.model.PokemonEntity;
import br.com.smashingthosepumpkins.infra.dao.PokemonDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static br.com.smashingthosepumpkins.TestPokemonInfo.*;
import static br.com.smashingthosepumpkins.util.PokemonUtil.buildPokemon;
import static br.com.smashingthosepumpkins.util.PokemonUtil.pokemonListContains;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Pablo A. G. Silva Jr. on 02/02/2022
 * @project pokemon-repository-testing
 */
class PokemonRepositoryTest {
    private PokemonRepository instance;
    private PokemonDAO daoMock;

    @BeforeEach
    public void beforeEach() {
        daoMock = mock(PokemonDAO.class);
        instance = new PokemonRepositoryImpl(daoMock);
    }

    @AfterEach
    public void afterEach() {
    }

    @Test
    public void whenHighestLevelMethodIsCalled_shouldReturnPinsir() {
        List<PokemonEntity> list = Arrays.asList(
                buildPokemon("Machoke", MACHOKE, 35),
                buildPokemon("Raichu", RAICHU, 25),
                buildPokemon("Pinsir", PINSIR, 60),
                buildPokemon("Kabuto", KABUTO, 30),
                buildPokemon("Ninetales", NINETALES, 40),
                buildPokemon("Dewgong", DEWGONG, 40)
        );
        when(daoMock.getAllEntries()).thenReturn(list);

        PokemonEntity result = instance.getHighestLevel();

        assertNotNull(result);
        assertEquals(PINSIR.asInfo(), result.getPokemonInfo());
    }

    @Test
    public void whenHighestLevelMethodIsCalled_shouldReturnPinsirNinetalesAndDewgong() {
        List<PokemonEntity> list = Arrays.asList(
                buildPokemon("Machoke", MACHOKE, 35),
                buildPokemon("Raichu", RAICHU, 25),
                buildPokemon("Pinsir", PINSIR, 60),
                buildPokemon("Kabuto", KABUTO, 30),
                buildPokemon("Ninetales", NINETALES, 40),
                buildPokemon("Dewgong", DEWGONG, 40)
        );
        when(daoMock.getAllEntries()).thenReturn(list);

        List<PokemonEntity> result = instance.getHighestLevel(3);

        assertNotNull(result);
        assertTrue(pokemonListContains(result, PINSIR));
        assertTrue(pokemonListContains(result, NINETALES));
        assertTrue(pokemonListContains(result, DEWGONG));
    }

    @Test
    public void whenLowestLevelMethodIsCalled_shouldReturnRaichu() {
        List<PokemonEntity> list = Arrays.asList(
                buildPokemon("Machoke", MACHOKE, 35),
                buildPokemon("Raichu", RAICHU, 25),
                buildPokemon("Pinsir", PINSIR, 60),
                buildPokemon("Kabuto", KABUTO, 30),
                buildPokemon("Ninetales", NINETALES, 40),
                buildPokemon("Dewgong", DEWGONG, 40)
        );
        when(daoMock.getAllEntries()).thenReturn(list);

        PokemonEntity result = instance.getLowestLevel();

        assertNotNull(result);
        assertEquals(RAICHU.asInfo(), result.getPokemonInfo());
    }

    @Test
    public void whenLowestLevelMethodIsCalled_shouldReturnRaichuKabutoAndMachoke() {
        List<PokemonEntity> list = Arrays.asList(
                buildPokemon("Machoke", MACHOKE, 35),
                buildPokemon("Raichu", RAICHU, 25),
                buildPokemon("Pinsir", PINSIR, 60),
                buildPokemon("Kabuto", KABUTO, 30),
                buildPokemon("Ninetales", NINETALES, 40),
                buildPokemon("Dewgong", DEWGONG, 40)
        );
        when(daoMock.getAllEntries()).thenReturn(list);

        List<PokemonEntity> result = instance.getLowestLevel(3);

        assertNotNull(result);
        assertTrue(pokemonListContains(result, RAICHU));
        assertTrue(pokemonListContains(result, KABUTO));
        assertTrue(pokemonListContains(result, MACHOKE));
    }

    @Test
    public void removeMethod_shouldCallDAODeleteMethod() {
        when(daoMock.deleteById(anyInt())).thenReturn(true); // Mocking successful deletion

        instance.removePokemon(new Random().nextInt(1000));

        verify(daoMock, times(1)).deleteById(anyInt());
    }
}