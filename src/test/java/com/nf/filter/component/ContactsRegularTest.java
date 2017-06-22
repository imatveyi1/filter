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
        
        List<String> value = new ArrayList();
        Stream.of("тест","1239454","fdghjkasd","впроварпова","").forEach(str->value.add(str));
        
        Mockito.when(cd.getContacts(regular)).thenReturn(value);
        ContactsRegular instance = new ContactsRegular(cd);
        List<String> result = instance.getContacts(regular);
        List<String> expResult = value.stream().filter((str)->!str.matches(regular)).collect(Collectors.toList());
        System.out.println("" + result.stream().reduce((s1,s2)-> s1 + "|" + s2));
        Assert.assertEquals(expResult, result);
        
    }
    
}
