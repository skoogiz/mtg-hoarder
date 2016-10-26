/**
 * 
 */
package in.hoarder.mtg.csv;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.exceptions.CsvBadConverterException;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * 
 * Resolver class wrap the OpenCSV library to map CSV into java beans. It will turn a array ocf column values and
 * convert them into a {@link CsvBean} implementation.
 * 
 * @author skoogiz
 *
 */
public class CsvToBeanResolver<T extends CsvBean>
{
    // Initialize logger
    private static Logger logger = LoggerFactory.getLogger(CsvToBeanResolver.class);

    private final MappingStrategy<T> mapper;

    private final CsvToBeanConverter<T> converter;

    /**
     * Constructor will take a OpenCSV {@link MappingStrategy} as parameter. This will tell the resolver how to map
     * the CSV columns.
     * 
     * @param mapper
     */
    public CsvToBeanResolver(MappingStrategy<T> mapper)
    {
        this.mapper = mapper;
        this.converter = new CsvToBeanConverter<T>();
    }

    /**
     * Fail safe method resolving the mapping with an {@link Optional}.
     * 
     * @param line
     * @return
     */
    public Optional<T> resolvOptional(String[] line)
    {
        try
        {
            return Optional.of(resolv(line));
        }
        catch (Exception e)
        {
            logger.error("Error while processing line [" + String.join(",", line) + "]", e);
        }
        return Optional.empty();
    }

    /**
     * Resolve conversion from line to {@link CsvBean}.
     * 
     * @param line
     * @return
     * @throws Exception
     */
    public T resolv(String[] line) throws Exception
    {
        return converter.lineToBean(mapper, line);
    }

    /**
     * Inner class extending {@link CsvToBean} to be able to wrap the lineToBean method.
     * 
     * @param <V>
     */
    private class CsvToBeanConverter<V> extends CsvToBean<V>
    {
        public V lineToBean(MappingStrategy<V> mapper, String... line) throws IllegalAccessException,
                InvocationTargetException, InstantiationException, CsvBadConverterException,
                CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, CsvConstraintViolationException,
                IntrospectionException
        {
            return processLine(mapper, line);
        }
    }

}
