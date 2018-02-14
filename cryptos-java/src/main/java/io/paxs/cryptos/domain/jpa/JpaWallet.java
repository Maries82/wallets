package io.paxs.cryptos.domain.jpa;

import io.paxs.cryptos.domain.Wallet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 13/02/2018.
 */

@Entity

public class JpaWallet implements Wallet {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)

        int id;

         String name;

         @Transient // Don't want to save in database. It is a Business attribute, not a database item
         List<JpaLine> lines = new ArrayList<>();

   @Override
    public int getId() {
        return  this.id;
    }

    @Override
    public String getName() {
        return name;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<JpaLine> getLines() {
        return lines;
    }

    public void setLines(List<JpaLine> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
