// Generated by delombok at Fri Jul 21 16:16:09 CEST 2017
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.lombok;

import de.sourcepark.smd.base.config.SMDConfiguration;
import de.sourcepark.smd.base.config.SMDConfigurationException;
import de.sourcepark.smd.ocl.AbstractOCL;
import de.sourcepark.smd.ocl.ConnectionEvent;
import java.io.IOException;
import java.util.concurrent.Future;
import lombok.DataReceived;
import lombok.PrintAST;
import org.apache.commons.configuration.ConfigurationException;

/**
 * @author ttreibmann
 */
//@DataRecieved({"VALID", "Mavenproject1Controller"})
@PrintAST
public class PersonDelomboked extends AbstractOCL implements PersonInterface {
   @java.lang.SuppressWarnings("all")
   @javax.annotation.Generated("lombok")
   private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(Person.class);
   @java.lang.SuppressWarnings("all")
   @javax.annotation.Generated("lombok")
   private java.util.List<java.util.concurrent.Future<java.lang.String>> future = new java.util.ArrayList<>();
   @java.lang.SuppressWarnings("all")
   @javax.annotation.Generated("lombok")
   private java.util.concurrent.ExecutorService processingThreads = java.util.concurrent.Executors.newFixedThreadPool(5);
   @java.lang.SuppressWarnings("all")
   @javax.annotation.Generated("lombok")
   private de.sourcepark.smd.base.queue.ISCPQueue queue;

   public void test() {
      TestFuture testF = new TestFuture();
      TestFuture testF2 = new TestFuture();
      TestFuture testF3 = new TestFuture();
      this.future.add(testF);
      this.future.add(testF2);
      this.future.add(testF3);
   }

   @Override
   public void doCleanupOrProcessing(Future f) {
      log.info("In doCleanupOrProcessing method " + f.hashCode());
   }

   @Override
   public void doStatusProcessing() throws Exception {
      try {
         throw new Exception();
      } catch (final SMDConfigurationException | ConfigurationException ex) {
         log.error("SMDConfigurationException: Configuration error occurred.", ex);
      }
   }

   @java.lang.SuppressWarnings("all")
   @javax.annotation.Generated("lombok")
   private void init() {
      try {
         queue = de.sourcepark.smd.base.output.OutputQueueCollection.getInstance().get(Mavenproject1Controller.QUEUE_ID);
      } catch (final java.lang.Exception initEx) {
         log.error("Field initialization failed. Error occurred.", initEx);
      }
   }

   @java.lang.Override
   @java.lang.SuppressWarnings("all")
   @javax.annotation.Generated("lombok")
   public void dataReceived(final de.sourcepark.smd.ocl.ConnectionEvent evt) {
      this.init();
      de.sourcepark.smd.base.util.scp.SCPDataObject scpd = evt.getDataObject();
      try {
         if (scpd.hasStatus()) {
            this.doStatusProcessing();
            return;
         }
         if (scpd.isDirty()) {
            log.error("Unwanted modification detected. Message is flagged as dirty.");
            return;
         }
         if (scpd.getCommand("<Custom Command>") != null) {
            for (final java.util.concurrent.Future f : future) {
               if (f.isDone()) {
                  this.doCleanupOrProcessing(f);
               }
            }
            future.add(processingThreads.submit(new Mavenproject1Controller(scpd)));
         } else if (scpd.getCommand("ALIVE") != null) {
            de.sourcepark.smd.base.util.scp.SCPStatusObject scps = new de.sourcepark.smd.base.util.scp.SCPStatusObject(scpd);
            de.sourcepark.smd.base.util.scp.SCPCommand scpsAddParam = scps.getCommand("ALIVE");
            scpsAddParam.addParam(new de.sourcepark.smd.base.util.scp.SCPParameter("Version", "string", de.sourcepark.smd.base.config.SMDConfiguration.getVersionString(Person.class)));
            queue.offer(scps);
         } else if ((scpd.getCommand("STOP") != null) && scpd.getSource().equalsIgnoreCase(de.sourcepark.smd.base.config.SMDConfiguration.getInstance().getIdentity())) {
            this.stop();
         } else {
            log.error("Unprocessable message received. Possible conflict between command and context. Check message.");
         }
      } catch (final SMDConfigurationException ex) {
         log.error("SMDConfigurationException: Configuration error occurred.", ex);
      } catch (final ConfigurationException ex2) {
         log.error("ConfigurationException: Configuration error occurred.", ex2);
      } catch (final java.lang.Throwable th) {
         log.fatal("Unexpected Exception", th);
      }
   }

   @java.lang.SuppressWarnings("all")
   @javax.annotation.Generated("lombok")
   public java.util.List<java.util.concurrent.Future<java.lang.String>> getFuture() {
      return this.future;
   }

   @java.lang.SuppressWarnings("all")
   @javax.annotation.Generated("lombok")
   public java.util.concurrent.ExecutorService getProcessingThreads() {
      return this.processingThreads;
   }

   @java.lang.SuppressWarnings("all")
   @javax.annotation.Generated("lombok")
   public de.sourcepark.smd.base.queue.ISCPQueue getQueue() {
      return this.queue;
   }
}