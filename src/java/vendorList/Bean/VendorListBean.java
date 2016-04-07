/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vendorList.Bean;

import DAO.Database;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.swing.JOptionPane;

/**
 *
 * @author kaniz
 */
@ManagedBean
@SessionScoped
public class VendorListBean {
private String vendor_name, vendor_address, vendor_email, vendor_mobile, vendor_comp_name;
private int vendor_id;
    /**
     * Creates a new instance of VendorListBean
     */
    public VendorListBean() {
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
     * @return the vendor_address
     */
    public String getVendor_address() {
        return vendor_address;
    }

    /**
     * @param vendor_address the vendor_address to set
     */
    public void setVendor_address(String vendor_address) {
        this.vendor_address = vendor_address;
    }

    /**
     * @return the vendor_email
     */
    public String getVendor_email() {
        return vendor_email;
    }

    /**
     * @param vendor_email the vendor_email to set
     */
    public void setVendor_email(String vendor_email) {
        this.vendor_email = vendor_email;
    }

    /**
     * @return the vendor_mobile
     */
    public String getVendor_mobile() {
        return vendor_mobile;
    }

    /**
     * @param vendor_mobile the vendor_mobile to set
     */
    public void setVendor_mobile(String vendor_mobile) {
        this.vendor_mobile = vendor_mobile;
    }

    /**
     * @return the vendor_comp_name
     */
    public String getVendor_comp_name() {
        return vendor_comp_name;
    }

    /**
     * @param vendor_comp_name the vendor_comp_name to set
     */
    public void setVendor_comp_name(String vendor_comp_name) {
        this.vendor_comp_name = vendor_comp_name;
    }

    /**
     * @return the vendor_id
     */
    public int getVendor_id() {
        return vendor_id;
    }

    /**
     * @param vendor_id the vendor_id to set
     */
    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }
    
     public void save(){
       String sql="Insert Into vendor_list (vendor_name, vendor_address, vendor_email, vendor_mobile, vendor_comp_name) values(?,?,?,?,?)";
       try {
           Connection con=DAO.Database.getConnection();
           Statement stm=con.createStatement();
           ResultSet rs=null;
           rs=stm.executeQuery("select * from vendor_list where vendor_name='"+getVendor_name()+"'");
           if(rs.next()==true){
               System.out.println("Data already Exist");
           }else{
              PreparedStatement ps=con.prepareStatement(sql);
              ps.setString(1, getVendor_name());
              ps.setString(2, getVendor_address());
              ps.setString(3, getVendor_email());
              ps.setString(4, getVendor_mobile());
              ps.setString(5, getVendor_comp_name());
              int value=ps.executeUpdate();
              if(value>0){
                  System.out.println("Data is Saved");
              }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
     
     public List<VendorListBean> getAllData(){
        List<VendorListBean> data=new ArrayList<VendorListBean>();
        try {
            Connection con = null;
            Statement stm = null;
            ResultSet rs = null;
            con = Database.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM health_care.vendor_list;");
            //order by test_id desc limit 10;
            while(rs.next()){
                VendorListBean vlb=new VendorListBean();
                vlb.setVendor_name(rs.getString("vendor_name"));
                vlb.setVendor_address(rs.getString("vendor_address"));
                vlb.setVendor_email(rs.getString("vendor_email"));
                vlb.setVendor_mobile(rs.getString("vendor_mobile"));
                vlb.setVendor_comp_name(rs.getString("vendor_comp_name"));
                vlb.setVendor_id(rs.getInt("vendor_id"));
                data.add(vlb);
            }
        } catch (Exception e) {
        }
        return data;
    }
     
     public void deleterow(){
        try {
             Connection con = null;
            Statement stm = null;
            ResultSet rs = null;
            con = Database.getConnection();
            stm = con.createStatement();
            int i=stm.executeUpdate("delete from vendor_list where vendor_id='"+this.vendor_id+"'");
            if(i>0){
                System.out.println("Data is Deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     public void update(){   
        String sql="UPDATE vendor_list set vendor_name=?, vendor_address=?,vendor_email=?,vendor_mobile=?, vendor_comp_name=? where vendor_id='"+this.vendor_id+"'";
        try {
            Connection con=DAO.Database.getConnection();
            Statement stm=con.createStatement();
            PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1, vendor_name); 
                ps.setString(2, vendor_address); 
                ps.setString(3, vendor_email); 
                ps.setString(4, vendor_mobile); 
                ps.setString(5, vendor_comp_name); 
                int value = ps.executeUpdate();
                //System.out.println("ok1");
                if (value > 0) {
                  /// JOptionPane.showMessageDialog(null, "Data is Updated");
                    System.out.println("Data Updated");
                }
        } catch (Exception e) {
        }
    }
     public String goUpdate(){
         return "update_vendor_list";
     }
}
