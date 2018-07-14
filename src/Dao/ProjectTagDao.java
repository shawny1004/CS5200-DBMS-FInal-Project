package Dao;

import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectTagDao {

  public ConnectionManager connectionManager;

  private static ProjectTagDao instance = null;

  private ProjectTagDao() {
    connectionManager = new ConnectionManager();
  }

  public static ProjectTagDao getInstance() {
    if (instance == null) {
      instance = new ProjectTagDao();
    }
    return instance;
  }

  public void create(ProjectTag projectTag) throws SQLException {
    String ps =
        "insert into ProjectTag(ProjectID,TagID) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(ps,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setInt(1, projectTag.getProjectID());
      insertStmt.setInt(2, projectTag.getTagID());
      insertStmt.execute();
      System.out.println("Successfully Created Project " + projectTag.getProjectID() + " with Tag "+ projectTag.getTagID());

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

  public void deleteByProject(int projectID) throws SQLException {
      String ps =
          "DELETE FROM ProjectTag WHERE ProjectID = ?;";
      Connection connection = null;
      PreparedStatement insertStmt = null;
      try {
        connection = connectionManager.getConnection();
        insertStmt = connection.prepareStatement(ps,
            Statement.RETURN_GENERATED_KEYS);
        insertStmt.setInt(1, projectID);
        insertStmt.execute();
        System.out.println("Successfully deleted relationship for Project "+ projectID);

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

  public void deleteByTag(int tagID) throws SQLException {
    String ps =
        "DELETE FROM ProjectTag WHERE TagID = ?;";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(ps,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setInt(1, tagID);
      insertStmt.execute();
      System.out.println("Successfully deleted relationship for Tag "+ tagID);

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

  public ArrayList<Integer> getTagsIDsByProjectID(int projectID) throws SQLException {
    ArrayList<Integer> tags = new ArrayList<Integer>();
    String ps = "select * from ProjectTag where ProjectID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(ps);
      selectStmt.setInt(1, projectID);
      results = selectStmt.executeQuery();
      while(results.next()) {
        tags.add(results.getInt(2));
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
    return tags;
  }

}
