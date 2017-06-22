/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nf.filter;

import com.nf.filter.component.ContactsCache;
import com.nf.filter.component.ContactsData;
import com.nf.filter.component.ContactsRegular;
import java.util.List;
import spark.Request;
import spark.Response;
import spark.Spark;

/**
 * http://localhost:4567/hello?nameFilter=
 * @author Matvey
 */
public class FilterServiceMain {

    private static final String MATCH = "^\\!\\.\\*.*$";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        Spark.before("/hello", (Request req, Response res) -> {
                
                if(req.queryParams("nameFilter").matches(MATCH)){
                    Spark.halt(400, "BAD REQUEST");
                }
            
        });
        
        Spark.get("/hello", (Request req, Response res) -> {
            List<String> result;
            try{
                long start = System.currentTimeMillis();
                result = new ContactsRegular(new ContactsCache(new ContactsData())).getContacts(req.queryParams("nameFilter"));
                long end = System.currentTimeMillis();
                System.out.println("times = " + (end - start));// время выполнения
                return result;
            } catch (Exception e){
                e.printStackTrace();
                res.status(500);
                return "SERVER ERROR";
            }
        });
    }
    
}
