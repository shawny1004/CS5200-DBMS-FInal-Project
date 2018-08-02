package Servlets;

import Dao.CommentsDao;
import Dao.NotificationDao;
import Dao.ProjectDao;
import Model.Comments;
import Model.Notification;
import Model.Project;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class CommentServlet extends HttpServlet {

    private CommentsDao commentsDao = CommentsDao.getInstance();
    private ProjectDao projectDao = ProjectDao.getInstance();
    private NotificationDao notificationDao = NotificationDao.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("comment dopost");

        String action = request.getParameter("action");
        System.out.println("action= " + action);

        // if the action == insert, insert comment
        switch (action) {
            case "insert":
                String projectIDStr = request.getParameter("projectID");
                int projectID = Integer.valueOf(projectIDStr);
                String contentStr = request.getParameter("content");
                String replyToCommentIDStr = request.getParameter("replyToCommentID");
                int replyToCommentID = Integer.valueOf(replyToCommentIDStr);
                // current timestamp
                Timestamp currentTS = new Timestamp(System.currentTimeMillis());
                String dislikeCountStr = request.getParameter("dislikeCount");
                int dislikeCount = Integer.valueOf(dislikeCountStr);
                String likeCountStr = request.getParameter("likeCount");
                int likeCount = Integer.valueOf(likeCountStr);
                String availableStr = request.getParameter("available");
                boolean available = false;
                if (availableStr.equals("IsAvailable"))
                    available = true;
                // get userId from session
                HttpSession session = request.getSession();
                String username = (String) session.getAttribute("UserName");
                int userID = (int) session.getAttribute("userID");

                Comments comment = new Comments(projectID, contentStr, currentTS, available, replyToCommentID, dislikeCount, likeCount, userID);
                try {
                    commentsDao.create(comment);
                    List<Comments> myComments = commentsDao.getCommentsByUserID(userID);
                    List<Notification> myNoti = notificationDao.getNotificationByUserID(userID);
                    List<Project> myproject = projectDao.getProjectsByUserID(userID);
                    request.setAttribute("myComments", myComments);
                    request.setAttribute("userName", username);
                    request.setAttribute("myNoti", myNoti);
                    request.setAttribute("myProjects", myproject);
                    request.getRequestDispatcher("/Main.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            // update comment
            case "update":
                String commentIdStr = request.getParameter("commentId");
                int commentIdUpdated = Integer.valueOf(commentIdStr);
                String projectUpdatedIDStr = request.getParameter("projectID");
                int projectUpdatedID = Integer.valueOf(projectUpdatedIDStr);
                String contentUpdatedStr = request.getParameter("content");
                Timestamp currentTSUpdated = new Timestamp(System.currentTimeMillis());
                String replyCommentIDStrUpadted = request.getParameter("replyToCommentID");
                int replyCommentIDUpdated = Integer.valueOf(replyCommentIDStrUpadted);
                String dislikeCountStrUpdated = request.getParameter("dislikeCount");
                int dislikeCountUpdated = Integer.valueOf(dislikeCountStrUpdated);
                String likeCountStrUpdated = request.getParameter("likeCount");
                likeCount = Integer.valueOf(likeCountStrUpdated);
                String availableStrUpdated = request.getParameter("available");
                boolean availableUpdated = false;
                if (availableStrUpdated.equals("IsAvailable")) {
                    availableUpdated = true;
                } else {
                    availableUpdated = false;
                }
                session = request.getSession();
                int userIDUpdated = (int) session.getAttribute("userID");
                username = (String) session.getAttribute("UserName");

                Comments updatedComment = new Comments(commentIdUpdated, projectUpdatedID, contentUpdatedStr, currentTSUpdated, availableUpdated,
                        replyCommentIDUpdated, dislikeCountUpdated, likeCount, userIDUpdated);
                try {
                    commentsDao.updateComment(updatedComment);
                    List<Comments> myComments = commentsDao.getCommentsByUserID(userIDUpdated);
                    List<Notification> myNoti = notificationDao.getNotificationByUserID(userIDUpdated);
                    List<Project> myproject = projectDao.getProjectsByUserID(userIDUpdated);
                    request.setAttribute("myComments", myComments);
                    request.setAttribute("userName", username);
                    request.setAttribute("myNoti", myNoti);
                    request.setAttribute("myProjects", myproject);
                    request.getRequestDispatcher("/Main.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                // get userId from session
                break;
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("comment doget");

        String action = request.getParameter("action");
        System.out.println("action= " + action);

        switch (action) {
            case "getCommentByCommentId":
                System.out.println("get comment by id");
                try {
                    int commentId = Integer.valueOf(request.getParameter("commentId"));
                    Comments comment = commentsDao.getCommentsByCommentId(commentId);
                    request.setAttribute("comment", comment);
                    request.getRequestDispatcher("/UpdateComment.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            // delete comment by comment id
            case "delete":
                System.out.println("delete comment");
                int commentId = Integer.valueOf(request.getParameter("commentId"));
                System.out.println("comment id= " + commentId);
                try {
                    commentsDao.deleteCommentByCommentID(commentId);
                    HttpSession session = request.getSession();
                    String username = (String) session.getAttribute("UserName");
                    int userID = (int) session.getAttribute("userID");
                    List<Comments> myComments = commentsDao.getCommentsByUserID(userID);
                    List<Notification> myNoti = notificationDao.getNotificationByUserID(userID);
                    List<Project> myproject = projectDao.getProjectsByUserID(userID);
                    request.setAttribute("myComments", myComments);
                    request.setAttribute("userName", username);
                    request.setAttribute("myNoti", myNoti);
                    request.setAttribute("myProjects", myproject);
                    request.getRequestDispatcher("/Main.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
