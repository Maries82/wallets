package io.paxs.cryptos.dao;

import io.paxs.cryptos.domain.jdbc.SimpleWallet;
import io.paxs.cryptos.domain.Wallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 06/02/2018.
 */
public class WalletDao {

    JdbcConnector connector = new JdbcConnector();

   public List<Wallet> listWallets() throws SQLException {

        List<Wallet> wallets = new ArrayList<>();

        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM wallet");

        while(rs.next()){
            String name = rs.getString("name");
            int id = rs.getInt("id");
            System.out.println(name + ":" +id);
            wallets.add(new SimpleWallet(id,name));

        }

        rs.close();
        stmt.close();
        conn.close();

        return wallets;
    }

    public int createWallet(int userId, String name)throws SQLException{
        String query = "INSERT INTO wallet(name,user_id) VALUES(?,?)";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1,name);
        stmt.setInt(2,userId);

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

    public void deleteWallet(int walletId) throws SQLException{
        String query = "DELETE FROM wallet WHERE id=?";
        //System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1,walletId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }


    public List<Wallet> findByName(String extract)throws SQLException{
        String query = "SELECT * FROM wallet WHERE name LIKE ?";
        List<Wallet> wallets = new ArrayList<>();

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setString(1,extract+"%");
        ResultSet rs = stmt.executeQuery();


        while(rs.next()){

            String name = rs.getString("name") ;
            int id = rs.getInt("id");
            wallets.add(new SimpleWallet(id,name));
            System.out.println("resp = "+id+name);
        }

        rs.close();
        stmt.close();
        conn.close();

        return wallets;
    }


    /**
     *
     * @param walletId the id of the wallet
     * @param newName the new name
     */
    public void updateWallet(int walletId, String newName) throws SQLException{

        //on veut chercher le nom du wallet par l'id et modifier juste le nom
        String query = "UPDATE wallet SET name = ? WHERE id = ? ";

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1,newName);
        statement.setInt(2,walletId);

        statement.executeUpdate();
        statement.close();
        conn.close();
    }

    public void deleteByName(String name) throws SQLException{
        String query = "DELETE FROM wallet WHERE name =?";

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,name);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }


    public void deleteAll(int userId)throws SQLException{
        String query = "DELETE FROM wallet WHERE user_id=?";
        //System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1,userId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public static void main(String[] args) throws SQLException {
        WalletDao dao = new WalletDao();
        //dao.createWallet(5,"Yoloni");
       // dao.createWallet(8,"Nana");
        //System.out.println(dao.findByName("Yo"));
        dao.createWallet(12,"serieux22");
        //dao.deleteByName("Nana");
        //System.out.println(dao.deleteByName("Yolo"));

       //new WalletDao().deleteWallet(27);
    }
}
