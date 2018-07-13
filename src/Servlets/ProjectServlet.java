package Servlets;

import Dao.LoginDao;
import Dao.ProjectDao;
import Model.Project;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProjectServlet extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse resp) {
    String url = req.getRequestURI();
    int index = url.lastIndexOf("/");
    int projectID = Integer.parseInt(url.substring(index + 1));
    ProjectDao projectDao = ProjectDao.getInstance();
    LoginDao loginDao = LoginDao.getInstance();
    try {
      Project currentProject = projectDao.getProjectByID(projectID);
      if (currentProject != null) {
        req.setAttribute("currentProject", currentProject);
        String projectUsername  = loginDao.getUserNamebyUserID(currentProject.getUserID());
        req.setAttribute("projectUserName", projectUsername);
      }
      req.getRequestDispatcher("/Project.jsp").forward(req, resp);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void doPost(HttpServletRequest req, HttpServletResponse resp) {
    doGet(req, resp);
  }
}
