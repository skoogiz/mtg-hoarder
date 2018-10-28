package in.hoarder.mtg.domain;

public interface Edition {

    String getName();

    static Edition of(String edition) {
        return () -> edition;
    }
}
