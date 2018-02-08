package io.paxs.cryptos.ws;

import io.paxs.cryptos.dao.WalletDao;
import io.paxs.cryptos.domain.Wallet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 06/02/2018.
 */

@Path("wallets") //pour avoir /cryptos/api/wallets
@Produces(MediaType.APPLICATION_JSON)

public class WalletWs {

    @GET
    public List<Wallet> getWallets() throws SQLException {
        WalletDao dao = new WalletDao();
        return dao.listWallets();
    }

}
