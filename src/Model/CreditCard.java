package Model;


import java.sql.Date;

public class CreditCard {

  private String cardNumber;
  private Date expiration;
  private String cvv;
  private int userID;


  public CreditCard(String cardNumber, Date expiration, String cvv, int userID) {
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

  public Date getExpiration() {
    return expiration;
  }

  public void setExpirationDate (Date expiration) {
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

  public String getMask(){
    return getCardNumber().substring(getCardNumber().length()-4);
  }

  // covert the Date to String to be used in JSP
  public String getExpirDate(){
    return getExpiration().getYear()+"/"+getExpiration().getMonth();
  }
}
