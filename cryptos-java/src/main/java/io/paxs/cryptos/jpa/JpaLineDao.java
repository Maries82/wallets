package io.paxs.cryptos.jpa;

import io.paxs.cryptos.domain.jpa.JpaLine;
import io.paxs.cryptos.domain.jpa.JpaUser;
import io.paxs.cryptos.domain.jpa.JpaWallet;

import javax.persistence.EntityManager;

/**
 * Created by AELION on 14/02/2018.
 */
public class JpaLineDao {

    JpaConnector connector = new JpaConnector();

    public static void main(String[] args) {

        JpaUserDao userDao = new JpaUserDao();
        JpaUser kenny = userDao.createUser("Kenny");
        JpaWallet wallet = kenny.getWallets().get(0);

        JpaLineDao dao = new JpaLineDao();
        EntityManager em = dao.connector.createEntityManager();

        em.getTransaction().begin();
       kenny = em.merge(kenny);
        wallet = em.merge(wallet);

        JpaLine lineBtc = new JpaLine();
        lineBtc.setQuantity(12.55);
        lineBtc.setSymbol("BTC");
        lineBtc.setWallet(wallet);
        em.persist(lineBtc);

        JpaLine lineXmr = new JpaLine();
        lineXmr.setQuantity(1045);
        lineXmr.setSymbol("XMR");
        lineXmr.setWallet(wallet);
        em.persist(lineXmr);

        //commit and close
        em.getTransaction().commit();
        em.close();
        dao.connector.close();

        System.out.println(lineXmr.getWallet());


    }
}
