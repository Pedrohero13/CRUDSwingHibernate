/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.uv.datos;


import com.uv.gui.FactoryGUI;
import com.uv.gui.VentanaGUI;
import java.util.Scanner;

/**
 *
 * @author pedro
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in);
        int seleccion = 0;
        boolean bandera = true;
        VentanaGUI empController = FactoryGUI.create(FactoryGUI.ControllerType.EMPLEADO);
        VentanaGUI depController = FactoryGUI.create(FactoryGUI.ControllerType.DEPARTAMENTO);

        
        
        while (bandera) {
            System.out.println("1 para guardar, 2 para modificar, 3 para eliminar, 4 para consultar, 5 Buscar por id, 6 SALIR");
            seleccion = teclado.nextInt();
            teclado.nextLine();
            switch (seleccion) {
                case 1:
                    System.out.println("1 para Empleado, 2 para Departamento");
                    int desicion = teclado.nextInt();
                    teclado.nextLine();
                    if (desicion == 1) {
                        empController.guardar();
                    } else {
                        depController.guardar();
                    }

                    break;
                case 2:
                    System.out.println("1 para Empleado, 2 para Departamento");
                    desicion = teclado.nextInt();
                    if (desicion == 1) {
                        empController.modificar();
                    } else {
                        depController.modificar();
                    }
                    break;
                case 3:
                    System.out.println("1 para Empleado, 2 para Departamento");
                    desicion = teclado.nextInt();
                    if (desicion == 1) {
                        empController.eliminar();
                    } else {
                        depController.eliminar();
                    }
                    break;
                case 4:
                    System.out.println("1 para Empleado, 2 para Departamento");
                    desicion = teclado.nextInt();
                    if (desicion == 1) {
                        empController.consultar();
                    } else {
                        depController.consultar();
                    }
                    break;
                case 5:
                    System.out.println("1 para Empleado, 2 para Departamento");
                    desicion = teclado.nextInt();
                    if (desicion == 1) {
                        empController.buscarID();
                    } else {
                        depController.buscarID();
                    }
                    break;
                case 6:   
                    bandera = false;
                    break;
            }

        }

    }
    
}
