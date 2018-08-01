package Servlets;

import Dao.LoginDao;
import java.io.PrintWriter;
import java.io.Writer;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NewUserServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    String setusername = req.getParameter("UserName");
    String setpassword = req.getParameter("PassWord");
    LoginDao loginDao = LoginDao.getInstance();
    try {
      if (loginDao.create(login)) {
        int userID = loginDao.getUserIDbyUserName(username);
        HttpSession session = req.getSession();
        session.setAttribute("UserName", username);
        session.setAttribute("userID",userID);
        resp.sendRedirect("./Login");
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
