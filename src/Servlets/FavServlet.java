package Servlets;

import Dao.FavoriteDao;
import Dao.PeopleDao;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FavServlet extends HttpServlet {
public void doGet(HttpServletRequest req, HttpServletResponse resp){
  HttpSession session = req.getSession();
  String username = (String) session.getAttribute("UserName");
  int userID = (int) session.getAttribute("userID");
  String URL = req.getRequestURI();
  int actionIndex = URL.lastIndexOf("/");
  String action = URL.substring(actionIndex + 1);
  FavoriteDao favoriteDao = FavoriteDao.getInstance();
  System.out.println(action);
  switch (action) {
    case "DeleteFav":
      int FavID = Integer.valueOf(req.getParameter("FavID"));
      try {
        favoriteDao.deleteByFavID(FavID);
        req.setAttribute("DeleteInfo","Successfully Removed Favorite!");
        req.getRequestDispatcher("/MainPage").forward(req,resp);
      }catch (Exception e){
        System.out.println(e);
      }
      break;
  }
}

  public void doPost(HttpServletRequest req, HttpServletResponse resp){
doGet(req,resp);
  }
}
