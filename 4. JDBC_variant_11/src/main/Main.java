package main;

import main.query.Getter;
import main.query.Modifier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection = Connector.connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from planet");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));

        }

        Getter getter = new Getter(connection);
        getter.getPlanetsWithLifeAndTheirSatellites(1);
        getter.getPlanetsWithMinRadiusAndMaxSatellites();
        getter.getPlanetsWithMaxSatellitesAndMinCapacityOfTheseSatellites();
        getter.getGalaxyWithMaxCoreTemperatureOfItsPlanets();

        Modifier modifier = new Modifier(connection);
        modifier.addSatellite(3, "Proxima B", 2345.3, 45235.3, 1, 2);
        modifier.deletePlanet(2,2);
        modifier.updateGalaxy(1,1,"Milky Way");
    }
}
