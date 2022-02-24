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
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author pedro
 */
public class DAOEmpleado implements IDAOGeneral<Empleado> {

    @Override
    public boolean guardar(Empleado pojo) {

        TransactionDB<Empleado> t = new TransactionDB<Empleado>(pojo) {
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
        return SessionDB.getInstance().execute(t);
    }

    @Override
    public boolean modificar(Empleado pojo) {
        TransactionDB<Empleado> t = new TransactionDB<Empleado>(pojo) {
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
        return SessionDB.getInstance().execute(t);

    }

    @Override
    public boolean borrar(long clave) {
        Empleado pojo = new Empleado();
        pojo.setClave(clave);
        TransactionDB<Empleado> t = new TransactionDB<Empleado>(pojo) {
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
        return SessionDB.getInstance().execute(t);

    }

    @Override
    public List<Empleado> consultar() {
        List<Empleado> listEmp = new ArrayList<>();

        TransactionDB<Empleado> t;
        t = new TransactionDB<Empleado>() {
            @Override
            public boolean execute(Session session) {
                boolean re = false;
                try {
                    session.beginTransaction();
                    Query query = session.createQuery("from Empleado ORDER BY clave");
                    for (Object empleado : query.list()) {
                        listEmp.add((Empleado) empleado);
                    }

                    session.getTransaction().commit();

                } catch (HibernateException ex) {
                    session.getTransaction().rollback();
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                return re;
            }
        };
        SessionDB.getInstance().execute(t);
        return listEmp;

    }

    @Override
    public Empleado buscarID(long clave) {
        Empleado empleado = new Empleado();
        TransactionDB<Empleado> t = new TransactionDB<Empleado>() {
            @Override
            public boolean execute(Session session) {
                boolean re = false;
                try {
                    session.beginTransaction();
                    pojo = (Empleado) session.get(Empleado.class, clave);

                    empleado.setClave(pojo.getClave());
                    empleado.setNombre(pojo.getNombre());
                    empleado.setDireccion(pojo.getDireccion());
                    empleado.setTelefono(pojo.getTelefono());
                    empleado.setClaveDepartamento(new Departamento(pojo.getClaveDepartamento().getClave(),pojo.getClaveDepartamento().getNombre()));
                    session.getTransaction().commit();
                    re = true;
                } catch (HibernateException ex) {
                    session.getTransaction().rollback();
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                return re;
            }
        };
        SessionDB.getInstance().execute(t);
        
        return empleado;
    }
}
