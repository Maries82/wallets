package io.paxs.cryptos.ws;

import io.paxs.cryptos.dao.UserDao;
import io.paxs.cryptos.domain.jdbc.FullUser;
import io.paxs.cryptos.domain.jpa.User;
import io.paxs.cryptos.domain.jpa.Wallet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 09/02/2018.
 */

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserWs {
    @GET
    public List<User> getUsers() throws SQLException {
        UserDao dao = new UserDao();
        return dao.listUsers();
    }

    @GET
    @Path("{id}")//this is a PathParam
    public User getUser(@PathParam("id") int userId) throws SQLException {
        return new UserDao().findUserWithWallets(userId);
    }

    //JaxRS annotations
    @POST
    /* returns future name with an id */
    public User createUser (FullUser user){
        List<Wallet> wallets = new ArrayList<>();

        if (user.getName().length()<2){
            throw new NotAcceptableException("406 : user name must have at least 2 letters");
        }

        try {
            int id = new UserDao().createUser(user.getName());

            return new FullUser(id,user.getName(),wallets);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerErrorException("Database error, sorry",500);
        }
    }
}