package ru.mirea.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum DataSource implements DataSourceAPI {

    WEATHER();
    Map<String, String> hashMap = new HashMap<>();

	DataSource(){
         this.hashMap.put("Omsk", "+1");
         this.hashMap.put("Perm", "+5");
         this.hashMap.put("Moscow", "+15");
         this.hashMap.put("Sochi", "+25");
         this.hashMap.put("Vologda", "+5");
     }

	public Set <String> cities () {	return this.hashMap.keySet(); }

	public String getByCity (String city) {
		return this.hashMap.get(city);
	}
}