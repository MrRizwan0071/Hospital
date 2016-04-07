package medicine_purchase;


import java.io.Serializable;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author prima
 */
public class OrderBean implements Serializable{
     private static final long serialVersionUID = 1L;
     private String med_name,group_name,vendor_name,ref ;
     private Integer qty;
     private Double price, totalp=0.0;
     Date date,date1;

    public OrderBean(String med_name, String group_name, String vendor_name, Integer qty, Double price,Double totalp) {
        this.med_name = med_name;
        this.group_name = group_name;
        this.vendor_name = vendor_name;
        this.qty = qty;
        this.price = price;
        this.totalp=totalp;
       
    }

    public Double getTotalp() {
        totalp=qty*price;
        return totalp;
    }

    public void setTotalp(Double totalp) {
        this.totalp = totalp;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Date getDate() {
        date=new Date();
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   
    
    public String getMed_name() {
        return med_name;
    }

    /**
     * @param med_name the med_name to set
     */
    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    /**
     * @return the group_name
     */
    public String getGroup_name() {
        return group_name;
    }

    /**
     * @param group_name the group_name to set
     */
    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    /**
     * @return the vendor_name
     */
    public String getVendor_name() {
        return vendor_name;
    }

    /**
     * @param vendor_name the vendor_name to set
     */
    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    /**
     * @return the qty
     */
    public Integer getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }
     
    
    
    
}
