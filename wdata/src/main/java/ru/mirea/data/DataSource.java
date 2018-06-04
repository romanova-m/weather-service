package ru.mirea.data;

import ru.mirea.data.DataSourceAPI;
import java.util.Set;
import java.sql.*;
import java.util.HashSet;


public enum DataSource implements DataSourceAPI {
    Weather();
	
	Connection conn;

	DataSource() {
        try {
            initDB();
        } catch (SQLException e) {
            e.printStackTrace();
            }
        }

	public String getByCity(String city){
        try {
            String Result;
            synchronized (conn) {
                PreparedStatement ps = conn.prepareStatement("SELECT Weather FROM WeatherForecast WHERE City ='" + city + "'");
                ResultSet rs = ps.executeQuery();
                rs.beforeFirst();
                rs.next();
                Result = rs.getString("Weather");
            }
            return Result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<String> cities(){
        try {
            Set<String> result = new HashSet<>();
            synchronized (conn) {
                PreparedStatement ps = conn.prepareStatement("SELECT City FROM WeatherForecast");
                ResultSet rs = ps.executeQuery();
                rs.beforeFirst();
                while (rs.next()) {
                    result.add(rs.getString("City"));
                }
            }
            return  result;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    private void initDB() throws SQLException{
        conn = DriverManager.getConnection("jdbc:h2:~/WeatherDB/WeatherDB", "sa", "");

        PreparedStatement ps = conn.prepareStatement("DROP TABLE WeatherForecast IF EXISTS");
        ps.execute();

        ps = conn.prepareStatement("CREATE TABLE WeatherForecast (City VARCHAR, Weather VARCHAR )");
        ps.execute();

        ps = conn.prepareStatement("INSERT INTO WeatherForecast VALUES ('Omsk','+1'),('Moscow','+15'),('Perm','+5'),('Sochi','+25'),('Vologda','+5')");
        ps.execute();
    }
}
