package Dao;


import Model.Tags;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TagsDao {

  public ConnectionManager connectionManager;

  private static TagsDao instance = null;

  private TagsDao() {
    connectionManager = new ConnectionManager();
  }

  public static TagsDao getInstance() {
    if (instance == null) {
      instance = new TagsDao();
    }
    return instance;
  }

  public Tags create(Tags tag) throws SQLException {
    String ps =
        "insert into tags(TagName,TagDescription) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(ps,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, tag.getTagName());
      insertStmt.setString(2, tag.getTagDescription());
      insertStmt.executeUpdate();
      resultKey = insertStmt.getGeneratedKeys();
      int tagID = -1;
      if (resultKey.next()) {
        tagID = resultKey.getInt(1);
      } else {
        throw new SQLException();
      }
      tag.setTagID(tagID);
      System.out.println("Successfully Created Tag" + tagID);
      return tag;
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
      if (resultKey != null) {
        resultKey.close();
      }
    }
  }


  public void updateDescription(int tagID, String tagDescription ) throws SQLException {
    String ps = "update tags set TagDescription=? where TagID = ?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(ps);
      updateStmt.setInt(2, tagID);
      updateStmt.setString(1, tagDescription);
      updateStmt.executeUpdate();
      System.out.println("Successfully Update Tag Description " + tagID);
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

  public boolean checkGivenTagExists(String tagName) throws SQLException {
    String ps = "SELECT * FROM Tags WHERE TagName=?";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(ps);
      updateStmt.setString(1, tagName);
      ResultSet rs = updateStmt.executeQuery();
      return rs.next();
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


  public List<Tags> getTagsBysimilarTagName(String tagName) throws SQLException {
    List<Tags> tag = new ArrayList<Tags>();
    String ps = "select * from tags where TagName LIKE %?%;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(ps);
      selectStmt.setString(1, tagName);
      results = selectStmt.executeQuery();
      while(results.next()) {
        tag.add( new Tags(results.getInt(1),results.getString(2),results.getString(3)));
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
    return tag;
  }

  public Tags getTagByTagName(String tagName) throws SQLException {
    String ps = "select * from tags where TagName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(ps);
      selectStmt.setString(1, tagName);
      results = selectStmt.executeQuery();
      if(results.next()) {
        return new Tags(results.getInt(1),results.getString(2),results.getString(3));
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
    return null;
  }

  public Tags getTagByTagID(int tagID) throws SQLException {
    String ps = "select * from tags where TagID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(ps);
      selectStmt.setInt(1, tagID);
      results = selectStmt.executeQuery();
      if(results.next()) {
        return new Tags(results.getInt(1),results.getString(2),results.getString(3));
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
    return null;
  }

  public void delete(int tagID) throws SQLException {
    String ps = "delete from tags where TagID=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(ps);
      deleteStmt.setInt(1, tagID);
      deleteStmt.executeUpdate();
      System.out.println("Successfully Deleted Tag " + tagID);
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }


}
