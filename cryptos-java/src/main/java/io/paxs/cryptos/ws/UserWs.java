package io.paxs.cryptos.ws;

import io.paxs.cryptos.dao.UserDao;
import io.paxs.cryptos.domain.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 09/02/2018.
 */

@Path("users")
@Produces(MediaType.APPLICATION_JSON)

public class UserWs {
    @GET
    public List<User> getUsers() throws SQLException {
        UserDao dao = new UserDao();
        return dao.listUsers();
    }
}
