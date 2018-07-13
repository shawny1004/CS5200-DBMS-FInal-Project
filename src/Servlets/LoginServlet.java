package Servlets;

import Dao.LoginDao;
import Model.Login;
import java.io.PrintWriter;
import java.io.Writer;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

protected void doGet(HttpServletRequest req,HttpServletResponse resp) {
try{
  Writer writer = resp.getWriter();
  ((PrintWriter) writer).print("hahaahahah");
  String username= req.getParameter("UserName");
  String password = req.getParameter("Password");
//  if(LoginDao.geUserbyUsername(username)){
//    if(Login.getPassword()==password){
//      resp.sendRedirect("./login.jsp");
//    }
//  }else{
//    ((PrintWriter) writer).print("loginfail");
//  }

  ((PrintWriter) writer).print("Your Username is "+ username);
  ((PrintWriter) writer).print("Your Password is "+ password);

}catch(Exception e){

}
  }

protected void doPost(HttpServletRequest req,HttpServletResponse resp){

  }

}
