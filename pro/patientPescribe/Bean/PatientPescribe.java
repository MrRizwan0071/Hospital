/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package patientPescribe.Bean;

import patientTest.Bean.*;
import DAO.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

@ManagedBean
@SessionScoped
public class PatientPescribe {

    private static final long serialVersionUID = 1L;
    private String med_name;
    Date date, next_date;
    OrderBeanP order;
    String patient_name, doctor_name, douse, taking_schedule, textarea;
    int patient_id, doctor_id, med_id, patient_age;

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public int getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(int patient_age) {
        this.patient_age = patient_age;
    }

    public String getDouse() {
        return douse;
    }

    public void setDouse(String douse) {
        this.douse = douse;
    }

    public String getTaking_schedule() {
        return taking_schedule;
    }

    public void setTaking_schedule(String taking_schedule) {
        this.taking_schedule = taking_schedule;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getTextarea() {
        return textarea;
    }

    public void setTextarea(String textarea) {
        this.textarea = textarea;
    }

    public PatientPescribe() {
        date = new Date();
        next_date = new Date();
        med_name = "--Select--";
//        this.patient_name = "Rahim";
//        this.doctor_name = "Ab Khan";

    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public int getMed_id() {
        return med_id;
    }

    public void setMed_id(int med_id) {
        this.med_id = med_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getNext_date() {
        return next_date;
    }

    public void setNext_date(Date next_date) {
        this.next_date = next_date;
    }

    public OrderBeanP getOrder() {
        return order;
    }

    public void setOrder(OrderBeanP order) {
        this.order = order;
    }
    private static final ArrayList<OrderBeanP> orderList = new ArrayList<OrderBeanP>();

    public ArrayList<OrderBeanP> getOrderList() {
        return orderList;
    }

    public String addAction() {
        OrderBeanP orderitem = new OrderBeanP(this.med_name, this.date, this.douse, this.taking_schedule);
        orderList.add(orderitem);

        med_name = "--Select--";

        return null;
    }

    public void onEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Item Edited", ((OrderBeanP) event.getObject()).getMed_name());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Item Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        orderList.remove((OrderBeanP) event.getObject());
    }

    public List<SelectItem> getComBobox() {
        List<SelectItem> total = new ArrayList<SelectItem>();
        try {
            Connection con = (Connection) Database.getConnection();
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery("select med_name from medicine_list");
            while (rs.next()) {
                total.add(new SelectItem(rs.getString("med_name")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return total;
    }

    public void selctinfo() {
        Connection con = Database.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT med_id from  medicine_list where med_name='" + med_name + "'");
            while (rs.next()) {
                setMed_id(rs.getInt("med_id"));
            }
        } catch (Exception e) {
        }
    }

    public List getDouses() {
        List list = new ArrayList();
        list.add("1-0-1");
        list.add("1-1-1");
        list.add("0-0-1");
        list.add("1-1-1-1");
        list.add("1-0-0");
        return list;
    }

    public void patientSelect() {
        try {
            Connection con = (Connection) Database.getConnection();
            Statement st = (Statement) con.createStatement();
            ResultSet rs5 = st.executeQuery("SELECT concat(doctor_info.doctor_fname,' ',doctor_info.doctor_lname) flname,"
                    + "patient_info.patient_name,patient_info.age,appointment.patient_id, "
                    + "appointment.doctor_id FROM    (   health_care.appointment appointment"
                    + " INNER JOIN health_care.patient_info patient_info "
                    + "ON (appointment.patient_id = patient_info.patient_id))"
                    + "INNER JOIN health_care.doctor_info doctor_info "
                    + "ON (appointment.doctor_id = doctor_info.doctor_id)"
                    + " where patient_info.patient_id='" + patient_id + "'");
            while (rs5.next()) {
                setPatient_name(rs5.getString("patient_name"));
                setPatient_id(rs5.getInt("patient_id"));
                setDoctor_id(rs5.getInt("doctor_id"));
                setPatient_age(rs5.getInt("age"));
                setDoctor_name(rs5.getString("flname"));
                System.out.println(patient_name + patient_age);

//               String doc_fname=rs5.getString(3);
//               String doc_lname=rs5.getString(4);
//                setDoctor_name(doc_fname+doc_lname);
//                System.out.println(patient_name+doctor_name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dt = df.format(getDate());
            String dt1 = df.format(getNext_date());
            Statement st, st1, st2, st3;
            ResultSet rs, rs1, rs2, rs3;
            int pres_id;

            Connection con = (Connection) Database.getConnection();
            st = (Statement) con.createStatement();
            st1 = (Statement) con.createStatement();
            st2 = (Statement) con.createStatement();
            st3 = (Statement) con.createStatement();

            st.executeUpdate("insert into prescription (patient_id, doctor_id, pres_date,"
                    + " next_consult_date,remarks) values ('" + this.getPatient_id() + "','"+this.getDoctor_id()+"','" + dt + "','" + dt1 + "','" + textarea + "');");
                   
            rs = st1.executeQuery("SELECT MAX(last_insert_id(pres_id)) FROM prescription");
            while (rs.next()) {
                int mg = 0;
                pres_id = rs.getInt(1);
                // System.out.println(diag_id);
                for (int i = 0; i < orderList.size(); i++) {
                    med_name = orderList.get(i).getMed_name();
                    douse = orderList.get(i).getDouse();
                    taking_schedule = orderList.get(i).getTaking_schedule();
                    //System.out.println(test_name);
                    mg = st2.executeUpdate("insert into prescription_line (pres_id, med_id, douse, douse_schedule) values ('" + pres_id + "', (select med_id from medicine_list where med_name='" + this.med_name + "'),'" + douse + "','" + taking_schedule + "')");
                    System.out.println(mg);

                }
                if (mg > 0) {
                    FacesMessage msg1 = new FacesMessage("Data is saved");
                    FacesContext.getCurrentInstance().addMessage(null, msg1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}