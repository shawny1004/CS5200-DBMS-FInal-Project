package Servlets;

import Dao.*;
import Model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NeedServlet extends HttpServlet {

    NeedDao needDao = NeedDao.getInstance();
    CommentsDao commentsDao = CommentsDao.getInstance();
    ProjectTagDao projectTagDao = ProjectTagDao.getInstance();
    TagsDao tagsDao = TagsDao.getInstance();
    LoginDao loginDao=LoginDao.getInstance();
    ProjectDao projectDao=ProjectDao.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("need servlet dopost");
        String action = request.getParameter("action");
        String needId = request.getParameter("needId");
        switch (action) {
            case "update":
                try {
                    Need currentNeed = needDao.getNeedsByNeedId(needId);
                    String typeStr = request.getParameter("type");
                    String parameterStr = request.getParameter("parameter");
                    String descriptionStr = request.getParameter("description");
                    String quantityStr = request.getParameter("quantity");
                    String activeStr = request.getParameter("active");
                    System.out.println("activeStr=" + activeStr);
                    boolean active = false;
                    if (activeStr == "IsActive") {
                        active = true;
                    }
                    currentNeed.setType(typeStr);
                    currentNeed.setParameter(parameterStr);
                    currentNeed.setDescription(descriptionStr);
                    currentNeed.setQuantity(Integer.valueOf(quantityStr));
                    currentNeed.setActive(active);
                    needDao.updateNeedObject(Integer.valueOf(needId), currentNeed);

                    Project currentProject = projectDao.getProjectByID(currentNeed.getProjectID());
                    String projectUsername  = loginDao.getUserNamebyUserID(currentProject.getUserID());
                    List<Need> needList = needDao.getNeedsByProjectID(currentNeed.getProjectID());
                    List<Comments> commentsList = commentsDao.getCommentsByProjectID(currentNeed.getProjectID());
                    // lol
                    ArrayList<Integer> tagsIDList = projectTagDao.getTagsIDsByProjectID(currentNeed.getProjectID());
                    ArrayList<Tags> tagsList = new ArrayList<>();
                    for (int i = 0; i < tagsIDList.size(); i++) {
                        tagsList.add(tagsDao.getTagByTagID(tagsIDList.get(i)));
                    }

                    request.setAttribute("currentProject", currentProject);
                    request.setAttribute("projectUserName", projectUsername);
                    request.setAttribute("tagsList", tagsList);
                    request.setAttribute("commentsList", commentsList);
                    request.setAttribute("needList", needList);
                    request.getRequestDispatcher("/Project.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("need servlet doget");
        String action = request.getParameter("action");
        String needId = request.getParameter("needId");

        switch (action) {
            case "getNeedByNeedId":
                System.out.println("needid= " + needId);
                try {
                    Need cuurrentNeed = needDao.getNeedsByNeedId(needId);
                    request.setAttribute("need", cuurrentNeed);
                    request.getRequestDispatcher("/UpdateNeed.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                System.out.println("delete need");
                try {
                    needDao.deleteNeedByID(Integer.valueOf(needId));
                    int projectId=Integer.valueOf(request.getParameter("projectId"));

                    Project currentProject = projectDao.getProjectByID(projectId);
                    String projectUsername  = loginDao.getUserNamebyUserID(currentProject.getUserID());
                    List<Need> needList = needDao.getNeedsByProjectID(projectId);
                    List<Comments> commentsList = commentsDao.getCommentsByProjectID(projectId);
                    // lol
                    ArrayList<Integer> tagsIDList = projectTagDao.getTagsIDsByProjectID(projectId);
                    ArrayList<Tags> tagsList = new ArrayList<>();
                    for (int i = 0; i < tagsIDList.size(); i++) {
                        tagsList.add(tagsDao.getTagByTagID(tagsIDList.get(i)));
                    }

                    request.setAttribute("currentProject", currentProject);
                    request.setAttribute("projectUserName", projectUsername);
                    request.setAttribute("tagsList", tagsList);
                    request.setAttribute("commentsList", commentsList);
                    request.setAttribute("needList", needList);
                    request.getRequestDispatcher("/Project.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
