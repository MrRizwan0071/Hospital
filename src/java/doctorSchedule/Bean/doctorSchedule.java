/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doctorSchedule.Bean;

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
import javax.faces.model.SelectItem;

/**
 *
 * @author kaniz
 */
@ManagedBean
@RequestScoped
public class doctorSchedule {
    String doctor_name, doctor_sch_time, doctor_sch_day;
  static  int doctor_id;
  int doctor_fee,doc_id;

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
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

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getDoctor_fee() {
        return doctor_fee;
    }

    public void setDoctor_fee(int doctor_fee) {
        this.doctor_fee = doctor_fee;
    }

  
     public List getDoctor_sch() {
        List list = new ArrayList();
        list.add("9.0AM-1.0PM");
        list.add("4.0PM-8.0PM");
        list.add("5.0PM-9.0PM");
        list.add("6.0PM-10.0PM");
        list.add("7.0PM-11.0PM");
        return list;
    }
     
     public List getDoctor_sch_da() {
        List list = new ArrayList();
        list.add("Saterday");
        list.add("Sunday");
        list.add("Monday");
        list.add("Tuesday");
        list.add("Wednesday");
        list.add("Thusday");
        list.add("Friday");
        return list;
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
                data.add(new SelectItem(rs.getString("doctor_name")));
            }
        } catch (Exception e) {
        }
        return data;
    }
     
      public void selectDctorName() {
        try {
            Connection con = DAO.Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = null;
            rs = stm.executeQuery("select doctor_id from doctor_info where concat(doctor_fname,' ',doctor_lname)='" + doctor_name + "'");
            while (rs.next()) {
                doctor_id = rs.getInt("doctor_id");
                System.out.println(doctor_id);
               }
        } catch (Exception e) {
        }
    }
    public void save() {
        String sql = "INSERT INTO doctor_schedule (doctor_id, doctor_sch_time, doctor_sch_day, doctor_fee) values (?,?,?,?)";
        try {
            Connection con = DAO.Database.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1,doctor_id);
                ps.setString(2, doctor_sch_time);
                ps.setString(3, doctor_sch_day);
                ps.setInt(4, doctor_fee);
                int value = ps.executeUpdate();
                
                     if(value>0){
                   FacesMessage msg1=new FacesMessage("Data is saved");
                 FacesContext.getCurrentInstance().addMessage(null,msg1);  
                    System.out.println("Data is saved");
                    clear();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void clear(){
       doctor_fee=0;
       doctor_name="";
       doctor_sch_day="";
       doctor_sch_time="";
   }
}
