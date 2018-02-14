package io.paxs.cryptos.domain;

import java.util.List;
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

    List<? extends Line> getLines();
}
