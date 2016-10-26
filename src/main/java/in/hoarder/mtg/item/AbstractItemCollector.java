/**
 * 
 */
package in.hoarder.mtg.item;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author skoogiz
 *
 */
public abstract class AbstractItemCollector<I extends Item> implements ItemCollector<I>
{
    @Override
    public List<I> collect(File file)
    {
        return doCollect(file, Optional.empty());
    }

    @Override
    public List<I> collect(File file, Predicate<I> filter)
    {
        return doCollect(file, Optional.of(filter));
    }

    protected abstract List<I> doCollect(File file, Optional<Predicate<I>> filter);

    protected Predicate<String> notEmpty()
    {
        return row -> row.matches("^\\s*$");
    }

    protected boolean filter(I item, Optional<Predicate<I>> filter)
    {
        return filter.isPresent() ? filter.get().test(item) : true;
    }
}
