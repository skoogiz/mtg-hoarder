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

import in.hoarder.mtg.decked.DeckedExportCollector;
import in.hoarder.mtg.decked.DeckedItem;
import in.hoarder.mtg.item.ItemCollector;

/**
 * @author skoogiz
 *
 */
public class DeckedExportCollectorTest
{
    private final File deckboxDir = new File("src/test/data/decked");

    private final File file = new File(deckboxDir, "collection.coll2");

    private ItemCollector<DeckedItem> collector = new DeckedExportCollector();

    /**
     * Test method for {@link in.hoarder.mtg.item.AbstractCsvItemCollector#collect(java.io.File)}.
     */
    @Test
    public void testCollectFile()
    {
        List<DeckedItem> items = collector.collect(file);
        assertThat("All rows are collected", items.size(), is(72));
    }

    /**
     * Test method for
     * {@link in.hoarder.mtg.item.AbstractCsvItemCollector#collect(java.io.File, java.util.function.Predicate)}.
     */
    @Test
    public void testCollectFilePredicateOf()
    {
        List<DeckedItem> items =
                collector.collect(
                    file,
                    item -> "389605".equals(item.getMultiverseId()) && item.getFoilQuantity() > 0);

        assertThat("Row is collected", items.size(), is(1));

        DeckedItem item = items.get(0);

        assertThat("Item has MultiverseId", item.getMultiverseId(), is("389605"));
        assertTrue("Item is foiled", item.getFoilQuantity() > 0);
    }

}
