/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package registrationAll.Bean;

import DAO.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kaniz
 */
@ManagedBean
@RequestScoped
@SessionScoped
public class ShowUsersInfo {
String user_name, pass, urole,e_id;
//int e_id;
    /**
     * Creates a new instance of ShowUsers
     */
    public ShowUsersInfo() {
   
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUrole() {
        return urole;
    }

    public void setUrole(String urole) {
        this.urole = urole;
    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

  
  
     public List<ShowUsersInfo> getAllData(){
        List<ShowUsersInfo> data=new ArrayList<ShowUsersInfo>();
        try {
            Connection con = null;
            Statement stm = null;
            ResultSet rs = null;
            con = Database.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM health_care.userinfo;");
            //order by test_id desc limit 10;
            while(rs.next()){
                 ShowUsersInfo su=new ShowUsersInfo();
                 su.setE_id(rs.getString("id"));
                su.setUser_name(rs.getString("user"));             
                su.setPass(rs.getString("pass"));
                su.setUrole(rs.getString("role"));
                
                System.out.println(e_id);
                data.add(su);
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
            int i=stm.executeUpdate("delete from userinfo where id='"+this.e_id+"'");
            System.out.println(e_id);
            if(i>0){
                System.out.println(i);
                 FacesMessage msg1 = new FacesMessage("User is deleted");
                    FacesContext.getCurrentInstance().addMessage(null, msg1);
                System.out.println("User is Deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
