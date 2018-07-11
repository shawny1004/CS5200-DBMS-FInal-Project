package Model;

public class Resource {
  private int resourceID;
  private String type;
  private String description;
  private int quantity;
  private int hourlyRate;
  private int userID;

  public Resource(int resourceID, String type, String description, int quantity, int hourlyRate,
      int userID) {
    this.resourceID = resourceID;
    this.type = type;
    this.description = description;
    this.quantity = quantity;
    this.hourlyRate = hourlyRate;
    this.userID = userID;
  }

  public Resource(String type, String description, int quantity, int hourlyRate, int userID) {
    this.type = type;
    this.description = description;
    this.quantity = quantity;
    this.hourlyRate = hourlyRate;
    this.userID = userID;
  }

  public int getResourceID() {
    return resourceID;
  }

  public void setResourceID(int resourceID) {
    this.resourceID = resourceID;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getHourlyRate() {
    return hourlyRate;
  }

  public void setHourlyRate(int hourlyRate) {
    this.hourlyRate = hourlyRate;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }
}
