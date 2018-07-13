package Dao;

import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {

  public ConnectionManager connectionManager;
  private static UsersDao instance = null;
  private UsersDao() {
    connectionManager = new ConnectionManager();
  }
  public static UsersDao getInstance() {
    if (instance == null) {
      instance = new UsersDao();
    }
    return instance;
  }

  public void create(Users user) throws SQLException {
    String ps =
        "insert into users(UserID,Nickname,Email,Verified) VALUES(?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(ps,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setInt(1, user.getUserID());
      insertStmt.setString(2, user.getNickName());
      insertStmt.setString(3, user.getEmail());
      insertStmt.setBoolean(4, user.isVerified());

      insertStmt.execute();

      System.out.println("Successfully Created User" + user.getUserID());

    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (insertStmt != null) {
        insertStmt.close();
      }

    }
  }
  public void updateUserNickname(int userid, String nickname) throws SQLException {
    String ps = "update users set Nickname = ?  where UserID = ?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(ps);
      updateStmt.setString(1, nickname);
      updateStmt.setInt(2, userid);
      updateStmt.executeUpdate();
      System.out.println("Successfully Update User Nickname " + userid);
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(updateStmt != null) {
        updateStmt.close();
      }
    }
  }

  public void updateUserEmail(int userid, String email) throws SQLException {
    String ps = "update users set Email = ?  where UserID = ?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(ps);
      updateStmt.setString(1, email);
      updateStmt.setInt(2, userid);
      updateStmt.executeUpdate();
      System.out.println("Successfully Update User Email " + userid);
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(updateStmt != null) {
        updateStmt.close();
      }
    }
  }

  public void updateIsVerified(int userid, boolean verified) throws SQLException {
    String ps = "update users set Verified = ?  where UserID = ?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(ps);
      updateStmt.setBoolean(1, verified);
      updateStmt.setInt(2, userid);
      updateStmt.executeUpdate();
      System.out.println("Successfully Update User Verified " + userid);
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(updateStmt != null) {
        updateStmt.close();
      }
    }
  }
  public List<Users> getUsersByUsername(String username) throws SQLException {
    List<Users> user = new ArrayList<Users>();
    String ps = "select * from users where nickname = ?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(ps);
      selectStmt.setString(1, username);
      results = selectStmt.executeQuery();
      while(results.next()) {
        user.add( new Users(results.getInt(1),results.getString(2),results.getString(3),
            results.getBoolean(4)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return user;
  }


}
