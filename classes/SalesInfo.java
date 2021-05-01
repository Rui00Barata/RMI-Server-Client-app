/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports_store.classes;

import sports_store.classes.Product;
import java.io.Serializable;

/**
 * Class of info of sales.
 * A SalesInfo has:
 *  -  product: the product that was bought
 *  - quantity: the quantity of the product that was bought
 *
 * @author  rpaba
 * @see     Product
 */
public class SalesInfo implements Serializable {
        
    Product product;
    int quantity;

    // Constructor
    public SalesInfo(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "SalesInfo[ " + "product: " + product + " , quantity: " + quantity + " ]";
    }
}
