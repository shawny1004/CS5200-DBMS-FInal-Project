package Dao;

import Model.Contract;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContractDao {

    protected ConnectionManager connectionManager;

    private static ContractDao instance = null;

    protected ContractDao() {
        connectionManager = new ConnectionManager();
    }

    public static ContractDao getInstance() {
        if (instance == null)
            instance = new ContractDao();

        return instance;
    }

    //create new contract
    public Contract createContact(Contract contract) throws SQLException {

        String insertContract = "INSERT INTO Contract(NeedId,Type,TypeId,Quantity,CreateTime) VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertContract);
            insertStmt.setInt(1, contract.getNeedID());
            insertStmt.setString(2, contract.getType());
            insertStmt.setInt(3, contract.getTypeID());
            insertStmt.setInt(4, contract.getQuantity());
            insertStmt.setTimestamp(5, contract.getCreateTime());
            insertStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return contract;
    }

    //search contract by contract id
    public Contract getContractById(int contractId) throws SQLException {

        String selectContract = "SELECT * FROM Contract WHERE ContractId=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectContract);
            selectStmt.setInt(1, contractId);
            results = selectStmt.executeQuery();

            if (results.next()) {
                int needId = results.getInt("NeedId");
                String type = results.getString("Type");
                int typeId = results.getInt("TypeId");
                int quantity = results.getInt("Quantity");
                Timestamp createTime = results.getTimestamp("createTime");
                Contract contract = new Contract(contractId, needId, type, typeId, quantity, createTime);
                return contract;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return null;
    }

    // return the recent n days contract
    public List<Contract> getRecentContract(int lastNDays) {

        String selectContract = "SELECT * FROM Contract WHERE createTime > DATE_SUB(CURRENT_TIMESTAMP, INTERVAL " + lastNDays + " DAY)";
        System.out.println("select statement = " + selectContract);
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        List<Contract> contracts = new ArrayList<>();

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectContract);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int contractId = results.getInt("contractId");
                int needId = results.getInt("NeedId");
                String type = results.getString("Type");
                int typeId = results.getInt("TypeId");
                int quantity = results.getInt("Quantity");
                Timestamp createTime = results.getTimestamp("createTime");
                Contract contract = new Contract(contractId, needId, type, typeId, quantity, createTime);
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("size= " + contracts.size());
        return contracts;
    }

    //delete contract by contractId
    public void deleteContractById(int contractId) throws SQLException {

        String deleteContract = "DELETE FROM Contract WHERE ContractId=?";
        Connection connection = null;
        System.out.println("delete contract by id");
        System.out.println("delete contract sql= " + deleteContract);
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteContract);
            deleteStmt.setInt(1, contractId);
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    //update contract quantity and at the same time, update the create time
    public Contract updateContract(Contract contract, int quantity) throws SQLException {
        String updateQuantity = "UPDATE Contract SET Quantity=?, CreateTime=? WHERE ContractId=?;";
        System.out.println("update contract");
        System.out.println("update sql= " + updateQuantity);
        Connection connection = null;
        PreparedStatement updateStmt = null;
        Timestamp currentTS = new Timestamp(System.currentTimeMillis());
        System.out.println("currentTs= " + currentTS);
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateQuantity);
            updateStmt.setInt(1, quantity);
            updateStmt.setTimestamp(2, currentTS);
            updateStmt.setInt(3, contract.getContractID());
            updateStmt.executeUpdate();
            return contract;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }

}
