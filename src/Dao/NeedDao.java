package Dao;

import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
      System.out.println("Successfully created Need ID: " + needID);
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

  public List<Need> getNeedsByNeedParameter(String parameter) throws SQLException {
    List<Need> result = new ArrayList<>();
    String selectNeed = "select * from Need where Parameter=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectNeed);
      selectStmt.setString(1, parameter);
      results = selectStmt.executeQuery();
      while (results.next()) {
        result.add(new Need(results.getInt(1), results.getInt(2), results.getString(3),
            results.getString(4), results.getString(5), results.getInt(6), results.getTimestamp(7),
            results.getBoolean(8)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return null;
  }

  public List<Need> getNeedsByProjectID(int projectID) throws SQLException {
    List<Need> result = new ArrayList<>();
    String selectNeed = "select * from Need where ProjectID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectNeed);
      selectStmt.setInt(1, projectID);
      results = selectStmt.executeQuery();
      while (results.next()) {
        result.add(new Need(results.getInt(1), results.getInt(2), results.getString(3),
            results.getString(4), results.getString(5), results.getInt(6), results.getTimestamp(7),
            results.getBoolean(8)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return null;
  }

  public List<Need> getNeedsByType(String Type) throws SQLException {
    List<Need> result = new ArrayList<>();
    String selectNeed = "select * from Need where Type=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectNeed);
      selectStmt.setString(1, Type);
      results = selectStmt.executeQuery();
      while (results.next()) {
        result.add(new Need(results.getInt(1), results.getInt(2), results.getString(3),
            results.getString(4), results.getString(5), results.getInt(6), results.getTimestamp(7),
            results.getBoolean(8)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return null;
  }

  public void updateNeed(int NeedID, Need newNeed) throws SQLException {
    String selectNeed = "UPDATE Need SET Type=?,Parameter=?,Description=?,Quantity=? where NeedID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectNeed);
      selectStmt.setString(1, newNeed.getType());
      selectStmt.setString(2, newNeed.getParameter());
      selectStmt.setString(3, newNeed.getDescription());
      selectStmt.setInt(4, newNeed.getQuantity());
      selectStmt.setInt(5, NeedID);
      selectStmt.executeUpdate();
      System.out.println("Successfully updated Need " + NeedID);
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
    }
  }

  public void updateNeedActive(int NeedID, boolean active) throws SQLException {
    String selectNeed = "UPDATE Need SET Active=? where NeedID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectNeed);
      selectStmt.setBoolean(1, active);
      selectStmt.setInt(2, NeedID);
      selectStmt.executeUpdate();
      System.out.println("Successfully updated Need " + NeedID + " Active to " + active);
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
    }
  }

  public void deleteNeedByID(int NeedID) throws SQLException {
    String selectNeed = "DELETE FROM Need where NeedID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectNeed);
      selectStmt.setInt(1, NeedID);
      selectStmt.executeUpdate();
      System.out.println("Successfully deleted Need " + NeedID);
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
    }
  }

  public void deleteNeedByProject(int projectID) throws SQLException {
    String selectNeed = "DELETE FROM Need where Project=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectNeed);
      selectStmt.setInt(1, projectID);
      selectStmt.executeUpdate();
      System.out.println("Successfully deleted Need for Project" + projectID);
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }

    }
  }
}
