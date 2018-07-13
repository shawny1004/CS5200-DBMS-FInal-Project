package Dao;
import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NotificationDao {

  public ConnectionManager connectionManager;

  private static NotificationDao instance = null;

  private NotificationDao() {
    connectionManager = new ConnectionManager();
  }

  public static NotificationDao getInstance() {
    if (instance == null) {
      instance = new NotificationDao();
    }
    return instance;
}

  public Notification create(Notification notification) throws SQLException {
    String insertReview =
        "insert into notification(CreateTime,Content,Viewed,Type,UserID) VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertReview,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setTimestamp(1, notification.getCreateTime());
      insertStmt.setString(2, notification.getContent());
      insertStmt.setBoolean(3, notification.isViewed());
      insertStmt.setString(4, notification.getType());
      insertStmt.setInt(5, notification.getUserID());
      insertStmt.executeUpdate();
      resultKey = insertStmt.getGeneratedKeys();
      int notificationID = -1;
      if (resultKey.next()) {
        notificationID = resultKey.getInt(1);
      } else {
        throw new SQLException();
      }
      notification.setNotificationID(notificationID);
      System.out.println("Successfully Created Notification " + notificationID);
      return notification;
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
    public Notification getNotificationByID(int notificationID) throws SQLException {
      String selectReview = "select * from notification where notificationID=?;";
      Connection connection = null;
      PreparedStatement selectStmt = null;
      ResultSet results = null;
      try {
        connection = connectionManager.getConnection();
        selectStmt = connection.prepareStatement(selectReview);
        selectStmt.setInt(1, notificationID);
        results = selectStmt.executeQuery();
        if(results.next()) {
          return new Notification(results.getInt(1),results.getTimestamp(2),results.getString(3),
              results.getBoolean(4),results.getString(5),results.getInt(6));
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


  public List<Notification> getNotificationByUserID(int UserID) throws SQLException {
    List<Notification> notifications = new ArrayList<Notification>();
    String selectNotification = "select * from notification where UserID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectNotification);
      selectStmt.setInt(1, UserID);
      results = selectStmt.executeQuery();
      while(results.next()) {
        notifications.add( new Notification(results.getInt(1),results.getTimestamp(2),results.getString(3),
            results.getBoolean(4),results.getString(5),results.getInt(6)));
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
    return notifications;
  }



  public void view(int notificationID ) throws SQLException {
    String updateview = "update notification set Viewed=1 where NotificationID = ?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(updateview);
      deleteStmt.setInt(1, notificationID);
      deleteStmt.executeUpdate();
      System.out.println("Successfully Viewed " + notificationID);
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


  public void delete(int notificationID) throws SQLException {
    String deleteNotification = "delete from notification where NotificationID=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteNotification);
      deleteStmt.setInt(1, notificationID);
      deleteStmt.executeUpdate();
      System.out.println("Successfully Deleted Notification " + notificationID);
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

