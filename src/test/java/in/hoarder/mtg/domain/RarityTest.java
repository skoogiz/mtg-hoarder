package in.hoarder.mtg.domain;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RarityTest {

    @Test
    public void of() {
        assertThat(Rarity.of("Mythic Rare"), is(Rarity.MYTHIC_RARE));
        assertThat(Rarity.of("Rare"), is(Rarity.RARE));
        assertThat(Rarity.of("Uncommon"), is(Rarity.UNCOMMON));
        assertThat(Rarity.of("Common"), is(Rarity.COMMON));
        assertThat(Rarity.of("Basic Land"), is(Rarity.BASIC_LAND));
        assertThat(Rarity.of("Special"), is(Rarity.SPECIAL));
        assertThat(Rarity.of("Foo Nar"), is(Rarity.UNKNOWN));
    }
}