/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medicineType.Bean;

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
public class MedicineTypeBean implements Serializable{
private String med_type_name;
private int med_type_id;
    /**
     * Creates a new instance of MedicineTypeBean
     */
    public MedicineTypeBean() {
    }

    public String getMed_type_name() {
        return med_type_name;
    }

    public void setMed_type_name(String med_type_name) {
        this.med_type_name = med_type_name;
    }

    public int getMed_type_id() {
        return med_type_id;
    }

    public void setMed_type_id(int med_type_id) {
        this.med_type_id = med_type_id;
    }
   public void save(){
       String sql="Insert Into medicine_type (med_type_name) values(?)";
       try {
           Connection con=DAO.Database.getConnection();
           Statement stm=con.createStatement();
           ResultSet rs=null;
           rs=stm.executeQuery("select * from medicine_type where med_type_name='"+getMed_type_name()+"'");
           if(rs.next()==true){
               System.out.println("Data already Exist");
           }else{
              PreparedStatement ps=con.prepareStatement(sql);
              ps.setString(1, getMed_type_name());
              int value=ps.executeUpdate();
              if(value>0){
                  System.out.println("Data is Saved");
              }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   
   public List<MedicineTypeBean> getAllData(){
        List<MedicineTypeBean> data=new ArrayList<MedicineTypeBean>();
        try {
            Connection con = null;
            Statement stm = null;
            ResultSet rs = null;
            con = Database.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM health_care.medicine_type;");
            //order by med_type_id desc limit 10;
            while(rs.next()){
                MedicineTypeBean mtb=new MedicineTypeBean();
                mtb.setMed_type_name(rs.getString("med_type_name"));
                
                mtb.setMed_type_id(rs.getInt("med_type_id"));
                data.add(mtb);
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
            int i=stm.executeUpdate("delete from medicine_type where med_type_id='"+this.med_type_id+"'");
            if(i>0){
                System.out.println("Data is Deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public void update(){   
        String sql="UPDATE medicine_type set med_type_name=? where med_type_id='"+this.med_type_id+"'";
        try {
            Connection con=DAO.Database.getConnection();
            Statement stm=con.createStatement();
            PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1, med_type_name); 
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
         return "update_medicine_type";
     }
}
