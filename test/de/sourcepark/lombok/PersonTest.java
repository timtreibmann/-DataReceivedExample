/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.lombok;

import de.sourcepark.smd.base.config.SMDConfiguration;
import de.sourcepark.smd.base.config.SMDConfigurationException;
import de.sourcepark.smd.base.queue.ISCPQueue;
import de.sourcepark.smd.ocl.ConnectionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author ttreibmann
 */
public class PersonTest {
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    public PersonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of test method, of class Person.
     */
    @Test
    public void testTest() {
        
        Person instance = new Person();
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of dataRecieved1 method, of class Person.
     */
    @Test
    public void testFieldInvocation() {
        
        //Testing Field initialization
        //This is not a good practice and its only perfomed to check, if lombok field initialization is working!
        //Annotated class need tho have lombokAnnotation @Getter at classLevel to Test this
        System.out.println("Testing fieldInvocation of class Person annotated by @DataRecevied{ ... } ...");
        ConnectionEvent evt = new ConnectionEvent(false, false,true,true,SMDConfiguration.getInstance().getIdentity());;
        Person instance = new Person();
        Person instanceForStaticFieldTest = new Person();
        
        //test future field
        assertNotNull("future field has not been correctly initialized "+instance.getFuture());
        assertTrue("future field must be an ArrayList ",instance.getFuture() instanceof ArrayList);
        assertTrue("future field must be empty ",instance.getFuture().isEmpty());
         //test processingThreads field
        assertNotNull(instance.getProcessingThreads());
        assertTrue("processingThreads field must be an ExecutorService ",instance.getProcessingThreads() instanceof ExecutorService);
        assertFalse("processingThreads.isShutdown should false because "
                + "processingThreads is initialized with Executors.newFixedThreadPool(5): ",instance.getProcessingThreads().isShutdown());
        
        //test queue field before init() in dataRecieved is called
        assertNull("queue Field should be null before calling init in dataRecieved ",instance.getQueue());
      
        //call dataRecieved with to different objects of class Person annotated by @DataRecevied{ ... }
        instance.dataReceived(evt);
        instanceForStaticFieldTest.dataReceived(evt);
        //test queue field after init() in dataRecieved is called
        assertNotNull("queue Field should NOT be null before calling init in dataRecieved ",instance.getQueue());
        assertTrue("queue field must be an ISCPQueue ", instance.getQueue() instanceof ISCPQueue);
        assertEquals("queue.getID() static field access is not equal :", instance.getQueue().getId(), instanceForStaticFieldTest.getQueue().getId());        
    }
    @Test
    public void testdoStatusProcessing(){
        /* System.out.println("Testing doStatusProcessing of class Person annotated by @DataRecevied{ ... } ...");
         ConnectionEvent evt = new ConnectionEvent(true,false,true,true,SMDConfiguration.getInstance().getIdentity());;
         Person instance = new Person();
         
         exception.expectCause(is(IsInstanceOf.<Throwable>ins‌tanceOf(SMDConfigurationException.class)));
         instance.dataReceived(evt);
         */
    }
    
}
