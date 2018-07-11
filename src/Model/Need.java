package Model;


import java.sql.Timestamp;

public class Need {
  private int needID;
  private int projectID;
  private String type;
  private String parameter;
  private String description;
  private int quantity;
  private Timestamp createTime;
  private boolean active;

  public Need(int needID, int projectID, String type, String parameter, String description,
      int quantity, Timestamp createTime, boolean active) {
    this.needID = needID;
    this.projectID = projectID;
    this.type = type;
    this.parameter = parameter;
    this.description = description;
    this.quantity = quantity;
    this.createTime = createTime;
    this.active = active;
  }

  public Need(int projectID, String type, String parameter, String description, int quantity,
      Timestamp createTime, boolean active) {
    this.projectID = projectID;
    this.type = type;
    this.parameter = parameter;
    this.description = description;
    this.quantity = quantity;
    this.createTime = createTime;
    this.active = active;
  }

  public int getNeedID() {
    return needID;
  }

  public void setNeedID(int needID) {
    this.needID = needID;
  }

  public int getProjectID() {
    return projectID;
  }

  public void setProjectID(int projectID) {
    this.projectID = projectID;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getParameter() {
    return parameter;
  }

  public void setParameter(String parameter) {
    this.parameter = parameter;
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

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
