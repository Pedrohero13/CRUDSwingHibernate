/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uv.datos;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author pedro
 */
public class SessionDB {

    private static SessionDB cx = null;//singleton

    public static SessionDB getInstance() {
        
            cx = new SessionDB();
             return cx;
    }
    private Session session;

    private SessionDB() {
        try {
            SessionFactory sessionFactory = new Configuration().configure()
                    .buildSessionFactory();
            session = sessionFactory.openSession();
        } catch (HibernateException ex) {
            Logger.getLogger(SessionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean execute(TransactionDB transaction) {
        boolean response = transaction.execute(session);
        return response;
    }
    public void closeSession(){
         session.close();
    }
    public Session getSession(){
        return session;
    }

}
