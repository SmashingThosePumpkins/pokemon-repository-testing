package br.com.smashingthosepumpkins;

import br.com.smashingthosepumpkins.core.model.PokemonInfo;

import static br.com.smashingthosepumpkins.PokemonTestType.*;

/**
 * @author Pablo A. G. Silva Jr. on 31/01/2022
 * @project pokemon-repository-testing
 */
public enum PokemonTestInfo {
    BULBASAUR(1, GRASS, POISON),
    IVYSAUR(2, GRASS, POISON),
    VENUSAUR(3, GRASS, POISON),
    CHARMANDER(4, FIRE, UNKNOWN),
    CHARMELEON(5, FIRE, UNKNOWN),
    CHARIZARD(6, FIRE, FLYING),
    SQUIRTLE(7, WATER, UNKNOWN),
    WARTORTLE(8, WATER, UNKNOWN),
    BLASTOISE(9, WATER, UNKNOWN),
    CATERPIE(10, BUG, UNKNOWN),
    METAPOD(11, BUG, UNKNOWN),
    BUTTERFREE(12, BUG, FLYING),
    WEEDLE(13, BUG, POISON),
    KAKUNA(14, BUG, POISON),
    BEEDRILL(15, BUG, POISON),
    PIDGEY(16, NORMAL, FLYING),
    PIDGEOTTO(17, NORMAL, FLYING),
    PIDGEOT(18, NORMAL, FLYING),
    RATTATA(19, NORMAL, UNKNOWN),
    RATICATE(20, NORMAL, UNKNOWN),
    SPEAROW(21, NORMAL, FLYING),
    FEAROW(22, NORMAL, FLYING),
    EKANS(23, POISON, UNKNOWN),
    ARBOK(24, POISON, UNKNOWN),
    PIKACHU(25, ELECTRIC, UNKNOWN),
    RAICHU(26, ELECTRIC, UNKNOWN),
    SANDSHREW(27, GROUND, UNKNOWN),
    SANDSLASH(28, GROUND, UNKNOWN),
    NIDORAN_FEMALE(29, POISON, UNKNOWN),
    NIDORINA(30, POISON, UNKNOWN),
    NIDOQUEEN(31, POISON, GROUND),
    NIDORAN_MALE(32, POISON, UNKNOWN),
    NIDORINO(33, POISON, UNKNOWN),
    NIDOKING(34, POISON, GROUND),
    CLEFAIRY(35, FAIRY, UNKNOWN),
    CLEFABLE(36, FAIRY, UNKNOWN),
    VULPIX(37, FIRE, UNKNOWN),
    NINETALES(38, FIRE, UNKNOWN),
    JIGGLYPUFF(39, NORMAL, FAIRY),
    WIGGLYTUFF(40, NORMAL, FAIRY),
    ZUBAT(41, POISON, FLYING),
    GOLBAT(42, POISON, FLYING),
    ODDISH(43, GRASS, POISON),
    GLOOM(44, GRASS, POISON),
    VILEPLUME(45, GRASS, POISON),
    PARAS(46, BUG, GRASS),
    PARASECT(47, BUG, GRASS),
    VENONAT(48, BUG, POISON),
    VENOMOTH(49, BUG, POISON),
    DIGLETT(50, GROUND, UNKNOWN),
    DUGTRIO(51, GROUND, UNKNOWN),
    MEOWTH(52, NORMAL, UNKNOWN),
    PERSIAN(53, NORMAL, UNKNOWN),
    PSYDUCK(54, WATER, UNKNOWN),
    GOLDUCK(55, WATER, UNKNOWN),
    MANKEY(56, FIGHTING, UNKNOWN),
    PRIMEAPE(57, FIGHTING, UNKNOWN),
    GROWLITHE(58, FIRE, UNKNOWN),
    ARCANINE(59, FIRE, UNKNOWN),
    POLIWAG(60, WATER, UNKNOWN),
    POLIWHIRL(61, WATER, UNKNOWN),
    POLIWRATH(62, WATER, FIGHTING),
    ABRA(63, PSYCHIC, UNKNOWN),
    KADABRA(64, PSYCHIC, UNKNOWN),
    ALAKAZAM(65, PSYCHIC, UNKNOWN),
    MACHOP(66, FIGHTING, UNKNOWN),
    MACHOKE(67, FIGHTING, UNKNOWN),
    MACHAMP(68, FIGHTING, UNKNOWN),
    BELLSPROUT(69, GRASS, POISON),
    WEEPINBELL(70, GRASS, POISON),
    VICTREEBEL(71, GRASS, POISON),
    TENTACOOL(72, WATER, POISON),
    TENTACRUEL(73, WATER, POISON),
    GEODUDE(74, ROCK, GROUND),
    GRAVELER(75, ROCK, GROUND),
    GOLEM(76, ROCK, GROUND),
    PONYTA(77, FIRE, UNKNOWN),
    RAPIDASH(78, FIRE, UNKNOWN),
    SLOWPOKE(79, WATER, PSYCHIC),
    SLOWBRO(80, WATER, PSYCHIC),
    MAGNEMITE(81, ELECTRIC, STEEL),
    MAGNETON(82, ELECTRIC, STEEL),
    FARFETCH_D(83, NORMAL, FLYING),
    DODUO(84, NORMAL, FLYING),
    DODRIO(85, NORMAL, FLYING),
    SEEL(86, WATER, UNKNOWN),
    DEWGONG(87, WATER, ICE),
    GRIMER(88, POISON, UNKNOWN),
    MUK(89, POISON, UNKNOWN),
    SHELLDER(90, WATER, UNKNOWN),
    CLOYSTER(91, WATER, ICE),
    GASTLY(92, GHOST, POISON),
    HAUNTER(93, GHOST, POISON),
    GENGAR(94, GHOST, POISON),
    ONIX(95, ROCK, GROUND),
    DROWZEE(96, PSYCHIC, UNKNOWN),
    HYPNO(97, PSYCHIC, UNKNOWN),
    KRABBY(98, WATER, UNKNOWN),
    KINGLER(99, WATER, UNKNOWN),
    VOLTORB(100, ELECTRIC, UNKNOWN),
    ELECTRODE(101, ELECTRIC, UNKNOWN),
    EXEGGCUTE(102, GRASS, PSYCHIC),
    EXEGGUTOR(103, GRASS, PSYCHIC),
    CUBONE(104, GROUND, UNKNOWN),
    MAROWAK(105, GROUND, UNKNOWN),
    HITMONLEE(106, FIGHTING, UNKNOWN),
    HITMONCHAN(107, FIGHTING, UNKNOWN),
    LICKITUNG(108, NORMAL, UNKNOWN),
    KOFFING(109, POISON, UNKNOWN),
    WEEZING(110, POISON, UNKNOWN),
    RHYHORN(111, GROUND, ROCK),
    RHYDON(112, GROUND, ROCK),
    CHANSEY(113, NORMAL, UNKNOWN),
    TANGELA(114, GRASS, UNKNOWN),
    KANGASKHAN(115, NORMAL, UNKNOWN),
    HORSEA(116, WATER, UNKNOWN),
    SEADRA(117, WATER, UNKNOWN),
    GOLDEEN(118, WATER, UNKNOWN),
    SEAKING(119, WATER, UNKNOWN),
    STARYU(120, WATER, UNKNOWN),
    STARMIE(121, WATER, PSYCHIC),
    MR_MIME(122, PSYCHIC, FAIRY),
    SCYTHER(123, BUG, FLYING),
    JYNX(124, ICE, PSYCHIC),
    ELECTABUZZ(125, ELECTRIC, UNKNOWN),
    MAGMAR(126, FIRE, UNKNOWN),
    PINSIR(127, BUG, UNKNOWN),
    TAUROS(128, NORMAL, UNKNOWN),
    MAGIKARP(129, WATER, UNKNOWN),
    GYARADOS(130, WATER, FLYING),
    LAPRAS(131, WATER, ICE),
    DITTO(132, NORMAL, UNKNOWN),
    EEVEE(133, NORMAL, UNKNOWN),
    VAPOREON(134, WATER, UNKNOWN),
    JOLTEON(135, ELECTRIC, UNKNOWN),
    FLAREON(136, FIRE, UNKNOWN),
    PORYGON(137, NORMAL, UNKNOWN),
    OMANYTE(138, ROCK, WATER),
    OMASTAR(139, ROCK, WATER),
    KABUTO(140, ROCK, WATER),
    KABUTOPS(141, ROCK, WATER),
    AERODACTYL(142, ROCK, FLYING),
    SNORLAX(143, NORMAL, UNKNOWN),
    ARTICUNO(144, ICE, FLYING),
    ZAPDOS(145, ELECTRIC, FLYING),
    MOLTRES(146, FIRE, FLYING),
    DRATINI(147, DRAGON, UNKNOWN),
    DRAGONAIR(148, DRAGON, UNKNOWN),
    DRAGONITE(149, DRAGON, FLYING),
    MEWTWO(150, PSYCHIC, UNKNOWN),
    MEW(151, PSYCHIC, UNKNOWN);

    private final int pokedexId;
    private final PokemonTestType primaryType;
    private final PokemonTestType secondaryType;

    PokemonTestInfo(int pokedexId,
                    PokemonTestType primaryType,
                    PokemonTestType secondaryType) {
        this.pokedexId = pokedexId;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
    }

    public int getPokedexId() {
        return pokedexId;
    }

    public PokemonTestType getPrimaryType() {
        return primaryType;
    }

    public PokemonTestType getSecondaryType() {
        return secondaryType;
    }

    public PokemonInfo asInfo() {
        PokemonInfo info = new PokemonInfo();
        info.setName(name());
        info.setPokedexId(pokedexId);
        info.setPrimaryType(primaryType.asType());
        info.setSecondaryType(secondaryType.asType());
        return info;
    }
}



