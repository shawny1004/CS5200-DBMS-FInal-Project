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
    String username = req.getParameter("UserName");
    String password = req.getParameter("PassWord");
    LoginDao loginDao = LoginDao.getInstance();
    try {
      if (loginDao.login(username, password)) {
        HttpSession session = req.getSession();
        session.setAttribute("username", username);
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
