/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nf.filter.component;

import java.util.List;

/**
 *
 * @author Matvey
 */
public interface ContactsDao {
    
    public List<Contact> getContacts(String regular);
    
}
