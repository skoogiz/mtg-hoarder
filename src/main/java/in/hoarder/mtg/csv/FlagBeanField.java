/**
 * 
 */
package in.hoarder.mtg.csv;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.bean.BeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * {@link BeanField} that turns a value cell into a {@link Boolean}, if a cell value is 1 of more characters return
 * true and if null or empty return false.
 * 
 * @author skoogiz
 *
 */
public class FlagBeanField extends AbstractBeanField<Boolean>
{
    @Override
    protected Boolean convert(String value) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException,
            CsvConstraintViolationException
    {
        return value != null && value.length() > 0;
    }
}
