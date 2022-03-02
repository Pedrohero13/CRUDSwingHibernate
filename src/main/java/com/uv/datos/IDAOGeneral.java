/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uv.datos;

import java.util.List;

/**
 *
 * @author pedro
 */
public interface IDAOGeneral<T> {

    public boolean guardar(T pojo);

    public boolean borrar(long clave);

    public boolean modificar(T pojo);

    public List<T> consultar();

    public T buscarID(long clave);

}
