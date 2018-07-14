package Servlets;

import Dao.LoginDao;
import Dao.NotificationDao;
import Dao.ProjectDao;
import Model.Notification;
import Model.Project;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePasswordServlet extends HttpServlet {

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    HttpSession session = req.getSession();
    String username = (String) session.getAttribute("UserName");
    int userID = (int) session.getAttribute("userID");
    ProjectDao projectDao = ProjectDao.getInstance();
    NotificationDao notificationDao = NotificationDao.getInstance();
    String oldPassword = req.getParameter("oldpassword");
    String newPassword = req.getParameter("newpassword");
    LoginDao loginDao = LoginDao.getInstance();
    try {
      boolean result = loginDao.changePassword(username, oldPassword, newPassword);
      if (!result) {
        req.setAttribute("changepassword", "Fail to changed your password, your old password is incorrect");
      } else {
        req.setAttribute("changepassword", "Successfully changed your password! ");
      }
      List<Project> myproject = projectDao.getProjectsByUserID(userID);
      List<Notification> myNoti = notificationDao.getNotificationByUserID(userID);
      System.out.println(myproject.size() + " projects found for user " + userID);
      req.setAttribute("userName", username);
      req.setAttribute("myNoti", myNoti);
      req.setAttribute("myProjects", myproject);
      req.getRequestDispatcher("/Main.jsp").forward(req, resp);


    } catch (Exception e) {
      System.out.println(e);
    }
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    doGet(req, resp);
  }

}
