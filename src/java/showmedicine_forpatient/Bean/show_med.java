package showmedicine_forpatient.Bean;

import DAO.Database;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class show_med  {

    int prescrip_id;
    String med_name, dos, dos_sch;
    Date date;
List<show_med> list=new ArrayList<show_med>();
    public show_med() {
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getDos_sch() {
        return dos_sch;
    }

    public void setDos_sch(String dos_sch) {
        this.dos_sch = dos_sch;
    }

    public int getPrescrip_id() {
        return prescrip_id;
    }

    public void setPrescrip_id(int prescrip_id) {
        this.prescrip_id = prescrip_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public void showMed() {
        //List<show_med> data = new ArrayList<show_med>();
        try {
            Connection con = Database.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = null;
            rs = st.executeQuery("SELECT prescription_line.douse, prescription_line.douse_schedule,"
                    + " medicine_list.med_name,prescription.pres_date"
                    + " FROM    (   health_care.prescription_line prescription_line "
                    + "INNER JOIN health_care.medicine_list medicine_list "
                    + "ON (prescription_line.med_id = medicine_list.med_id)) "
                    + "INNER JOIN health_care.prescription prescription "
                    + "ON (prescription_line.pres_id = prescription.pres_id) where prescription.patient_id='"+prescrip_id+"'");

            if (rs.next()) {
                
                show_med sh = new show_med();
                sh.setMed_name(rs.getString("med_name"));
                sh.setDos(rs.getString("douse"));
                sh.setDos_sch(rs.getString("douse_schedule"));
                sh.setDate(rs.getDate("pres_date"));
                list.add(sh);
            }else{  
              FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("you have no prescribe!", " plz consult the doctor "));
                
            
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public List<show_med>getMedicinepres(){
      return list;
    }
}
