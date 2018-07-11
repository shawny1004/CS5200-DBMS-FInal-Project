package Model;

import java.sql.Time;
import java.sql.Timestamp;

public class Project {

  private int projectID;
  private String title;
  private String description;
  private Timestamp createTime;
  private int likedCount;
  private int dislikedCount;
  private boolean active;
  private int userID;

  public Project(int projectID, String title, String description, Timestamp createTime,
      int likedCount, int dislikedCount, boolean active, int userID) {
    this.projectID = projectID;
    this.title = title;
    this.description = description;
    this.createTime = createTime;
    this.likedCount = likedCount;
    this.dislikedCount = dislikedCount;
    this.active = active;
    this.userID = userID;
  }

  public Project(String title, String description, Timestamp createTime, int likedCount,
      int dislikedCount, boolean active, int userID) {
    this.title = title;
    this.description = description;
    this.createTime = createTime;
    this.likedCount = likedCount;
    this.dislikedCount = dislikedCount;
    this.active = active;
    this.userID = userID;
  }

  public int getProjectID() {
    return projectID;
  }

  public void setProjectID(int projectID) {
    this.projectID = projectID;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public int getLikedCount() {
    return likedCount;
  }

  public void setLikedCount(int likedCount) {
    this.likedCount = likedCount;
  }

  public int getDislikedCount() {
    return dislikedCount;
  }

  public void setDislikedCount(int dislikedCount) {
    this.dislikedCount = dislikedCount;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }
}
