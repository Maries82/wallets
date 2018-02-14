package io.paxs.cryptos.ws;

import io.paxs.cryptos.dao.WalletDao;
import io.paxs.cryptos.domain.User;
import io.paxs.cryptos.domain.Wallet;
import io.paxs.cryptos.domain.jdbc.FullWallet;
import io.paxs.cryptos.domain.jdbc.SimpleUser;
import io.paxs.cryptos.jpa.JpaWalletDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by AELION on 06/02/2018.
 */

@Path("wallets") //pour avoir /cryptos/api/wallets
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WalletWs {

    @GET
    public List<Wallet> getWallets() throws SQLException {
        WalletDao dao = new WalletDao();
        return dao.listWallets();
    }

    @GET
    @Path("{id}")
    public Wallet getWallet(@PathParam("id") int walletId){
        return new JpaWalletDao().getWallet(walletId);
    }

    //JaxRS annotations
    @POST
    /* returns future wallet with an id */
    public Wallet createWallet (FullWallet wallet /* sent wallet, has ni id*/){

       Optional<User> option = wallet.getUser();

       if (! option.isPresent ()){
           //400x : navigator sent wrong informations
           throw new NotAcceptableException("406 : No user id sent");
       }

       if (wallet.getName().length()<2){
           throw new NotAcceptableException("406 : wallet name must have at least 2 letters");
       }

        try {
            int id = new WalletDao().createWallet(option.get().getId(),wallet.getName());

            User boundUser = wallet.getUser().get(); //optional : here sure to have a user
            SimpleUser simpleUser = new SimpleUser(boundUser.getId(),boundUser.getName());
            return new FullWallet(id,wallet.getName(),simpleUser);

        } catch (SQLException e) {
            throw new ServerErrorException("Database error, sorry",500);
        }
    }


}
