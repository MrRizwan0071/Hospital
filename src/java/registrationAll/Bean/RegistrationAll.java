/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package registrationAll.Bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kaniz
 */
@ManagedBean
@RequestScoped
public class RegistrationAll {

    String uname, pass, userrole;
    int role;

    /**
     * Creates a new instance of RegistrationAll
     */
    public RegistrationAll() {
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public List getUserType() {
        List list = new ArrayList();
        list.add("Doctor Assistant");
        list.add("Pharmacy");
        list.add("Diagnosis");
        list.add("Doctor");
        
        return list;
    }

    public void save() {
        if (userrole.equals("Doctor")) {
            role = 2;
            System.out.println(role);
        } else if (userrole.equals("Pharmacy")) {
            role = 3;
            System.out.println(2);
        } else if (userrole.equals("Diagnosis")) {
            role = 4;
        }else  if (userrole.equals("Doctor Assistant")) {
            role=5;
        }
        String sql = "INSERT INTO userinfo (user, pass, role) values (?,?,?)";
        try {
            Connection con = DAO.Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = null;
            rs = stm.executeQuery("select * from userinfo where user='" + uname + "'");
      
            if (rs.next()) {
                FacesMessage msg1 = new FacesMessage("User name already exist");
                FacesContext.getCurrentInstance().addMessage(null, msg1);
                System.out.println("User already exist");
            } else {


                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, uname);
                ps.setString(2, pass);
                ps.setInt(3, role);

                int value = ps.executeUpdate();

                if (value > 0) {
                    FacesMessage msg1 = new FacesMessage("Data is saved");
                    FacesContext.getCurrentInstance().addMessage(null, msg1);
                    System.out.println("Data is saved");
                    clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        setUname("");
        setPass("");
        setUserrole("--Select--");
    }
}
