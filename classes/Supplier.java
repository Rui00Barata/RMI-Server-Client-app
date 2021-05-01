/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports_store.classes;

import java.io.Serializable;

/**
 * Class of a supplier.
 * A supplier has:
 *  - name: the name of the a supplier
 *  -   nc: "numero de contribuinte" of a supplier
 * 
 * @author rpaba
 */
public class Supplier implements Serializable {
    
    private String name;
    private String nc;

    public Supplier(String nome, String nc) {
        this.name = nome;
        this.nc = nc;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getNc() { return nc; }

    public void setNc(String nc) { this.nc = nc; }  

    @Override
    public String toString() {
        return "Fornecedor[ " + 
                "nome: " + this.getName() + " , " + 
                "numero de contribuinte: " + this.getNc() + " ]";
    }
    
    
}
