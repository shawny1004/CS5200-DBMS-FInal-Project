package Model;

import java.sql.Timestamp;

public class Comments {

  private int commentID;
  private int projectID;
  private String content;
  private Timestamp createTime;
  private boolean available;
  private int replyToCommentID;
  private int dislikedCount;
  private int likedCount;
  private int userID;

  public Comments(int commentID, int projectID, String content, Timestamp createTime,
      boolean available, int replyToCommentID, int dislikedCount, int likedCount, int userID) {
    this.commentID = commentID;
    this.projectID = projectID;
    this.content = content;
    this.createTime = createTime;
    this.available = available;
    this.replyToCommentID = replyToCommentID;
    this.dislikedCount = dislikedCount;
    this.likedCount = likedCount;
    this.userID = userID;
  }

  public Comments(int projectID, String content, Timestamp createTime, boolean available,
      int replyToCommentID, int dislikedCount, int likedCount, int userID) {
    this.projectID = projectID;
    this.content = content;
    this.createTime = createTime;
    this.available = available;
    this.replyToCommentID = replyToCommentID;
    this.dislikedCount = dislikedCount;
    this.likedCount = likedCount;
    this.userID = userID;
  }

  public int getCommentID() {
    return commentID;
  }

  public void setCommentID(int commentID) {
    this.commentID = commentID;
  }

  public int getProjectID() {
    return projectID;
  }

  public void setProjectID(int projectID) {
    this.projectID = projectID;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  public int getReplyToCommentID() {
    return replyToCommentID;
  }

  public void setReplyToCommentID(int replyToCommentID) {
    this.replyToCommentID = replyToCommentID;
  }

  public int getDislikedCount() {
    return dislikedCount;
  }

  public void setDislikedCount(int dislikedCount) {
    this.dislikedCount = dislikedCount;
  }

  public int getLikedCount() {
    return likedCount;
  }

  public void setLikedCount(int likedCount) {
    this.likedCount = likedCount;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }
}
