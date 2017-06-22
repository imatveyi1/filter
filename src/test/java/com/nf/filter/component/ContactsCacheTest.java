/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nf.filter.component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author Matvey
 */
public class ContactsCacheTest {
    
    public ContactsCacheTest() {
    }
    
    /**
     * Test of getContacts method, of class ContactsCache.
     */
    @Test
    public void testGetContacts() {
        System.out.println("getContacts");
        String regular = "";
        ContactsData cd = Mockito.mock(ContactsData.class);
        
        List<String> expResult = new ArrayList();
        Stream.of("тест","1239454","fdghjkasd","впроварпова","").forEach(str->expResult.add(str));
        
        Mockito.when(cd.getContacts(regular)).thenReturn(expResult);
        ContactsCache instance = new ContactsCache(cd);
        List<String> result = instance.getContacts(regular);
        assertEquals(expResult, result);
        
    }
    
}
