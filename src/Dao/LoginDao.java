package Dao;

import Model.*;
import Model.Comments;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {
  //singleton
  public ConnectionManager connectionManager;

  private static LoginDao instance = null;

  private LoginDao() {
    connectionManager = new ConnectionManager();
  }

  public static LoginDao getInstance() {
    if (instance == null) {
      instance = new LoginDao();
    }
    return instance;
  }

  public Login create(Login login) throws SQLException {
    String checkQuery = "SELECT * FROM Login WHERE UserName = ?;";
    String query = "INSERT INTO Login(UserName,Password,Active,BlockUntil,UserTypeName) VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement checkps = null;
    ResultSet checkResult = null;
    PreparedStatement ps = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, login.getUsername());
      ps.setString(2, login.getPassword());
      ps.setBoolean(3, login.isActive());
      ps.setDate(4, login.getBlockUntil());
      ps.setString(5, login.getUserTypeName());
      ps.execute();
      resultKey = ps.getGeneratedKeys();
      int userID = -1;
      if (resultKey.next()) {
        userID = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      login.setUserID(userID);
      System.out.println("Successfully created User ID: "+ userID);
      return login;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (ps != null) {
        ps.close();
      }
    }
  }

  public void changePassword(String Username, String newPassword) throws SQLException {
    String query = "UPDATE Login SET Password = ? WHERE UserName = ?;";
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      ps = connection.prepareStatement(query);
      ps.setString(1, newPassword);
      ps.setString(2, Username);
      ps.executeUpdate();
      System.out.println("Successfully update password for UserName: "+ Username);
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (ps != null) {
        ps.close();
      }
    }
  }

}
