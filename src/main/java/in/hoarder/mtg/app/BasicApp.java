package in.hoarder.mtg.app;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import in.hoarder.mtg.deckbox.DeckboxExportCollector;
import in.hoarder.mtg.deckbox.DeckboxItem;
import in.hoarder.mtg.domain.Edition;
import in.hoarder.mtg.domain.Rarity;
import in.hoarder.mtg.item.ItemCollector;

public class BasicApp {

    public static void main(String... args) {
        if (args.length > 0) {
            Arrays.asList(args).forEach(System.out::println);
            Context ctx = Context.create(args);
            System.out.println("Editions");
            ctx.getEditions().forEach(ed -> System.out.println(ed.getName()));
            System.out.println("Rarities");
            ctx.getRarities().forEach(System.out::println);

            final List<String> editions = ctx.getEditions().stream().map(ed -> ed.getName().toLowerCase()).collect(Collectors.toList());

            Function<String, Boolean> checkEdition = editionName -> editions.isEmpty() || (editionName != null && editions.contains(editionName.toLowerCase()));
            Function<String, Boolean> checkRarity = rarity -> ctx.getRarities().isEmpty() || (rarity != null && ctx.getRarities().contains(Rarity.of(rarity)));

            ItemCollector<DeckboxItem> collector = new DeckboxExportCollector();
            List<DeckboxItem> items = collector.collect(ctx.getFile())
                    .stream()
                    .filter(item -> checkEdition.apply(item.getEdition()))
                    .filter(item -> !item.isFoil())
                    .filter(item -> checkRarity.apply(item.getRarity()))
                    .filter(item -> item.getCount() < 4)
                    .collect(Collectors.toList());
            if (items.isEmpty()) {
                System.out.println("Set complete!");
            } else {
                items.forEach(item -> System.out.printf("%d %s (%s)%n", item.getCount(), item.getName(), item.getEdition()));
            }
        } else {
            System.out.print("Program cannot run without a file to parse. Pleas pass cvs file path as argument.");
        }

        System.exit(0);
    }
}
