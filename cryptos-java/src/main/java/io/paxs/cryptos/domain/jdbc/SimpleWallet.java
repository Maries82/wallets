package io.paxs.cryptos.domain.jdbc;

import io.paxs.cryptos.domain.User;
import io.paxs.cryptos.domain.Wallet;

import java.util.Optional;

/**
 * Created by AELION on 06/02/2018.
 */
public class SimpleWallet implements Wallet {

    int id;
    String name;

        //Useless constructort, but for java EE
    public SimpleWallet() { super();
    }

    public SimpleWallet(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Optional<User> getUser() {
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name + "("+this.id+")";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
