/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uv.datos;

/**
 *
 * @author pedro
 */
public class FactoryDAO {

    public enum DAOType {
        EMPLEADO, DEPARTAMENTO
    };

    public static IDAOGeneral create(DAOType type) {
        IDAOGeneral dao = null;
        switch (type) {
            case EMPLEADO:
                dao = new DAOEmpleado();
                break;
            case DEPARTAMENTO:
                dao = new DAODepartamento();
                break;
        }

        return dao;
    }
}
