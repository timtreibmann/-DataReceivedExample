/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.lombok;

//import de.sourcepark.smd.ocl.ConnectionEvent;

import de.sourcepark.smd.base.config.SMDConfiguration;
import de.sourcepark.smd.ocl.ConnectionEvent;




/**
 *
 * @author ttreibmann
 */
public class Main {

     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        Person person = new Person();    
        ConnectionEvent evt = new ConnectionEvent(false, false,false,false,SMDConfiguration.getInstance().getIdentity());
       
       
        
        person.dataReceived(evt);  
        
        System.out.println("TOSTring: "+person.toString());
        
       person.getProcessingThreads().shutdown();
       
        
         
       
    }
    
}
