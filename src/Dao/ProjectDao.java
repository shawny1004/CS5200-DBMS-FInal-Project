package Dao;

import Model.Project;
import Model.Tags;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {

    public ConnectionManager connectionManager;
    private static ProjectDao instance = null;
    private ProjectDao() {
      connectionManager = new ConnectionManager();
    }
    public static ProjectDao getInstance() {
      if (instance == null) {
        instance = new ProjectDao();
      }
      return instance;
    }

    public Project create(Project project) throws SQLException {
      String ps =
          "insert into project(Title,Description,CreateTime,LikedCount,DislikedCount,Active,UserID) VALUES(?,?,?,?,?,?,?);";
      Connection connection = null;
      PreparedStatement insertStmt = null;
      ResultSet resultKey = null;
      try {
        connection = connectionManager.getConnection();
        insertStmt = connection.prepareStatement(ps,
            Statement.RETURN_GENERATED_KEYS);
        insertStmt.setString(1, project.getTitle());
        insertStmt.setString(2, project.getDescription());
        insertStmt.setTimestamp(3, project.getCreateTime());
        insertStmt.setInt(4, project.getLikedCount());
        insertStmt.setInt(5, project.getDislikedCount());
        insertStmt.setBoolean(6, project.isActive());
        insertStmt.setInt(7, project.getUserID());

        insertStmt.execute();
        resultKey = insertStmt.getGeneratedKeys();
        int projectID = -1;
        if (resultKey.next()) {
          projectID = resultKey.getInt(1);
        } else {
          throw new SQLException();
        }
        project.setProjectID(projectID);
        System.out.println("Successfully Created Project" + projectID);
        return project;
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

  public void updateProjectTitleDesc(int projectID, String projectTitle, String projectDescription  ) throws SQLException {
    String ps = "update project set Title = ? , Description=? where ProjectID = ?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(ps);
      updateStmt.setString(1, projectTitle);
      updateStmt.setString(2, projectDescription);
      updateStmt.setInt(3, projectID);
      updateStmt.executeUpdate();
      System.out.println("Successfully Update Project Title and Description " + projectID);
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


  public void updateProjectActive(int projectID , boolean active ) throws SQLException {
    String ps = "update project set Active = ? where ProjectID = ?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(ps);
      updateStmt.setBoolean(1, active);
      updateStmt.setInt(2, projectID);
      updateStmt.executeUpdate();
      System.out.println("Successfully Update Project Active " + projectID);
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

  public void addLikeBy1(int projectID) throws SQLException {
    String ps = "update project set LikedCount= LikedCount+1 where ProjectID = ?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(ps);
      updateStmt.setInt(1, projectID);
      updateStmt.executeUpdate();
      System.out.println("Successfully added 1 like to Project " + projectID);
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

  public void addDislikeBy1(int projectID) throws SQLException {
    String ps = "update project set DislikedCount= DislikedCount+1 where ProjectID = ?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(ps);
      updateStmt.setInt(1, projectID);
      updateStmt.executeUpdate();
      System.out.println("Successfully added 1 dislike to Project" + projectID);
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

  public List<Project> getProjectsByUserID(int userID) throws SQLException {
    List<Project> project = new ArrayList<Project>();
    String ps = "select * from project where UserID = ?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(ps);
      selectStmt.setInt(1, userID);
      results = selectStmt.executeQuery();
      while(results.next()) {
        project.add( new Project(results.getInt(1),results.getString(2),results.getString(3),
            results.getTimestamp(4), results.getInt(5),results.getInt(6),results.getBoolean(7),
            results.getInt(8)));
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
    return project;
  }

  public Project getProjectByID(int projectID) throws SQLException {
    String ps = "select * from project where ProjectID = ?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(ps);
      selectStmt.setInt(1, projectID);
      results = selectStmt.executeQuery();
      if(results.next()) {
        return new Project(results.getInt(1),results.getString(2),results.getString(3),
            results.getTimestamp(4), results.getInt(5),results.getInt(6),results.getBoolean(7),
            results.getInt(8));
      }else{
        return null;
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
  }

  public List<Project> getProjectsBySimilarName(String title) throws SQLException {
    List<Project> project = new ArrayList<Project>();
    String ps = "select * from project where Title LIKE '%?%';";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(ps);
      selectStmt.setString(1, title);
      results = selectStmt.executeQuery();
      while(results.next()) {
        project.add( new Project(results.getInt(1),results.getString(2),results.getString(3),
            results.getTimestamp(4), results.getInt(5),results.getInt(6),results.getBoolean(7),
            results.getInt(8)));
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
    return project;
  }

  public void deleteByProjectID(int projectID) throws SQLException {
    String ps = "delete from project where ProjectID=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(ps);
      deleteStmt.setInt(1, projectID);
      deleteStmt.executeUpdate();
      System.out.println("Successfully Deleted Project " + projectID);
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
