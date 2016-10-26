/**
 * 
 */
package in.hoarder.mtg.decked;

import java.util.List;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;

import in.hoarder.mtg.item.AbstractCsvItemCollector;
import in.hoarder.mtg.item.Item;
import in.hoarder.mtg.item.ItemCollector;

/**
 * {@link ItemCollector} that collects all CVS rows from an Decked Builder export and returns them as a
 * {@link List} of {@link Item}s.
 * 
 * @author skoogiz
 *
 */
public class DeckedExportCsvCollector extends AbstractCsvItemCollector<DeckedItem, DeckedBean>
{
    private final HeaderColumnNameMappingStrategy<DeckedBean> strategy;

    public DeckedExportCsvCollector()
    {
        this.strategy = new HeaderColumnNameMappingStrategy<>();
        this.strategy.setType(DeckedBean.class);
    }

    @Override
    public MappingStrategy<DeckedBean> getStrategy()
    {
        return strategy;
    }

    @Override
    protected DeckedItem beanToItem(DeckedBean bean)
    {
        return DeckedItem.create(bean);
    }
}
