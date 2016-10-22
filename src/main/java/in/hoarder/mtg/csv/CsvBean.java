/**
 * 
 */
package in.hoarder.mtg.csv;

import in.hoarder.mtg.item.Item;

/**
 * 
 * Interface used to represent a row in a CSV file.
 * 
 * @author skoogiz
 *
 */
public interface CsvBean<I extends Item>
{
    I toItem();
}
