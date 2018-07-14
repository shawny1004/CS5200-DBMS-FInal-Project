package Dao;

import Model.CreditCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreditCardDao {

    protected ConnectionManager connectionManager;

    private static CreditCardDao instance = null;

    protected CreditCardDao() {
        connectionManager = new ConnectionManager();
    }

    public static CreditCardDao getInstance() {
        if (instance == null) {
            instance = new CreditCardDao();
        }
        return instance;
    }

//    private String cardNumber;
//    private Timestamp expiration;
//    private String cvv;
//    private int userID;

    //insert new credit card information
    public CreditCard create(CreditCard creditCard) throws SQLException {

        String insertCredits = "INSERT INTO CreditCard(CardNumber,Expiration,Cvv,UserId) VALUES (?,?,?,?)";
        System.out.println("Insert Credit card");
        System.out.println("insert credit card sql = " + insertCredits);
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCredits);
            insertStmt.setString(1, creditCard.getCardNumber());
            insertStmt.setTimestamp(2, creditCard.getExpiration());
            insertStmt.setString(3, creditCard.getCvv());
            insertStmt.setInt(4, creditCard.getUserID());
            insertStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return creditCard;
    }

    //update credit card expiration date
    public CreditCard updateExpiration(CreditCard creditCard, Timestamp newExpiration) throws SQLException {
        String updateCreditCards = "UPDATE CreditCard SET Expiration=? WHERE CardNumber=?;";
        System.out.println("update timeStamp");
        System.out.println("update sql = " + updateCreditCards);
        Connection connection = null;
        PreparedStatement updateStmt = null;

        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateCreditCards);
            updateStmt.setTimestamp(1, newExpiration);
            updateStmt.setString(2, creditCard.getCardNumber());
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return creditCard;
    }

    //delete credit card information
    public CreditCard deleteCredit(CreditCard creditCard) throws SQLException {
        String deleteCreditCards = "DELETE FROM CreditCard WHERE CardNumber=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteCreditCards);
            deleteStmt.setString(1, creditCard.getCardNumber());
            deleteStmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }

    //get creditcard by card number
    public CreditCard getCreditCardByCardNumber(String cardNumber) throws SQLException {
        String selectCreditCards = "SELECT * FROM CreditCard WHERE CardNumber=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCreditCards);
            selectStmt.setString(1, cardNumber);
            results = selectStmt.executeQuery();
            CreditCardDao creditCardsDao = CreditCardDao.getInstance();
            UsersDao usersDao = UsersDao.getInstance();
            if (results.next()) {
                String resultCardNumber = results.getString("CardNumber");
                Timestamp expiration = results.getTimestamp("Expiration");
                String CVV = results.getString("CVV");
                int userId = results.getInt("userId");
                CreditCard creditCard = new CreditCard(resultCardNumber, expiration, CVV, userId);
                return creditCard;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }

    //get all the credit cards by the same user
    public List<CreditCard> getCreditCardsByUserId(int userId) throws SQLException {
        List<CreditCard> creditCards = new ArrayList<CreditCard>();
        String selectCreditCard = "SELECT * FROM CreditCard WHERE userId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        System.out.println("get creditcard sql= " + selectCreditCard);
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCreditCard);
            selectStmt.setInt(1, userId);
            results = selectStmt.executeQuery();
            CreditCardDao creditCardsDao = CreditCardDao.getInstance();
            UsersDao usersDao = UsersDao.getInstance();
            while (results.next()) {
                String resultCardNumber = results.getString("CardNumber");
                Timestamp expiration = results.getTimestamp("Expiration");
                String CVV = results.getString("CVV");
                CreditCard creditCard = new CreditCard(resultCardNumber, expiration, CVV, userId);
                creditCards.add(creditCard);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        System.out.println("number of credit card= " + creditCards.size());
        return creditCards;
    }

}
