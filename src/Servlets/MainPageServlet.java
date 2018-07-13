package Servlets;

import Dao.LoginDao;
import Dao.ProjectDao;
import Model.Favorite;
import Model.Project;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainPageServlet extends HttpServlet {

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    HttpSession session = req.getSession();
    String username =(String) session.getAttribute("UserName");
    int userID = (int) session.getAttribute("userID");
    ProjectDao projectDao = ProjectDao.getInstance();
    try {
      List<Project> myproject = projectDao.getProjectsByUserID(userID);
    for(Project p : myproject){

    }


    } catch (Exception e) {
      System.out.println(e);
    }
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    doGet(req, resp);
  }
}
