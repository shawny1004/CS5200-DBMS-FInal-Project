package Servlets;

import Dao.LoginDao;
import Dao.ProjectDao;
import Dao.ProjectTagDao;
import Dao.TagsDao;
import Model.Project;
import Model.Tags;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProjectServlet extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse resp) {
    String url = req.getRequestURI();
    int index = url.lastIndexOf("/");
    int projectID = Integer.valueOf(req.getParameter("ProjectID"));
    ProjectDao projectDao = ProjectDao.getInstance();
    LoginDao loginDao = LoginDao.getInstance();
    TagsDao tagsDao = TagsDao.getInstance();
    ProjectTagDao projectTagDao = ProjectTagDao.getInstance();
    try {
      Project currentProject = projectDao.getProjectByID(projectID);
      if (currentProject != null) {
        req.setAttribute("currentProject", currentProject);
        String projectUsername  = loginDao.getUserNamebyUserID(currentProject.getUserID());
        req.setAttribute("projectUserName", projectUsername);
        ArrayList<Integer> tagsIDList = projectTagDao.getTagsIDsByProjectID(projectID);
        System.out.println(tagsIDList.size() + " tags found");
        ArrayList<Tags> tagsList = new ArrayList<>();
        for(int i=0;i<tagsIDList.size();i++){
          tagsList.add(tagsDao.getTagByTagID(tagsIDList.get(i)));
        }
        req.setAttribute("tagsList", tagsList);
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
