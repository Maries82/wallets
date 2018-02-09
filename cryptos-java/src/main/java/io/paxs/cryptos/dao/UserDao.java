
package io.paxs.cryptos.dao;


import io.paxs.cryptos.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    JdbcConnector connector = new JdbcConnector();

    public List<User> listUsers() throws SQLException {

        List<User> users = new ArrayList<>();

        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");

        while(rs.next()){
            String name = rs.getString("name");
            int id = rs.getInt("id");
            System.out.println(name + ":" +id);
            users.add(new SimpleUser(id,name));
        }

        rs.close();
        stmt.close();
        conn.close();

        return users;
    }

    public User findUserWithWallets(int userId) throws SQLException{
        Connection connection = connector.getConnection();

        String query="SELECT * FROM wallet w RIGHT JOIN user u ON w.user_id = u.id WHERE u.id=?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,userId);

        ResultSet rs = statement.executeQuery();

        User user = null;

        //pro tip : always init lists
        List<Wallet> wallets = new ArrayList<>();

        while (rs.next()){

            String userName = rs.getString("u.name");
            System.out.println("userName:" + userName);
            user = new FullUser(userId,userName,wallets);

            int walletId = rs.getInt("w.id");
            String walletName = rs.getString("w.name");

            if (walletId > 0){
            Wallet wallet = new SimpleWallet(walletId, walletName);
            wallets.add(wallet);
            }
        }

        rs.close();
        statement.close();
        connection.close();

        return user;


    }

    public int createUser(String name) throws SQLException {
        String query = "INSERT INTO users(name,user_id) VALUES(?,?)";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1,name);

        stmt.executeUpdate();

        /*int rows = stmt.executeUpdate(query);
        if( rows != 1){
            throw new SQLException("Something wrong happened with :" + query);
        }*/

        ResultSet keys = stmt.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);

        stmt.close();
        conn.close();

        return  id;
    }

    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM user WHERE id=?";
        //System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1,userId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public List<User> findByName(String extract) throws SQLException {

        String query = "SELECT * FROM user WHERE name LIKE ?";
        List<User> users = new ArrayList<>();

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setString(1, extract + "%");
        ResultSet rs = stmt.executeQuery();


        while (rs.next()) {

            String name = rs.getString("name");
            int id = rs.getInt("id");
            users.add(new SimpleUser(id, name));
            System.out.println("resp = " + id + name);
        }

        rs.close();
        stmt.close();
        conn.close();

        return users;
    }

    public void deleteByName(String exactName) throws SQLException{
        String query = "DELETE FROM user WHERE name =?";

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,exactName);
        stmt.executeUpdate();

        stmt.close();
        conn.close();

    }

    public void updateUser(int userId, String newName)throws SQLException{

        //on veut chercher le nom du user par l'id et modifier juste le nom

        String query = "UPDATE user SET name = ? WHERE id = ? ";

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1,newName);
        statement.setInt(2,userId);

        statement.executeUpdate();
        statement.close();
        conn.close();
    }

    public void deleteWalletUser(int userId){
        // delete wallets, then delete User
    }

    public static void main(String[] args) throws SQLException {
        UserDao dao = new UserDao();

        System.out.println(dao.findUserWithWallets(2));

    }
}

