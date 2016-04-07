/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package patientDetails;

import DAO.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author prima
 */
@ManagedBean
@SessionScoped
public class Patientshow {
    
    
    public Date birthdate;
    public String p_name;
    public String doctor_name;
    public String gender;
    public String address;
    public String maritul_status;
    public String email;
    public String bloodgroup;
    private String second_contact;
    public  String contact_no;
    public String symptom;
    public int patient_id;
    public String pdate;

    String b;

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getMaritul_status() {
        return maritul_status;
    }

    public void setMaritul_status(String maritul_status) {
        this.maritul_status = maritul_status;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
   
    
    public void pdetails(){
        System.out.println("**************************************************");
    
      try {
            Statement st;
            ResultSet rs;
            Connection con = (Connection) Database.getConnection();
            st = (Statement) con.createStatement();
            rs = st.executeQuery("SELECT * FROM health_care.patient_info  where patient_info.patient_id='"+this.patient_id+"'");
            
            
            if (rs.next()) {
                // this.setPatient_id(rs.getInt("patient_id"));
                //b=(rs.getString("patient_name"));
                setP_name(rs.getString("patient_name"));
                 setGender(rs.getString("gender"));
               setContact_no(rs.getString("patient_cell"));
                setBirthdate(rs.getDate("patient_dob"));
                setMaritul_status(rs.getString("patient_marital_status"));
                setAddress(rs.getString("patient_address"));
                setEmail(rs.getString("patient_email"));
//                
                setSymptom(rs.getString("symptom"));
                setBloodgroup(rs.getString("patient_blood_group"));
                setPdate(rs.getString("regidate"));
                 System.out.println(patient_id);
                 
            }else{
              p_name="id doesnot match";
              gender="";
              contact_no="";
              birthdate=new Date();
              maritul_status="";
              address="";
              email="";
              symptom="";
              bloodgroup="";
              
            
            }

        } catch (Exception e) {
        }
 // return b;
    
    }
    public static void main(String[] args) {
        Patientshow p=new Patientshow();
        p.pdetails();
    }
    
}
