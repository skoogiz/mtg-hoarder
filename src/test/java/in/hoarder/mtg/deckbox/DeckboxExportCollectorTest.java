/**
 * 
 */
package in.hoarder.mtg.deckbox;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import in.hoarder.mtg.item.ItemCollector;

/**
 * @author skoogiz
 *
 */
public class DeckboxExportCollectorTest
{
    private final File deckboxDir = new File("src/test/data/deckbox");

    private final File file = new File(deckboxDir, "inventory.full.csv");

    private ItemCollector<DeckboxItem> collector = new DeckboxExportCollector();

    /**
     * Test method for {@link in.hoarder.mtg.item.AbstractCsvItemCollector#collect(java.io.File)}.
     */
    @Test
    public void testCollectFile()
    {
        List<DeckboxItem> items = collector.collect(file);
        assertThat("All rows are collected", items.size(), is(7127));
    }

    /**
     * Test method for
     * {@link in.hoarder.mtg.item.AbstractCsvItemCollector#collect(java.io.File, java.util.function.Predicate)}.
     */
    @Test
    public void testCollectFilePredicateOf()
    {
        List<DeckboxItem> items =
                collector.collect(
                    file,
                    item -> item.isFoil() && "Corrupt".equals(item.getName()));

        assertThat("Row is collected", items.size(), is(1));

        DeckboxItem item = items.get(0);

        assertThat("Item is named", item.getName(), is("Corrupt"));
        assertTrue("Item is foiled", item.isFoil());
    }

}
