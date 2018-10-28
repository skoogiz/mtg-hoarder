package in.hoarder.mtg.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class ArgUtils {

    public static List<String> getArgValues(String key, String... args) {
        return getArgValue(key, args).map(val -> Arrays.asList(val.split("[|]"))).orElse(Collections.EMPTY_LIST);
    }

    public static Optional<String> getArgValue(String key, String... args) {
        for (String a: args) {
            String arg = a.trim();
            if(arg.startsWith(key) && arg.length() > key.length() + 1) {
                String value = arg.substring(arg.indexOf("=") + 1);
                if (StringUtils.isNotEmpty(value)) {
                    return Optional.of(value);
                }
            }
        }
        return Optional.empty();
    }

    private ArgUtils() {}
}
