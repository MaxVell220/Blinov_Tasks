package main.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Getter {

  private Connection connection = null;

  public Getter(Connection connection) {
    this.connection = connection;
  }

  // Вывести информацию обо всех планетах, на которых присутствует жизнь, и их спутниках в заданной галактике
  public void getPlanetsWithLifeAndTheirSatellites(Integer galaxyId) throws SQLException {
    String query1 = "SELECT * FROM planets.satellite sat\n"
      + "INNER JOIN planets.planet pl ON sat.planet_id = pl.id AND  pl.presence_of_athmosphere = 1 AND pl.galaxy_id = ?";
    PreparedStatement preparedStatement = this.connection.prepareStatement(query1);
    preparedStatement.setInt(1, 1);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      System.out.println("Satellites: " + resultSet.getString("name"));

    }
    String query2 = "SELECT * FROM planets.planet pl\n"
      + "WHERE  pl.presence_of_athmosphere = 1 AND pl.galaxy_id = ?";
    preparedStatement = this.connection.prepareStatement(query2);
    preparedStatement.setInt(1, 1);
    resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      System.out.println("Planets: " + resultSet.getString("name"));

    }
  }

  //Вывести информацию о планетах и их спутниках, имеющих наименьший радиус и наибольшее количество спутников
  public void getPlanetsWithMinRadiusAndMaxSatellites() throws SQLException {
    String query = "SELECT MIN(pl.radius), pl.name, sat.name as satNames,  COUNT(sat.planet_id) satellites_count\n"
      + "FROM planets.planet pl LEFT JOIN\n"
      + "     satellite sat ON sat.planet_id = pl.id";
    PreparedStatement preparedStatement = this.connection.prepareStatement(query);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      System.out.println("Planet: " + resultSet.getString("name") + ", satellites: " + resultSet.getString("satNames"));
    }
  }

  //Вывести информацию о планете, галактике, в которой она находится, и ее спутниках,
  // имеющей максимальное количество спутников, но с наименьшим общим объемом этих спутников.
  public void getPlanetsWithMaxSatellitesAndMinCapacityOfTheseSatellites() throws SQLException {
    String query = "SELECT MAX(4/3*3.14*sat.radius*sat.radius*sat.radius) as commonVolume, pl.name, pl.galaxy_id, sat.name as satNames,  COUNT(sat.planet_id) satellites_count\n"
      + "FROM planets.planet pl JOIN\n"
      + "     satellite sat ON sat.planet_id = pl.id ";
    Integer galId = null;
    PreparedStatement preparedStatement = this.connection.prepareStatement(query);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      galId = resultSet.getInt("galaxy_id");
      System.out.println("Planet: " + resultSet.getString("name") + ", satellites: " + resultSet.getString("satNames"));
    }

    String query2 = "SELECT gal.name FROM planets.galaxy gal WHERE gal.id = ?";
    preparedStatement = this.connection.prepareStatement(query2);
    preparedStatement.setInt(1, galId );
    resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      System.out.println("Galaxy: " + resultSet.getString("name"));

    }
  }

  //Найти галактику, сумма ядерных температур планет которой наибольшая
  public void getGalaxyWithMaxCoreTemperatureOfItsPlanets() throws SQLException {
    String query = "SELECT SUM(pl.core_temperature_in_celsius) AS TotalSum, gal.name\n"
      + "FROM planets.galaxy gal JOIN\n"
      + "     planets.planet pl ON pl.galaxy_id = gal.id \n"
      + "ORDER BY MAX(TotalSum)";
    PreparedStatement preparedStatement = this.connection.prepareStatement(query);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      System.out.println("Galaxy: " + resultSet.getString("name"));
    }
  }
}
