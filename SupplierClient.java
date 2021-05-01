/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports_store;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import sports_store.classes.Product;
import sports_store.classes.Purchase;
import sports_store.classes.Supplier;
import sports_store.implementations.SupplierImplementation;
import sports_store.interfaces.StoreInterface;
import sports_store.interfaces.SupplierInterface;
import sports_store.util.Ler;

/**
 *
 * @author rpaba
 */
public class SupplierClient {
    
    private static StoreInterface store;        // remote object of server
    private static SupplierInterface supplier;  // remote object of supplier client
    private static String id;                   // id of client
    
    
    // Functions that print all of the menus
    private static void printMenu() {
        
        System.out.println("\n\n+==============================+");
        System.out.println("|              MENU            |");
        System.out.println("+==============================+");
        System.out.println(" 1 - Registar um produto");
        System.out.println(" 2 - Adicionar stock");
        System.out.println(" 3 - Eliminar um produto");
        System.out.println(" 4 - Registar um fornecedor");
        System.out.println(" 5 - Consultar compras e fornecedores");
        System.out.println("-1 - Sair");
          System.out.print("OPÇÃO: ");
    }
    private static void printPurchasesSearchChoices() {
        
        System.out.println("\n\n+===================================================+");
        System.out.println("|                   Consultar Compras               |");
        System.out.println("+===================================================+");
        System.out.println(" 1 - Mostrar todos os fornecedores");
        System.out.println(" 2 - Filtrar histórico de compras por fornecedor");
        System.out.println(" 3 - Filtrar por produtos");
        System.out.println(" 4 - Ordenar por data de validade(só Alimentos)");
        System.out.println(" 5 - Ordenar por preço de compra");
        System.out.println("-1 - Voltar atrás");
          System.out.print("OPÇÃO: ");
        
    }
    private static void printExpirationSearchMenu() {
        
        System.out.println("\n\n+==========================================+");
        System.out.println("|       Ordenar por data de validade       |");
        System.out.println("+==========================================+");
        System.out.println(" 1 - Por ordem crescente");
        System.out.println(" 2 - Por ordem decrescente");
        System.out.println("-1 - Voltar atrás");
          System.out.print("OPÇÃO: ");
        
    }
    private static void printBuyingPriceMenu() {
        
        System.out.println("\n\n+===========================================+");
        System.out.println("|        Ordenar por preço de compra        |");
        System.out.println("+===========================================+");
        System.out.println(" 1 - Por mais recente");
        System.out.println(" 2 - Por mais antiga");
        System.out.println("-1 - Voltar atrás");
          System.out.print("OPÇÃO: ");
        
    }
    
    // Functions that print some arrays that the supplier receives when searching
    private static void suppliersToString(ArrayList<Supplier> supplierList) {
        
        if(supplierList!=null)
            for(Supplier s : supplierList)
                System.out.println(s.toString());
        else
            System.out.println("Ainda não existem fornecedores...");
        
    }
    private static void purchasesToString(ArrayList<Purchase> purchaseList) {
        
        if (purchaseList != null)
            for(Purchase p : purchaseList)
                System.out.println(p.toString());
        else
            System.out.println("Ainda não existem compras...");
        
    }
     
    /**
     * Function that registers a new product in the server.
     * @throws RemoteException 
     */
    private static void registerProduct() throws RemoteException {
        
        String category, name;
        Double selling_price;
        int minimum_stock;
         
        System.out.print("Insira a categoria do produto: ");
        category = Ler.umaString();
        System.out.print("Insira o nome do produto: ");
        name = Ler.umaString();
        System.out.print("Insira o preço de venda do produto: ");
        selling_price = Ler.umDouble();
        System.out.print("Insira o stock mínimo para o produto: ");
        minimum_stock = Ler.umInt();
        
        if(store.registerProduct(new Product(category, name, selling_price, minimum_stock)) == 0)
            System.out.println("Produto registado com sucesso!"); 
        else
            System.out.println("ERRO! Produto já registado..");
        
    }
    
    /**
     * Functions that add stock of a product in server.
     * @throws RemoteException 
     */
    private static void addStock() throws RemoteException {
        
        String category, name, supplier;
        int quantity, status;
        double price;
        
        System.out.print("Escreva o nome do fornecedor: ");
        supplier = Ler.umaString();
        System.out.print("Escreva a categoria do produto: ");
        category = Ler.umaString();
        System.out.print("Escreva o nome do produto: ");
        name = Ler.umaString();
        System.out.print("Insira a quantidade comprada: ");
        quantity = Ler.umInt();
        System.out.print("Insira o preço de compra do produto: ");
        price = Ler.umDouble();
        
        if(category.equals("Alimentacao")) {
            System.out.print("Insira a data de validade (yyyy-mm-dd): ");
            status = store.addStock(supplier, Ler.umaDate(), price, quantity, name, category);
        }
        else {
            status = store.addStock(supplier, null, price, quantity, name, category);
        }
        
        switch(status) {
            
            case 0:
                System.out.println("Compra registada com sucesso!");
                break;
                
            case -1:
                System.out.println("ERRO! Produto não registado! O produto tem que estar registado para ser adicionado stock");
                break;
                
            case -2:
                System.out.println("ERRO! Fornecedor não registado! O Fornecedor tem que estar registado para ser adicionado stock");
                break;
                
            case -3:
                System.out.println("ERRO! Não foi possivel atualizar as informações do servidor");
                break;
                
        }
    }
    
    /**
     * Functions that delete a product
     * @throws RemoteException 
     */
    private static void deleteProduct() throws RemoteException {
        
        String category, name;
        
        System.out.print("Insira a categoria do produto: ");
        category = Ler.umaString();
        System.out.print("Insira o nome do produto: ");
        name = Ler.umaString();
        
        switch(store.deleteAProduct(name, category)){
            
            case 0:
                System.out.println("Produto eliminado com sucesso!");
                break;
                
            case -1:
                System.out.println("ERRO! Produto não existe...");
                break;
                
            case -2:
                System.out.println("ERRO! Não foi possivel atualizar as informaçoes do servidor");
                break;
        }
    }
    
    /**
     * Function that registers a new supplier in the server
     * @throws RemoteException 
     */
    private static void registerSupplier() throws RemoteException {
        
        String name, nc;
        
        System.out.print("Insira o nome do fornecedor: ");
        name = Ler.umaString();
        System.out.print("Insira o numero de contribuinte do fornecedor: ");
        nc = Ler.umaString();
        
        if(store.addSupplier(name, nc) == 0)
            System.out.println("Fornecedor registado com sucesso!"); 
        else
            System.out.println("ERRO! Fornecedor já registado..");
        
    }
    
    /**
     * Function that allows to search purchases in the server.
     * @throws RemoteException 
     */
    private static void purchasesSearch() throws RemoteException {
        
        int choice;
        boolean flag = true;
        
        while(flag) {
            
            printPurchasesSearchChoices();
            choice = Ler.umInt();
            
            switch(choice) {
                
                case 1:
                    {
                    ArrayList<Supplier> placeholder = (ArrayList<Supplier>)store.searchPurchases(choice,0);
                    suppliersToString(placeholder);
                    }
                    break;
                    
                case 2:
                    System.out.print("Insira o nome do fornecedor: ");
                    purchasesToString(store.sendFilteredSuppliers(Ler.umaString()));
                    break;
                    
                case 3:
                    System.out.print("Insira o nome do produto: ");
                    String name = Ler.umaString();
                    System.out.print("Insira a categoria do produto: ");
                    String category = Ler.umaString();
                    purchasesToString(store.sendFilteredProducts(name, category));
                    break;
                    
                case 4:{
                    boolean flag2 = true;
                    int order = 0;
                    while(flag2) {
                        
                        printExpirationSearchMenu();
                        order = Ler.umInt();
                        
                        if(order==-1 || order==1 || order==2)
                            flag2=false;
                        else
                            System.out.println("ERRO! Insira um opcao valida...");
                    }
                    if(order!=-1){
                        ArrayList<Purchase> placeholder = (ArrayList<Purchase>)store.searchPurchases(choice,order);
                        purchasesToString(placeholder);
                    }
                }
                    break;
                    
                case 5:{
                    boolean flag2 = true;
                    int order = 0;
                    while(flag2) {
                        
                        printBuyingPriceMenu();
                        order = Ler.umInt();
                        
                        if(order==-1 || order==1 || order==2)
                            flag2=false;
                        else
                            System.out.println("ERRO! Insira um opcao valida...");
                    }
                    if(order!=-1){
                        ArrayList<Purchase> placeholder = (ArrayList<Purchase>)store.searchPurchases(choice,order);
                        purchasesToString(placeholder);
                    }
                }
                    break;
                 
                    
                case -1:
                    flag = false;
                    break;
                    
                default:
                    System.out.println("ERRO! Insira um opcao valida...");
            }
        }
    }
    
    /**
     * Main function of the supplier. Has the menu and all the calls to the other functions.
     * @throws java.rmi.RemoteException 
     */
    private static void menu() throws java.rmi.RemoteException {
        
        boolean flag = true;
        
        while(flag) {
            
            printMenu();
            
            switch(Ler.umInt()) {
                
                case 1:
                    registerProduct();
                    break;
                
                case 2:
                    addStock();
                    break;
                    
                case 3:
                    deleteProduct();
                    break;
                    
                case 4: 
                    registerSupplier();
                    break;
                    
                case 5:
                    purchasesSearch();
                    break;
                    
                case -1:
                    store.unsubscribe(id, supplier);
                    flag = false;
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("ERRO! Insira uma opcao valida...");
                
            }
        }
    } 
    
    public static void main(String[] args) {
        
        System.setSecurityManager(new SecurityManager());
        
        try {
            
            store = (StoreInterface) Naming.lookup("rmi://127.0.0.1/SportsStore");
            supplier = new SupplierImplementation();
            System.out.print("Insira o seu id: ");
            id = Ler.umaString();
            store.subscribe(id, supplier);
            
            menu();
        }
        catch (RemoteException re) {
            System.out.println("RemoteException");
            System.out.println(re.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Adeus2...");
    }   
}
