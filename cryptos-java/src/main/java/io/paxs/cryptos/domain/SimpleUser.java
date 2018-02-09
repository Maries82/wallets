package io.paxs.cryptos.domain;

import java.util.List;

/**
 * Created by AELION on 09/02/2018.
 */

public class SimpleUser implements User{

    int id;
    String name;

    public SimpleUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Wallet> getWallets() {
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
