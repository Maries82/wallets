package io.paxs.cryptos.domain.jpa;

import java.util.Optional;

/**
 * Created by AELION on 06/02/2018.
 */
public interface Wallet {
    int getId();
    default Optional<User> getUser(){
        return null;
    }
    String getName();
}
