/**
 * 
 */
package in.hoarder.mtg.item;

import java.util.stream.Stream;

/**
 * @author skoogiz
 *
 */
public interface RowHandler<I extends Item>
{
    void handle(String row);

    Stream<I> data();
}
