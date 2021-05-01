/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports_store.interfaces;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import sports_store.classes.Product;
import sports_store.classes.Purchase;
import sports_store.classes.SalesInfo;

/**
 *
 * @author rpaba
 */
public interface StoreInterface extends java.rmi.Remote {
    
    public void subscribe(String name, SupplierInterface supplier) throws java.rmi.RemoteException;
    
    public void unsubscribe(String name, SupplierInterface supplier) throws java.rmi.RemoteException;
    
    public void checkIfDayHasPassed() throws java.rmi.RemoteException;
    /**
     * 
     * @Supplier
     * 
     */
    
    // Register a new product *
    public int registerProduct(Product p) throws RemoteException;
    
    // Add stock to a product *
    public int addStock(String supplier, LocalDate expirationDate, double price, int quantity, String name, String category) throws RemoteException;
    
    // Delete a product *
    public int deleteAProduct(String name, String category) throws RemoteException;
    
    // Search purchases
    public Object searchPurchases(int choice, int order) throws RemoteException;
    public ArrayList<Purchase> sendFilteredSuppliers(String filter) throws RemoteException;
    public ArrayList<Purchase> sendFilteredProducts(String name, String category) throws RemoteException;
    
    
    // Add a new supplier
    public int addSupplier(String name, String nc) throws RemoteException;
    
    /**
     * 
     * @Seller
     * 
     */
    
    public int sellProduct(ArrayList<SalesInfo> products) throws RemoteException;
    
    public Product getProduct(String name, String category) throws RemoteException;
    
    public ArrayList<Product> searchProducts(int choice, int order, String filter) throws RemoteException; 
    
    public Object searchSales(int choice, int order) throws RemoteException;
}
