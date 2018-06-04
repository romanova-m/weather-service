package ru.mirea.weather;

import ru.mirea.weather.WeatherService;
import org.junit.Test;
import static org.junit.Assert.*;

public class WeatherService_Test {

	@Test
	public void testCities () {
		WeatherService ws = new WeatherService();
		assertEquals(5, ws.availableCities().size());
	}
	
	@Test
	public void testWeather () {
		WeatherService ws = new WeatherService();
		assertEquals("+15", ws.getWeather("Moscow"));
	}
}
