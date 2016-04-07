/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package department.Bean;

import DAO.Database;
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
import testEntry.Bean.TestEntry;

/**
 *
 * @author kaniz
 */
@ManagedBean
@RequestScoped
@SessionScoped
public class DepartmentEntry {

    String dept_name;
    int department_id;

    /**
     * Creates a new instance of DepartmentEntry
     */
    public DepartmentEntry() {
        setDept_name("");
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public void save() {
        String sql = "Insert Into doctor_department (department_name) values(?)";
        try {
            Connection con = DAO.Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = null;
            rs = stm.executeQuery("select * from doctor_department where department_name='" + getDept_name() + "'");
            if (rs.next() == true) {
                FacesMessage msg1 = new FacesMessage("User name already exist");
                FacesContext.getCurrentInstance().addMessage(null, msg1);
                System.out.println("Data already Exist");
            } else {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, getDept_name());
                int value = ps.executeUpdate();
                if (value > 0) {
                    FacesMessage msg1 = new FacesMessage("Data is saved");
                    FacesContext.getCurrentInstance().addMessage(null, msg1);
                    System.out.println("Data is saved");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<DepartmentEntry> getAllData(){
        List<DepartmentEntry> data=new ArrayList<DepartmentEntry>();
        try {
            Connection con = null;
            Statement stm = null;
            ResultSet rs = null;
            con = Database.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM health_care.doctor_department;");
            //order by test_id desc limit 10;
            while(rs.next()){
                 DepartmentEntry des=new DepartmentEntry();
                des.setDept_name(rs.getString("department_name"));             
                des.setDepartment_id(rs.getInt("department_id"));
                System.out.println(department_id);
                data.add(des);
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
            int i=stm.executeUpdate("delete from doctor_department where department_id='"+this.department_id+"'");
            if(i>0){
                 FacesMessage msg1 = new FacesMessage("Data is deleted");
                    FacesContext.getCurrentInstance().addMessage(null, msg1);
                System.out.println("Data is Deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
