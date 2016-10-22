/**
 * 
 */
package in.hoarder.mtg.item;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.opencsv.bean.MappingStrategy;

import in.hoarder.mtg.csv.CsvBean;
import in.hoarder.mtg.csv.CsvSpliterator;
import in.hoarder.mtg.csv.CsvToBeanResolver;

/**
 * @author skoogiz
 *
 */
public abstract class AbstractItemCollector<I extends Item, B extends CsvBean<I>> implements ItemCollector<I>
{

    @Override
    public List<I> collect(File file)
    {
        return doCollect(file, Optional.empty(), Optional.empty());
    }

    @Override
    public List<I> collect(File file, int limit)
    {
        return doCollect(file, Optional.empty(), limit == 0 ? Optional.empty() : Optional.of(limit));
    }

    @Override
    public List<I> collect(File file, Predicate<I> filter)
    {
        return doCollect(file, Optional.of(filter), Optional.empty());
    }

    @Override
    public List<I> collect(File file, Predicate<I> filter, int limit)
    {
        return doCollect(file, Optional.of(filter), limit == 0 ? Optional.empty() : Optional.of(limit));
    }

    private List<I> doCollect(File file, Optional<Predicate<I>> filter,
            Optional<Integer> limit)
    {
        CsvToBeanResolver<B> csvToBean = new CsvToBeanResolver<B>(getStrategy());

        try (Stream<String[]> stream = CsvSpliterator.csvStream(getStrategy(), new FileInputStream(file)))
        {
            if (limit.isPresent())
            {
                return stream
                    .parallel()
                    .filter(noEmptyFields())
                    .map(fields -> lineToItem(csvToBean, fields))
                    .filter(item -> {
                        return filter(item, filter);
                    })
                    .limit(limit.get())
                    .collect(toList());
            }
            else
            {
                return stream
                    .parallel()
                    .filter(noEmptyFields())
                    .map(fields -> lineToItem(csvToBean, fields))
                    .filter(item -> {
                        return filter(item, filter);
                    })
                    .collect(toList());
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private I lineToItem(CsvToBeanResolver<B> csvToBean, String... values)
    {
        return csvToBean.resolvOptional(values).get().toItem();
    }

    private boolean filter(I item, Optional<Predicate<I>> filter)
    {
        return filter.isPresent() ? filter.get().test(item) : true;
    }

    private Predicate<String[]> noEmptyFields()
    {
        return array -> !String.join("", array).matches("^\\s*$");
    }

    public abstract MappingStrategy<B> getStrategy();
}
