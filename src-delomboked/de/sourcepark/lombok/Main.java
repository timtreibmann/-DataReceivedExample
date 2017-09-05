/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.lombok;

//import de.sourcepark.smd.ocl.ConnectionEvent;

import de.sourcepark.smd.base.config.SMDConfiguration;
import de.sourcepark.smd.ocl.ConnectionEvent;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;




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
        
        TestFuture e0 = new TestFuture();
        TestFuture e1 = new TestFuture();
        person.getFuture().add(e0);
        person.getFuture().add(e1);
        
        person.dataReceived(evt);  
        
        System.out.println("future: "+person.getFuture().size());
        person.getFuture().forEach(f -> {
            try {
                System.out.println("FutureElement: "+ f.get());
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
});
        
       person.getProcessingThreads().shutdown();
       
        
         
       
    }
    
}
