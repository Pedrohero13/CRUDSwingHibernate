/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uv.gui;

import com.uv.model.DAODepartamento;
import com.uv.model.DAOEmpleado;
import com.uv.model.IDAOGeneral;

/**
 *
 * @author pedro
 */
public class FactoryGUI {
    public enum ControllerType {
        EMPLEADO, DEPARTAMENTO
    };

    public static IController create(ControllerType type) {
        IController controller = null;
        switch (type) {
            case EMPLEADO:
                controller = new EmpleadoGUI();
                break;
            case DEPARTAMENTO:
                controller = new DepartamentoGUI();
                break;
        }

        return controller;
    }
}
