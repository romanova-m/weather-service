package ru.mirea.data;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum DataSource implements DataSourceAPI {

    WEATHER();
    Map<String, String> hashMap = new HashMap<>();

	DataSource() {
            try {
                this.hashMap.put("Omsk", update("Omsk"));
                this.hashMap.put("Perm", update("Perm"));
                this.hashMap.put("Moscow", update("Moscow"));
                this.hashMap.put("Sochi", update("Sochi"));
                this.hashMap.put("Vologda", update("Vologda"));
            } catch (IOException e) {}
     }

	public Set <String> cities () {	return this.hashMap.keySet(); }

	public String getByCity (String city) {
		return this.hashMap.get(city);
	}
        
        private String update(String city) throws IOException {
            Document doc = Jsoup.connect("https://yandex.ru/pogoda/" + city).get();
            Elements els = doc.getElementsByAttributeValue("class", "temp fact__temp");
            return els.get(0).child(0).text();                     
        }      
    }