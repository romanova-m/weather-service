package ru.mirea.data;

import java.util.Set;

	public interface DataSourceAPI {

		public Set<String> cities();
		public String getByCity(String city);
	}
