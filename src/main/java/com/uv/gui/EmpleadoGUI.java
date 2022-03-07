/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uv.gui;

import com.uv.datos.Departamento;
import com.uv.datos.Empleado;
import com.uv.datos.FactoryDAO;
import com.uv.datos.IDAOGeneral;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pedro
 */
public class EmpleadoGUI extends VentanaGUI {
    
    public Scanner teclado = new Scanner(System.in);
    
    public EmpleadoGUI() {
        this.setTitle("Empleados");
        this.labelTitulo.setText("Empleados");
    }
    
    @Override
    public void guardar() {
        IDAOGeneral<Empleado> dao = FactoryDAO.create(FactoryDAO.DAOType.EMPLEADO);
        IDAOGeneral<Departamento> daodep = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        Empleado empleado = new Empleado();
        empleado.setNombre(txtNombre.getText());
        empleado.setDireccion(txtDireccion.getText());
        empleado.setTelefono(txtTelefono.getText());
        empleado.setClaveDepartamento(daodep.buscarID(Integer.parseInt(txtDepartamento.getText())));
        
        boolean res = dao.guardar(empleado);
        if (res) {
            JOptionPane.showMessageDialog(this, "Guardado correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "Guardado correctamente");
        }
        
    }
    
    @Override
    public void modificar() {
        
        IDAOGeneral<Empleado> dao = FactoryDAO.create(FactoryDAO.DAOType.EMPLEADO);
        IDAOGeneral<Departamento> daodep = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        
        Empleado empleado = new Empleado();
        empleado.setClave(Integer.parseInt(txtClave.getText()));
        empleado.setNombre(txtNombre.getText());
        empleado.setDireccion(txtDireccion.getText());
        empleado.setTelefono(txtTelefono.getText());
        empleado.setClaveDepartamento(daodep.buscarID(Integer.parseInt(txtDepartamento.getText())));
        
        boolean res = dao.modificar(empleado);
        if (res) {
            JOptionPane.showMessageDialog(this, "Modificado correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "Modificado correctamente");
        }
    }
    
    @Override
    public void consultar() {
        Vector<String> columnas = new Vector<>();
        columnas.add("clave");
        columnas.add("nombre");
        columnas.add("direccion");
        columnas.add("telefono");
        columnas.add("Departamento");
        
        Vector datos = new Vector();
        
        IDAOGeneral<Empleado> dao = FactoryDAO.create(FactoryDAO.DAOType.EMPLEADO);
        List<Empleado> lstEmpleados = dao.consultar();
        for (Empleado empleado : lstEmpleados) {
            Vector row = new Vector();
            row.add(empleado.getClave());
            row.add(empleado.getNombre());
            row.add(empleado.getDireccion());
            row.add(empleado.getTelefono());
            row.add(empleado.getClaveDepartamento().getNombre());
            
            datos.add(row);
        }
        
        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        tabla.setModel(model);
    }
    
    @Override
    public void buscarID() {
        IDAOGeneral<Empleado> dao = FactoryDAO.create(FactoryDAO.DAOType.EMPLEADO);
        
        Empleado empleado = dao.buscarID(Integer.parseInt(txtClave.getText()));
        if (empleado.getNombre() != null) {
            Vector<String> columnas = new Vector<>();
            columnas.add("clave");
            columnas.add("nombre");
            columnas.add("direccion");
            columnas.add("telefono");
            columnas.add("Departamento");
            Vector datos = new Vector();
            
            Vector row = new Vector();
            row.add(empleado.getClave());
            row.add(empleado.getNombre());
            row.add(empleado.getDireccion());
            row.add(empleado.getTelefono());
            row.add(empleado.getClaveDepartamento().getNombre());
            datos.add(row);
            
            DefaultTableModel model = new DefaultTableModel(datos, columnas);
            tabla.setModel(model);
            
        }
        else{
            JOptionPane.showMessageDialog(this, "No se encontro el registro", null ,JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void eliminar() {
        IDAOGeneral<Empleado> dao = FactoryDAO.create(FactoryDAO.DAOType.EMPLEADO);
        boolean res = dao.borrar(Integer.parseInt(txtClave.getText()));
        if (res) {
            JOptionPane.showMessageDialog(this, "Borrado correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "Borrado correctamente");
        }
    }
}
