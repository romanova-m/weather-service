package ru.mirea.data;

import ru.mirea.data.DataSourceAPI;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;



public class DataSource implements DataSourceAPI {

	private Map<String, String> cities = new HashMap();

	public DataSource () {
		cities.put("Moscow","+15");
		cities.put("Perm","+5");
		cities.put("Omsk","+1");
		cities.put("Sochi","+25");
		cities.put("Vologda","+5");
	}

	public Set <String> cities () {
		return this.cities.keySet();
	}

	public String getByCity (String city) {
		return this.cities.get(city);
	}
}