package ru.mirea.data;

import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yaskatt
 */
public class DataSourceTest {

    @Test
    public void getWeatherTest() {
        String result = DataSource.WEATHER.getByCity("Moscow");
        assertEquals("+15", result);
    }

    @Test
    public void getCitiesTest() {
        Set<String> result = DataSource.WEATHER.cities();
        assertEquals(5,result.size());
        assertTrue(result.contains("Moscow"));

    }
    
}
