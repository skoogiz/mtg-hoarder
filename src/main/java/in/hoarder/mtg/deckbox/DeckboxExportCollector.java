/**
 * 
 */
package in.hoarder.mtg.deckbox;

import java.util.List;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;

import in.hoarder.mtg.item.AbstractCsvItemCollector;
import in.hoarder.mtg.item.Item;
import in.hoarder.mtg.item.ItemCollector;

/**
 * {@link ItemCollector} that collects all CVS rows from an Deckbox export and returns them as a {@link List} of
 * {@link Item}s.
 * 
 * @author skoogiz
 *
 */
public class DeckboxExportCollector extends AbstractCsvItemCollector<DeckboxItem, DeckboxBean>
{
    private final HeaderColumnNameMappingStrategy<DeckboxBean> strategy;

    public DeckboxExportCollector()
    {
        this.strategy = new HeaderColumnNameMappingStrategy<>();
        this.strategy.setType(DeckboxBean.class);
    }

    @Override
    public MappingStrategy<DeckboxBean> getStrategy()
    {
        return strategy;
    }

    @Override
    protected DeckboxItem beanToItem(DeckboxBean bean)
    {
        return DeckboxItem.create(bean);
    }
}
