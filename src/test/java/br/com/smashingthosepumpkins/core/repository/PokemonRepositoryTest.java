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
        PokemonEntity machoke = buildPokemon("Machoke", MACHOKE, 35);
        PokemonEntity raichu = buildPokemon("Raichu", RAICHU, 25);
        PokemonEntity pinsir = buildPokemon("Pinsir", PINSIR, 60);
        PokemonEntity kabuto = buildPokemon("Kabuto", KABUTO, 30);
        PokemonEntity ninetales = buildPokemon("Ninetales", NINETALES, 40);
        PokemonEntity dewgong = buildPokemon("Dewgong", DEWGONG, 40);
        List<PokemonEntity> list = Arrays.asList(machoke, raichu, pinsir, kabuto, ninetales, dewgong);
        when(daoMock.getAllEntries()).thenReturn(list);

        PokemonEntity result = instance.getHighestLevel();

        assertNotNull(result);
        assertEquals(PINSIR.asInfo(), result.getPokemonInfo());
    }

    @Test
    public void whenHighestLevelMethodIsCalled_shouldReturnPinsirNinetalesAndDewgong() {
        PokemonEntity machoke = buildPokemon("Machoke", MACHOKE, 35);
        PokemonEntity raichu = buildPokemon("Raichu", RAICHU, 25);
        PokemonEntity pinsir = buildPokemon("Pinsir", PINSIR, 60);
        PokemonEntity kabuto = buildPokemon("Kabuto", KABUTO, 30);
        PokemonEntity ninetales = buildPokemon("Ninetales", NINETALES, 40);
        PokemonEntity dewgong = buildPokemon("Dewgong", DEWGONG, 40);
        List<PokemonEntity> list = Arrays.asList(machoke, raichu, pinsir, kabuto, ninetales, dewgong);
        when(daoMock.getAllEntries()).thenReturn(list);

        List<PokemonEntity> result = instance.getHighestLevel(3);

        assertNotNull(result);
        assertFalse(result.contains(machoke));
        assertFalse(result.contains(raichu));
        assertTrue(result.contains(pinsir));
        assertFalse(result.contains(kabuto));
        assertTrue(result.contains(ninetales));
        assertTrue(result.contains(dewgong));
    }

    @Test
    public void whenLowestLevelMethodIsCalled_shouldReturnRaichu() {
        PokemonEntity machoke = buildPokemon("Machoke", MACHOKE, 35);
        PokemonEntity raichu = buildPokemon("Raichu", RAICHU, 25);
        PokemonEntity pinsir = buildPokemon("Pinsir", PINSIR, 60);
        PokemonEntity kabuto = buildPokemon("Kabuto", KABUTO, 30);
        PokemonEntity ninetales = buildPokemon("Ninetales", NINETALES, 40);
        PokemonEntity dewgong = buildPokemon("Dewgong", DEWGONG, 40);
        List<PokemonEntity> list = Arrays.asList(machoke, raichu, pinsir, kabuto, ninetales, dewgong);
        when(daoMock.getAllEntries()).thenReturn(list);

        PokemonEntity result = instance.getLowestLevel();

        assertNotNull(result);
        assertEquals(raichu, result);
    }

    @Test
    public void whenLowestLevelMethodIsCalled_shouldReturnRaichuKabutoAndMachoke() {
        PokemonEntity machoke = buildPokemon("Machoke", MACHOKE, 35);
        PokemonEntity raichu = buildPokemon("Raichu", RAICHU, 25);
        PokemonEntity pinsir = buildPokemon("Pinsir", PINSIR, 60);
        PokemonEntity kabuto = buildPokemon("Kabuto", KABUTO, 30);
        PokemonEntity ninetales = buildPokemon("Ninetales", NINETALES, 40);
        PokemonEntity dewgong = buildPokemon("Dewgong", DEWGONG, 40);
        List<PokemonEntity> list = Arrays.asList(machoke, raichu, pinsir, kabuto, ninetales, dewgong);
        when(daoMock.getAllEntries()).thenReturn(list);

        List<PokemonEntity> result = instance.getLowestLevel(3);

        assertNotNull(result);
        assertTrue(result.contains(machoke));
        assertTrue(result.contains(raichu));
        assertFalse(result.contains(pinsir));
        assertTrue(result.contains(kabuto));
        assertFalse(result.contains(ninetales));
        assertFalse(result.contains(dewgong));
    }

    @Test
    public void removeMethod_shouldCallDAODeleteMethod() {
        when(daoMock.deleteById(anyInt())).thenReturn(true); // Mocking successful deletion

        instance.removePokemon(new Random().nextInt(1000));

        verify(daoMock, times(1)).deleteById(anyInt());
    }
}