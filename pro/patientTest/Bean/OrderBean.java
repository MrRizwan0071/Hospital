/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package patientTest.Bean;

import java.io.Serializable;
import java.util.Date;

public class OrderBean {

    private static final long serialVersionUID = 1L;
    private String test_name;
    Date date;
    String patient_name, doctor_name;
    int patient_id, doctor_id;

    public String getPatient_name() {
        return patient_name;
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

    public OrderBean(String test_name, Date date) {
        this.test_name = test_name;
        this.date = date;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

  
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}