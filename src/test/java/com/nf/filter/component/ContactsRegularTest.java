/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nf.filter.component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Matvey
 */
public class ContactsRegularTest {
    
    public ContactsRegularTest() {
    }
    
    /**
     * Test of getContacts method, of class ContactsRegular.
     */
    @Test
    public void testGetContacts() {
        System.out.println("getContacts");
//        String regular = "!.*$";
        String regular = "^[0-9].*$";
        
        ContactsCache cd = Mockito.mock(ContactsCache.class);
        
        List<Contact> value = new ArrayList();
        Stream.of(new Contact(0, "тест"),new Contact(1, "1239454"),
                  new Contact(2, "fdghjkasd"),new Contact(3, "впроварпова"), 
                  new Contact(4, "")).forEach(contact->value.add(contact));
        
        Mockito.when(cd.getContacts(regular)).thenReturn(value);
        ContactsRegular instance = new ContactsRegular(cd);
        List<Contact> result = instance.getContacts(regular);
        List<Contact> expResult = value.stream().filter((str)->!str.getName().matches(regular)).collect(Collectors.toList());
        Assert.assertEquals(expResult, result);
        
    }
    
}
