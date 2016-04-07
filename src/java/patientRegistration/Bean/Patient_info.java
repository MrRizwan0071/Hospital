package patientRegistration.Bean;

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
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author NAHID
 */
@ManagedBean
@RequestScoped
public class Patient_info {

    private Date birthdate;
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
    private int id;
    private int age;
    String pass;
    String dat;

    public void Patient_info() {
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public void register() {
         
           
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

    public void patientInfo() {
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
                this.setBirthdate(rs.getDate("patient_dob"));
                this.setMaritul_status(rs.getString("patient_marital_status"));
                System.out.println(maritul_status + "***************");
                this.setSymptom(rs.getString("symptom"));
                System.out.println(symptom + "***************");

            }

        } catch (Exception e) {
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

    public String backRagi() {
        return "patient_registration";

    }

    public void save() { 
        int dat1=0,dat2=0,dat3=0;
        String dats1,dats2;
        try {
            DateFormat df = new SimpleDateFormat("YYYY");
            Date dt = new Date();
            dats1 = df.format(dt);
            dats2 = df.format(getBirthdate());
             dat1=Integer.parseInt(dats1);
             dat2=Integer.parseInt(dats2);
             dat3=dat1-dat2;
            System.out.println(dat3);
        } catch (Exception e) {
        }
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formet = df.format(getBirthdate());
        try {
            Statement st = Database.getConnection().createStatement();
            int a = st.executeUpdate("update patient_info set  patient_address='" + address + "', "
                    + "patient_marital_status='" + maritul_status + "', patient_email='" + email + "', patient_dob ='" + formet + "',"
                    + "patient_blood_group='" + bloodgroup + "',symptom='" + symptom + "', "
                    + "second_contuct='" + second_contact + "', age='"+dat3+"' where patient_id='" + patient_id + "';");
            System.out.println(patient_id);

            if (a > 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Data is Saved!", "!"));
                System.out.println("Data is saved");
                Statement st3 = Database.getConnection().createStatement();
                ResultSet rs5=st3.executeQuery("Select * from userinfo where user='"+patient_id+"'");
               if(rs5.next()==true){
                    System.out.println("User Exist");
                }else{
                   Statement st2 = Database.getConnection().createStatement();
                int patient = st.executeUpdate("Insert into userinfo (user, pass, role) values ('" + patient_id + "','"+pass+"','6')");
                if (patient > 0) {
                    System.out.println("User Save");
                }
               }
                
            }
            clear();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void clear() {




        name = "";
        doctor_name = "";
        gender = "";
        //String address "" ;
        maritul_status = "";
        email = "";
        bloodgroup = "";
        second_contact = "";
        contact_no = "";
        symptom = "";
        patient_id = 0;
        address = "";



    }

    public String goTest() {
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
            Logger.getLogger(Patient_info.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public List getPatientsDetails() {
        List pids = new ArrayList();
        try {
            Statement st = Database.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from patient_info where patient_name='" + name + "'");
            while (rs.next()) {
                pids.add(rs.getInt(2));
                System.out.println(pids);
            }

        } catch (Exception e) {
        }

        return pids;
    }
//    private static final ArrayList<Patient_info> pList = new ArrayList<Patient_info>();
//    private ArrayList list = new ArrayList();
//
//    public ArrayList<Patient_info> getOrderList() {
//        return pList;
//    }

    public List<Patient_info> getTodayPatient() {

        //Integer patId = 0;
        List<Patient_info> pnames = new ArrayList<Patient_info>();
        //List pids = getPatientsId();
//        for (int i = 0; i < pids.size(); i++) {
//            patId = (Integer) pids.get(i);
        try {
            Statement st = Database.getConnection().createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT patient_info.patient_name, patient_info.patient_id, appointment.appoint_date\n"
                    + "  FROM    health_care.appointment appointment\n"
                    + "       INNER JOIN\n"
                    + "          health_care.patient_info patient_info\n"
                    + "       ON (appointment.patient_id = patient_info.patient_id)\n"
                    + "			 where appointment.appoint_date=NOW() \n"
                    + "			  ");
            while (rs.next()) {
                Patient_info pi = new Patient_info();
                //pnames.add(rs.getString("patient_name"));
                pi.setName(rs.getString("patient_name"));
                pi.setPatient_id(rs.getInt("patient_id"));
                pnames.add(pi);
                System.out.println(pnames);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public Patient_info() {
        birthdate = new Date();
        maritul_status = "--select--";
    }
}
