package Model;

public class Tags {

  private int tagID;
  private String tagName;
  private String tagDescription;

  public Tags(int tagID, String tagName, String tagDescription) {
    this.tagID = tagID;
    this.tagName = tagName;
    this.tagDescription = tagDescription;
  }

  public Tags(String tagName, String tagDescription) {
    this.tagName = tagName;
    this.tagDescription = tagDescription;
  }

  public int getTagID() {
    return tagID;
  }

  public void setTagID(int tagID) {
    this.tagID = tagID;
  }

  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

  public String getTagDescription() {
    return tagDescription;
  }

  public void setTagDescription(String tagDescription) {
    this.tagDescription = tagDescription;
  }
}
