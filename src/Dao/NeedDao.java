package Dao;

import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NeedDao {
  //singleton
  public ConnectionManager connectionManager;

  private static NeedDao instance = null;

  private NeedDao() {
    connectionManager = new ConnectionManager();
  }

  public static NeedDao getInstance() {
    if (instance == null) {
      instance = new NeedDao();
    }
    return instance;
  }

  public Need create(Need need) throws SQLException {
    String query = "INSERT INTO Need(ProjectID,Type,Parameter,Description,Quantity,CreateTime,Active) VALUES(?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      ps.setInt(1, need.getProjectID());
      ps.setString(2, need.getType());
      ps.setString(3, need.getParameter());
      ps.setString(4, need.getDescription());
      ps.setInt(5, need.getQuantity());
      ps.setTimestamp(6, need.getCreateTime());
      ps.setBoolean(7, need.isActive());
      ps.execute();
      resultKey = ps.getGeneratedKeys();
      int needID = -1;
      if (resultKey.next()) {
        needID = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      need.setNeedID(needID);
      System.out.println("Successfully created Need ID: "+ needID);
      return need;
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
