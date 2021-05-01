/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports_store.classes;

import sports_store.classes.SalesInfo;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class of a sale.
 * A sale has:
 *  -     products: ArrayList of SalesInfo that has all the products of a sale
 *  - total_amount: the total amount that the client paid for the sale
 *  -         date: Date of the sale
 * 
 * @author  rpaba
 * @see     SalesInfo
 * @see     LocalDate
 */ 
public class Sales implements Serializable {
    
    private ArrayList<SalesInfo> products;
    private double total_amount;
    private LocalDate date;

    // Constructor
    public Sales(ArrayList<SalesInfo> products) {
        
        this.products = products;
        total_amount = getTotalAmount();
        date = LocalDate.now();
    }

    // Getters and Setters
    public ArrayList<SalesInfo> getProducts() { return products; }
    public void setProducts(ArrayList<SalesInfo> products) { this.products = products; }
    public double getTotal_amount() { return total_amount; }
    public void setTotal_amount(double total_amount) { this.total_amount = total_amount; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    private double getTotalAmount() {
        
        double total=0;
        
        for (SalesInfo s : products)
            total += s.getQuantity()*s.getProduct().getSelling_price();
        
        return total;
        
    }

    @Override
    public String toString() {
        return "Sales[ " + "products: " + products + " , total_amount: " + total_amount + " , date: " + date + '}';
    }
  
}
