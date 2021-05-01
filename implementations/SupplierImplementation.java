/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports_store.implementations;

import java.rmi.RemoteException;
import sports_store.interfaces.SupplierInterface;

/**
 *
 * @author rpaba
 */
public class SupplierImplementation extends java.rmi.server.UnicastRemoteObject implements SupplierInterface {

    public SupplierImplementation() throws RemoteException {
        super();
    }

    /**
     * Print a message from the server in the client.
     * @param s string to print
     * @throws RemoteException 
     */
    public void printOnClient(String s) throws RemoteException {
        System.out.println("\n" + s);
    }
    
    
    
}
