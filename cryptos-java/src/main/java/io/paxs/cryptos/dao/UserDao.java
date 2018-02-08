
package io.paxs.cryptos.dao;



import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import io.paxs.cryptos.domain.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDao {

    public DataSource connect(){
        DataSource dataSource;

        try {
            Context  context= new InitialContext();
            dataSource = (DataSource) context.lookup("java:/cryptos");
        } catch (NamingException e) {
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setUser("root");
            mysqlDataSource.setPassword("");
            mysqlDataSource.setServerName("localhost");
            mysqlDataSource.setDatabaseName("cryptos");
            mysqlDataSource.setPort(3306);

            dataSource = mysqlDataSource;
        }

        return dataSource;
    }


    public int createUser(String name) throws SQLException {
        String query = "INSERT INTO users(name,user_id) VALUES(?,?)";
        System.out.println(query);

        Connection conn = connect().getConnection();
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

        Connection conn = connect().getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1,userId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public List<User> findByName(String extract){
        return null;
    }

    public void deleteByName(String exactName){

    }

    public void updateUser(int userId, String newName){

    }

    public void deleteWalletUser(int userId){
        // delete wallets, then delete User
    }
}

