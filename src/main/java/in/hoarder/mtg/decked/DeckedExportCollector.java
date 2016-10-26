/**
 * 
 */
package in.hoarder.mtg.decked;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import in.hoarder.mtg.item.AbstractReadRowCollector;
import in.hoarder.mtg.item.Item;
import in.hoarder.mtg.item.ItemCollector;
import in.hoarder.mtg.item.RowHandler;

/**
 * {@link ItemCollector} that collects all CVS rows from an Decked Builder export and returns them as a
 * {@link List} of {@link Item}s.
 * 
 * @author skoogiz
 *
 */
public class DeckedExportCollector extends AbstractReadRowCollector<DeckedItem>
{
    private final ExportHandler handler;

    public DeckedExportCollector()
    {
        this.handler = new ExportHandler();
    }

    public ExportHandler getHandler()
    {
        return handler;
    }

    private static class ExportHandler implements RowHandler<DeckedItem>
    {
        private List<ExportItem> data = new ArrayList<ExportItem>();

        private int index = 0;

        // e.g. - - id: 370349
        private static final String ID = "- - id:";
        // e.g. - r: 1
        private static final String REGULAR = "- r:";
        // e.g. - f: 1
        private static final String FOIL = "- f:";

        @Override
        public void handle(String row)
        {
            if (row.contains(ID))
            {
                ExportItem item = new ExportItem(stringValue(ID, row));
                data.add(item);
                index = data.indexOf(item);
            }
            else if (row.contains(REGULAR) && dataAtIndex())
            {
                data.get(index).regularQuantity = numericValue(REGULAR, row);
            }
            else if (row.contains(FOIL) && dataAtIndex())
            {
                data.get(index).foilQuantity = numericValue(FOIL, row);
            }
        }

        private boolean dataAtIndex()
        {
            return (data.size() - 1) == index;
        }

        private String stringValue(String key, String val)
        {
            return val.substring(val.indexOf(key) + key.length()).trim();
        }

        private int numericValue(String key, String val)
        {
            return Integer.parseInt(stringValue(key, val));
        }

        @Override
        public Stream<DeckedItem> data()
        {
            return data.stream().map((item) -> {
                return new DeckedItem.Builder()
                    .multiverseId(item.multiverseId)
                    .regularQuantity(item.regularQuantity)
                    .foilQuantity(item.foilQuantity)
                    .totalQuantity(item.regularQuantity + item.foilQuantity)
                    .build();
            });
        }

    }

    private static class ExportItem
    {
        private final String multiverseId;

        public int regularQuantity = 0, foilQuantity = 0;

        public ExportItem(String multiverseId)
        {
            this.multiverseId = multiverseId;
        }
    }
}
