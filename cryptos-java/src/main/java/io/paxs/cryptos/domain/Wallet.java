package io.paxs.cryptos.domain;

import java.util.Optional;

/**
 * Created by AELION on 06/02/2018.
 */
public interface Wallet {
    int getId();
    Optional<User> getUser();
    String getName();
}
