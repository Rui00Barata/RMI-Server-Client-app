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
import sports_store.classes.Sales;
import sports_store.classes.SalesInfo;
import sports_store.interfaces.StoreInterface;
import sports_store.util.Ler;

/**
 *
 * @author rpaba
 */
public class SellerClient {
    
    // remote object
    private static StoreInterface store;
    
    // Functions that print all of the menus
    private static void printMenu() {
        
        System.out.println("\n\n+==============================+");
        System.out.println("|              MENU            |");
        System.out.println("+==============================+");
        System.out.println(" 1 - Vender um produto");
        System.out.println(" 2 - Consultar produtos existentes"); // testar tudo ate aqui
        System.out.println(" 3 - Consultar as vendas"); // falta isto 
        System.out.println("-1 - Sair");
          System.out.print("OPÇÃO: ");
    }
    private static void printProductSearchChoices() {
        
        System.out.println("\n\n+==================================================+");
        System.out.println("|                Consultar Produtos                |");
        System.out.println("+==================================================+");
        System.out.println(" 1 - Mostrar todos os produtos");
        System.out.println(" 2 - Filtrar produtos por categoria");
        System.out.println(" 3 - Filtrar produtos por nome");
        System.out.println(" 4 - Ordenar por preço de venda");
        System.out.println("-1 - Voltar atrás");
          System.out.print("OPÇÃO: ");
        
    }
    private static void printExpirationSearchMenu() {
        
        System.out.println("\n\n+========================================+");
        System.out.println("|       Ordenar por preço de venda       |");
        System.out.println("+========================================+");
        System.out.println(" 1 - Por ordem crescente");
        System.out.println(" 2 - Por ordem decrescente");
        System.out.println("-1 - Voltar atrás");
          System.out.print("OPÇÃO: ");
        
    }
    private static void printSalesSearchChoice() {
        
        System.out.println("\n\n+======================================+");
        System.out.println("|           Consultar Vendas           |");
        System.out.println("+======================================+");
        System.out.println(" 1 - Mostrar todos as vendas");
        System.out.println(" 2 - Ordenar por preço da venda");
        System.out.println(" 3 - Ordenar por numero de vendas");
        System.out.println("-1 - Voltar atrás");
          System.out.print("OPÇÃO: ");
        
    }
    private static void printPriceOfSearchMenu() {
        
        System.out.println("\n\n+==============================================+");
        System.out.println("|         Ordenar por preço das vendas         |");
        System.out.println("+==============================================+");
        System.out.println(" 1 - Por ordem crescente");
        System.out.println(" 2 - Por ordem decrescente");
        System.out.println("-1 - Voltar atrás");
          System.out.print("OPÇÃO: ");
        
    }
    private static void printNumberOfSalesMenu() {
        
        System.out.println("\n\n+==============================================+");
        System.out.println("|         Ordenar por numero de vendas         |");
        System.out.println("+==============================================+");
        System.out.println(" 1 - Por ordem crescente");
        System.out.println(" 2 - Por ordem decrescente");
        System.out.println("-1 - Voltar atrás");
          System.out.print("OPÇÃO: ");
        
    }
    
    // Functions that print some arrays that the seller receives when searching
    private static void productsToString(ArrayList<Product> products) {
        
        for(Product p : products)
            System.out.println(p.toString());
    }
    private static void salesToString(ArrayList<Sales> sales) {
        
        for(Sales s : sales)
            System.out.println(s.toString());
    }
    
    /**
     * Function that sends a ArrayList of SalesInfo to server
     * Has a loop with 3 options:
     *  - "y": insert a new product in the sell
     *  - "c": cancel the sell
     *  - "n": send the sell to the server
     * @throws RemoteException 
     */
    private static void sellProduct() throws RemoteException {
        
        ArrayList<SalesInfo> placeholder = new ArrayList<>();
        
        String category, name;
        int quantity;
        
        while(true) {
            
            System.out.print("Quer inserir um novo produto na compra?(y/c/n)");
            
            switch(Ler.umaString()){
                
                case "y":
                    System.out.print("Insira a categoria do produto: ");
                    category = Ler.umaString();
                    System.out.print("Insira o nome do produto: ");
                    name = Ler.umaString();
                    System.out.print("Insira a quantidade do produto: ");
                    quantity = Ler.umInt();
                    
                    Product p = store.getProduct(name, category);
                    
                    if(p != null)
                        placeholder.add(new SalesInfo(p,quantity));
                    else
                        System.out.println("Produto nao disponivel");
                    break;
                    
                case "c":
                    System.out.println("A cancelar compra...");
                    return;
                    
                case "n":
                    while(true) {
                        
                        System.out.println(placeholder.toString());
                        System.out.print("Confirmar compra?(y/n)");

                        if(Ler.umaString().equals("y")) {
                            int status = store.sellProduct(placeholder);
                            
                            switch(status){
                                
                                case 0:
                                    System.out.println("Compra registada com sucesso!");
                                    break;
                                
                                case -1:
                                    System.out.println("ERRO! Produto não existe");
                                    break;
                                    
                                case -2:
                                    System.out.println("ERRO! Não há stock suficiente");
                                    break;
                                    
                                case -3:
                                    System.out.println("ERRO! Não foi possivel atualizar as informacoes do servidor");
                                    break;
                            } 
                                
                            return;
                        }


                        if(Ler.umaString().equals("n")) {
                            System.out.println("A cancelar compra...");
                            return;
                        }
                        
                        System.out.println("Insira uma opcao valida...");
                    }
                    
                default:
                    System.out.println("Insira uma opcao valida...");
            }
        }
    }
    
    /**
     * Function that allows to search products in the server.
     * @throws RemoteException 
     */
    private static void searchProducts() throws RemoteException {
        
        int choice;
        boolean flag = true;
        
        while(flag) {
            
            printProductSearchChoices();
            choice = Ler.umInt();
            
            switch(choice) {
                
                case 1:
                    productsToString(store.searchProducts(choice,-1,null));
                    break;
                    
                case 2:
                    System.out.print("Insira a categoria que quer mostrar: ");
                    productsToString(store.searchProducts(choice,-1,Ler.umaString()));
                    break;
                    
                case 3:
                    System.out.print("Insira o nome do produto: ");
                    productsToString(store.searchProducts(choice,-1,Ler.umaString()));
                    break;
                    
                case 4:
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
                        productsToString(store.searchProducts(choice, order, null));
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
     * Function that allows to search past sales in the server.
     * @throws RemoteException 
     */
    private static void searchSales() throws RemoteException {
        
        int choice;
        boolean flag = true;
        
        while(flag) {
            
            printSalesSearchChoice();
            choice = Ler.umInt();
            
            switch(choice) {
                
                case 1:
                    salesToString((ArrayList<Sales>) store.searchSales(choice, -1));
                    break;
                    
                case 2:
                {
                    boolean flag2 = true;
                    int order = 0;
                    while(flag2) {
                        
                        printPriceOfSearchMenu();
                        order = Ler.umInt();
                        
                        if(order==-1 || order==1 || order==2)
                            flag2=false;
                        else
                            System.out.println("ERRO! Insira um opcao valida...");
                    }
                    if(order!=-1){
                        salesToString((ArrayList<Sales>) store.searchSales(choice, order));
                    }
                }
                    break;
                    
                case 3:
                {
                    boolean flag2 = true;
                    int order = 0;
                    while(flag2) {
                        
                        printNumberOfSalesMenu();
                        order = Ler.umInt();
                        
                        if(order==-1 || order==1 || order==2)
                            flag2=false;
                        else
                            System.out.println("ERRO! Insira um opcao valida...");
                    }
                    if(order!=-1){
                        productsToString((ArrayList<Product>) store.searchSales(choice, order));
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
     * Main function of the seller. Has the menu and all the calls to the other functions.
     * @throws RemoteException 
     */
    private static void menu() throws RemoteException {
        
        int choice = 0;
        boolean flag = true;
        
        while(flag) {
            
            printMenu();
            choice = Ler.umInt();
            
            switch(choice) {
                
                case 1:
                    sellProduct();
                    break;
                    
                case 2:
                    searchProducts();
                    break;
                    
                case 3:
                    searchSales();
                    break;
                    
                case -1: 
                    System.out.println("Cliente a desligar-se...");
                    flag = false;
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("ERRO! Insira uma opção válida.");
                    
            }
            
        }
        
        
    }
    
    public static void main(String[] args) {
        
        try {
            
            store = (StoreInterface) Naming.lookup("rmi://127.0.0.1/SportsStore");
            
            menu();
        }
        catch (RemoteException re) {
            System.out.println("RemoteException");
            System.out.println(re.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
    }
    
}
