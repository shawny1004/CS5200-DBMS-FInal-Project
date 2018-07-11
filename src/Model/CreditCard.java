package Model;

import java.sql.Timestamp;

public class CreditCard {

  private String cardNumber;
  private Timestamp expiration;
  private String cvv;
  private int userID;

  public CreditCard(String cardNumber, Timestamp expiration, String cvv, int userID) {
    this.cardNumber = cardNumber;
    this.expiration = expiration;
    this.cvv = cvv;
    this.userID = userID;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public Timestamp getExpiration() {
    return expiration;
  }

  public void setExpiration(Timestamp expiration) {
    this.expiration = expiration;
  }

  public String getCvv() {
    return cvv;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }
}
