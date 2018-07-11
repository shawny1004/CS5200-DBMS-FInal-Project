package Model;


import java.sql.Timestamp;

public class Contract {

  private int contractID;
  private int needID;
  private String Type;
  private int typeID;
  private int quantity;
  private Timestamp createTime;

  public Contract(int contractID, int needID, String type, int typeID, int quantity,
      Timestamp createTime) {
    this.contractID = contractID;
    this.needID = needID;
    Type = type;
    this.typeID = typeID;
    this.quantity = quantity;
    this.createTime = createTime;
  }

  public Contract(int needID, String type, int typeID, int quantity, Timestamp createTime) {
    this.needID = needID;
    Type = type;
    this.typeID = typeID;
    this.quantity = quantity;
    this.createTime = createTime;
  }

  public int getContractID() {
    return contractID;
  }

  public void setContractID(int contractID) {
    this.contractID = contractID;
  }

  public int getNeedID() {
    return needID;
  }

  public void setNeedID(int needID) {
    this.needID = needID;
  }

  public String getType() {
    return Type;
  }

  public void setType(String type) {
    Type = type;
  }

  public int getTypeID() {
    return typeID;
  }

  public void setTypeID(int typeID) {
    this.typeID = typeID;
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
}
