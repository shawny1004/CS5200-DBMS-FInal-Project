package Servlets;

import Dao.LoginDao;
import Dao.UsersDao;
import Model.Login;
import Model.Users;
import java.io.PrintWriter;
import java.io.Writer;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    String username = req.getParameter("UserName");
    String password = req.getParameter("PassWord");
    System.out.println(username);
    System.out.println(password);
    LoginDao loginDao = LoginDao.getInstance();
    try {
      if (loginDao.login(username, password)) {
        int userID = loginDao.getUserIDbyUserName(username);
        HttpSession session = req.getSession();
        session.setAttribute("UserName", username);
        session.setAttribute("userID",userID);
        resp.sendRedirect("./MainPage");
      } else {
        Writer writer = resp.getWriter();
        ((PrintWriter) writer).print("Login Failed");
      }

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    doGet(req, resp);
  }

}
