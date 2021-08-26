package main.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modifier {

  private Connection connection = null;

  public Modifier(Connection connection) {
    this.connection = connection;
  }

  public void addPlanet(Integer id, String name, Double radius, Double coreTemperatureInCelsius,
    Boolean presenceOfAthmosphere, Boolean existenceOfLife, Integer galaxyId) throws SQLException {
    String query = "\n"
      + "INSERT INTO `planets`.`planet`\n"
      + "(`id`,\n"
      + "`name`,\n"
      + "`radius`,\n"
      + "`core_temperature_in_celsius`,\n"
      + "`presence_of_athmosphere`,\n"
      + "`existence_of_life`,\n"
      + "`galaxy_id`)\n"
      + "VALUES\n"
      + "(?,\n"
      + "?,\n"
      + "?,\n"
      + "?>,\n"
      + "?>,\n"
      + "?>,\n"
      + "?>);";
    PreparedStatement preparedStatement = this.connection.prepareStatement(query);
    preparedStatement.setInt(1, id);
    preparedStatement.setString(2, name);
    preparedStatement.setDouble(3, radius);
    preparedStatement.setDouble(4, coreTemperatureInCelsius);
    preparedStatement.setBoolean(5, presenceOfAthmosphere);
    preparedStatement.setBoolean(6, existenceOfLife);
    preparedStatement.setInt(7, galaxyId);
    preparedStatement.execute();
  }

  public void updatePlanet(Integer oldId, Integer newId, String name, Double radius, Double coreTemperatureInCelsius,
    Boolean presenceOfAthmosphere, Boolean existenceOfLife, Integer galaxyId) throws SQLException {
    String query = "UPDATE `planets`.`planet`\n"
      + "SET\n"
      + "`id` = ?,\n"
      + "`name` = ?,\n"
      + "`radius` = ?,\n"
      + "`core_temperature_in_celsius` = ?,\n"
      + "`presence_of_athmosphere` = ?,\n"
      + "`existence_of_life` = ?,\n"
      + "`galaxy_id` = ?\n"
      + "WHERE `id` = ? AND `galaxy_id` = ?;";
    PreparedStatement preparedStatement = this.connection.prepareStatement(query);
    preparedStatement.setInt(1, newId);
    preparedStatement.setString(2, name);
    preparedStatement.setDouble(3, radius);
    preparedStatement.setDouble(4, coreTemperatureInCelsius);
    preparedStatement.setBoolean(5, presenceOfAthmosphere);
    preparedStatement.setBoolean(6, existenceOfLife);
    preparedStatement.setInt(7, galaxyId);
    preparedStatement.setInt(8, oldId);
    preparedStatement.setInt(9, galaxyId);
    preparedStatement.execute();
  }

  public void deletePlanet(Integer planetId, Integer galaxyId) throws SQLException {
    String query = "DELETE FROM `planets`.`planet`\n"
      + "WHERE planet.id = ? AND planet.galaxy_id = ?;";
    PreparedStatement preparedStatement = this.connection.prepareStatement(query);
    preparedStatement.setInt(1, planetId);
    preparedStatement.setInt(2, galaxyId);
    preparedStatement.execute();
  }

  public void addGalaxy(Integer id, String name) throws SQLException {
    String query = "INSERT INTO `planets`.`galaxy`\n"
      + "(`id`,\n"
      + "`name`)\n"
      + "VALUES\n"
      + "(?,\n"
      + " ?);";
    PreparedStatement preparedStatement = this.connection.prepareStatement(query);
    preparedStatement.setInt(1, id);
    preparedStatement.setString(2, name);
    preparedStatement.execute();
  }

  public void updateGalaxy(Integer newId, Integer oldId, String name) throws SQLException {
    String query = "UPDATE `planets`.`galaxy`\n"
      + "SET\n"
      + "`id` = ?,\n"
      + "`name` = ? \n"
      + "WHERE `id` = ?;";
    PreparedStatement preparedStatement = this.connection.prepareStatement(query);
    preparedStatement.setInt(1, newId);
    preparedStatement.setString(2, name);
    preparedStatement.setInt(3, oldId);
    preparedStatement.execute();
  }

  public void deleteGalaxy(Integer id) throws SQLException {
    String query = "DELETE FROM `planets`.`galaxy`\n"
      + "WHERE galaxy.id = ? ;";
    PreparedStatement preparedStatement = this.connection.prepareStatement(query);
    preparedStatement.setInt(1, id);
    preparedStatement.execute();
  }


  public void addSatellite(Integer id, String name, Double radius, Double distanceToPlanet, Integer planetId,
    Integer galaxyId) throws SQLException {
    String query = "\n"
      + "INSERT INTO `planets`.`satellite`\n"
      + "(`id`,\n"
      + "`name`,\n"
      + "`radius`,\n"
      + "`distance_to_planet`,\n"
      + "`planet_id`,\n"
      + "`planet_galaxy_id`)\n"
      + "VALUES\n"
      + "(?,\n"
      + "?,\n"
      + "?,\n"
      + "?,\n"
      + "?,\n"
      + "?);";
    PreparedStatement preparedStatement = this.connection.prepareStatement(query);
    preparedStatement.setInt(1, id);
    preparedStatement.setString(2, name);
    preparedStatement.setDouble(3, radius);
    preparedStatement.setDouble(4, distanceToPlanet);
    preparedStatement.setInt(5, planetId);
    preparedStatement.setInt(6, galaxyId);
    preparedStatement.execute();
  }

  public void updateSatellite(Integer oldId, Integer newId, String name, Double radius, Double distanceToPlanet,
    Integer planetId, Integer galaxyId) throws SQLException {
    String query = "UPDATE `planets`.`satellite`\n"
      + "SET\n"
      + "`id` = ?,\n"
      + "`name` = ?,\n"
      + "`radius` = ?,\n"
      + "`distance_to_planet` = ?,\n"
      + "`planet_id` = ?,\n"
      + "`planet_galaxy_id` = ?\n"
      + "WHERE `id` = ? AND `planet_id` = ? AND `planet_galaxy_id` = ?;";
    PreparedStatement preparedStatement = this.connection.prepareStatement(query);
    preparedStatement.setInt(1, newId);
    preparedStatement.setString(2, name);
    preparedStatement.setDouble(3, radius);
    preparedStatement.setDouble(4, distanceToPlanet);
    preparedStatement.setInt(5, planetId);
    preparedStatement.setInt(6, galaxyId);
    preparedStatement.setInt(7, oldId);
    preparedStatement.setInt(9, galaxyId);
    preparedStatement.execute();
  }

  public void deleteSatellite(Integer id) throws SQLException {
    String query = "DELETE FROM `planets`.`satellite`\n"
      + "WHERE satellite.id = ? ;";
    PreparedStatement preparedStatement = this.connection.prepareStatement(query);
    preparedStatement.setInt(1, id);
    preparedStatement.execute();
  }
}
