package io.paxs.cryptos.jpa;

import io.paxs.cryptos.domain.jpa.JpaUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by AELION on 13/02/2018.
 */
public class JpaConnector {

    static EntityManagerFactory factory;

    void connect(){
        if(this.factory == null){
            this.factory = Persistence.createEntityManagerFactory("cryptos");
        }
    }

    public EntityManager createEntityManager(){

        // if already connected, do nothing
        this.connect();
        return factory.createEntityManager();
    }

    public void close(){
        this.factory.close();
    }


    public static void main(String[] args) {
        JpaConnector connector = new JpaConnector();

        EntityManager em = connector.createEntityManager();

        JpaUser jean = new JpaUser();
        jean.setName("Jean");

        JpaUser jack = new JpaUser();
        jean.setName("Jack");

        JpaUser jackie = new JpaUser();
        jean.setName("Jackie");

        JpaUser jules = new JpaUser();
        jean.setName("Jules");

        JpaUser jasper = new JpaUser();
        jean.setName("Jasper");

        //open the box
        em.getTransaction().begin();

        em.persist(jean);
        em.persist(jack);
        em.persist(jackie);
        em.persist(jasper);
        em.persist(jules);

        em.getTransaction().commit();

        em.close();
        connector.close();
    }
}

