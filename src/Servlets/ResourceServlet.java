package Servlets;

import Dao.ResourceDao;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ResourceServlet extends HttpServlet {

  public void doGet(HttpServletRequest req,HttpServletResponse resp){
    HttpSession session = req.getSession();
    String username =(String) session.getAttribute("UserName");
    int userID = (int) session.getAttribute("userID");
    ResourceDao resourceDao = ResourceDao.getInstance();
  }

  public void doPost(HttpServletRequest req,HttpServletResponse resp){
    doGet(req,resp);
  }
}
