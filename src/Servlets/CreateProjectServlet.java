package Servlets;

import Dao.*;
import Model.*;

import java.sql.Timestamp;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateProjectServlet extends HttpServlet {

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    HttpSession session = req.getSession();
    String username = (String) session.getAttribute("UserName");
    int userID = (int) session.getAttribute("userID");
    ProjectDao projectDao = ProjectDao.getInstance();
    TagsDao tagsDao = TagsDao.getInstance();
    ProjectTagDao projectTagDao = ProjectTagDao.getInstance();
    NotificationDao notificationDao = NotificationDao.getInstance();
    CommentsDao commentsDao = CommentsDao.getInstance();

    try {
      String ProjectTitle = req.getParameter("projectTitle");
      String ProjectDes = req.getParameter("Description");
      String tag1 = req.getParameter("Tag1");
      String tag2 = req.getParameter("Tag2");
      String tag3 = req.getParameter("Tag3");
      int projectID = projectDao.create(
          new Project(ProjectTitle, ProjectDes, new Timestamp(System.currentTimeMillis()), 0, 0,
              true, userID)).getProjectID();
      int tag1ID,tag2ID,tag3ID;

      if(!tagsDao.checkGivenTagExists(tag1)){
        tag1ID = tagsDao.create(new Tags(tag1,"")).getTagID();
      }else{
        tag1ID = tagsDao.getTagByTagName(tag1).getTagID();
      }
      if(!tagsDao.checkGivenTagExists(tag2)){
        tag2ID = tagsDao.create(new Tags(tag2,"")).getTagID();
      }else{
        tag2ID = tagsDao.getTagByTagName(tag2).getTagID();
      }
      if(!tagsDao.checkGivenTagExists(tag3)){
        tag3ID = tagsDao.create(new Tags(tag3,"")).getTagID();
      }else{
        tag3ID = tagsDao.getTagByTagName(tag3).getTagID();
      }
      projectTagDao.create(new ProjectTag(projectID,tag1ID));
      projectTagDao.create(new ProjectTag(projectID,tag2ID));
      projectTagDao.create(new ProjectTag(projectID,tag3ID));
      System.out.println("project "+projectID+" created successfully with tags "+tag1ID+" , "+tag2ID+" , "+tag3ID);


      List<Comments> myComments=commentsDao.getCommentsByUserID(userID);
      List<Project> myproject = projectDao.getProjectsByUserID(userID);
      List<Notification> myNoti = notificationDao.getNotificationByUserID(userID);
      System.out.println(myproject.size()+" projects found for user "+ userID);
      req.setAttribute("userName",username);
      req.setAttribute("myNoti",myNoti);
      req.setAttribute("myProjects",myproject);
      req.setAttribute("myComments",myComments);
      req.getRequestDispatcher("/Main.jsp").forward(req, resp);

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    doGet(req, resp);
  }
}
