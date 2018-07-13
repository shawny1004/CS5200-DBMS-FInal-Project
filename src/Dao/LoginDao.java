package Dao;

import Model.*;
import Model.Comments;
import java.sql.Connection;
import java.sql.Date;
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
    PreparedStatement checkps;
    ResultSet checkResult = null;
    PreparedStatement ps = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      //check if this username already exist, if already exist, return null
      checkps = connection.prepareStatement(checkQuery);
      checkps.setString(1, login.getUsername());
      checkResult = checkps.executeQuery();
      if (checkResult.next()) {
        System.out.println("Username " + login.getUsername() + " already exists!");
        return null;
      }
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
      System.out.println("Successfully created User ID: " + userID);
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

  public boolean login(String Username, String Password) throws SQLException {

    String query = "SELECT * FROM Login WHERE Username =? AND Password = ?;";
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      ps = connection.prepareStatement(query);
      ps.setString(1, Username);
      ps.setString(2, Password);
      resultKey=ps.executeQuery();
      //Check if there's any Match
      if (resultKey.next()) {
        //If there's more than one Match, means repeated Username and password,  alert and stop login
        if (resultKey.next()) {
          System.out.println("REPEATED USERNAME FOUND FOR USERNAME "+ Username);
          return false;
        }
        return true;
      } else {
        //PASSWORD WRONG
        System.out.println("Failed to login");
        return false;
      }
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
      System.out.println("Successfully update password for UserName: " + Username);
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

  public void SetActive(String userName, boolean active) throws SQLException {
    String query = "UPDATE Login SET Active = ? WHERE UserName = ?;";
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      ps = connection.prepareStatement(query);
      ps.setBoolean(1, active);
      ps.setString(2, userName);
      ps.executeUpdate();
      System.out.println("Successfully update Status for UserName: " + userName);
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

  public void SetBlockUntil(String userName, Date blockTo) throws SQLException {
    String query = "UPDATE Login SET BlockUntil = ? WHERE UserName = ?;";
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      ps = connection.prepareStatement(query);
      ps.setDate(1, blockTo);
      ps.setString(2, userName);
      ps.executeUpdate();
      System.out.println(
          "Successfully update Block time to " + blockTo.toString() + " for UserName: " + userName);
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
