/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.lombok;

import de.sourcepark.smd.base.config.SMDConfigurationException;
import de.sourcepark.smd.base.util.scp.SCPDataObject;
import java.util.concurrent.Callable;
import org.apache.commons.configuration.ConfigurationException;

/**
 *
 * @author ttreibmann
 */
public class Mavenproject1Controller implements Callable{
    
    public final static String QUEUE_ID = "Mavenproject1ID";
    private SCPDataObject scp;
        
    public Mavenproject1Controller(SCPDataObject scp)throws SMDConfigurationException, ConfigurationException{
    this.scp = scp;
    }

    @Override
    public Object call() throws Exception {
        
        return "Class: "+this.getClass().getName()+" with Parameter: "+scp.getClass().getName();
        
    }
    
}
