/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package showmedicine_forpatient.Bean;

import DAO.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import viewDoctorSchedule.Bean.ViewDoctorSchedule;

/**
 *
 * @author kaniz
 */
@ManagedBean
@RequestScoped
public class Prescription4D {

    private ArrayList<Prescription4D> list = new ArrayList<Prescription4D>();
    String patient_name, date, med_name, dose, schedule;
    int patient_id;

    /**
     * Creates a new instance of Prescription4D
     */
    public Prescription4D() {
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public void patientPrescription() {
        try {
            Connection con = Database.getConnection();
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery("SELECT patient_info.patient_name,\n"
                    + "       prescription.pres_date,\n"
                    + "       medicine_list.med_name,\n"
                    + "       prescription_line.douse,\n"
                    + "       prescription_line.douse_schedule\n"
                    + "  FROM    (   (   health_care.prescription_line prescription_line\n"
                    + "               INNER JOIN\n"
                    + "                  health_care.medicine_list medicine_list\n"
                    + "               ON (prescription_line.med_id = medicine_list.med_id))\n"
                    + "           INNER JOIN\n"
                    + "              health_care.prescription prescription\n"
                    + "           ON (prescription_line.pres_id = prescription.pres_id))\n"
                    + "       INNER JOIN\n"
                    + "          health_care.patient_info patient_info\n"
                    + "       ON (prescription.patient_id = patient_info.patient_id) where prescription.patient_id='" + patient_id + "'");
            while (rs.next()) {
                setPatient_name(rs.getString(1));
            }
        } catch (Exception e) {
        }
    }

    public List<SelectItem> getComDate() {
        List<SelectItem> total = new ArrayList<SelectItem>();
        try {
            int diag_id = 0;
            Connection con = (Connection) Database.getConnection();
            Statement st = (Statement) con.createStatement();
            Statement st9 = (Statement) con.createStatement();
            ResultSet rs = st9.executeQuery("SELECT distinct prescription.pres_date, patient_info.patient_id\n"
                    + "  FROM    (   (   health_care.prescription_line prescription_line\n"
                    + "               INNER JOIN\n"
                    + "                  health_care.medicine_list medicine_list\n"
                    + "               ON (prescription_line.med_id = medicine_list.med_id))\n"
                    + "           INNER JOIN\n"
                    + "              health_care.prescription prescription\n"
                    + "           ON (prescription_line.pres_id = prescription.pres_id))\n"
                    + "       INNER JOIN\n"
                    + "          health_care.patient_info patient_info\n"
                    + "       ON (prescription.patient_id = patient_info.patient_id) where prescription.patient_id='" + patient_id + "'");
            while (rs.next()) {
                total.add(new SelectItem(rs.getString(1)));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return total;
    }

    public void allSchedule() {
        try {
            list.clear();
            Connection con = DAO.Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs5 = null;
            rs5 = stm.executeQuery("SELECT medicine_list.med_name,\n"
                    + "       prescription_line.douse,\n"
                    + "       prescription_line.douse_schedule\n"
                    + "  FROM    (   (   health_care.prescription_line prescription_line\n"
                    + "               INNER JOIN\n"
                    + "                  health_care.medicine_list medicine_list\n"
                    + "               ON (prescription_line.med_id = medicine_list.med_id))\n"
                    + "           INNER JOIN\n"
                    + "              health_care.prescription prescription\n"
                    + "           ON (prescription_line.pres_id = prescription.pres_id))\n"
                    + "       INNER JOIN\n"
                    + "          health_care.patient_info patient_info\n"
                    + "       ON (prescription.patient_id = patient_info.patient_id) where prescription.patient_id='"+patient_id+"' and prescription.pres_date='"+date+"'");
            while (rs5.next()) {
                Prescription4D vds = new Prescription4D();
                vds.setMed_name(rs5.getString(1));
                vds.setDose(rs5.getString(2));
                vds.setSchedule(rs5.getString(3));
                list.add(vds);

            }
        } catch (Exception e) {
        }
    }

    public List<Prescription4D> getTableData() {
        return list;
    }
}
