package in.hoarder.mtg.item;

import java.io.File;
import java.util.List;
import java.util.function.Predicate;

/**
 * 
 * @author skoogiz
 *
 * @param <I>
 */
public interface ItemCollector<I extends Item>
{
    /**
     * Collect all items from a file.
     * 
     * @param file
     * @return
     */
    List<I> collect(File file);

    /**
     * Collect all filtered items from a file.
     * 
     * @param file
     * @param filter
     * @return
     */
    List<I> collect(File file, Predicate<I> filter);
}
