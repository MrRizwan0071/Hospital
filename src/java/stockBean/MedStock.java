/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockBean;

/**
 *
 * @author NAHID
 */
public class MedStock {
    String med_name,med_group,ven_name;
    int stock;
     public MedStock(String med_name, String group_name, String vendor_name, Integer qty, Double price,Double totalp) {
        this.med_name = med_name;
        this.med_group = group_name;
        this.ven_name = vendor_name;
        this.stock = qty;
        
       
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public String getMed_group() {
        return med_group;
    }

    public void setMed_group(String med_group) {
        this.med_group = med_group;
    }

    public String getVen_name() {
        return ven_name;
    }

    public void setVen_name(String ven_name) {
        this.ven_name = ven_name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
    
    
    
    
}
