/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package patientPescribe.Bean;

import patientTest.Bean.*;
import java.io.Serializable;
import java.util.Date;

public class OrderBeanP {

    private static final long serialVersionUID = 1L;

    public String getDouse() {
        return douse;
    }

    public void setDouse(String douse) {
        this.douse = douse;
    }

  
    private String med_name;
    Date date;
    String patient_name, doctor_name,douse, taking_schedule;
    int patient_id, doctor_id, med_id;

    public String getPatient_name() {
        return patient_name;
    }

    public String getTaking_schedule() {
        return taking_schedule;
    }

    public void setTaking_schedule(String taking_schedule) {
        this.taking_schedule = taking_schedule;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
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

    public OrderBeanP(String med_name, Date date,String douse, String taking_schedule) {
        this.med_name = med_name;
        this.date = date;
        this.douse=douse;
        this.taking_schedule=taking_schedule;
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
}