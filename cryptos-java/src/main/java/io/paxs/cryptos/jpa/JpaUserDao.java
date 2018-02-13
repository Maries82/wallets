package io.paxs.cryptos.jpa;

import io.paxs.cryptos.domain.jpa.JpaUser;
import io.paxs.cryptos.domain.jpa.User;

import javax.persistence.EntityManager;

/**
 * Created by AELION on 13/02/2018.
 */
public class JpaUserDao {

    JpaConnector connector = new JpaConnector();


    public User createUser(String name){
        JpaUser user = new JpaUser();
        user.setName(name);
        EntityManager em = connector.createEntityManagerFactory();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        System.out.println("User id :" + user.getId());
        return user;


    }

    public static void main(String[] args) {

        JpaUserDao dao = new JpaUserDao();
        dao.createUser("Arthur");

        //end of a very long program
        dao.connector.close();



    }


}
