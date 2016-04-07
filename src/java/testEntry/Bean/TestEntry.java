/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testEntry.Bean;

import DAO.Database;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.swing.JOptionPane;

/**
 *
 * @author kaniz
 */
@ManagedBean
@SessionScoped
public class TestEntry {
private String test_name, test_type, test_cost;
private int test_id;
    /**
     * Creates a new instance of VendorListBean
     */
    public TestEntry() {
    }

    /**
     * @return the vendor_name
     */
    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getTest_type() {
        return test_type;
    }

    public void setTest_type(String test_type) {
        this.test_type = test_type;
    }

    public String getTest_cost() {
        return test_cost;
    }

    public void setTest_cost(String test_cost) {
        this.test_cost = test_cost;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

  
    
     public void save(){
       String sql="Insert Into test (test_name, test_type, test_cost) values(?,?,?)";
       try {
           Connection con=DAO.Database.getConnection();
           Statement stm=con.createStatement();
           ResultSet rs=null;
           rs=stm.executeQuery("select * from test where test_name='"+getTest_type()+"'");
           if(rs.next()==true){
              FacesMessage msg1 = new FacesMessage("User name already exist");
                FacesContext.getCurrentInstance().addMessage(null, msg1);
           }else{
              PreparedStatement ps=con.prepareStatement(sql);
              ps.setString(1, getTest_name());
              ps.setString(2, getTest_type());
              ps.setString(3, getTest_cost());
              int value=ps.executeUpdate();
              if(value>0){
                  System.out.println("Data is Saved");
              }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
     
     public List<TestEntry> getAllData(){
        List<TestEntry> data=new ArrayList<TestEntry>();
        try {
            Connection con = null;
            Statement stm = null;
            ResultSet rs = null;
            con = Database.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM health_care.test;");
            //order by test_id desc limit 10;
            while(rs.next()){
                TestEntry vlb=new TestEntry();
                vlb.setTest_name(rs.getString("test_name"));
                vlb.setTest_type(rs.getString("test_type"));
                vlb.setTest_cost(rs.getString("test_cost"));
                vlb.setTest_id(rs.getInt("test_id"));
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
            int i=stm.executeUpdate("delete from test where test_id='"+this.test_id+"'");
            if(i>0){
                System.out.println("Data is Deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     public void update(){   
        String sql="UPDATE test set test_name=?, test_type=?, test_cost=? where test_id='"+this.test_id+"'";
        try {
            Connection con=DAO.Database.getConnection();
            Statement stm=con.createStatement();
            PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1, test_name); 
                ps.setString(2, test_type); 
                ps.setString(3, test_cost); 
              
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
         return "update_Test_Entry";
     }
}
