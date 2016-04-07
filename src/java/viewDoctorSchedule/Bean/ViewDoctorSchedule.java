/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewDoctorSchedule.Bean;

import doctorSchedule.Bean.doctorSchedule;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import org.primefaces.component.orderlist.OrderList;

/**
 *
 * @author kaniz
 */
public class ViewDoctorSchedule {
    
    String doctor_fname, doctor_lname, doctor_sch_time, doctor_sch_day, doctor_fee, doctor_name;
    int doctor_id;
private ArrayList<ViewDoctorSchedule> list = new ArrayList<ViewDoctorSchedule>();
    /**
     * Creates a new instance of ViewDoctorSchedule
     */
    public ViewDoctorSchedule() {
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }
    
    public String getDoctor_fname() {
        return doctor_fname;
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
    
    public String getDoctor_sch_time() {
        return doctor_sch_time;
    }
    
    public void setDoctor_sch_time(String doctor_sch_time) {
        this.doctor_sch_time = doctor_sch_time;
    }
    
    public String getDoctor_sch_day() {
        return doctor_sch_day;
    }
    
    public void setDoctor_sch_day(String doctor_sch_day) {
        this.doctor_sch_day = doctor_sch_day;
    }
    
    public String getDoctor_fee() {
        return doctor_fee;
    }
    
    public void setDoctor_fee(String doctor_fee) {
        this.doctor_fee = doctor_fee;
    }
    
    public int getDoctor_id() {
        return doctor_id;
    }
    
    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }
    
    public List<SelectItem> getDoctorName() {
        List<SelectItem> data = new ArrayList<SelectItem>();
        try {
            Connection con = DAO.Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = null;
            rs = stm.executeQuery("select concat(doctor_fname,' ',doctor_lname) as doctor_name from doctor_info");
            while (rs.next()) {
                doctorSchedule doc_sch = new doctorSchedule();
//                doctor_name=rs.getString("doctor_name");
                data.add(new SelectItem(rs.getString("doctor_name")));
            }
        } catch (Exception e) {
        }
        return data;
    }
    
    public void allSchedule() {
        try {
           list.clear();
            Connection con = DAO.Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = null;
            rs = stm.executeQuery("select doctor_sch_time, doctor_sch_day, doctor_fee from doctor_schedule where doctor_id=(Select doctor_id from doctor_info where concat(doctor_fname,' ',doctor_lname)='" + doctor_name + "')");
            while (rs.next()) {
                ViewDoctorSchedule vds = new ViewDoctorSchedule();
                vds.setDoctor_sch_day(rs.getString(1));
                vds.setDoctor_sch_time(rs.getString(2));
                vds.setDoctor_fee(rs.getString(3));
                list.add(vds);
               
            }
        } catch (Exception e) {
        }  
    }
     public List<ViewDoctorSchedule> getTableData(){
        return list;
     
     
     }
}
