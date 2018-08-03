package Dao;

import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResourceDao {

  //singleton
  public ConnectionManager connectionManager;

  private static ResourceDao instance = null;

  private ResourceDao() {
    connectionManager = new ConnectionManager();
  }

  public static ResourceDao getInstance() {
    if (instance == null) {
      instance = new ResourceDao();
    }
    return instance;
  }

  public Resource create(Resource resource) throws SQLException {
    String query = "INSERT INTO Resource(Type,Description,Quantity,HourlyRate,UserID) VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, resource.getType());
      ps.setString(2, resource.getDescription());
      ps.setInt(3, resource.getQuantity());
      ps.setInt(4, resource.getHourlyRate());
      ps.setInt(5, resource.getUserID());
      ps.execute();
      resultKey = ps.getGeneratedKeys();
      int ResourceID = -1;
      if (resultKey.next()) {
        ResourceID = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      resource.setResourceID(ResourceID);
      System.out.println("Successfully created Resource ID: " + resource);
      return resource;
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

  public List<Resource> getRandomRes() throws SQLException {
    List<Resource> result = new ArrayList<>();
    String selectResource = "select * from Resource Order By rand() LIMIT 20;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectResource);
      results = selectStmt.executeQuery();
      while (results.next()) {
        result.add(new Resource(results.getInt(1), results.getString(2), results.getString(3),
            results.getInt(4), results.getInt(5), results.getInt(6)));
      }
      return result;
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
  }

  public List<Resource> getResourceByType(String type) throws SQLException {
    List<Resource> result = new ArrayList<>();
    String selectResource = "select * from Resource where type=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectResource);
      selectStmt.setString(1, type);
      results = selectStmt.executeQuery();
      while (results.next()) {
        result.add(new Resource(results.getInt(1), results.getString(2), results.getString(3),
            results.getInt(4), results.getInt(5), results.getInt(6)));
      }
      return result;
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
  }

  public List<Resource> getResourceByUserID(int UserID) throws SQLException {
    List<Resource> result = new ArrayList<>();
    String selectResource = "select * from Resource where UserID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectResource);
      selectStmt.setInt(1, UserID);
      results = selectStmt.executeQuery();
      while (results.next()) {
        result.add(new Resource(results.getInt(1), results.getString(2), results.getString(3),
            results.getInt(4), results.getInt(5), results.getInt(6)));
      }
      return result;
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
  }

  public List<Resource> getResourceByHourlyRate(boolean ifLarger, int amount) throws SQLException {
    List<Resource> result = new ArrayList<>();
    String selectResource;
    if (ifLarger) {
      selectResource = "select * from Resource where HourlyRate>=?;";
    } else {
      selectResource = "select * from Resource where HourlyRate<=?;";
    }
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectResource);
      selectStmt.setInt(1, amount);
      results = selectStmt.executeQuery();
      while (results.next()) {
        result.add(new Resource(results.getInt(1), results.getString(2), results.getString(3),
            results.getInt(4), results.getInt(5), results.getInt(6)));
      }
      return result;
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
  }

  public List<Resource> getResourceByDescriptionSearch(String keyword) throws SQLException {
    List<Resource> result = new ArrayList<>();
    String selectResource = "select * from Resource where Description LIKE %?%;";

    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectResource);
      selectStmt.setString(1, keyword);
      results = selectStmt.executeQuery();
      while (results.next()) {
        result.add(new Resource(results.getInt(1), results.getString(2), results.getString(3),
            results.getInt(4), results.getInt(5), results.getInt(6)));
      }
      return result;
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
  }

  public void UpdateResource(int ResourceID, Resource newResource) throws SQLException {
    String selectResource = "UPDATE Resource SET Type=?,Description=?,Quantity=?,HourlyRate=? where ResourceID=?;";
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      ps = connection.prepareStatement(selectResource);
      ps.setString(1, newResource.getType());
      ps.setString(2, newResource.getDescription());
      ps.setInt(3, newResource.getQuantity());
      ps.setInt(4, newResource.getHourlyRate());
      ps.setInt(5, ResourceID);
      ps.executeUpdate();
      System.out.println("Successfully Updated Resource " + ResourceID);
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
      if (results != null) {
        results.close();
      }
    }
  }

  public void deleteResourceByID(int ResourceID) throws SQLException {
    String query = "DELETE FROM Resource where ResourceID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(query);
      selectStmt.setInt(1, ResourceID);
      selectStmt.execute();
      System.out.println("Successfully deleted Resource " + ResourceID);
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

  public List<Resource> getRandomResource() throws SQLException {
    List<Resource> result = new ArrayList<>();
    String selectResource = "select * from Resource Order By rand() LIMIT 20;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectResource);
      results = selectStmt.executeQuery();
      while (results.next()) {
        result.add(new Resource(results.getInt(1), results.getString(2), results.getString(3),
            results.getInt(4), results.getInt(5), results.getInt(6)));
      }
      return result;
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
  }

  public List<Resource> getResourceByCondition(int ResourceID,String type, int quant, int rate) throws SQLException {
    List<Resource> result = new ArrayList<>();
    String selectResource = "select * from Resource where ResourceID>0";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();

      if(ResourceID>0) {
        selectResource += (" AND ResourceID=" + ResourceID);
      }
      if(type.length()>0) {
        selectResource += (" AND Type='" + type+"'");
      }
      if(quant>0) {
        selectResource += (" AND Quantity>=" + quant+"");
      }
      if(rate>0) {
        selectResource += ( " AND HourlyRate<=" + rate);
      }
      selectStmt = connection.prepareStatement(selectResource);
      System.out.println(selectStmt);
      results = selectStmt.executeQuery();
      while (results.next()) {
        result.add(new Resource(results.getInt(1), results.getString(2), results.getString(3),
            results.getInt(4), results.getInt(5), results.getInt(6)));
      }
      return result;
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
  }

  public Resource getResourceByID(int ResourceID) throws SQLException {
    String selectResource = "select * from Resource where ResourceID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectResource);
      selectStmt.setInt(1, ResourceID);
      results = selectStmt.executeQuery();
      if(results.next()) {
        return new Resource(results.getInt(1), results.getString(2), results.getString(3),
            results.getInt(4), results.getInt(5), results.getInt(6));
      }
      else return null;
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
  }
}
