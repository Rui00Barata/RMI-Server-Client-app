/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports_store.classes;

import java.io.Serializable;

/**
 * Class of stock.
 * A stock has:
 *  - quantity: the the current real quantity of a pruchase
 *  -        p: the purchase of the stock
 * 
 * @author  rpaba
 * @see     Purchase
 */
public class Stock implements Serializable {
    
    private int quantity;
    private Purchase p;

    public Stock(int quantity, Purchase p) {
        this.quantity = quantity;
        this.p = p;
    }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Purchase getPurchase() { return p; }

    public void setPurchase(Purchase p) { this.p = p; }  
}
