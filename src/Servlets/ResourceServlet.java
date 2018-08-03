package Servlets;

import Dao.FavoriteDao;
import Dao.NotificationDao;
import Dao.ResourceDao;
import Model.Favorite;
import Model.Notification;
import Model.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ResourceServlet extends HttpServlet {

  public void doGet(HttpServletRequest req,HttpServletResponse resp) {
    HttpSession session = req.getSession();
    String username = (String) session.getAttribute("UserName");
    int userID = (int) session.getAttribute("userID");
    String URL = req.getRequestURI();
    int actionIndex = URL.lastIndexOf("/");
    String action = URL.substring(actionIndex + 1);
    System.out.println(action);
    ResourceDao resourceDao = ResourceDao.getInstance();
    switch (action) {
      case "getResource":
        try {
          List<Resource> ResourceList = resourceDao.getRandomResource();
          req.setAttribute("ResourceList", ResourceList);
          req.getRequestDispatcher("./Resource.jsp").forward(req, resp);
        } catch (Exception e) {
          System.out.println(e);
        }
        break;
      case "SearchResource":
        try {
          String ResIDStr= req.getParameter("ResourceID");
          int ResourceID= 0;
          if(ResIDStr.length()!=0) {
            ResourceID = Integer.valueOf(ResIDStr);
          }
          String Type= req.getParameter("Type");
          int Quantity = 0;
          String QuantityStr=req.getParameter("Quantity");
          if(QuantityStr.length()>0){
            Quantity = Integer.valueOf(QuantityStr);
          }
          String RateStr= req.getParameter("Rate");
          int Rate = 0;
          if(RateStr.length()!=0) {
            Rate = Integer.valueOf(RateStr);
          }
          List<Resource> ResourceList = resourceDao.getResourceByCondition(ResourceID,Type,Quantity,Rate);
          req.setAttribute("ResourceList", ResourceList);
          req.getRequestDispatcher("./Resource.jsp").forward(req, resp);
        } catch (Exception e) {
          System.out.println(e);
        }
        break;

      case "ContactResource":
        try {

          List<Resource> ResourceList = resourceDao.getRandomResource();
          NotificationDao notificationDao = NotificationDao.getInstance();
          notificationDao.create(new Notification(new Timestamp(System.currentTimeMillis()), username +" is interested in Your Resource !",false, "normal",
              resourceDao.getResourceByID(Integer.valueOf(req.getParameter("ResourceID"))).getUserID()));
          req.setAttribute("contactInfo","Contact Notification successfully sent to Owner of Resource "+req.getParameter("ResourceID"));
          req.setAttribute("ResourceList", ResourceList);
          req.getRequestDispatcher("./Resource.jsp").forward(req, resp);
        } catch (Exception e) {
          System.out.println(e);
        }
        break;

      case "PublishResource":
        try {
          String type = req.getParameter("type");
          String description = req.getParameter("description");
          int quant = Integer.valueOf(req.getParameter("quantity"));
          int rate = Integer.valueOf(req.getParameter("hourlyRate"));
          Resource result = resourceDao.create(new Resource(type,description,quant,rate,userID));
          List<Resource> ResourceList = resourceDao.getRandomResource();
          req.setAttribute("publishInfo","Successfully published your item! Your item ID is " + result.getResourceID());
          req.setAttribute("ResourceList", ResourceList);
          req.getRequestDispatcher("./Resource.jsp").forward(req, resp);
        } catch (Exception e) {
          System.out.println(e);
        }
        break;

      case "AddFavResource":
        try {
          int ResourceID = Integer.valueOf(req.getParameter("ResourceID"));
          FavoriteDao favoriteDao = FavoriteDao.getInstance();
          favoriteDao.createFavorite(new Favorite("Resource",ResourceID,userID));
          List<Resource> ResourceList = resourceDao.getRandomResource();
          req.setAttribute("addFav","Successfully add favorite Resource "+req.getParameter("ResourceID"));
          req.setAttribute("ResourceList", ResourceList);
          req.getRequestDispatcher("./Resource.jsp").forward(req, resp);
        } catch (Exception e) {
          System.out.println(e);
        }
        break;
    }
  }
  public void doPost(HttpServletRequest req,HttpServletResponse resp){
    doGet(req,resp);
  }
}
