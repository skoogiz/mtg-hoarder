/**
 * 
 */
package in.hoarder.mtg.item;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.MappingStrategy;

import in.hoarder.mtg.csv.CsvBean;
import in.hoarder.mtg.csv.CsvSpliterator;
import in.hoarder.mtg.csv.CsvToBeanResolver;

/**
 * @author skoogiz
 *
 */
public abstract class AbstractCsvItemCollector<I extends Item, B extends CsvBean> extends AbstractItemCollector<I>
{
    // Initialize logger
    private static Logger logger = LoggerFactory.getLogger(AbstractCsvItemCollector.class);

    protected List<I> doCollect(File file, Optional<Predicate<I>> filter)
    {
        CsvToBeanResolver<B> csvToBean = new CsvToBeanResolver<B>(getStrategy());

        try (Stream<String[]> stream = CsvSpliterator.csvStream(getStrategy(), new FileInputStream(file)))
        {
            return stream
                .parallel()
                .filter(noEmptyFields())
                .map(fields -> lineToItem(csvToBean, fields))
                .filter(item -> filter(item, filter))
                .collect(toList());
        }
        catch (FileNotFoundException e)
        {
            logger.error(e.getMessage(), e);
        }

        return new ArrayList<>();
    }

    private I lineToItem(CsvToBeanResolver<B> csvToBean, String... values) throws RuntimeException
    {
        return beanToItem(csvToBean.resolvOptional(values).get());
    }

    protected abstract I beanToItem(B bean);

    protected Predicate<String[]> noEmptyFields()
    {
        return array -> !String.join("", array).matches("^\\s*$");
    }

    public abstract MappingStrategy<B> getStrategy();
}
