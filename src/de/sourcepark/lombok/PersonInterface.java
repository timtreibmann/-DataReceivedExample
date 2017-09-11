/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.lombok;

import de.sourcepark.smd.ocl.ConnectionEvent;
import java.util.concurrent.Future;

/**
 *
 * @author ttreibmann Schnittstelle die alle für die Annotation @DataReceived
 * nötigen Methoden bereit stellt.
 */
public interface PersonInterface {

    public void dataReceived(ConnectionEvent evt);

    public void doCleanupOrProcessing(Future f);

    public void doStatusProcessing() throws Exception;

}
