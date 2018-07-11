package Model;

public class Favorite {

  private int favoriteID;
  private String favoriteType;
  private int favoriteTypeID;
  private int userID;

  public Favorite(int favoriteID, String favoriteType, int favoriteTypeID, int userID) {
    this.favoriteID = favoriteID;
    this.favoriteType = favoriteType;
    this.favoriteTypeID = favoriteTypeID;
    this.userID = userID;
  }

  public Favorite(String favoriteType, int favoriteTypeID, int userID) {
    this.favoriteType = favoriteType;
    this.favoriteTypeID = favoriteTypeID;
    this.userID = userID;
  }

  public int getFavoriteID() {
    return favoriteID;
  }

  public void setFavoriteID(int favoriteID) {
    this.favoriteID = favoriteID;
  }

  public String getFavoriteType() {
    return favoriteType;
  }

  public void setFavoriteType(String favoriteType) {
    this.favoriteType = favoriteType;
  }

  public int getFavoriteTypeID() {
    return favoriteTypeID;
  }

  public void setFavoriteTypeID(int favoriteTypeID) {
    this.favoriteTypeID = favoriteTypeID;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }
}
