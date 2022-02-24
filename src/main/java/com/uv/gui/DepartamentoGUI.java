/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uv.gui;

import com.uv.model.Departamento;
import com.uv.model.Empleado;
import com.uv.model.FactoryDAO;
import com.uv.model.IDAOGeneral;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pedro
 */
public class DepartamentoGUI implements IController{

    public  Scanner teclado = new Scanner(System.in);

    public  void guardar() {
        Departamento nuevo = new Departamento();
        
        System.out.println("NOMBRE:");
        nuevo.setNombre(teclado.nextLine());

        IDAOGeneral<Departamento> dao = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);

        boolean res = dao.guardar(nuevo);
        if (res) {
            System.out.println("Guardado correctamente");
        } else {
            System.out.println("No se pudo guardar");
        }

    }

    public  void modificar() {
        Departamento nuevo = new Departamento();
        System.out.println("CLAVE:");
        nuevo.setClave(teclado.nextLong());
        teclado.nextLine();
        System.out.println("NOMBRE:");
        nuevo.setNombre(teclado.nextLine());

        IDAOGeneral<Departamento> dao = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);

        boolean res = dao.modificar(nuevo);
        if (res) {
            System.out.println("Moficado correctamente");
        } else {
            System.out.println("No se pudo modificar");
        }
    }

    public  void borrar() {
        System.out.println("CLAVE:");
        long clave = teclado.nextLong();
        teclado.nextLine();
        IDAOGeneral<Departamento> dao = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);

        boolean res = dao.borrar(clave);
        if (res) {
            System.out.println("Borrado correctamente");
        } else {
            System.out.println("No se pudo borrar");
        }

    }
    
    
    public  void consultar() {
        IDAOGeneral<Departamento> dao = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        List<Departamento> lista = dao.consultar();
        for (Departamento departamento : lista) {
            System.out.println("CLAVE: " + departamento.getClave() + " NOMBRE: " + departamento.getNombre());
            Collection<Empleado> collDep = collDep=departamento.getEmpleadoCollection();
            for (Empleado empleado : collDep) {
                System.out.println("Empleado: "+ empleado.getNombre()+" Direccion: "+empleado.getDireccion() 
                        +" Telefono: "+ empleado.getTelefono());
            }
            
            
            
        }
        
    }

    public  void buscarID() {

        System.out.println("CLAVE:");
        long clave = teclado.nextLong();
        teclado.nextLine();
        IDAOGeneral<Departamento> dao = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        Departamento departamento = dao.buscarID(clave);
        System.out.println("CLAVE: " + departamento.getClave() + " NOMBRE: " + departamento.getNombre());
        Collection<Empleado> collDep = collDep=departamento.getEmpleadoCollection();
            for (Empleado empleado : collDep) {
                System.out.println("Empleado: "+ empleado.getNombre()+" Direccion: "+empleado.getDireccion() 
                        +" Telefono: "+ empleado.getTelefono());
            }
    }
}
