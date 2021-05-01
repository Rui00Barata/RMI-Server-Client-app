/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports_store;

import java.rmi.Naming;
import java.rmi.RemoteException;
import sports_store.implementations.StoreImplementation;
import sports_store.interfaces.StoreInterface;


/**
 *
 * @author rpaba
 */
public class Sports_Store {
    
    // remote object to send to clients
    private static StoreInterface store;

    /**
     * Initialization of remote object and registry
     */
    public Sports_Store() {
        
        System.setSecurityManager(new SecurityManager());
        
        try {
            
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            store = new StoreImplementation();
            Naming.rebind("rmi://127.0.0.1:1099/SportsStore", store);
            System.out.println("Servidor está OK");
        } 
        catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }
    
    /**
     * Initialize server and has a loop to check if a day has passed
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        new Sports_Store();
        
        while(true) {
            try{
                store.checkIfDayHasPassed();
            }
            catch(RemoteException e){
                System.out.println(e.getMessage());
            }
            
        }
        
            
            
        
    }
    
}
