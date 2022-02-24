/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uv.gui;

import com.uv.model.Departamento;
import com.uv.model.Empleado;
import com.uv.model.FactoryDAO;
import com.uv.model.IDAOGeneral;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class EmpleadoGUI implements IController {

    public Scanner teclado = new Scanner(System.in);

    public void guardar() {
        IDAOGeneral<Empleado> dao = FactoryDAO.create(FactoryDAO.DAOType.EMPLEADO);
        IDAOGeneral<Departamento> daodep = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        Empleado nuevo = new Empleado();
        System.out.println("NOMBRE:");
        nuevo.setNombre(teclado.nextLine());
        System.out.println("DIRECCION:");
        nuevo.setDireccion(teclado.nextLine());
        System.out.println("TELEFONO:");
        nuevo.setTelefono(teclado.nextLine());
        System.out.println("CLAVE DEPARTAMENTO:");
        nuevo.setClaveDepartamento(daodep.buscarID(teclado.nextLong()));

        boolean res = dao.guardar(nuevo);
        if (res) {
            System.out.println("Guardado correctamente");
            teclado.nextLine();
        } else {
            System.out.println("No se pudo guardar");
            teclado.nextLine();

        }

    }

    public void modificar() {
        
        IDAOGeneral<Empleado> dao = FactoryDAO.create(FactoryDAO.DAOType.EMPLEADO);
        IDAOGeneral<Departamento> daodep = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        Empleado nuevo = new Empleado();
        
        System.out.println("CLAVE:");
        nuevo.setClave(teclado.nextLong());
        teclado.nextLine();
        System.out.println("NOMBRE:");
        nuevo.setNombre(teclado.nextLine());
        System.out.println("DIRECCION:");
        nuevo.setDireccion(teclado.nextLine());
        System.out.println("TELEFONO:");
        nuevo.setTelefono(teclado.nextLine());
        
        System.out.println("CLAVE DEPARTAMENTO:");
        nuevo.setClaveDepartamento(daodep.buscarID(teclado.nextLong()));
        
        boolean res = dao.modificar(nuevo);
        if (res) {
            System.out.println("Moficado correctamente");
        } else {
            System.out.println("No se pudo modificar");
        }
    }

    public void borrar() {
        System.out.println("CLAVE:");
        long clave = teclado.nextLong();
        teclado.nextLine();
        IDAOGeneral<Empleado> dao = FactoryDAO.create(FactoryDAO.DAOType.EMPLEADO);

        boolean res = dao.borrar(clave);
        if (res) {
            System.out.println("Borrado correctamente");
        } else {
            System.out.println("No se pudo borrar");
        }

    }

    public void consultar() {
        IDAOGeneral<Empleado> dao = FactoryDAO.create(FactoryDAO.DAOType.EMPLEADO);
        List<Empleado> lista = dao.consultar();
        for (Empleado emp : lista) {
            System.out.println("CLAVE: " + emp.getClave() + " NOMBRE: " + emp.getNombre() + " DIRECCION: "
                    + emp.getDireccion() + " TELEFONO: " + emp.getTelefono() + " Departamento: " + emp.getClaveDepartamento().getNombre());
        }
    }

    public void buscarID() {

        System.out.println("CLAVE:");
        long clave = teclado.nextLong();
        teclado.nextLine();
        IDAOGeneral<Empleado> dao = FactoryDAO.create(FactoryDAO.DAOType.EMPLEADO);
        Empleado nuevo = dao.buscarID(clave);
        System.out.println("CLAVE: " + nuevo.getClave() + " NOMBRE: " + nuevo.getNombre() + " DIRECCION: "
                + nuevo.getDireccion() + " TELEFONO: " + nuevo.getTelefono() + " DEPARTAMENTO: " + nuevo.getClaveDepartamento().getNombre());

    }
}
