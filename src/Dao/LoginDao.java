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
    String query = "INSERT INTO Login(UserName,Password,Active,BlockUntil,UserTypeName) VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      ps.setInt(1, comment.getProjectID());
      ps.setString(2, comment.getContent());
      ps.setTimestamp(3, comment.getCreateTime());
      ps.setBoolean(4, comment.isAvailable());
      ps.setInt(5, comment.getReplyToCommentID());
      ps.setInt(6, comment.getDislikedCount());
      ps.setInt(7, comment.getLikedCount());
      ps.setInt(8, comment.getUserID());
      ps.executeUpdate();
      resultKey = ps.getGeneratedKeys();
      int commentID = -1;
      if (resultKey.next()) {
        commentID = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      comment.setCommentID(commentID);
      System.out.println("Successfully created comment ID: "+ commentID);
      return comment;
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
