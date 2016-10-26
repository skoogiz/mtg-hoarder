/**
 * 
 */
package in.hoarder.mtg.item;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author skoogiz
 * @param <I>
 *
 */
public abstract class AbstractReadRowCollector<I extends Item> extends AbstractItemCollector<I>
{
    // Initialize logger
    private static Logger logger = LoggerFactory.getLogger(AbstractReadRowCollector.class);

    @Override
    protected List<I> doCollect(File file, Optional<Predicate<I>> filter)
    {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file))))
        {
            br
                .lines()
                .forEach(row -> getHandler().handle(row));

            br.close();
        }
        catch (IOException e)
        {
            logger.error(e.getMessage(), e);
        }

        return getHandler()
            .data()
            .filter(item -> filter(item, filter))
            .collect(toList());
    }

    public abstract RowHandler<I> getHandler();
}
