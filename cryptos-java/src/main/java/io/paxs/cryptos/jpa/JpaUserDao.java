package io.paxs.cryptos.jpa;

import io.paxs.cryptos.domain.jpa.JpaUser;
import io.paxs.cryptos.domain.jpa.JpaWallet;
import io.paxs.cryptos.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by AELION on 13/02/2018.
 */
public class JpaUserDao {

    JpaConnector connector = new JpaConnector();


    public JpaUser createUser(String name) {

        //1 creation du user
        JpaUser user = new JpaUser();
        user.setName(name);

        EntityManager em = connector.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);

        //2 creation du wallet

        JpaWallet defaultWallet = new JpaWallet();
        defaultWallet.setName(name+" 's wallet");
        em.persist(defaultWallet);

        //3 creation de la jointure

        user.getWallets().add(defaultWallet);

        em.getTransaction().commit();

        System.out.println("User id :" + user.getId());
        return user;

    }

    public JpaUser find(int id) {
        EntityManager em = connector.createEntityManager();
        em.getTransaction().begin();

        JpaUser user = em.find(JpaUser.class, id);

        em.getTransaction().commit();
        em.close();
        return user;
    }

    public JpaUser findByName(String name) {
        EntityManager em = connector.createEntityManager();
        em.getTransaction().begin();

        //JPQL : Java Persistence Query Language
        TypedQuery<JpaUser> query = em.createQuery("SELECT u  FROM JpaUser u WHERE u.name= :pName", JpaUser.class);

        query.setParameter("pName", name);
        List<JpaUser> users = query.getResultList();


        em.getTransaction().commit();
        em.close();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    public JpaUser deletByName(String name) {
        EntityManager em = connector.createEntityManager();
        em.getTransaction().begin();

        //JPQL : Java Persistence Query Language
        TypedQuery<JpaUser> query = em.createQuery("SELECT u  FROM JpaUser u WHERE u.name= :pName", JpaUser.class);

        query.setParameter("pName", name);
        List<JpaUser> users = query.getResultList();

        for (User u : users) {
            em.remove(u);
        }
        em.getTransaction().commit();
        em.close();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {

        JpaUserDao dao = new JpaUserDao();
        System.out.println(dao.createUser("Arthur"));
        //System.out.println(dao.find(1));
       // System.out.println(dao.findByName("Marie"));

       // dao.deletByName("Arthur");

        //end of a very long program
        dao.connector.close();


    }


}
