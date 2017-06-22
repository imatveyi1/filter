/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nf.filter.component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * возвращает все записи с таблицы contacts
 * @author Matvey
 */
public class ContactsData implements ContactsDao {

//    private static final Class clas = Class.forName("org.postgresql.Driver");
    private static final String URL = System.getProperty("url");// "jdbc:postgresql://localhost:5432/store";
    private static final String LOGIN = System.getProperty("login");//"postgres";
    private static final String PASSWORD = System.getProperty("password");//"karolina123";
    
    private static final String QUERY = "select id, name from contacts";
    
    @Override
    public List<Contact> getContacts(String regular) {
        List<Contact> result = new ArrayList();
        try {
            Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(QUERY)) {
                    while (rs.next()) {
                        result.add(new Contact(Integer.parseInt(rs.getString(1)), rs.getString(2)));
                    }
                }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
        
        return result;
    }
    
}
