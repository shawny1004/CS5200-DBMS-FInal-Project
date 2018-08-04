package Servlets;


import Dao.*;
import Model.CreditCard;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;


public class CreditCardServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doGet(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("UserName");
        int userID = (int) session.getAttribute("userID");
        String URL = request.getRequestURI();
        int actionIndex = URL.lastIndexOf("/");
        String action = URL.substring(actionIndex + 1);
        System.out.println(action);

        CreditCardDao creditCardDao = CreditCardDao.getInstance();

        switch (action) {


            // delete
            case "DeleteCard":
                String creditCardDelete = request.getParameter("creditCardNum");
                try {
                    creditCardDao.deleteCreditbyCardNum(creditCardDelete);
                    request.getRequestDispatcher("/MainPage").forward(request, response);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;


            case "AddCard":
                String creditCardNumStr = request.getParameter("creditCardNum");


                // get the year and month value
                int year, month;
                // input will be in format : 2023/12
                String expInput = request.getParameter("expiration");
                year = Integer.valueOf(expInput.substring(0, 4));
                month = Integer.valueOf(expInput.substring(5));
                Date expiration = new Date(year, month, 1);
                // getParameter will return String
                // Integer.Valueof will convert the String to Integer

                String cvv = request.getParameter("cvv");

                CreditCard creditCard = new CreditCard(creditCardNumStr, expiration, cvv, userID);
                try {
                    creditCardDao.create(creditCard);
                    request.getRequestDispatcher("/MainPage").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

        }
    }
}
