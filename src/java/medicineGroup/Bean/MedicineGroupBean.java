/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medicineGroup.Bean;

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
 * @author A
 */
@ManagedBean
@SessionScoped
public class MedicineGroupBean implements Serializable{
    private String med_group_name;
    private String med_group_description;
    private int medicineid;
    /**
     * Creates a new instance of MedicineGroupBean
     */
    public MedicineGroupBean() {
    }

    /**
     * @return the med_group_name
     */
    public String getMed_group_name() {
        return med_group_name;
    }

    /**
     * @param med_group_name the med_group_name to set
     */
    public void setMed_group_name(String med_group_name) {
        this.med_group_name = med_group_name;
    }

    /**
     * @return the med_group_description
     */
    public String getMed_group_description() {
        return med_group_description;
    }

    public int getMedicineid() {
        return medicineid;
    }

    public void setMedicineid(int medicineid) {
        this.medicineid = medicineid;
    }
    

    /**
     * @param med_group_description the med_group_description to set
     */
    public void setMed_group_description(String med_group_description) {
        this.med_group_description = med_group_description;
    }
    public void save(){
        String sql="INSERT INTO medicine_group (med_group_name, med_group_description) values (?,?)";
        try {
            Connection con=DAO.Database.getConnection();
            Statement stm=con.createStatement();
            ResultSet rs=null;
            rs = stm.executeQuery("select * from medicine_group where med_group_name='" +getMed_group_name() + "'");
            if (rs.next() == true) {
                System.out.println("Data Already exist");
            } else {
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1, getMed_group_name());
                ps.setString(2, getMed_group_description());
                int value = ps.executeUpdate();
                if (value > 0) {
                    System.out.println("Data is saved");
                }
            }
            
        } catch (Exception e) {
        }
    }
    
    public List<MedicineGroupBean> getAllData(){
        List<MedicineGroupBean> data=new ArrayList<MedicineGroupBean>();
        try {
            Connection con = null;
            Statement stm = null;
            ResultSet rs = null;
            con = Database.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM health_care.medicine_group;");
            //order by med_group_id desc limit 10
            while(rs.next()){
                MedicineGroupBean mgb=new MedicineGroupBean();
                mgb.setMed_group_name(rs.getString("med_group_name"));
                mgb.setMed_group_description(rs.getString("med_group_description"));
                mgb.setMedicineid(rs.getInt("med_group_id"));
                data.add(mgb);
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
            int i=stm.executeUpdate("delete from medicine_group where med_group_id='"+this.medicineid+"'");
            if(i>0){
                System.out.println("ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public void update(){
         
        String sql="UPDATE medicine_group set med_group_name=?, med_group_description=? where med_group_id='"+this.medicineid+"'";
        try {
            Connection con=DAO.Database.getConnection();
            Statement stm=con.createStatement();
            ResultSet rs=null;
            PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1, med_group_name);
                ps.setString(2, med_group_description);
                
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
         return "update_medicine_group";
     }
}
