/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diagnosis.Bean;

import patientPescribe.Bean.*;
import patientTest.Bean.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderBeanP {

    private static final long serialVersionUID = 1L;

    Date date, test_date, report_date;
   
    String patient_name, doctor_name,report,test_name,tstdt,rptdt;
    int patient_id, doctor_id,test_id;
    private String med_name;

    public Date getTest_date() {
        return test_date;
    }

    public void setTest_date(Date test_date) {
       this.test_date=test_date;
        
    }

    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public String getTstdt() {
        Date d = test_date;
        DateFormat df=new SimpleDateFormat("dd-MM-yyyy");
         String p=df.format(d);
        return p;
    }

    public void setTstdt(String tstdt) {
      this.tstdt=tstdt;
    }

    public String getRptdt() {
         Date d = report_date;
        DateFormat df=new SimpleDateFormat("dd-MM-yyyy");
         String p=df.format(d);
         return p;
    }

    public void setRptdt(String rptdt) {
         this.rptdt=rptdt;     
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

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

    public OrderBeanP(String test_name, Date test_date,String report, Date report_date) {
        this.test_name = test_name;
        this.test_date=test_date;
        this.report=report;
        this.report_date=report_date;
        
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

   
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}