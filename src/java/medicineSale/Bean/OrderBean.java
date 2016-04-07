package medicineSale.Bean;

import java.io.Serializable;
import java.util.Date;

public class OrderBean implements Serializable {

    private static final long serialVersionUID = 1L;
    Date s_date;
    int quan, med_sale_id, pre_qty,med_quantity;
    Double amount, total;
    String med_name, med_type, med_group, refer, vendor_name;

    public int getMed_quantity() {
        return med_quantity;
    }

    public void setMed_quantity(int med_quantity) {
        this.med_quantity = med_quantity;
    }

    public int getPre_qty() {
        return pre_qty;
    }

    public void setPre_qty(int pre_qty) {
        this.pre_qty = pre_qty;
    }
    

    public OrderBean(String med_name, String med_group, String med_type, String vendor_name, int quan, Double amount, String refer, Date s_date, Double total, int pre_qty) {
        this.med_name = med_name;
        this.med_group = med_group;
        this.med_type = med_type;
        this.vendor_name = vendor_name;
        this.quan = quan;
        this.amount = amount;
        this.refer = refer;
        this.s_date = s_date;
        this.total = total;
        this.med_quantity = pre_qty;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public Double getTotal() {
        return (quan * amount);
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMed_group() {
        return med_group;
    }

    public void setMed_group(String med_group) {
        this.med_group = med_group;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public int getMed_sale_id() {
        return med_sale_id;
    }

    public void setMed_sale_id(int med_sale_id) {
        this.med_sale_id = med_sale_id;
    }

    public String getMed_type() {
        return med_type;
    }

    public void setMed_type(String med_type) {
        this.med_type = med_type;
    }

    public int getQuan() {
        return quan;
    }

    public void setQuan(int quan) {
        this.quan = quan;
    }

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }

    public Date getS_date() {
        return s_date;
    }

    public void setS_date(Date s_date) {
        this.s_date = s_date;
    }
}
