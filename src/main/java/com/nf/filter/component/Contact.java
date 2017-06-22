/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nf.filter.component;

import java.io.Serializable;

/**
 *
 * @author Matvey
 */
public class Contact implements Serializable {
    
    private final int id;
    private final String name;

    public Contact(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
}
