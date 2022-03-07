/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uv.datos;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author pedro
 */
public class DAODepartamento implements IDAOGeneral<Departamento> {

    @Override
    public boolean guardar(Departamento pojo) {
        TransactionDB<Departamento> transaction = new TransactionDB<Departamento>(pojo) {
            @Override
            public boolean execute(SessionFactory sessionFactory) {
                boolean re = false;
                Session session = null;
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    session.save(pojo);
                    session.getTransaction().commit();
                    session.close();
                    re = true;

                } catch (HibernateException ex) {
                    session.getTransaction().rollback();
                    session.close();
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                return re;
            }
        };
        return SessionDB.getInstance().execute(transaction);

    }

    @Override
    public boolean modificar(Departamento pojo) {
        TransactionDB<Departamento> transaction = new TransactionDB<Departamento>(pojo) {
            @Override
            public boolean execute(SessionFactory sessionFactory) {
                boolean re = false;
                Session session = null;
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    session.update(pojo);
                    session.getTransaction().commit();
                    session.close();
                    re = true;

                } catch (HibernateException ex) {
                    session.getTransaction().rollback();
                    session.close();
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                return re;
            }
        };
        return SessionDB.getInstance().execute(transaction);

    }

    @Override
    public boolean borrar(long clave) {
        Departamento pojo = new Departamento();
        pojo.setClave(clave);
        TransactionDB<Departamento> transaction = new TransactionDB<Departamento>(pojo) {
            @Override
            public boolean execute(SessionFactory sessionFactory) {
                boolean re = false;
                Session session = null;
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    session.delete(pojo);
                    session.getTransaction().commit();
                    session.close();
                    re = true;

                } catch (HibernateException ex) {
                    session.getTransaction().rollback();
                    session.close();
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                return re;
            }
        };
        return SessionDB.getInstance().execute(transaction);
    }

    @Override
    public List<Departamento> consultar() {
        List<Departamento> listDpm = new ArrayList<>();
        TransactionDB<Departamento> transaction = new TransactionDB<Departamento>() {
            @Override
            public boolean execute(SessionFactory sessionFactory) {
                boolean re = false;
                Session session = null;
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    Query query = session.createQuery("from Departamento ORDER BY clave");
                    for (Object departamento : query.list()) {
                        listDpm.add((Departamento) departamento);
                    }
                    session.getTransaction().commit();
                    session.close();
                    re = true;

                } catch (HibernateException ex) {
                    session.getTransaction().rollback();
                    session.close();
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                return re;
            }
        };
        SessionDB.getInstance().execute(transaction);
        return listDpm;

    }

    @Override
    public Departamento buscarID(long clave) {
        Departamento departamento = new Departamento();
        TransactionDB<Departamento> transaction = new TransactionDB<Departamento>() {
            @Override
            public boolean execute(SessionFactory sessionFactory) {
                boolean re = false;
                Session session = null;
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    pojo = (Departamento) session.get(Departamento.class, clave);
                    if (pojo != null) {
                        departamento.setClave(pojo.getClave());
                        departamento.setNombre(pojo.getNombre());
                        departamento.setEmpleadoCollection(pojo.getEmpleadoCollection());
                        session.getTransaction().commit();
                        session.close();
                        re = true;
                    }
                } catch (HibernateException ex) {
                    session.getTransaction().rollback();
                    session.close();
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                return re;
            }
        };
        SessionDB.getInstance()
                .execute(transaction);

        return departamento;

    }
}
