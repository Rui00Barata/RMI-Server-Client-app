/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports_store.classes;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class of a product.
 * A product has:
 *  -      category: the category of the product
 *  -          name: the name of the product
 *  -         sales: the number of sales that the product has been sold
 *  - selling_price: the price that the store sells the product
 *  - minimum_stock: the minimum stock that the store needs to have of the product
 *
 * @author rpaba
 */
public class Product implements Serializable {
    
    private String category;
    private String name;
    private int sales;
    private double selling_price;
    private int minimum_stock;

    // Constructor
    public Product(String category, String name, Double selling_price, int minimum_stock) {
        this.category = category;
        this.name = name;
        this.minimum_stock = minimum_stock;
        this.selling_price = selling_price;
        sales = 0;
    }

    // Getters and Setters
    public int getSales() { return sales; }
    public void setSales(int sales) { this.sales = sales; }
    public void increaseSales(int quantity) { sales += quantity; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getSelling_price() { return selling_price; }
    public void setSelling_price(double selling_price) { this.selling_price = selling_price; }
    public int getMinimum_stock() { return minimum_stock; }
    public void setMinimum_stock(int minimum_stock) { this.minimum_stock = minimum_stock; }

    @Override
    public String toString() {
        return "Product[ " + "category: " + category + " , name: " + name + " , selling_price: " + selling_price + " , minimum_stock: " + minimum_stock + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        
        if (obj == null)
            return false;
        
        if (getClass() != obj.getClass())
            return false;
        
        
        final Product other = (Product) obj;
        if (Double.doubleToLongBits(this.selling_price) != Double.doubleToLongBits(other.selling_price)) {
            return false;
        }
        if (this.minimum_stock != other.minimum_stock) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }  
}
