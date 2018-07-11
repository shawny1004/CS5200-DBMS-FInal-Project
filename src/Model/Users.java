package Model;

public class Users {

  private int userID;
  private String nickName;
  private String email;
  private boolean verified;

  public Users(int userID, String nickName, String email, boolean verified) {
    this.userID = userID;
    this.nickName = nickName;
    this.email = email;
    this.verified = verified;
  }

  public Users(String nickName, String email, boolean verified) {
    this.nickName = nickName;
    this.email = email;
    this.verified = verified;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isVerified() {
    return verified;
  }

  public void setVerified(boolean verified) {
    this.verified = verified;
  }
}
