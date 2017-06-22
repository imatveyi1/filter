/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nf.filter.component;

import java.util.ArrayList;
import java.util.List;

/**
 * кеширование данных для ускорения отработки запроса
 * @author Matvey
 */
public class ContactsCache implements ContactsDao {

    private static volatile List<Contact> cache = new ArrayList();
    
    private static long timeToLive;
    
    private final ContactsData cd;

    public ContactsCache(ContactsData cd) {
        this.cd = cd;
    }
      
    @Override
    public List<Contact> getContacts(String regular) {
        if(checkTimeToLive()){
            return cache;
        }
        return getContactsAndCopyToCache(cd.getContacts(regular));
    }
    
    private boolean checkTimeToLive(){
        return timeToLive > System.currentTimeMillis() && !cache.isEmpty();
    }
    
    private static List<Contact> getContactsAndCopyToCache(List<Contact> list){
        cache = list;
        setTimeToLive();
        return cache;
    }
    
    private static void setTimeToLive(){
        timeToLive = System.currentTimeMillis() + 60000;
    }
    
}
