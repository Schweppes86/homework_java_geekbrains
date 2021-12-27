package com.geekbrains.part2.lesson7;

import com.geekbrains.part2.lesson7.entity.DailyForecastForDB;

import java.sql.*;

public class DBController {
    private static final String DB_NAME = "geekbrains.db";
    String insert_db = "insert into dailyForecasts (city, localdate, tempMin, tempMax, textDay, textNight) values (?, ?, ?, ?, ?, ?)";
    private static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS dailyForecasts" +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, city TEXT NOT NULL, localdate TEXT NOT NULL, tempMin DOUBLE NOT NULL," +
            "tempMax DOUBLE NOT NULL, textDay TEXT NOT NULL,textNight TEXT NOT NULL);";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            createTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable() throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection("jdbc:sqlite:"+ DB_NAME);
        Statement stmt = connection.createStatement();
        int result = stmt.executeUpdate(CREATE_TABLE);
        connection.close();
    }

    public boolean saveWeatherData(DailyForecastForDB dailyForecastDB) throws SQLException {
        Connection connection = null;
        PreparedStatement saveDailyForecast = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+ DB_NAME);
            saveDailyForecast = connection.prepareStatement(insert_db);
            saveDailyForecast.setString(1, dailyForecastDB.getCity());
            saveDailyForecast.setString(2, dailyForecastDB.getLocalDate());
            saveDailyForecast.setDouble(3, dailyForecastDB.getTempMin());
            saveDailyForecast.setDouble(4, dailyForecastDB.getTempMax());
            saveDailyForecast.setString(5, dailyForecastDB.getTextDay());
            saveDailyForecast.setString(6, dailyForecastDB.getTextNight());
            return saveDailyForecast.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            saveDailyForecast.close();
            connection.close();
        }
        throw new SQLException("Сохранение прогноза в базу данных не выполнено!");
    }

    public void getHistoryFromDB () throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+ DB_NAME);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from dailyForecasts");
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String city = resultSet.getString(2);
                String localDate = resultSet.getString(3);
                Float tempMin = resultSet.getFloat(4);
                Float tempMax = resultSet.getFloat(5);
                String textDay = resultSet.getString(6);
                String textNight = resultSet.getString(7);
                System.out.printf("%s. %s %s %.1f %.1f %s. %s. \n", id, city, localDate, tempMin, tempMax, textDay, textNight);
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            statement.close();
            connection.close();
        }
    }
}
