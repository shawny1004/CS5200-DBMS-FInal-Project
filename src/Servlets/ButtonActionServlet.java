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
    ProjectDao projectDao = ProjectDao.getInstance();
    NotificationDao notificationDao = NotificationDao.getInstance();
    String url = req.getRequestURI();
    int index = url.lastIndexOf("/");
    String activity = url.substring(index + 1, index + 5);
    String object = url.substring(index + 5, index + 9);

    System.out.println("url=" + url);
    System.out.println("activity=" + activity);
    System.out.println("object=" + object);

    try {
      if (activity.equals("Dele") && object.equals("Noti")) {
        int id = Integer.parseInt(req.getParameter("NotiID"));
        notificationDao.delete(id);
        req.getRequestDispatcher("/MainPage").forward(req, resp);
      }
      if (activity.equals("Like") && object.equals("Proj")) {
        int ProjectID = Integer.valueOf(req.getParameter("ProjectID"));
        projectDao.addLikeBy1(ProjectID);
        req.getRequestDispatcher("/projectItem?ProjectID=" + ProjectID).forward(req, resp);
      }
      if (activity.equals("UnLi") && object.equals("Proj")) {
        int ProjectID = Integer.valueOf(req.getParameter("ProjectID"));
        projectDao.addDislikeBy1(ProjectID);
        req.getRequestDispatcher("/projectItem?ProjectID=" + ProjectID).forward(req, resp);
      }

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void doPost(HttpServletRequest req, HttpServletResponse resp) {
    doGet(req, resp);
  }
}
