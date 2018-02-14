package io.paxs.cryptos.domain.jpa;

import io.paxs.cryptos.domain.Line;

import javax.persistence.*;

/**
 * Created by AELION on 14/02/2018.
 */

@Entity
public class JpaLine implements Line{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    int id;

    String symbol;
    double quantity;

    @ManyToOne
    JpaWallet wallet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public JpaWallet getWallet() {
        return wallet;
    }

    public void setWallet(JpaWallet wallet) {
        this.wallet = wallet;
    }
}
