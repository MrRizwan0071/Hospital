/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doctorSrarchandAppoint;

import java.sql.Connection;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author User
 */
@ManagedBean
@RequestScoped
public class appoint {
 String  p_name,gender,phone,doc_name,doc_schduel;
 

    /** Creates a new instance of appoint */
    public appoint() {
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public String getDoc_schduel() {
        return doc_schduel;
    }

    public void setDoc_schduel(String doc_schduel) {
        this.doc_schduel = doc_schduel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void appoin(){
        try {
             Connection con = DAO.Database.getConnection();
            Statement st = con.createStatement();
            
            st.executeUpdate("insert into appointment( patient_id, doctor_id, doctor_sch_id, appoint_date, is_visited)values()") ;
        } catch (Exception e) {
        }
    }
}
