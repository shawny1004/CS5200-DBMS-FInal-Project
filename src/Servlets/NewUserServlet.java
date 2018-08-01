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
//loginDao.create();

    } catch (Exception e) {

      System.out.println(e);
    }
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    doGet(req, resp);
  }
}
