package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

  public static Connection connect() throws SQLException {
      return DriverManager.getConnection("jdbc:mysql://localhost:3306/planets","root","admin");
  }

}
