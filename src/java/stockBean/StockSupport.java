package stockBean;

import DAO.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import medicineGroup.Bean.MedicineGroupBean;
import medicineList.Bean.MedicineListBean;
import medicine_purchase.OrderBean;

@ManagedBean
@RequestScoped
public class StockSupport {

    String med_name, med_group, ven_name;
    int stock;
    private static final ArrayList<MedStock> orderList = new ArrayList<MedStock>();
    private ArrayList<StockSupport> list = new ArrayList<StockSupport>();

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

    public List<SelectItem> getMedNameCombo() {
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

    public List<SelectItem> getMedGroupCombo() {
        List<SelectItem> total = new ArrayList<SelectItem>();
        try {
            Connection con = (Connection) Database.getConnection();
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery("SELECT med_group_name from  medicine_group");
            while (rs.next()) {
                total.add(new SelectItem(rs.getString("med_group_name")));

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return total;

    }
    public List<SelectItem> getVendorCombo() {
        List<SelectItem> total = new ArrayList<SelectItem>();
        try {
            Connection con = (Connection) Database.getConnection();
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery("SELECT vendor_name from  vendor_list");
            while (rs.next()) {
                total.add(new SelectItem(rs.getString("vendor_name")));

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return total;

    }

    public void selectMedName() {
        int pur_am = 0;
        int sale_am = 0;
        try {
            Connection con = null;
            Statement stm, stm1, stm2 = null;
            ResultSet rs, rs1, rs2 = null;
            con = Database.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT medicine_stock.stock_amount,"
                    + "       medicine_list.med_name,"
                    + "       medicine_group.med_group_name,"
                    + "       vendor_list.vendor_name"
                    + "  FROM    (   (   health_care.medicine_list medicine_list\n"
                    + "  INNER JOIN\n"
                    + "  health_care.vendor_list vendor_list\n"
                    + "  ON (medicine_list.vendor_id = vendor_list.vendor_id))\n"
                    + "  INNER JOIN\n"
                    + "  health_care.medicine_stock medicine_stock\n"
                    + "  ON (medicine_stock.med_id = medicine_list.med_id))\n"
                    + "  INNER JOIN\n"
                    + "  health_care.medicine_group medicine_group\n"
                    + "  ON (medicine_list.med_group_id = medicine_group.med_group_id)"
                    + "where medicine_list.med_name='"+med_name+"'");
            //order by med_group_id desc limit 10
            while (rs.next()) {
                StockSupport stocksupport = new StockSupport();
                stocksupport.setMed_name(rs.getString("med_name"));
                stocksupport.setMed_group(rs.getString("med_group_name"));
                stocksupport.setVen_name(rs.getString("vendor_name"));
                stocksupport.setStock(rs.getInt("stock_amount"));
                list.add(stocksupport);
            }
        } catch (Exception e) {
        }
    }
    
    
    public void selectMedGroup() {
        
        try {
            Connection con = null;
            Statement stm, stm1, stm2 = null;
            ResultSet rs, rs1, rs2 = null;
            con = Database.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT medicine_stock.stock_amount,"
                    + "       medicine_list.med_name,"
                    + "       medicine_group.med_group_name,"
                    + "       vendor_list.vendor_name"
                    + "  FROM    (   (   health_care.medicine_list medicine_list\n"
                    + "  INNER JOIN\n"
                    + "  health_care.vendor_list vendor_list\n"
                    + "  ON (medicine_list.vendor_id = vendor_list.vendor_id))\n"
                    + "  INNER JOIN\n"
                    + "  health_care.medicine_stock medicine_stock\n"
                    + "  ON (medicine_stock.med_id = medicine_list.med_id))\n"
                    + "  INNER JOIN\n"
                    + "  health_care.medicine_group medicine_group\n"
                    + "  ON (medicine_list.med_group_id = medicine_group.med_group_id)"
                    + "where  medicine_group.med_group_name='"+med_group+"'");
            //order by med_group_id desc limit 10
            while (rs.next()) {
                StockSupport stocksupport = new StockSupport();
                stocksupport.setMed_name(rs.getString("med_name"));
                stocksupport.setMed_group(rs.getString("med_group_name"));
                stocksupport.setVen_name(rs.getString("vendor_name"));
                stocksupport.setStock(rs.getInt("stock_amount"));
                list.add(stocksupport);
            }
        } catch (Exception e) {
        }



    }
    public void selectVendor() {
        
        try {
            Connection con = null;
            Statement stm, stm1, stm2 = null;
            ResultSet rs, rs1, rs2 = null;
            con = Database.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT medicine_stock.stock_amount,"
                    + "       medicine_list.med_name,"
                    + "       medicine_group.med_group_name,"
                    + "       vendor_list.vendor_name"
                    + "  FROM    (   (   health_care.medicine_list medicine_list\n"
                    + "  INNER JOIN\n"
                    + "  health_care.vendor_list vendor_list\n"
                    + "  ON (medicine_list.vendor_id = vendor_list.vendor_id))\n"
                    + "  INNER JOIN\n"
                    + "  health_care.medicine_stock medicine_stock\n"
                    + "  ON (medicine_stock.med_id = medicine_list.med_id))\n"
                    + "  INNER JOIN\n"
                    + "  health_care.medicine_group medicine_group\n"
                    + "  ON (medicine_list.med_group_id = medicine_group.med_group_id)"
                    + "where  vendor_list.vendor_name='"+ven_name+"'");
            //order by med_group_id desc limit 10
            while (rs.next()) {
                StockSupport stocksupport = new StockSupport();
                stocksupport.setMed_name(rs.getString("med_name"));
                stocksupport.setMed_group(rs.getString("med_group_name"));
                stocksupport.setVen_name(rs.getString("vendor_name"));
                stocksupport.setStock(rs.getInt("stock_amount"));
                list.add(stocksupport);
            }
        } catch (Exception e) {
        }



    }
     public List<StockSupport> getTableData(){
        return list;
     
     
     }

    public void getAllData() {

        try {
            Connection con = null;
            Statement stm = null;
            ResultSet rs = null;
            con = Database.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT medicine_stock.stock_amount,"
                    + "       medicine_list.med_name,"
                    + "       medicine_group.med_group_name,"
                    + "       vendor_list.vendor_name"
                    + "  FROM    (   (   health_care.medicine_list medicine_list\n"
                    + "  INNER JOIN\n"
                    + "  health_care.vendor_list vendor_list\n"
                    + "  ON (medicine_list.vendor_id = vendor_list.vendor_id))\n"
                    + "  INNER JOIN\n"
                    + "  health_care.medicine_stock medicine_stock\n"
                    + "  ON (medicine_stock.med_id = medicine_list.med_id))\n"
                    + "  INNER JOIN\n"
                    + "  health_care.medicine_group medicine_group\n"
                    + "  ON (medicine_list.med_group_id = medicine_group.med_group_id)");
            
            //where medicine_list.med_name='"+med_name+"'
            while (rs.next()) {
                StockSupport stocksupport = new StockSupport();
                stocksupport.setMed_name(rs.getString("med_name"));
                stocksupport.setMed_group(rs.getString("med_group_name"));
                stocksupport.setVen_name(rs.getString("vendor_name"));
                stocksupport.setStock(rs.getInt("stock_amount"));
                list.add(stocksupport);
            }
        } catch (Exception e) {
        }
        

    }
}
