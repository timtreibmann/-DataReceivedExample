
package de.sourcepark.lombok;


import de.sourcepark.smd.ocl.AbstractOCL;
import java.util.concurrent.Future;
import lombok.DataReceived;
import lombok.Getter;
import lombok.PrintAST;
import lombok.extern.apachecommons.CommonsLog;


/**
 *
 * @author ttreibmann
 * Mit @DataReceived annotierte Test Klasse
 * Muss mit @CommonsLog annotiert werden
 * Kann mit @Getter annotiert werden um an injizierte Feldvariablen zu gelangen
 * Kann mit @PrintAST annotiert werden um Ausgabe des abstrakten Syntaxbaum dieser Klasse zu erzeugen.
 * 
 * Mit @DataReceived annotierte Klasse:
 * muss die Klasse AbstractOCL erweitern
 * muss das Interface PersonInterface implementieren
 */
//@CommonsLog injiziert log Variable
@CommonsLog
/* @dataReceved injiziert
   private java.util.List<java.util.concurrent.Future<java.lang.String>> future = new java.util.ArrayList<>();
   private java.util.concurrent.ExecutorService processingThreads = java.util.concurrent.Executors.newFixedThreadPool(5);
   private de.sourcepark.smd.base.queue.ISCPQueue queue;
    
private void init() {
      try {
         queue = de.sourcepark.smd.base.output.OutputQueueCollection.getInstance().get(<angegebene KontrollerKlasse>.QUEUE_ID);
      } catch (final java.lang.Exception initEx) {
         log.error("Field initialization failed. Error occurred.", initEx);
      }
   }
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
         if (scpd.getCommand(<angegebene CommandID>) != null) {
            for (final java.util.concurrent.Future f : future) {
               if (f.isDone()) {
                  this.doCleanupOrProcessing(f);
               }
            }
            future.add(processingThreads.submit(new <angegebene ControllerKlasse>(scpd)));
         } else if (scpd.getCommand("ALIVE") != null) {
            de.sourcepark.smd.base.util.scp.SCPStatusObject scps = new de.sourcepark.smd.base.util.scp.SCPStatusObject(scpd);
            scps.getCommand("ALIVE").addParam(new de.sourcepark.smd.base.util.scp.SCPParameter("Version", "string", de.sourcepark.smd.base.config.SMDConfiguration.getVersionString(<annotierte Klasse>.class)));
            queue.offer(scps);
         } else if ((scpd.getCommand("STOP") != null) && scpd.getSource().equalsIgnoreCase(de.sourcepark.smd.base.config.SMDConfiguration.getInstance().getIdentity())) {
            this.stop();
         } else {
            log.error("Unprocessable message received. Possible conflict between command and context. Check message.");
         }
      } catch (final de.sourcepark.smd.base.config.SMDConfigurationException ex) {
         log.error("SMDConfigurationException: Configuration error occurred.", ex);
      } catch (final org.apache.commons.configuration.ConfigurationException ex2) {
         log.error("ConfigurationException: Configuration error occurred.", ex2);
      } catch (final java.lang.Throwable th) {
         log.fatal("Unexpected Exception", th);
      }
   }
*/
@DataReceived(
        value = "VALID",
        controller = "Mavenproject1Controller"
)
//@Getter um auf inizierte Feldvariablen  zugreifen zu können.
@Getter
//Ausgabe des abstrakten Syntaxbaums dieser Klasse in die Datei
@PrintAST
public class Person extends AbstractOCL implements PersonInterface{
   
  //hier kann individueller Quellcode eingefügt werden der innerhalb der Methode dataReceived(ConnectionEvent evt) ausgeführt wird
 @Override
 public void doCleanupOrProcessing(Future d){
     System.out.println("d ist: "+d);
     
 }
//hier kann individueller Quellcode eingefügt werden der innerhalb der Methode dataReceived(ConnectionEvent evt) ausgeführt wird
 @Override
 public void doStatusProcessing()throws Exception{
     System.out.println("statusprocessing");
 } 

}
