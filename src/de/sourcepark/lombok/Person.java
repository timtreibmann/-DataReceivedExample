
package de.sourcepark.lombok;


import de.sourcepark.smd.ocl.AbstractOCL;
import java.util.concurrent.Future;
import lombok.DataReceived;
import lombok.Getter;
import lombok.PrintAST;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;


/**
 *
 * @author ttreibmann
 */

@CommonsLog
@DataReceived(
        value = "VALID",
        controller = "Mavenproject1Controller"
)
//@Getter Annotation only need to access and shut down invoked threads 
@Getter
@PrintAST
public class Person extends AbstractOCL implements PersonInterface{
   
 @Override
 public void doCleanupOrProcessing(Future d){
     System.out.println("d ist: "+d);
 }

 @Override
 public void doStatusProcessing()throws Exception{
     System.out.println("statusprocessing");
 } 

}
