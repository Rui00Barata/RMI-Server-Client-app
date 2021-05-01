/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports_store.implementations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.*;
import java.time.LocalDate;
import sports_store.classes.*;
import java.util.ArrayList;
import sports_store.interfaces.StoreInterface;
import sports_store.interfaces.SupplierInterface;


/**
 * Class of the remote object of the server.
 * A storeImplmentation has:
 *  -     allProducts: all products registered in the server
 *  -    allSuppliers: all suppliers registered in the server
 *  -        allSales: all sales registered in the server
 *  -    allPurchases: all purchases registered in the server
 *  -        allStock: all stock registered in the server
 *  - onlineSuppliers: all suppliers conected to the server
 *  -           today: today's date
 * 
 * @author  rpaba
 * @see     Product
 * @see     Supplier
 * @see     Sales
 * @see     Purchase
 * @see     Stock
 * @see     SupplierInterface
 * @see     LocalDate
 */
public class StoreImplementation extends java.rmi.server.UnicastRemoteObject implements StoreInterface {
    
    private ArrayList<Product> allProducts;
    private ArrayList<Supplier> allSuppliers;
    private ArrayList<Sales> allSales;
    private ArrayList<Purchase> allPurchases;
    private ArrayList<Stock> allStock;
    private ArrayList<SupplierInterface> onlineSuppliers;
    private LocalDate today;

    // Constructor
    public StoreImplementation() throws RemoteException {
        super();
        readProductsFromFile();   
        readSuppliersFromFile();  
        readSalesFromFile();      
        readPurchasesFromFile();  
        readStockFromFile();      
        readTodayFromFile();           
        
        this.onlineSuppliers = new ArrayList<>();
    }
    
    // Save to files
    public synchronized int saveProducts2File() {
        
        try{
            
            FileOutputStream f = new FileOutputStream("products.bin");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            
            oos.writeObject(allProducts);
            
            return 0;
            
        }
        catch (IOException e) {
            return -1;
        }
    }
    public synchronized int saveSuppliers2File() {
        
        try{
            
            FileOutputStream f = new FileOutputStream("suppliers.bin");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            
            oos.writeObject(allSuppliers);
            
            return 0;
            
        }
        catch (IOException e) {
            return -1;
        }
    }
    public synchronized int saveSales2File() {
        
        try{
            
            FileOutputStream f = new FileOutputStream("sales.bin");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            
            oos.writeObject(allSales);
            
            return 0;
            
        }
        catch (IOException e) {
            return -1;
        }
    }
    public synchronized int savePurchases2File() {
        
        try{
            
            FileOutputStream f = new FileOutputStream("purchases.bin");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            
            oos.writeObject(allPurchases);
            
            return 0;
            
        }
        catch (IOException e) {
            return -1;
        }
    }
    public synchronized int saveStock2File() {
        
        try{
            
            FileOutputStream f = new FileOutputStream("stock.bin");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            
            oos.writeObject(allStock);
            
            return 0;
            
        }
        catch (IOException e) {
            return -1;
        }
    }
    public synchronized int saveToday2File() {
        
        try{
            
            FileOutputStream f = new FileOutputStream("today.bin");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            
            oos.writeObject(today);
            
            return 0;
            
        }
        catch (IOException e) {
            return -1;
        }
    }
    
    // Read from files
    private void readProductsFromFile() {
        
        allProducts = new ArrayList<>();
        
        try {
            
            FileInputStream fin = new FileInputStream("products.bin");
            ObjectInputStream ois = new ObjectInputStream(fin);

            allProducts = (ArrayList<Product>)ois.readObject();
                
        } catch (IOException e) {
            System.out.println("Ainda não existem produtos");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    private void readSuppliersFromFile() {
        
        allSuppliers = new ArrayList<>();
        
        try {
            
            FileInputStream fin = new FileInputStream("suppliers.bin");
            ObjectInputStream ois = new ObjectInputStream(fin);

            allSuppliers = (ArrayList<Supplier>)ois.readObject();
                
        } catch (IOException e) {
            System.out.println("Ainda não existem fornecedores");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    private void readSalesFromFile() {
        
        allSales = new ArrayList<>();
        
        try {
            
            FileInputStream fin = new FileInputStream("sales.bin");
            ObjectInputStream ois = new ObjectInputStream(fin);

            allSales = (ArrayList<Sales>)ois.readObject();
                
        } catch (IOException e) {
            System.out.println("Ainda não existem vendas");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    private void readPurchasesFromFile() {
        
        allPurchases = new ArrayList<>();
        
        try {
            
            FileInputStream fin = new FileInputStream("purchases.bin");
            ObjectInputStream ois = new ObjectInputStream(fin);

            allPurchases = (ArrayList<Purchase>)ois.readObject();
                
        } catch (IOException e) {
            System.out.println("Ainda não existem compras");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    private void readStockFromFile() {
        
        allStock = new ArrayList<>();
        
        try {
            
            FileInputStream fin = new FileInputStream("stock.bin");
            ObjectInputStream ois = new ObjectInputStream(fin);

            allStock = (ArrayList<Stock>)ois.readObject();
                
        } catch (IOException e) {
            System.out.println("Ainda não existe stock");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    private void readTodayFromFile() {
        
        try {
            
            FileInputStream fin = new FileInputStream("today.bin");
            ObjectInputStream ois = new ObjectInputStream(fin);

            today = (LocalDate)ois.readObject();
                
        } catch (IOException e) {
            today = LocalDate.now();
            saveToday2File();
            System.out.println("Data ainda não foi guardada");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Allows suppliers to register to the server.
     * @param name name of the supplier
     * @param supplier supplier that is about to be registered
     * @throws RemoteException 
     */
    public synchronized void subscribe(String name, SupplierInterface supplier) throws RemoteException {
        
        System.out.println("Subscribing " + name);
        onlineSuppliers.add(supplier);
        
    }
    
    /**
     * Allows suppliers to unregister to the server.
     * @param name name of the supplier
     * @param supplier supplier that is about to be unregistered
     * @throws java.rmi.RemoteException 
     */
    public synchronized void unsubscribe(String name, SupplierInterface supplier) throws java.rmi.RemoteException {
        
        System.out.println("Unsubscribing " + name);
        onlineSuppliers.remove(supplier);
        
    }
    
    /**
     * Checks if the server has sufficient stock of a product.
     * @param p product that will be verified
     * @throws RemoteException 
     */
    private synchronized void checkForMinimumStock(Product p) throws RemoteException{
        
        int actualStock = 0;
        
        for(Stock s : allStock)
            if(s.getPurchase().getProduct().equals(p))
                actualStock += s.getQuantity();
        
        if(p.getMinimum_stock() >  actualStock)
            for(SupplierInterface supplier : onlineSuppliers) {
                
                supplier.printOnClient("\nÉ necessário repor o stock de(nome,categoria): " + p.getName() + ", " + p.getCategory() + 
                                       "\n\tStock atual: " + actualStock + 
                                       "\n\tStock minimo: " + p.getMinimum_stock()
                                      );
                
            }
    }
    
    /**
     * Checks if there are any out of date stock.
     * @throws RemoteException 
     */
    private synchronized void checkOutofdateStock() throws RemoteException {
        
        ArrayList<Stock> placeHolder = (ArrayList<Stock>) allStock.clone(); // maybe vai dar erro
        ArrayList<Product> removedProducts = new ArrayList<>();
        
        
        for(Stock s : allStock) {
            Purchase purchase = s.getPurchase();
            
            try{
                if(purchase.getExpirationDate().isBefore(today)) {
                    Product product = purchase.getProduct();
                    placeHolder.remove(s);

                    if(!removedProducts.contains(product))
                        removedProducts.add(product);
                }
            }
            catch(NullPointerException e){
                
            }
        }
        
        allStock = (ArrayList<Stock>) placeHolder.clone();
        
        for(Product p : removedProducts) {
            checkForMinimumStock(p);
        }
        
        saveStock2File();
    }
    
    /**
     * Checks if a day has passed.
     * @throws RemoteException 
     */
    public void checkIfDayHasPassed() throws RemoteException{
       
        if(today.isBefore(LocalDate.now())) {
            today = LocalDate.now();
            checkOutofdateStock();
            saveToday2File();
        }
        
        
    }
    
    
    /**
     * Finds a product given a name and a category.
     * @param name name of the product to be searched
     * @param category category of the product to be searched
     * @return returns the product's ID if exists, otherwise returns -1.
     */
    private int findProduct(String name, String category) {
        
        for(Product p : allProducts) 
            if(p.getName().equals(name) && p.getCategory().equals(category))
                return allProducts.indexOf(p);
        
        return -1;
        
    }
    
    /**
     * Copies an ArrayList of pruchases.
     * @param source the ArrayList to be copied
     * @return the new ArrayList with the elements of source
     */
    private ArrayList<Purchase> copyPurchaseArrayList(ArrayList<Purchase> source) {
        
        ArrayList<Purchase> destiny = new ArrayList<>();
        
        for(Purchase p : source) 
            destiny.add(p);
        
        return destiny;
        
    }
    
    /**
     * Finds a supplier given a ID.
     * @param supplier ID of the supplier
     * @return returns the supplier's ID if exists, otherwise returns -1.
     */
    private int findSupplier(String supplier) {
        
        for(Supplier s : allSuppliers) 
            if(s.getName().equals(supplier))
                return allSuppliers.indexOf(s);
        
        return -1;
    }
    
    /**
     * Returns the ArrayList of suppliers
     * @return ArrayList of suppliers
     */
    private ArrayList<Supplier> sendAllSuppliers() {
        
        return (ArrayList<Supplier>) allSuppliers.clone();
    }
    
    /**
     * 
     * @param order 1 for low to high else high to low
     * @return ArrayList of purchases order by expiration date
     */
    private ArrayList<Purchase> sortByExpirationDate(int order) {
        
        ArrayList<Purchase> sorted = (ArrayList<Purchase>) allPurchases.clone();
        
        sorted.removeIf(o -> !o.getProduct().getCategory().equals("Alimentacao"));
        
        if(order==1)
            sorted.sort((o1,o2) -> o1.getExpirationDate().compareTo(o2.getExpirationDate()));
        else
            sorted.sort((o1,o2) -> -(o1.getExpirationDate().compareTo(o2.getExpirationDate())));
        
        return sorted;
    }
    
    /**
     * 
     * @param order 1 for low to high else high to low
     * @return ArrayList of purchases order by purchase price
     */
    private ArrayList<Purchase> sortByPurchasePrice(int order) {
        
        ArrayList<Purchase> sorted = (ArrayList<Purchase>) allPurchases.clone();
        
        if(order==1)
            sorted.sort((o1,o2) -> (int)(o1.getPrice() - o2.getPrice()));
        else
            sorted.sort((o1,o2) -> (int)(-(o1.getPrice() - o2.getPrice())));
        
        return sorted;
    }
    
    /**
     * Register a product to the server.
     * @param p product to register
     * @return returns 0 if it succeeded, 1 product already exists and -1 if it couldn't be saved to the file
     * @throws RemoteException 
     */
    public synchronized int registerProduct(Product p) throws RemoteException {
        
        if(!allProducts.contains(p)) {
            if(allProducts.add(p))
                if(0 == saveProducts2File())
                    return 0;
        }
        else
            return 1;
        
        return -1;
    }
    
    /**
     * Add stock to a product.
     * @param supplier          supplier ID
     * @param expirationDate    date that the product will expire
     * @param price             price of the purchase
     * @param quantity          quantity bought
     * @param name              name of the product
     * @param category          category of the product
     * @return                  returns 0 if succeeded otherwise -1 if product doesn't exist, -2 if supplier doesn't exist and -3 if it couldn't be saved to the file
     * @throws RemoteException 
     */
    public synchronized int addStock(String supplier, LocalDate expirationDate, double price, int quantity, String name, String category) throws RemoteException {
        
        int product_ind = findProduct(name, category);
        int supplier_ind = findSupplier(supplier);
        LocalDate expDate;
        expDate = expirationDate;
         
        if(product_ind == -1)
            return -1;
        
        if(supplier_ind == -1)
            return -2;
        
        Purchase p = new Purchase (allSuppliers.get(supplier_ind), expDate, price, quantity, allProducts.get(product_ind));
        allPurchases.add(p);
        allStock.add(new Stock(quantity,p));
        
        if(0 == savePurchases2File() && 0 == saveStock2File())
            return 0;
        
        return -3;
    }
    
    /**
     * Delete a product.
     * @param name      name of the product
     * @param category  category of the product
     * @return          returns 0 if succeeded, -1 if the product doesn't exist and -2 if it couldn't be saved to the file
     * @throws RemoteException 
     */
    public synchronized int deleteAProduct(String name, String category) throws RemoteException {
        
        int product_ind = findProduct(name, category);
        
        if(product_ind == -1)
            return -1;
        
        allProducts.remove(product_ind);
        
        if(0 == saveProducts2File())
            return 0;
        
        return -2;
    }
    
    /**
     * Register a new supplier to the server.
     * @param name ID of the supplier 
     * @param nc "numero de contribuinte" of the supplier
     * @return  returns 0 if it succeeded otherwise returns -1.
     * @throws RemoteException 
     */
    public synchronized int addSupplier(String name, String nc) throws RemoteException {
        
        Supplier s = new Supplier(name,nc);
        
        if(!allSuppliers.contains(s)) {
            if(allSuppliers.add(s))
                if(0 == saveSuppliers2File())
                    return 0;
        }
        
        return 1;
        
    }
    
    /**
     * Search the Purchase ArrayList.
     * @param choice choice of the search
     * @param order order of the search
     * @return returns the ArrayList with the result of the search
     * @throws RemoteException 
     */
    public Object searchPurchases(int choice, int order) throws RemoteException {
        
        switch(choice) {
            
            case 1:
                return sendAllSuppliers();
                
            case 4:
                return sortByExpirationDate(order);
                
            case 5: 
                return sortByPurchasePrice(order);
        }
        
        return null;
    }
    
    /**
     * Creates a new ArrayList of purchases filtered with the name of the supplier.
     * @param filter name of the supplier to filter with
     * @return return the ArrayList filtered
     * @throws RemoteException 
     */
    public ArrayList<Purchase> sendFilteredSuppliers(String filter) throws RemoteException {
        
        ArrayList<Purchase> filtered = copyPurchaseArrayList(allPurchases);
        
        filtered.removeIf(o -> !o.getSupplier().getName().equals(filter));
        
        return filtered;
    }
    
    /**
     * Creates a new ArrayList of purchases with a given product.
     * @param name name of the product
     * @param category name of the category of the product
     * @return return the ArrayList filtered
     * @throws RemoteException 
     */
    public ArrayList<Purchase> sendFilteredProducts(String name, String category) throws RemoteException {
        
        ArrayList<Purchase> filtered = copyPurchaseArrayList(allPurchases);
        
        filtered.removeIf(o -> !(o.getProduct().getName().equals(name) && o.getProduct().getCategory().equals(category)));
        
        return filtered;
    }
 
    /**
     * Finds the first stock of the given product.
     * @param p product to search
     * @return returns the stock's ID if exists, otherwise returns -1.
     */
    private int findStock(Product p) {
        
        for(Stock s : allStock)
            if(s.getPurchase().getProduct().equals(p))
               return allStock.indexOf(s);
        
        return -1;
        
    }
    
    /**
     * Returns the ArrayList of products
     * @return ArrayList of products
     */
    private ArrayList<Product> getAllProducts() {
        
        return allProducts;
    }
    
    /**
     * Finds all products with the given category.
     * @param filter category of the product
     * @return returns an ArrayList filtered with filter
     */
    private ArrayList<Product> filterByCategory(String filter) {
        
        ArrayList<Product> filtered = (ArrayList<Product>) allProducts.clone();
        
        filtered.removeIf(o -> !o.getCategory().equals(filter));
        
        return filtered;
    }
    
    /**
     * Finds all products with the given name.
     * @param filter name of the product
     * @return returns an ArrayList filtered with filter
     */
    private ArrayList<Product> filterByName(String filter) {
        
        ArrayList<Product> filtered = (ArrayList<Product>) allProducts.clone();
        
        filtered.removeIf(o -> !o.getName().equals(filter));
        
        return filtered;
    }
    
    /**
     * Finds a product with the given name.
     * @param filter name of the product
     * @return returns an ArrayList filtered with filter
     */
    private ArrayList<Product> orderByPrice(int order) {
        
        ArrayList<Product> sorted = (ArrayList<Product>) allProducts.clone();
        
        if(order==1)
            sorted.sort((o1,o2) -> (int)(o1.getSelling_price() - o2.getSelling_price()));
        else
            sorted.sort((o1,o2) -> (int)(-(o1.getSelling_price() - o2.getSelling_price())));
        
        return sorted;
        
    }
    
    /**
     * 
     * @param order 1 for low to high else high to low
     * @return ArrayList of sales order by sales price
     */
    private ArrayList<Sales> orderByPriceOfSales(int order) {
        
        ArrayList<Sales> sorted = (ArrayList<Sales>) allSales.clone();
        
        if(order==1)
            sorted.sort((o1,o2) -> (int)(o1.getTotal_amount() - o2.getTotal_amount()));
        else
            sorted.sort((o1,o2) -> (int)(-(o1.getTotal_amount() - o2.getTotal_amount())));
        
        return sorted;
        
    }
    
    /**
     * 
     * @param order 1 for low to high else high to low
     * @return ArrayList of products order by number of sales
     */
    private ArrayList<Product> orderByNumberOfSales(int order) {
        
        ArrayList<Product> sorted = (ArrayList<Product>) allProducts.clone();
        
        if(order==1)
            sorted.sort((o1,o2) -> (int)(o1.getSales() - o2.getSales()));
        else
            sorted.sort((o1,o2) -> (int)(-(o1.getSales() - o2.getSales())));
        
        return sorted;
        
    }
    
    /**
     * Sell a product
     * @param products ArrayList of products to sell and their quantity
     * @return returns 0 if it succeeded otherwise returns -1 if one of the products doesn't exist, returns -2 if there's no stock of the product and returns -3 if couldn't save to files 
     * @throws RemoteException 
     */
    public synchronized int sellProduct(ArrayList<SalesInfo> products) throws RemoteException{
        
        ArrayList<Stock> mem = (ArrayList<Stock>)allStock.clone();
        
        for(SalesInfo si : products) {
            
            Product p = si.getProduct();
            int quantity = si.getQuantity();
            
            int i = allProducts.indexOf(p);
            
            if(i==-1){
                allStock = (ArrayList<Stock>)mem.clone();
                return -1;
            }
                
            
            while(quantity>0){
                
                int stockInd = findStock(p);
                
                if(stockInd==-1) {
                    allStock = (ArrayList<Stock>)mem.clone();
                    return -2;
                }
                    
                int stockQuantity = allStock.get(stockInd).getQuantity();
                
                if(stockQuantity>quantity){
                    allStock.get(stockInd).setQuantity(stockQuantity-quantity);
                    quantity = 0;
                }
                
                if(stockQuantity<=quantity){
                    quantity -= stockQuantity;
                    allStock.remove(stockInd);
                }
                
            }
        }
        
        for(SalesInfo si : products) {
            
            Product p = si.getProduct();
            int i = allProducts.indexOf(p);
            
            allProducts.get(i).increaseSales(si.getQuantity());
            checkForMinimumStock(p);
        }
        
        allSales.add(new Sales(products));
        
        if(0 == saveStock2File()  && 0 == saveProducts2File() && 0 == saveSales2File())
            return 0;
        
        return -3;
    }
    
    /**
     * Returns a product with a given name and category
     * @param name name of the product
     * @param category categort of the product
     * @return returns the product if exists otherwise returns null
     * @throws RemoteException 
     */
    public Product getProduct(String name, String category) throws RemoteException {
        
        int ind = findProduct(name,category);
        
        if(ind==-1)
            return null;
        
        return allProducts.get(ind);
        
    }
    
    /**
     * Search products with a given choice, order and filter
     * @param choice    choice of the search
     * @param order     order of the product
     * @param filter    filter of products
     * @return returns an ArrayList of products with a given order or a filter
     * @throws RemoteException 
     */
    public ArrayList<Product> searchProducts(int choice, int order, String filter) throws RemoteException {
        
        switch(choice) {
            
            case 1:
                return getAllProducts();
                
            case 2:
                return filterByCategory(filter);
                
            case 3:
                return filterByName(filter);
                
            case 4:
                return orderByPrice(order);
                
        }
        
        return null;
        
    }
    
    /**
     * earch products with a given choice and order
     * @param choice    choice of the search
     * @param order     order of the product
     * @return returns an ArrayList of sales with a given order
     * @throws RemoteException 
     */
    public Object searchSales(int choice, int order) throws RemoteException {
        
        switch(choice) {
            
            case 1:
                return allSales;
                
            case 2:
                return orderByPriceOfSales(order);
                
            case 3:
                return orderByNumberOfSales(order);
            
        }
        
        return null;
    }
}
