package Dao;

import Model.Favorite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoriteDao {

    protected ConnectionManager connectionManager;
    private static FavoriteDao instance = null;

    protected FavoriteDao() {
        connectionManager = new ConnectionManager();
    }

    public static FavoriteDao getInstance() {
        if (instance == null) {
            instance = new FavoriteDao();
        }
        return instance;
    }

    //insert favorite
    public Favorite createFavorite(Favorite favorite) throws SQLException {
        String inseretFavorite = "INSERT INTO Favorite(FavoriteType,FavoriteTypeId,UserId) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(inseretFavorite);
            insertStmt.setString(1, favorite.getFavoriteType());
            insertStmt.setInt(2, favorite.getFavoriteID());
            insertStmt.setInt(3, favorite.getUserID());
            insertStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return favorite;
    }

    // get favorite by userId
    public List<Favorite> getFavoriteByUserId(int userId) {

        List<Favorite> favorites = new ArrayList<>();
        String selectFavorite = "SELECT * FROM Favorite WHERE UserId=?";
        System.out.println("get favorite by searching userId");
        System.out.println(selectFavorite);
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectFavorite);
            selectStmt.setInt(1, userId);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String favoriteType = "";
                if (!results.getString("FavoriteType").equals("")) {
                    favoriteType = results.getString("FavoriteType");
                }
                int favoriteTypeId = results.getInt("FavoriteTypeId");
                Favorite favorite = new Favorite(favoriteType, favoriteTypeId, userId);
                favorites.add(favorite);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("size= " + favorites.size());
        return favorites;
    }

    //delete favorite by userId
    public Favorite delete(int userId) throws SQLException {
        String deleteFavorite = "DELETE FROM Favorite WHERE userId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        System.out.println("delete favorite by searching userId");
        System.out.println(deleteFavorite);
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteFavorite);
            deleteStmt.setInt(1, userId);
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

    //update favoriteType
    public Favorite updateFavoriteType(Favorite favorite, String favoriteType) throws SQLException {
        String updateFavoriteS = "Update Favorite SET FavoriteType = ? WHERE FavoriteId= ?";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        System.out.println("update favorite type");
        System.out.println("update favorite sql= " + updateFavoriteS);
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateFavoriteS);
            updateStmt.setString(1, favoriteType);
            updateStmt.setInt(2, favorite.getFavoriteID());
            updateStmt.executeUpdate();
            favorite.setFavoriteType(favoriteType);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return favorite;
    }
}
