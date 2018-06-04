/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mirea.data;

import java.util.Set;
import java.sql.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ?????????
 */
public class DataSourceTest {
    

    @Test
    public void getWeatherTest() throws SQLException{
        String result = DataSource.Weather.getByCity("Moscow");
        assertEquals("+15", result);
    }

    @Test
    public void getCitiesTest() throws SQLException{
        Set<String> result = DataSource.Weather.cities();
        assertEquals(5,result.size());
        assertTrue(result.contains("Moscow"));

    }
    
}
