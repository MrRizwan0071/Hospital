package medicineSale.Bean;

import DAO.Database;
import java.awt.Component;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.primefaces.event.RowEditEvent;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

@ManagedBean
@SessionScoped
public class insert implements Serializable {

    Date s_date;
    int quan, med_sale_line_id, med_sale_id, med_id, med_quantity, vendor_id, pre_qty;
    String med_name, med_type, med_group, refer, vendor_name;
    Double amount, total, totalp = 0.0;
    private Component rootPane;
    OrderBean order;
    List<insert> data = new ArrayList<insert>();

    public insert() {
        med_name = "--Select One--";
        this.s_date = new Date();

    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public Double getTotal() {
        return total;
    }

    public Double getTotalp() {
        return totalp;
    }

    public void setTotalp(Double totalp) {
        this.totalp = totalp;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
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

    public int getMed_quantity() {
        return med_quantity;
    }

    public void setMed_quantity(int med_quantity) {
        this.med_quantity = med_quantity;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
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

    public Component getRootPane() {
        return rootPane;
    }

    public void setRootPane(Component rootPane) {
        this.rootPane = rootPane;
    }

    public Date getS_date() {
        return s_date;
    }

    public void setS_date(Date s_date) {
        this.s_date = s_date;
    }

    public List<SelectItem> combo() {
        List<SelectItem> data = new ArrayList<SelectItem>();

        try {
            Connection con = DAO.Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery("select med_name from medicine_list");
            while (rs.next()) {
                data.add(new SelectItem(rs.getString("med_name")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public void omboGro() {


        try {
            Connection con = DAO.Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery("SELECT medicine_stock.stock_amount,\n"
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

                this.setMed_group(rs.getString("med_group_name"));
                this.setMed_type(rs.getString("med_type_name"));
                this.setVendor_name(rs.getString("vendor_name"));
                this.setMed_quantity(rs.getInt("stock_amount"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return data;
    }

    public List<SelectItem> ComboType() {
        List<SelectItem> data = new ArrayList<SelectItem>();

        try {
            Connection con = DAO.Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery("select * from medicine_type");

            while (rs.next()) {
                insert in = new insert();
                data.add(new SelectItem(rs.getString("med_type_name")));
            }
        } catch (Exception e) {
        }
        return data;
    }

    public List<SelectItem> vendor() {
        List<SelectItem> data = new ArrayList<SelectItem>();
        Statement st, st1, st2, st3;
        ResultSet rs, rs2, rs3, rs4;
        try {
            Connection con = DAO.Database.getConnection();
            st = con.createStatement();
            rs = null;
            rs = st.executeQuery("select * from vendor_list");

            while (rs.next()) {
                insert in = new insert();
                data.add(new SelectItem(rs.getString("vendor_name")));
            }
        } catch (Exception e) {
        }
        return data;
    }

    public void save() {
        Statement st, st1, st2, st3, st5;
        ResultSet rs, rs2, rs3, rs4;

        try {
            Connection con = DAO.Database.getConnection();
            allTotal();

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String bd = df.format(getS_date());
            st = con.createStatement();
            st2 = con.createStatement();
            st3 = con.createStatement();
            st1 = con.createStatement();
            st5 = con.createStatement();
//            rs2 = st.executeQuery("SELECT medicine_stock.stock_amount, medicine_list.med_name\n"
//                    + "  FROM    health_care.medicine_stock medicine_stock\n"
//                    + "       INNER JOIN\n"
//                    + "          health_care.medicine_list medicine_list\n"
//                    + "       ON (medicine_stock.med_id = medicine_list.med_id) where med_name='" + med_name + "'");
//            
//
//            while(rs2.next()){
//            pre_qty=rs2.getInt("stock_amount");
//                System.out.println("*********"+pre_qty);
//            }
//            if (pre_qty<med_quantity) {
//                FacesMessage msg1 = new FacesMessage("No available Medicine");
//                    FacesContext.getCurrentInstance().addMessage(null, msg1);
//            } else {

            st1 = con.createStatement();
            st1.executeUpdate("insert into medicine_sale"
                    + "(med_sale_date,med_sale_amount, med_sale_ref)"
                    + "VALUES('" + bd + "','" + totalp + "','" + refer + "')");

            st = con.createStatement();
            rs = st.executeQuery("SELECT MAX(last_insert_id(med_sale_id)) FROM medicine_sale");
            while (rs.next()) {
                int y = 0;
                int sale_id = rs.getInt(1);
                for (int i = 0; i < orderList.size(); i++) {
                    med_name = orderList.get(i).getMed_name();
                    med_group = orderList.get(i).getMed_group();
                    refer = orderList.get(i).getRefer();
                    vendor_name = orderList.get(i).getVendor_name();
                    quan = orderList.get(i).getQuan();
                    total = orderList.get(i).getTotal();
                    med_quantity=orderList.get(i).getMed_quantity();
                    System.out.println(med_quantity+"**");
                    y = st2.executeUpdate("insert into medicine_sale_line(med_sale_id, med_id, med_quantity, vendor_id, med_sale_price) "
                            + "VALUES('" + sale_id + "',(select med_id from medicine_list where med_name='" + med_name + "'),'"
                            + quan + "',(select vendor_id from vendor_list where vendor_name='"
                            + vendor_name + "'),'" + total + "')");
                    st3.executeUpdate("insert into medicine_sale(med_sale_ref,med_sale_amount)values('" + refer + "','" + totalp + "')");

                
                if (y > 0) {
                      
                    med_quantity -= quan;
                    System.out.println(med_quantity+"***********");
                    st3.executeUpdate("update medicine_stock "
                                + "set stock_amount ='" +med_quantity+ "' "
                                + "where med_id=(select med_id from medicine_list "
                                + "where med_name='" +med_name+ "' )");
                        


                    FacesMessage msg1 = new FacesMessage("Medicine sold Successfully!!");
                    FacesContext.getCurrentInstance().addMessage(null, msg1);
                    
                }
                }
//                 FacesMessage msg1 = new FacesMessage("Medicine sold failed!!");
//                    FacesContext.getCurrentInstance().addMessage(null, msg1);

            }
            // }
        } catch (SQLException t) {
            t.printStackTrace();
        }
        clear();

    }
    private static final ArrayList<OrderBean> orderList = new ArrayList<OrderBean>();

    public ArrayList<OrderBean> getOrderList() {
        return orderList;
    }

    public String addAction() {
         if (quan>med_quantity) {
             FacesMessage msg1 = new FacesMessage("No available stock!! ");
                    FacesContext.getCurrentInstance().addMessage(null, msg1);
        } else {
        OrderBean orderitem = new OrderBean(this.med_name, this.med_group, this.med_type, this.vendor_name, this.quan, this.amount, this.refer, this.s_date, this.total,this.med_quantity);
        orderList.add(orderitem);
        allTotal();

        med_name = "";
        med_group = "";
        med_type = "";
        quan = 0;
        amount = 0.0;
        refer = "";
        vendor_name="";
        med_quantity = 0;
       
         }
        return null;
    }

    public void clear() {
        med_name = "";
        med_group = "";
        med_type = "";
        quan = 0;
        amount = 0.0;
        refer = "";
        vendor_name="";
        med_quantity = 0;
        totalp = 0.0;
        orderList.clear();

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
            sum += orderList.get(i).getTotal();

        }
        totalp = sum;
    }
}