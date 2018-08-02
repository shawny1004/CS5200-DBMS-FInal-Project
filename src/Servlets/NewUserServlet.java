package Servlets;

import Dao.LoginDao;

import java.io.PrintWriter;
import java.io.Writer;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.UsersDao;
import Model.Login;
import Model.Users;

public class NewUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String newUserName = req.getParameter("newUserName");
        String newPassword = req.getParameter("newPassWord");
        String newNickname = req.getParameter("nickName");
        String newEmail = req.getParameter("email");
        System.out.println(newUserName + " " + " " + newPassword);
        LoginDao loginDao = LoginDao.getInstance();
        UsersDao userDao = UsersDao.getInstance();
        try {
            Login newlogin = loginDao.create(new Login(newUserName, newPassword, true, null,
                    "normal"));
            if (newlogin != null) {
                int userid = newlogin.getUserID();
                userDao.create(new Users(userid, newNickname, newEmail, false));
                req.setAttribute("newUser", "Successfully Created User " + newUserName);
            } else {
                req.setAttribute("newUser", "Username is already existed");
            }
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (Exception e) {


            System.out.println(e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}
