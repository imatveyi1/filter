/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nf.filter.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * фильтр для проверки регулярного выражения
 * @author Matvey
 */
public class ContactsRegular implements ContactsDao {

    private final ContactsCache cc;

    public ContactsRegular(ContactsCache cc) {
        this.cc = cc;
    }
    
    @Override
    public List<Contact> getContacts(String regular) {
        List<Contact> contacts = cc.getContacts(regular);
        return splitList(contacts, regular);
        
    }
    
    private List<Contact> splitList(List<Contact> contacts, String regular){
        
        List<List<Contact>> list = new ArrayList();
        
        int step = contacts.size()<1000 ? contacts.size() : contacts.size()/10;
        for(int i=0; i<contacts.size(); i+=step){
            int toIndex = (i + step);
            list.add(contacts.subList(i, toIndex >= contacts.size() ? contacts.size() : toIndex));
        }
        return list.parallelStream().map(cont -> regexList(cont, regular)).reduce((c1,c2) -> addAll(c1,c2)).orElse(Collections.EMPTY_LIST);
    }
    
    private static List<Contact> addAll(List<Contact> col1, List<Contact> col2){
        col1.addAll(col2); 
        return col1;
    }
    
    private List<Contact> regexList(List<Contact> contacts, String regular){ 
        return contacts.stream().filter((con) -> !con.getName().matches(regular)).collect(Collectors.toList());
    }
    
}
