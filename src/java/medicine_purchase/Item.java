package medicine_purchase;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.Database;
import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author prima
 */
@ManagedBean
@RequestScoped
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    private String med_name, group_name, vendor_name, med_type_name, ref;
    private int qty, med_quantity;
    private Double price;
    private Date purchasedate;
    Date date, date1;
    OrderBean order;
    String medname, medgroup, vendorname;
    Double unitp, totalp = 0.0, totaltotalp = 0.0;
    Integer quantity, med_id, med_group_id, vendor_id, purchase_id;
    int total;
    int med_amount;

    /**
     * Creates a new instance of Item
     */
    public Item() {
        med_name = "--Select--";
        vendor_name = "--Select--";
        med_type_name = "--Select--";
        date1 = new Date();
        date = new Date();
    }

    public Double getTotalp() {
        return totalp;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public void setTotalp(Double totalp) {
        this.totalp = totalp;
    }

    public Double getTotaltotalp() {
        return totaltotalp;
    }

    public void setTotaltotalp(Double totaltotalp) {
        this.totaltotalp = totaltotalp;
    }

    public int getMed_quantity() {
        return med_quantity;
    }

    public void setMed_quantity(int med_quantity) {
        this.med_quantity = med_quantity;
    }

    public String getMed_type_name() {
        return med_type_name;
    }

    public void setMed_type_name(String med_type_name) {
        this.med_type_name = med_type_name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }
    private static final ArrayList<OrderBean> orderList = new ArrayList<OrderBean>();
    private ArrayList list = new ArrayList();

    public ArrayList<OrderBean> getOrderList() {
        return orderList;
    }

    public List<SelectItem> getMedicineType() {
        List<SelectItem> name = new ArrayList<SelectItem>();
        try {
            Connection con = Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select med_type_name from medicine_type");
            while (rs.next()) {
                name.add(new SelectItem(rs.getString("med_type_name")));
            }


        } catch (Exception e) {
        }

        return name;

    }

    public List<SelectItem> getComBobox() {
        List<SelectItem> total = new ArrayList<SelectItem>();
        try {
            Connection con = (Connection) Database.getConnection();
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery("select med_name from medicine_list");
            while (rs.next()) {
                total.add(new SelectItem(rs.getString("med_name")));

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return total;

    }

    public List<SelectItem> getVendorComboBox() {
        List<SelectItem> data = new ArrayList<SelectItem>();
        String sql = "SELECT vendor_name from vendor_list";
        try {
            Statement st = Database.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                data.add(new SelectItem(rs.getString("vendor_name")));

            }
        } catch (Exception e) {
        }
        return data;
    }

    public void selctinfo() {

        Connection con = Database.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT medicine_stock.stock_amount,\n"
                    + "       medicine_list.med_name,\n"
                    + "       medicine_group.med_group_name,\n"
                    + "       vendor_list.vendor_name,\n"
                    + "       medicine_type.med_type_name\n"
                    + "  FROM    (   (   (   health_care.medicine_list medicine_list\n"
                    + "                   INNER JOIN\n"
                    + "                      health_care.vendor_list vendor_list\n"
                    + "                   ON (medicine_list.vendor_id = vendor_list.vendor_id))\n"
                    + "               INNER JOIN\n"
                    + "                  health_care.medicine_stock medicine_stock\n"
                    + "               ON (medicine_stock.med_id = medicine_list.med_id))\n"
                    + "           INNER JOIN\n"
                    + "              health_care.medicine_group medicine_group\n"
                    + "           ON (medicine_list.med_group_id = medicine_group.med_group_id))\n"
                    + "       INNER JOIN\n"
                    + "          health_care.medicine_type medicine_type\n"
                    + "       ON (medicine_list.med_type_id = medicine_type.med_type_id) "
                    + "where med_name='" + med_name + "'");
            while (rs.next()) {
                this.setGroup_name(rs.getString("med_group_name"));

                this.setMed_quantity(rs.getInt("stock_amount"));

            }

        } catch (Exception e) {
        }
    }

    public String addAction() {
        OrderBean orderitem = new OrderBean(this.med_name, this.group_name, this.vendor_name, this.qty, this.price, this.totalp);
        orderList.add(orderitem);
        //totaltotalp=totalp+=totalp;
        //System.out.println(totalp);
        //System.out.println(totaltotalp);
        allTotal();
        med_name = "";
        vendor_name = "";
        group_name = "";

        qty = 0;
        price = 0.0;
        //totalp=qty*price ;
        return null;
    }

    public void onEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Item Edited", ((OrderBean) event.getObject()).getMed_name());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        allTotal();
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Item Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        orderList.remove((OrderBean) event.getObject());
        allTotal();
    }

    public void allTotal() {
        double sum = 0;
        for (int i = 0; i < orderList.size(); i++) {
            sum += orderList.get(i).getTotalp();
        }
        totaltotalp = sum;

    }

    public void saveItems() throws SQLException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dt = df.format(date1);
        allTotal();
        String sql = "insert into medicine_purchase( med_pur_date, med_pur_amount, med_pur_ref) VALUES('" + dt + "','" + totaltotalp + "','" + ref + "');";
        Statement st, st2, st3, st4;
        ResultSet rs, rs2, rs3, rs4;

        //Connection con = Database.getConnection();
        st = Database.getConnection().createStatement();
        st2 = Database.getConnection().createStatement();
        st3 = Database.getConnection().createStatement();
        st4 = Database.getConnection().createStatement();
        int x = st.executeUpdate(sql);
        // System.out.println(x);
        if (x > 0) {
            //String selectId = "SELECT MAX(last_insert_id(med_pur_id)) FROM medicine_purchase";
            st = Database.getConnection().createStatement();

            rs = st.executeQuery("SELECT MAX(last_insert_id(med_pur_id)) FROM medicine_purchase");
            while (rs.next()) {
                int xy = 0;
                purchase_id = rs.getInt(1);
                //System.out.println(purchase_id);
                for (int i = 0; i < orderList.size(); i++) {
                    medname = orderList.get(i).getMed_name();
                    medgroup = orderList.get(i).getGroup_name();
                    vendorname = orderList.get(i).getVendor_name();
                    quantity = orderList.get(i).getQty();
                    unitp = orderList.get(i).getPrice();

                    xy = st2.executeUpdate("insert into medicine_purchase_line "
                            + " (med_pur_id, med_id, med_quantity, vendor_id, med_pur_price) values"
                            + " ('" + purchase_id + "',(select med_id from medicine_list "
                            + "where med_name='" + medname + "'),"
                            + "'" + quantity + "',(select vendor_id from vendor_list where "
                            + "vendor_name ='" + vendorname + "'),'" + unitp + "')");


                    if (xy > 0) {
                        rs2 = st3.executeQuery("select stock_amount from medicine_stock "
                                + "where med_id =(select med_id from medicine_list "
                                + "where med_name='" + medname + "' )");
                        while (rs2.next()) {
                            med_amount = rs2.getInt("stock_amount");
                            System.out.println(med_amount + "***************");

                        }
                        if (med_amount > 0) {
                            med_amount += quantity;
                            System.out.println(med_amount + "***************");
                            st3.executeUpdate("update medicine_stock "
                                    + "set stock_amount ='" + med_amount + "' "
                                    + "where med_id=(select med_id from medicine_list "
                                    + "where med_name='" + medname + "' )");
                            med_amount = 0;
                            System.out.println(med_name);
                        } else {

                            st3.executeUpdate("insert into medicine_stock "
                                    + "( med_pur_id, med_id, stock_amount) "
                                    + "values ('" + purchase_id + "',"
                                    + "(select med_id from medicine_list "
                                    + "where med_name='" + medname + "'),'" + quantity + "')");
                            System.out.println(medname);
                        }

                    } else {
                        FacesMessage msg1 = new FacesMessage("Purchase Failed");
                        FacesContext.getCurrentInstance().addMessage(null, msg1);

                    }
                }
                FacesMessage msg1 = new FacesMessage("Purchase Items are saved");
                FacesContext.getCurrentInstance().addMessage(null, msg1);
            }


            ////
        }
        // Database.close(con);
       clear();
    }

    public void clear() {
        
        med_name = "";
        group_name = "";
        med_type_name = "";
        qty = 0;
        price = 0.0;
        ref = "";
        vendor_name="";
        med_quantity = 0;
        totaltotalp = 0.0;
        orderList.clear();
        //orderList.clear();


    }

    public void inser3rd() throws SQLException {
        Statement st;

        st = Database.getConnection().createStatement();
        for (int i = 0; i < orderList.size(); i++) {
            medname = orderList.get(i).getMed_name();
            medgroup = orderList.get(i).getGroup_name();
            vendorname = orderList.get(i).getVendor_name();
            quantity = orderList.get(i).getQty();
            unitp = orderList.get(i).getPrice();


            ResultSet rs1 = st.executeQuery("select med_id from medicine_stock where med_id=(select med_id from medicine_list where med_name='" + medname + "')");
            if (rs1.next()) {
                int amount = rs1.getInt(3);

                int pamount = quantity;
                total = amount + pamount;
                st.executeUpdate("update medicine_stock set stock_amount='" + total + "' where med_id=(select med_id from medicine_list where med_name='" + medname + "')");

            } else {
                st.executeUpdate("insert into medicine_stock (med_id, stock_amount) values ((select med_id from medicine_list where med_name='" + medname + "'),'" + total + "')");
            }

//                        int stock=st.executeUpdate("insert into medicine_stock (med_pur_id, med_id, stock_amount) values"
//                            + "('" + purchase_id + "',(select med_id from medicine_purchase_line"
//                            + " where med_name='" + medname + "'),'" + quantity + "'");

        }

    }
}
