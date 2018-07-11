package Model;

import java.sql.Timestamp;

public class FAQ {

  private int faqID;
  private int projectID;
  private String question;
  private String answer;
  private Timestamp lastEditTime;
  private int available;
  private int userID;

  public FAQ(int faqID, int projectID, String question, String answer,
      Timestamp lastEditTime, int available, int userID) {
    this.faqID = faqID;
    this.projectID = projectID;
    this.question = question;
    this.answer = answer;
    this.lastEditTime = lastEditTime;
    this.available = available;
    this.userID = userID;
  }

  public FAQ(int projectID, String question, String answer, Timestamp lastEditTime, int available,
      int userID) {
    this.projectID = projectID;
    this.question = question;
    this.answer = answer;
    this.lastEditTime = lastEditTime;
    this.available = available;
    this.userID = userID;
  }

  public int getFaqID() {
    return faqID;
  }

  public void setFaqID(int faqID) {
    this.faqID = faqID;
  }

  public int getProjectID() {
    return projectID;
  }

  public void setProjectID(int projectID) {
    this.projectID = projectID;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public Timestamp getLastEditTime() {
    return lastEditTime;
  }

  public void setLastEditTime(Timestamp lastEditTime) {
    this.lastEditTime = lastEditTime;
  }

  public int getAvailable() {
    return available;
  }

  public void setAvailable(int available) {
    this.available = available;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }
}
