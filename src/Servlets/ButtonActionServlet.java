package Servlets;

import Dao.LoginDao;
import Dao.NotificationDao;
import Dao.ProjectDao;
import Dao.ProjectTagDao;
import Dao.TagsDao;
import Model.Notification;
import Model.Project;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ButtonActionServlet extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse resp) {
    HttpSession session = req.getSession();
    String username =(String) session.getAttribute("UserName");
    int userID = (int) session.getAttribute("userID");
    ProjectDao projectDao = ProjectDao.getInstance();

    NotificationDao notificationDao = NotificationDao.getInstance();

    String url = req.getRequestURI();
    int index = url.lastIndexOf("/");
    String activity = url.substring(index+1,index+5);
    String object = url.substring(index+5,index+9);
    int id = Integer.parseInt(req.getParameter("deletebutton").substring(7));

    System.out.println("url="+url);
    System.out.println("activity="+activity);
    System.out.println("object="+object);
    System.out.println("id="+id);

    try {
    if(activity.equals("dele")&&object.equals("Noti")){
      notificationDao.delete(id);
    }
      List<Project> myproject = projectDao.getProjectsByUserID(userID);
      List<Notification> myNoti = notificationDao.getNotificationByUserID(userID);
      System.out.println(myproject.size()+" projects found for user "+ userID);
      req.setAttribute("userName",username);
      req.setAttribute("myNoti",myNoti);
      req.setAttribute("myProjects",myproject);
      req.getRequestDispatcher("/Main.jsp").forward(req, resp);

    } catch (Exception e) {
      System.out.println(e);
    }
  }
  public void doPost(HttpServletRequest req, HttpServletResponse resp) {
    doGet(req, resp);
  }
}
