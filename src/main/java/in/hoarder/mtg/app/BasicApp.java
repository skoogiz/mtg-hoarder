package in.hoarder.mtg.app;

import in.hoarder.mtg.deckbox.DeckboxExportCollector;
import in.hoarder.mtg.deckbox.DeckboxItem;
import in.hoarder.mtg.item.ItemCollector;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BasicApp {

    public static void main(String... args) {
        if (args.length > 0) {
            Arrays.asList(args).forEach(System.out::println);
            final String csvFile = args[0];
            final List<String> editions = args.length >= 2
                    ? Arrays.asList(args[1].toLowerCase().split("[|]"))
                    : Collections.EMPTY_LIST;
            final List<String> rarities = args.length >= 3
                    ? Arrays.asList(args[2].toLowerCase().split("[|]"))
                    : Collections.EMPTY_LIST;
            System.out.println("Editions");
            editions.forEach(System.out::println);
            System.out.println("Rarities");
            rarities.forEach(System.out::println);


            Function<String, Boolean> checkEdition = (editionName) -> editions.isEmpty() || (editionName != null && editions.contains(editionName.toLowerCase()));
            Function<String, Boolean> checkRarity = (rarity) -> rarities.isEmpty() || (rarity != null && rarities.contains(rarity.toLowerCase()));

            final File file = new File(csvFile);
            if (file.exists()) {
                ItemCollector<DeckboxItem> collector = new DeckboxExportCollector();
                List<DeckboxItem> items = collector.collect(file)
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
                throw new IllegalArgumentException("File '" + csvFile + "' not found.");
            }
        } else {
            System.out.print("Program cannot run without a file to parse. Pleas pass cvs file path as argument.");
        }

        System.exit(0);
    }
}
