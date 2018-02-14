package io.paxs.cryptos.jpa;

import io.paxs.cryptos.domain.jpa.JpaLine;
import io.paxs.cryptos.domain.jpa.JpaWallet;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by AELION on 14/02/2018.
 */
public class JpaWalletDao {


    JpaConnector connector = new JpaConnector();

    public JpaWallet getWallet(int walletId) {

        EntityManager em = connector.createEntityManager();

        em.getTransaction().begin();

        JpaWallet w = em.find(JpaWallet.class, walletId);

        // we join with objects, not ids
        String jpql = "SELECT l FROM JpaLine l JOIN l.wallet w WHERE w.id = :id";
        List<JpaLine> lines = em.createQuery(jpql, JpaLine.class)
                .setParameter("id",walletId)
                .getResultList();

        System.out.println(lines);

        em.getTransaction().commit();
        em.close();

        return w;
    }


    public static void main(String[] args) {

        JpaWalletDao wdao = new JpaWalletDao();

        JpaWallet wallet = wdao.getWallet(1);
        System.out.println(wallet.getLines());
        wdao.connector.close();
    }
}

