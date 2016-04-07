/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doctorInfo.Bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author kaniz
 */
@ManagedBean
@RequestScoped
@SessionScoped
public class ViewDoctoeInfo {
String  doctor_fname, doctor_lname, doctor_gender, doctor_dob, doctor_address, doctor_email, doctor_cell, doctor_designation, qualification, doctor_name,department_name;
int doctor_id, department_id;   
/**
     * Creates a new instance of ViewDoctoeInfo
     */
    public ViewDoctoeInfo() {
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
    
    public String getDoctor_fname() {
        return doctor_fname;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public void setDoctor_fname(String doctor_fname) {
        this.doctor_fname = doctor_fname;
    }

    public String getDoctor_lname() {
        return doctor_lname;
    }

    public void setDoctor_lname(String doctor_lname) {
        this.doctor_lname = doctor_lname;
    }

    public String getDoctor_gender() {
        return doctor_gender;
    }

    public void setDoctor_gender(String doctor_gender) {
        this.doctor_gender = doctor_gender;
    }

    public String getDoctor_dob() {
        return doctor_dob;
    }

    public void setDoctor_dob(String doctor_dob) {
        this.doctor_dob = doctor_dob;
    }

    public String getDoctor_address() {
        return doctor_address;
    }

    public void setDoctor_address(String doctor_address) {
        this.doctor_address = doctor_address;
    }

    public String getDoctor_email() {
        return doctor_email;
    }

    public void setDoctor_email(String doctor_email) {
        this.doctor_email = doctor_email;
    }

    public String getDoctor_cell() {
        return doctor_cell;
    }

    public void setDoctor_cell(String doctor_cell) {
        this.doctor_cell = doctor_cell;
    }

    public String getDoctor_designation() {
        return doctor_designation;
    }

    public void setDoctor_designation(String doctor_designation) {
        this.doctor_designation = doctor_designation;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }
   
     public List<SelectItem> getDoctorName() {
        List<SelectItem> data = new ArrayList<SelectItem>();
        try {
            Connection con = DAO.Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = null;
            rs = stm.executeQuery("select concat(doctor_fname,' ',doctor_lname) as doctor_name, doctor_id from doctor_info");
            while (rs.next()) {
                data.add(new SelectItem(rs.getString("doctor_name")));
               
            }
        } catch (Exception e) {
        }
        return data;
    }
    public void doctorDetails(){
        try {
         Connection con = DAO.Database.getConnection();
            Statement stm = con.createStatement();
             Statement stm1 = con.createStatement();
            ResultSet rs = null;
            ResultSet rs2 = null;
            rs = stm.executeQuery("SELECT doctor_info.doctor_id,\n" +
"       doctor_info.doctor_fname,\n" +
"       doctor_info.doctor_lname,\n" +
"       doctor_info.doctor_gender,\n" +
"       doctor_info.doctor_dob,\n" +
"       doctor_info.doctor_address,\n" +
"       doctor_info.doctor_email,\n" +
"       doctor_info.doctor_cell,\n" +
"       doctor_info.doctor_designation,\n" +
"       doctor_info.qualification,\n" +
"       doctor_info.department_id,\n" +
"       doctor_department.department_name,\n" +
"       doctor_department.department_id\n" +
"  FROM    health_care.doctor_info doctor_info\n" +
"       INNER JOIN\n" +
"          health_care.doctor_department doctor_department\n" +
"       ON (doctor_info.department_id = doctor_department.department_id) where concat(doctor_fname,' ',doctor_lname)='"+doctor_name+"'");
            while(rs.next()){
                ViewDoctoeInfo vdf=new ViewDoctoeInfo();
                setDoctor_id(rs.getInt("doctor_id"));
                setDoctor_fname(rs.getString("doctor_fname"));
                setDoctor_lname(rs.getString("doctor_lname"));
                setDoctor_gender(rs.getString("doctor_gender"));
                setDoctor_dob(rs.getString("doctor_dob"));
                setDoctor_email(rs.getString("doctor_email"));
                setDoctor_address(rs.getString("doctor_address"));
                setDoctor_cell(rs.getString("doctor_cell"));
                setDoctor_designation(rs.getString("doctor_designation"));
                setQualification(rs.getString("qualification"));
                setDepartment_id(rs.getInt("department_id"));
                setDepartment_name(rs.getString("department_name"));
                System.out.println(department_id);
//            rs2=stm1.executeQuery("select department_name from doctor_department where department_id='"+department_id+"'");
//            while(rs2.next()){
//                setDepartment_name(rs.getString("department_name"));
//                System.out.println(department_name);
//            }
            }
            
        } catch (Exception e) {
        }
    }
}
