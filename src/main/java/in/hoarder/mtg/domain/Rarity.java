package in.hoarder.mtg.domain;

import java.util.function.Function;

public enum Rarity {

    MYTHIC_RARE, RARE, UNCOMMON, COMMON, BASIC_LAND, SPECIAL, UNKNOWN;

    public static final Function<String, String> TRIM_RARITY_NAME = rarity -> rarity.replaceAll("[\\s_]", "").toUpperCase();

    public static Rarity of(String value) {
        for (Rarity rarity : values()) {
            if (TRIM_RARITY_NAME.apply(rarity.name()).equals(TRIM_RARITY_NAME.apply(value))) {
                return rarity;
            }
        }
        return UNKNOWN;
    }
}
