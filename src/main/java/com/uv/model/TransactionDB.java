/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uv.model;

import org.hibernate.Session;

/**
 *
 * @author pedro
 */
public abstract class TransactionDB<T> {
    protected T pojo;
    public TransactionDB(T pojo) {
        this.pojo = pojo;
    }    

    public TransactionDB() {
    }
    
    public abstract boolean execute(Session session);
}
