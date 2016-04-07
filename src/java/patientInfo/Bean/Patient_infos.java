package patientInfo.Bean;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import DAO.Database;
import patientTest.Bean.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author NAHID
 */
@ManagedBean
@SessionScoped
public class Patient_infos {

    private Date birthdate;
    private Date sch_date;
    private String name;
    private String doctor_name;
    private String gender;
    private String address;
    private String maritul_status;
    private String email;
    private String bloodgroup;
    private String second_contact;
    private String contact_no;
    private String symptom;
   
    private int patient_id;
List<Patient_infos> pnames = new ArrayList<Patient_infos>();

    public List<Patient_infos> getPnames() {
        return pnames;
    }

    public void setPnames(List<Patient_infos> pnames) {
        this.pnames = pnames;
    }


    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public Date getSch_date() {
        return sch_date;
    }

    public void setSch_date(Date sch_date) {
        this.sch_date = sch_date;
    }
    private int id;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public void setId(int id) {
        this.id = 123;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaritul_status() {
        return maritul_status;
    }

    public void setMaritul_status(String maritul_status) {
        this.maritul_status = maritul_status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getSecond_contact() {
        return second_contact;
    }

    public void setSecond_contact(String second_contact) {
        this.second_contact = second_contact;
    }

    public Date getBirthdate() {
        //Date birthdate = new Date();
        //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //   String formet = df.format(date);
        // System.out.println("************************"+formet);
        return birthdate;
    }

    public void setBirthdate(Date date) {

        this.birthdate = date;
    }
    public void register(){
        try {
            Statement st1;
            ResultSet rs;
            Connection con = (Connection) Database.getConnection();
            st1 = (Statement) con.createStatement();
            rs = st1.executeQuery("SELECT * from patient_info where patient_id='" + patient_id + "'");
            while (rs.next()) {
               // this.setPatient_id(rs.getInt("patient_id"));
                this.setName(rs.getString("patient_name"));
                this.setContact_no(rs.getString("patient_cell"));
                this.setGender(rs.getString("gender"));
                System.out.println(gender);

            }

        } catch (Exception e) {
        }

        
        
        
    }
    public void patientInfo(){
        try {
            Statement st1;
            ResultSet rs;
            Connection con = (Connection) Database.getConnection();
            st1 = (Statement) con.createStatement();
            rs = st1.executeQuery("SELECT * from patient_info where patient_id='" + patient_id + "'");
            while (rs.next()) {
               // this.setPatient_id(rs.getInt("patient_id"));
                this.setName(rs.getString("patient_name"));
                this.setContact_no(rs.getString("patient_cell"));
                this.setGender(rs.getString("gender"));
                //this.setBirthdate(rs.getDate("patient_dob"));
                this.setMaritul_status(rs.getString("patient_marital_status"));
                this.setBloodgroup(rs.getString("patient_blood_group"));
                this.setAge(rs.getInt("age"));
               // System.out.println(maritul_status+"***************");
                this.setSymptom(rs.getString("symptom"));
               //System.out.println(symptom+"***************");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        
        
    }

    public List getMeritulStatusCombo() {
        List data = new ArrayList();
        
       // data.add("--Select--");
        data.add("Single");
        data.add("Married");
        data.add("Other");
        return data;
    }
     public String backRagi(){
        return "patient_registration";
    
    }


    public String goTest(){
         try {
            Statement st1;
            ResultSet rs;
            Connection con = (Connection) Database.getConnection();
            st1 = (Statement) con.createStatement();
            rs = st1.executeQuery("SELECT * from patient_info where patient_id='" + patient_id + "'");
            while (rs.next()) {
               // this.setPatient_id(rs.getInt("patient_id"));
                
                this.setName(rs.getString("patient_name"));
                this.setContact_no(rs.getString("patient_cell"));
                this.setGender(rs.getString("gender"));
                System.out.println(gender);

            }
         } catch (SQLException ex) {
            Logger.getLogger(Patient_infos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public String getSymptom() {
        return symptom;
    }

    
    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }



    public void etTodayPatient() {
pnames.clear();
        //Integer patId = 0;
        
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        String dte=sf.format(getSch_date());
        //List pids = getPatientsId();
//        for (int i = 0; i < pids.size(); i++) {
//            patId = (Integer) pids.get(i);
        try {
            Statement st = Database.getConnection().createStatement();
            ResultSet rs = st.executeQuery(

                    "SELECT appointment.appoint_date,patient_info.patient_id,"
                    + "patient_info.patient_name,patient_info.gender,patient_info.patient_address,"
                    + "patient_info.patient_cell,patient_info.patient_marital_status,"
                    + "patient_info.patient_email,patient_info.patient_blood_group,"
                    + "patient_info.symptom,patient_info.age,patient_info.second_contuct "
                    + "FROM    health_care.appointment appointment INNER JOIN health_care.patient_info patient_info "
                    + "ON (appointment.patient_id = patient_info.patient_id)  where appointment.appoint_date='"+dte+"'"); 
            while (rs.next()) {
                Patient_infos pi = new Patient_infos();
                //pnames.add(rs.getString("patient_name"));
                pi.setName(rs.getString("patient_name"));
                pi.setPatient_id(rs.getInt("patient_id"));
                pi.setAge(rs.getInt("age"));
                pi.setBloodgroup(rs.getString("patient_blood_group"));
                pi.setContact_no(rs.getString("patient_cell"));
                pi.setEmail(rs.getString("patient_email"));
                pi.setAddress(rs.getString("patient_address"));
                pi.setMaritul_status(rs.getString("patient_marital_status"));
                pi.setSymptom(rs.getString("symptom"));
                pi.setGender(rs.getString("gender"));
                pi.setSecond_contact(rs.getString("second_contuct"));
                pnames.add(pi);
                System.out.println(pnames);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

       
    }
    public List getDt(){
    return pnames;
    }
   
    public String goPatientDetail() {
        try {
            Statement st1;
            ResultSet rs;
            Connection con = (Connection) Database.getConnection();
            st1 = (Statement) con.createStatement();
            rs = st1.executeQuery("SELECT * from patient_info where patient_id='" + patient_id + "'");
            while (rs.next()) {
                this.setName(rs.getString("patient_name"));

            }

        } catch (Exception e) {
        }

        return "Patient_info";
    }
     public Patient_infos() {
        birthdate = new Date();
        sch_date = new Date();
       // maritul_status="--select--";
    }
}
