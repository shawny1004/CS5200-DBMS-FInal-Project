package Model;

import java.sql.Date;

public class Login {

  private int userID;
  private String username;
  private String password;
  private boolean active;
  private Date blockUntil;
  private String userTypeName;

  public Login(int userID, String username, String password, boolean active,
      Date blockUntil, String userTypeName) {
    this.userID = userID;
    this.username = username;
    this.password = password;
    this.active = active;
    this.blockUntil = blockUntil;
    this.userTypeName = userTypeName;
  }

  public Login(String username, String password, boolean active, Date blockUntil,
      String userTypeName) {
    this.username = username;
    this.password = password;
    this.active = active;
    this.blockUntil = blockUntil;
    this.userTypeName = userTypeName;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public Date getBlockUntil() {
    return blockUntil;
  }

  public void setBlockUntil(Date blockUntil) {
    this.blockUntil = blockUntil;
  }

  public String getUserTypeName() {
    return userTypeName;
  }

  public void setUserTypeName(String userTypeName) {
    this.userTypeName = userTypeName;
  }
}
