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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ttreibmann 
 * Main Klasse die ein Objekt der Klasse Person erzeugt und
 * die durch die Annotation @DataReceived injizierte Methode
 * dataReceived(ConnctionEvent evt) ausführt.
 *
 * Der Feldvariablen future der Klasse Person werden zwei Werte hinzugefügt
 * bevor die Methode dataReceived(ConnctionEvent evt) ausgeführt wird. Dies
 * dient dazu, dass bei einem Durchlaufen der forSchleife innherhalb der Methode
 * dataReceived(ConnctionEvent evt) min. 2 Durchläufe vollzogen werden.
 *
 * Alle Kontrollstrukturen der Methode dataReceived(ConnectionEvent evt) können
 * mit einer bestimmten Konfiguration durchlaufen werden. Dafür müssen die
 * übergebenen Konstruktorparameter des Objekts ConnectionEvent angepasst werden
 * und oder die CommandID der annotierten Klasse, durch Übergabe eines anderen
 * Annotationsparameter geändert werden.
 * 
 * Die folgende Tabelle gibt alle Möglichkeiten an:
 *
 *KS    Parameter 1     Parameter 2     Parameter 3     Parameter 4     CommandID
 *KS1     TRUE            FALSE           FALSE           FALSE           VALID/INVALID
 *KS2     FALSE           TRUE            FALSE           FALSE           VALID/INVALID
 *KS3     FALSE           FALSE           FALSE           FALSE           VALID
 *KS4     FALSE           FALSE           FALSE           FALSE           INVALID
 *KS5     FALSE           FALSE           TRUE            FALSE           INVALID
 *KS6     FALSE           FALSE           TRUE            TRUE            INVALID
 *
 * 
 * Legende der Tabelle Bedienungsanleitung1
* KS = Kontrollstruktur
* KS1 = if (scpd.hasStatus())
* KS2 = if (scpd.isDirty())
* KS3 = if (scpd.getCommand("<CommandId>") != null)
* KS4 = else if (scpd.getCommand("ALIVE") != null)
* KS5 = else if ((scpd.getCommand("STOP") != null) &&
*               scpd.getSource().equalsIgnoreCase(
*               de.sourcepark.smd.base.cong.
*               SMDConguration.getInstance().getIdentity()))
* KS6 = else{. . . }
* f.isDone() ist immer true im Beispielprojekt
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Objekt der annotierten Klasse
        Person person = new Person();
        //Erstellung des Objektes des Parameters für die Methode dataReceived(ConnectionEvent evt)
        ConnectionEvent evt = new ConnectionEvent(false, false, false, false, SMDConfiguration.getInstance().getIdentity());

        //Test-Objekte vom Typ Future die der Feldvariable future der mit @DataReceived 
        //annotierten Klasse Elementte hinzufügen um 2x Durchlauf der Forschleife 
        //innerhalb der Methode dataReceived(ConnectionEvent evt) zu gewährleisten
        TestFuture e0 = new TestFuture();
        TestFuture e1 = new TestFuture();
        person.getFuture().add(e0);
        person.getFuture().add(e1);

        //Aufruf der Methode dataReceived(ConnectionEvent evt) der Klasse Person 
        person.dataReceived(evt);
        //Test-Ausgabe wenn die Kontrollstruktur 
        //else if (scpd.getCommand("ALIVE") != null)
        //der Methode dataReceived(ConnectionEvent evt) der Klasse Person 
        //durchlaufen wird, werden Parameter ausgegeben sonst null
        System.out.println(person.getQueue().toString());
        //Test-Ausgabe für Feldvariable future
        //wird Kontrollstruktur 
        //if (scpd.getCommand("VALID") != null) durchlaufen
        //wird ein weiteres Element der Feldvariable future der Klasse Person hinzugefügt.
        System.out.println("future: " + person.getFuture().size());
        person.getFuture().forEach(f -> {
            try {
                System.out.println("FutureElement: " + f.get());
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //Durch die Annotation @DataReceived werden threads aktiviert, die sollten deaktiviert werden.
        //nur mit @Getter auf der durch @DataReceived annotierten Klasse möglich
        person.getProcessingThreads().shutdown();

    }

}
