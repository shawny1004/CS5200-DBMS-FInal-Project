package Model;

import java.sql.Date;

public class People {

  private int peopleID;
  private String firstName;
  private String lastName;
  private String occupation;
  private String description;
  private Date dob;
  private int hourlyRate;
  private int userID;

  public People(int peopleID, String firstName, String lastName, String occupation,
      String description, Date dob, int hourlyRate, int userID) {
    this.peopleID = peopleID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.occupation = occupation;
    this.description = description;
    this.dob = dob;
    this.hourlyRate = hourlyRate;
    this.userID = userID;
  }

  public People(String firstName, String lastName, String occupation, String description,
      Date dob, int hourlyRate, int userID) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.occupation = occupation;
    this.description = description;
    this.dob = dob;
    this.hourlyRate = hourlyRate;
    this.userID = userID;
  }

  public int getPeopleID() {
    return peopleID;
  }

  public void setPeopleID(int peopleID) {
    this.peopleID = peopleID;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
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
