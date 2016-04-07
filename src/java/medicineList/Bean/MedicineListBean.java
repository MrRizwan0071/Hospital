package medicineList.Bean;

import DAO.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import vendorList.Bean.VendorListBean;

/**
 *
 * @author kaniz
 */
@ManagedBean
@SessionScoped
public class MedicineListBean {

    private int med_id, med_group_id, med_type_id, vendor_id;
    private String med_name, vandor_name, med_type_name, med_group_name;

    public MedicineListBean() {
    }

    public int getMed_id() {
        return med_id;
    }

    public void setMed_id(int med_id) {
        this.med_id = med_id;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public String getVandor_name() {
        return vandor_name;
    }

    public void setVandor_name(String vandor_name) {
        this.vandor_name = vandor_name;
    }

    public String getMed_type_name() {
        return med_type_name;
    }

    public void setMed_type_name(String med_type_name) {
        this.med_type_name = med_type_name;
    }

    public String getMed_group_name() {
        return med_group_name;
    }

    public void setMed_group_name(String med_group_name) {
        this.med_group_name = med_group_name;
    }

    public List<SelectItem> getMedType() {
        List<SelectItem> data = new ArrayList<SelectItem>();
        try {
            Connection con = DAO.Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = null;
            rs = stm.executeQuery("select * from medicine_type");
            while (rs.next()) {
                MedicineListBean mlb = new MedicineListBean();
                data.add(new SelectItem(rs.getString("med_type_name")));

            }
        } catch (Exception e) {
        }
        return data;
    }

    public List<SelectItem> getMedGroup() {
        List<SelectItem> data = new ArrayList<SelectItem>();
        try {
            Connection con = DAO.Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = null;
            rs = stm.executeQuery("select * from medicine_group");
            while (rs.next()) {
                MedicineListBean mlb = new MedicineListBean();
                data.add(new SelectItem(rs.getString("med_group_name")));

            }
        } catch (Exception e) {
        }
        return data;
    }

    public List<SelectItem> getVendor() {
        List<SelectItem> data = new ArrayList<SelectItem>();
        try {
            Connection con = DAO.Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = null;
            rs = stm.executeQuery("select * from vendor_list");
            while (rs.next()) {
                MedicineListBean mlb = new MedicineListBean();
                data.add(new SelectItem(rs.getString("vendor_name")));

            }
        } catch (Exception e) {
        }
        return data;
    }

    public void selectMedType() {
        try {
            Connection con = DAO.Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = null;
            rs = stm.executeQuery("select med_type_id from medicine_type where med_type_name='" + med_type_name + "'");
            while (rs.next()) {
                med_type_id = rs.getInt("med_type_id");

                // System.out.println(med_type_id);
                // System.out.println(getMed_type_name());            
            }
        } catch (Exception e) {
        }
    }

    public void selectMedGroup() {
        try {
            Connection con = DAO.Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = null;
            rs = stm.executeQuery("select med_group_id from medicine_group where med_group_name='" + med_group_name + "'");
            while (rs.next()) {
                med_group_id = rs.getInt("med_group_id");
                //  System.out.println(med_group_id);
                // System.out.println(getMed_group_name());
            }
        } catch (Exception e) {
        }
    }

    public void selectVendor() {
        try {
            Connection con = DAO.Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = null;
            rs = stm.executeQuery("select vendor_id from vendor_list where vendor_name='" + vandor_name + "'");
            while (rs.next()) {
                vendor_id = rs.getInt("vendor_id");

                // System.out.println(vendor_id);
                //System.out.println(getVandor_name());
            }
        } catch (Exception e) {
        }
    }

    public void save() {
        String sql = "INSERT INTO medicine_list (med_group_id, med_type_id,vendor_id, med_name) values (?,?,?,?)";
        try {
            Connection con = DAO.Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = null;
            rs = stm.executeQuery("select * from medicine_list where med_name='" + med_name + "'");
            if (rs.next() == true) {
                 FacesMessage msg1 = new FacesMessage("Data Already exist");
                    FacesContext.getCurrentInstance().addMessage(null, msg1);
                
            } else {
//                PreparedStatement ps = con.prepareStatement(sql);
//                ps.setInt(1, med_group_id);
//                ps.setInt(2, med_type_id);
//                ps.setInt(3, vendor_id);
//                ps.setString(4, med_name);
                
                int value = stm.executeUpdate("INSERT INTO medicine_list "
                        + "(med_group_id, med_type_id,vendor_id, med_name) "
                        + "values ((select med_group_id from medicine_group "
                        + "where med_group_name='"+med_group_name+"'),"
                        + "(select med_type_id from medicine_type"
                        + " where med_type_name='"+med_type_name+"'),"
                        + "(select vendor_id from vendor_list"
                        + " where vendor_name='"+vandor_name+"'), '"+med_name+"')");
                if (value > 0) {
                     FacesMessage msg1 = new FacesMessage("medicine added");
                    FacesContext.getCurrentInstance().addMessage(null, msg1);
                    clear();
                }
            }

        } catch (Exception e) {
        }
    }

    public List<MedicineListBean> getAllData() {
        List<MedicineListBean> data = new ArrayList<MedicineListBean>();
        try {
            Connection con = null;
            Statement stm = null;
            ResultSet rs = null;
            con = Database.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT mt.med_type_name,\n"
                    + "       mg.med_group_name,\n"
                    + "       vl.vendor_name,\n"
                    + "       ml.med_name,ml.med_id\n"
                    + "  FROM    (   ( medicine_list ml\n"
                    + "               INNER JOIN\n"
                    + "                  medicine_type mt\n"
                    + "               ON (ml.med_type_id = mt.med_type_id))\n"
                    + "           INNER JOIN\n"
                    + "               vendor_list vl\n"
                    + "           ON (ml.vendor_id = vl.vendor_id))\n"
                    + "       INNER JOIN\n"
                    + "           medicine_group mg\n"
                    + "       ON (ml.med_group_id = mg.med_group_id)");
            while (rs.next()) {
                MedicineListBean mlb = new MedicineListBean();
                mlb.setMed_type_name(rs.getString("med_type_name"));
                mlb.setMed_group_name(rs.getString("med_group_name"));
                mlb.setVandor_name(rs.getString("vendor_name"));
                mlb.setMed_name(rs.getString("med_name"));
                mlb.setMed_id(rs.getInt("med_id"));
                data.add(mlb);
            }
        } catch (Exception e) {
        }
        return data;

    }

    public void deleterow() {
        try {
            Connection con = null;
            Statement stm = null;
            ResultSet rs = null;
            con = Database.getConnection();
            stm = con.createStatement();
            int i = stm.executeUpdate("delete from medicine_list where med_id='" + this.med_id + "'");
            if (i > 0) {
                System.out.println("Data is deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        String sql = "UPDATE medicine_list set med_type_id=?, med_group_id=?,vendor_id=?,med_name=? where med_id='" + this.med_id + "'";
        try {
            Connection con = DAO.Database.getConnection();
            Statement stm = con.createStatement();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, med_type_id);
            ps.setInt(2, med_group_id);
            ps.setInt(3, vendor_id);
            ps.setString(4, med_name);
            int value = ps.executeUpdate();
            //System.out.println("ok1");
            if (value > 0) {
                /// JOptionPane.showMessageDialog(null, "Data is Updated");
                System.out.println("Data Updated");
            }
        } catch (Exception e) {
        }
    }

    public String goUpdate() {
        return "update_medicine_list";
    }

    public void clear() {
        med_id = 0;
        med_group_id = 0;
        med_type_id = 0;
        vendor_id = 0;
        med_name = "";
        vandor_name = "";
        med_type_name = "";
        med_group_name = "";

    }
}