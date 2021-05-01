/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports_store.classes;

import sports_store.classes.Product;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class of a purchase.
 * A purchase has:
 *  -       supplier: the supplier that sold the product to the company
 *  - expirationDate: date that the product expires (only applicable to category "Alimentos" of a product)
 *  -          price: the price that the company had to pay for the purchase
 *  -       quantity: the quantity of the product that was bought in this purchase
 *  -        product: the product that was bought
 *  -           date: the date that the purchase was made
 * 
 * @author  rpaba
 * @see     Supplier
 * @see     LocalDate
 * @see     Product
 */
public class Purchase implements Serializable {
    
    private Supplier supplier;
    private LocalDate expirationDate;
    private double price;
    private int quantity;
    private Product product;
    private LocalDate date;

    public Purchase(Supplier supplier, LocalDate expirationDate, double price, int quantity, Product product) {
        this.supplier = supplier;
        this.expirationDate = expirationDate;
        this.price = price;
        this.quantity = quantity;
        this.product = product;
        
        date = LocalDate.now();
    }

    public Supplier getSupplier() { return supplier; }

    public void setSupplier(Supplier supplier) { this.supplier = supplier; }

    public LocalDate getExpirationDate() { return expirationDate; }

    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; } 

    @Override
    public String toString() {
        return "Purchase[ " + "supplier: " + supplier + " , expirationDate: " + expirationDate + " , price: " + price + " , quantity: " + quantity + " , product: " + product.toString() + " , date: " + date + " ]";
    }
    
    
}
