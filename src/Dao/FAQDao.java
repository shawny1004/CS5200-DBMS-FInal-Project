package Dao;

import Model.FAQ;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FAQDao {

    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static FAQDao instance = null;

    protected FAQDao() {
        connectionManager = new ConnectionManager();
    }

    public static FAQDao getInstance() {
        if (instance == null) {
            instance = new FAQDao();
        }
        return instance;
    }

    //insert faq
    public FAQ create(FAQ faq) throws SQLException {
        String insertFAQ = "INSERT INTO FAQ(ProjectId,Question,Answer,LastEditTime,Available,UserId) VALUES(?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        System.out.println("this is create a new faq");
        System.out.println("sql = " + insertFAQ);
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertFAQ);
            insertStmt.setInt(1, faq.getProjectID());
            insertStmt.setString(2, faq.getQuestion());
            insertStmt.setString(3, faq.getAnswer());
            insertStmt.setTimestamp(4, faq.getLastEditTime());
            insertStmt.setBoolean(5, faq.isAvailable());
            insertStmt.setInt(6, faq.getUserID());
            insertStmt.executeUpdate();
            return faq;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    //select faqs by userId
    public List<FAQ> getFAQsByUserId(int userId) throws SQLException {
        List<FAQ> faqs = new ArrayList<>();
        String selectFAQ = "SELECT * FROM FAQ WHERE UserId=?";
        System.out.println("get all the faqs by searching userId");
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectFAQ);
            selectStmt.setInt(1, userId);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int faqId = results.getInt("FAQId");
                int projectId = results.getInt("ProjectId");
                String question = results.getString("Question");
                String answer = results.getString("Answer");
                Timestamp ts = results.getTimestamp("lastEditTime");
                boolean isAvailable = results.getBoolean("Available");
                FAQ currentFAQ = new FAQ(faqId, projectId, question, answer, ts, isAvailable, userId);
                faqs.add(currentFAQ);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        System.out.println("size= " + faqs.size());
        return faqs;
    }


    //delete faq by userId
    public void deleteFAQByUserId(int userId) throws SQLException {
        String deleteFAQ = "DELETE FROM FAQ WHERE userId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteFAQ);
            deleteStmt.setInt(1, userId);
            deleteStmt.executeUpdate();
            // Return null so the caller can no longer operate on the Persons instance.
            return;
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

    //update the faq by changing the question and answer
    public FAQ updateFAQ(FAQ faq, String questionStr, String answerStr) throws SQLException {
        int faqId = faq.getFaqID();
        String updateFAQ = "UPDATE FAQ SET Question =? , Answer=?, LastEditTime=? WHERE FAQId=?";
        System.out.println("update faq with question and answer");
        System.out.println("updateFaq sql= " + updateFAQ);
        Connection connection = null;
        PreparedStatement updateStmt = null;
        Timestamp currentTs=new Timestamp(System.currentTimeMillis());
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateFAQ);
			updateStmt.setString(1, questionStr);
            updateStmt.setString(2, answerStr);
            updateStmt.setTimestamp(3,currentTs);
            updateStmt.setInt(4,faqId);
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return faq;
    }

}
