/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uv.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author pedro
 */
public class DAODepartamento implements IDAOGeneral<Departamento> {

    @Override
    public boolean guardar(Departamento pojo) {
        TransactionDB<Departamento> t = new TransactionDB<Departamento>(pojo) {
            @Override
            public boolean execute(Session session) {
                boolean re = false;
                try {
                    session.beginTransaction();
                    session.save(pojo);
                    session.getTransaction().commit();
                    re = true;
                } catch (HibernateException ex) {
                    session.getTransaction().rollback();
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                return re;
            }
        };
        SessionDB sesion = SessionDB.getInstance();
        boolean re = sesion.execute(t);
        sesion.getSession().close();
        return re;

    }

    @Override
    public boolean modificar(Departamento pojo) {
        TransactionDB<Departamento> t = new TransactionDB<Departamento>(pojo) {
            @Override
            public boolean execute(Session session) {
                boolean re = false;
                try {
                    session.beginTransaction();
                    session.update(pojo);
                    session.getTransaction().commit();
                    re = true;
                } catch (HibernateException ex) {
                    session.getTransaction().rollback();
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                return re;
            }
        };
        SessionDB sesion = SessionDB.getInstance();
        boolean re = sesion.execute(t);
        sesion.getSession().close();
        return re;

    }

    @Override
    public boolean borrar(long clave) {
        Departamento pojo = new Departamento();
        pojo.setClave(clave);
        TransactionDB<Departamento> t = new TransactionDB<Departamento>(pojo) {
            @Override
            public boolean execute(Session session) {
                boolean re = false;
                try {
                    session.beginTransaction();
                    session.delete(pojo);
                    session.getTransaction().commit();
                    re = true;
                } catch (HibernateException ex) {
                    session.getTransaction().rollback();
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                return re;
            }
        };
        SessionDB sesion = SessionDB.getInstance();
        boolean re = sesion.execute(t);
        sesion.getSession().close();
        return re;
    }

    @Override
    public List<Departamento> consultar() {
        List<Departamento> listDpm = new ArrayList<>();

        TransactionDB<Departamento> t = new TransactionDB<Departamento>() {
            @Override
            public boolean execute(Session session) {
                boolean re = false;
                try {
                    session.beginTransaction();
                    Query query = session.createQuery("from Departamento ORDER BY clave");
                    for (Object departamento : query.list()) {
                        listDpm.add((Departamento) departamento);
                    }

                    session.getTransaction().commit();

                } catch (HibernateException ex) {
                    session.getTransaction().rollback();
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                return re;
            }
        };
        SessionDB sesion = SessionDB.getInstance();
        sesion.execute(t);
        sesion.getSession().close();
        return listDpm;

    }

    @Override
    public Departamento buscarID(long clave) {
        Departamento departamento = new Departamento();

        TransactionDB<Departamento> t = new TransactionDB<Departamento>() {
            @Override
            public boolean execute(Session session) {
                boolean re = false;
                try {
                    session.beginTransaction();
                    pojo = (Departamento) session.get(Departamento.class, clave);

                    departamento.setClave(pojo.getClave());
                    departamento.setNombre(pojo.getNombre());
                    departamento.setEmpleadoCollection(pojo.getEmpleadoCollection());
                    session.getTransaction().commit();
                    re = true;
                } catch (HibernateException ex) {
                    session.getTransaction().rollback();
                    Logger.getLogger(DAODepartamento.class.getName()).log(Level.SEVERE, null, ex);
                }
                return re;
            }
        };
        SessionDB sesion = SessionDB.getInstance();
        sesion.execute(t);
        sesion.getSession().close();
        return departamento;

    }
}
