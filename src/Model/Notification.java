package Model;

import java.sql.Timestamp;

public class Notification {

  private int notificationID;
  private Timestamp createTime;
  private String content;
  private boolean viewed;
  private String type;
  private int userID;

  public Notification(int notificationID, Timestamp createTime, String content, boolean viewed,
      String type, int userID) {
    this.notificationID = notificationID;
    this.createTime = createTime;
    this.content = content;
    this.viewed = viewed;
    this.type = type;
    this.userID = userID;
  }

  public Notification(Timestamp createTime, String content, boolean viewed, String type,
      int userID) {
    this.createTime = createTime;
    this.content = content;
    this.viewed = viewed;
    this.type = type;
    this.userID = userID;
  }

  public int getNotificationID() {
    return notificationID;
  }

  public void setNotificationID(int notificationID) {
    this.notificationID = notificationID;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean isViewed() {
    return viewed;
  }

  public void setViewed(boolean viewed) {
    this.viewed = viewed;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }
}
