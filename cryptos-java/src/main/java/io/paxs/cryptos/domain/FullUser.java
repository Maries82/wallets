package io.paxs.cryptos.domain;

import java.util.List;

/**
 * Created by AELION on 09/02/2018.
 */
public class FullUser extends SimpleUser {

    List<Wallet> wallets;


    //empty constructor with no real sense
    //Needed for jave EE bindings technology
    public FullUser() {
        super();
    }


    public FullUser(int id, String name, List<Wallet> wallets) {
        super(id, name);
        this.wallets = wallets;
    }

  @Override
    public String toString() {
        return this.name+" : "+this.wallets;
    }

    @Override
    public List<Wallet> getWallets() {
        return this.wallets;
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }
}
