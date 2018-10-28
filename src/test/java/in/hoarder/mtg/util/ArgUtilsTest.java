package in.hoarder.mtg.util;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ArgUtilsTest {

    private static final String ARG_1 = "--foo=val";
    private static final String ARG_2 = "--bar=val1|val2|val3";
    private static final String ARG_3 = "--no-value=";
    private static final String ARG_4 = "--key-only";

    final String[] args = {ARG_1, ARG_2, ARG_3, ARG_4};

    @Test
    public void getArgValues() {
        List<String> values = ArgUtils.getArgValues("--bar", args);
        assertNotNull(values);
        assertThat(values.size(), is(3));
        assertThat(values.get(0), is("val1"));
        assertThat(values.get(1), is("val2"));
        assertThat(values.get(2), is("val3"));
    }

    @Test
    public void getArgValues_singleValue() {
        List<String> values = ArgUtils.getArgValues("--foo", args);
        assertNotNull(values);
        assertThat(values.size(), is(1));
        assertThat(values.get(0), is("val"));
    }

    @Test
    public void getArgValues_noValue() {
        List<String> values = ArgUtils.getArgValues("--no-value", args);
        assertNotNull(values);
        assertTrue(values.isEmpty());
    }

    @Test
    public void getArgValues_noArgs() {
        List<String> values = ArgUtils.getArgValues("--no-args");
        assertNotNull(values);
        assertTrue(values.isEmpty());
    }

    @Test
    public void getArgValue() {
        Optional<String> val = ArgUtils.getArgValue("--foo", args);
        assertTrue(val.isPresent());
        assertThat(val.get(), is("val"));
    }

    @Test
    public void getArgValue_noValue() {
        Optional<String> val = ArgUtils.getArgValue("--no-value", args);
        assertFalse(val.isPresent());
    }

    @Test
    public void getArgValue_keyOnly() {
        Optional<String> val = ArgUtils.getArgValue("--key-only", args);
        assertFalse(val.isPresent());
    }

    @Test
    public void getArgValue_unknownKey() {
        Optional<String> val = ArgUtils.getArgValue("--unknown-key", args);
        assertFalse(val.isPresent());
    }

    @Test
    public void getArgValue_noArgs() {
        Optional<String> val = ArgUtils.getArgValue("--no-args");
        assertFalse(val.isPresent());
    }
}