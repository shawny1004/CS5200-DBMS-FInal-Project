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
    private CreditCardDao creditCardDao = CreditCardDao.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        String action = request.getParameter("action");

        {// if the action == insert, insert comment
            switch (action) {
                case "insert":
                    String creditCardNumStr = request.getParameter("creditCardNum");
                    String creditCardNum = String.valueOf(creditCardNumStr);

                    // ????????
                    int year, month;
                    // input will be in format : 2023/12
                    String expInput = request.getParameter("expiration");
                    year = Integer.valueOf(expInput.substring(0, 4));
                    month = Integer.valueOf(expInput.substring(5));
                    Date expiration = new Date(year,month,1);
                    String cvv = request.getParameter("cvv");

                    // get userId from session
                    HttpSession session = request.getSession();
                    String username = (String) session.getAttribute("UserName");
                    int userID = (int) session.getAttribute("userID");

                    CreditCard creditCard = new CreditCard(creditCardNum, expiration, cvv, userID);
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {


    }
}
