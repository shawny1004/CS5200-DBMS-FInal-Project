package Servlets;

import Dao.*;
import Model.*;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainPageServlet extends HttpServlet {

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    HttpSession session = req.getSession();
    String username =(String) session.getAttribute("UserName");
    int userID = (int) session.getAttribute("userID");
    CommentsDao commentDao=CommentsDao.getInstance();
    ProjectDao projectDao = ProjectDao.getInstance();
    NotificationDao notificationDao = NotificationDao.getInstance();
    FavoriteDao favoriteDao = FavoriteDao.getInstance();
    // credit card
    CreditCardDao creditCardDao = CreditCardDao.getInstance();

    try {
      List<Project> myproject = projectDao.getProjectsByUserID(userID);
      List<Notification> myNoti = notificationDao.getNotificationByUserID(userID);
      List<Comments> myComments=commentDao.getCommentsByUserID(userID);
      //
      List<CreditCard> myCreditCard = creditCardDao.getCreditCardsByUserId(userID);
      List<Favorite> myFav = favoriteDao.getFavoriteByUserId(userID);

      System.out.println("my comment size= "+myComments.size());
      System.out.println(myproject.size()+" projects found for user "+ userID);
      req.setAttribute("myComments",myComments);
      req.setAttribute("userName",username);
      req.setAttribute("myNoti",myNoti);
      req.setAttribute("myProjects",myproject);
      req.setAttribute("myFav",myFav);
      //
      req.setAttribute("myCreditCard",myCreditCard);
      req.getRequestDispatcher("/Main.jsp").forward(req, resp);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    doGet(req, resp);
  }
}
