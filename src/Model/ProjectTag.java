package Model;

public class ProjectTag {

  private int projectID;
  private int tagID;

  public ProjectTag(int projectID, int tagID) {
    this.projectID = projectID;
    this.tagID = tagID;
  }

  public int getProjectID() {
    return projectID;
  }

  public void setProjectID(int projectID) {
    this.projectID = projectID;
  }

  public int getTagID() {
    return tagID;
  }

  public void setTagID(int tagID) {
    this.tagID = tagID;
  }
}
