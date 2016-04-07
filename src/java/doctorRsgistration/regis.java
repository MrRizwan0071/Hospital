/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doctorRsgistration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author User
 */
@ManagedBean
@SessionScoped
public class regis {

    String fname, lname, addres, email, desig, quali, depart, gender, phone;
    Date date;
    private UploadedFile photo;
    int dep_id;

    public regis() {
    }

    public UploadedFile getPhoto() {
        return photo;
    }

    public void setPhoto(UploadedFile photo) {
        this.photo = photo;
    }

  

    public int getDep_id() {
        return dep_id;
    }

    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDesig() {
        return desig;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getQuali() {
        return quali;
    }

    public void setQuali(String quali) {
        this.quali = quali;
    }

    public void handleFileUpload(FileUploadEvent e) {
    }

    public void inser() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dt = df.format(getDate());
//        System.out.println("ppppppppppppppppppppppp");
        String sql = "insert into doctor_info( doctor_fname, doctor_lname, doctor_gender, doctor_dob, "
                + "doctor_address, doctor_email, doctor_cell, doctor_designation, qualification, "
                + "department_id, doctor_photo) values (?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement pt;
//System.out.println("mmkkllffgghh");
       if (this.getPhoto() != null) {
            try {

                InputStream stfile = this.getPhoto().getInputstream();
                pt = DAO.Database.getConnection().prepareStatement(sql);

                pt.setString(1, this.getFname());
                pt.setString(2, this.getLname());
                pt.setString(3, this.getGender());
                pt.setString(4, dt);
                pt.setString(5, this.getAddres());
                pt.setString(6, this.getEmail());
                pt.setString(7, this.getPhone());
                pt.setString(8, this.getDesig());
                pt.setString(9, this.getQuali());
                pt.setInt(10, this.getDep_id());
                pt.setBinaryStream(11, stfile, this.getPhoto().getSize());
//            System.out.println("mmkkllffgghh");
               int i= pt.executeUpdate();
                if(i>0){
                 FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "All data save successfully");
        FacesContext.getCurrentInstance().addMessage(null, msg);
                }
                
                

                    clear();
//                JOptionPane.showMessageDialog(null, "Data Saved");

            } catch (IOException ex) {
        Logger.getLogger(regis.class.getName()).log(Level.SEVERE, null, ex);
      }
            catch (SQLException e) {
                Logger.getLogger(regis.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            FacesMessage msg = new FacesMessage("Please select image!!");
      FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<regis> geDepartment() {

        List<regis> data = new ArrayList<regis>();
        try {
            Connection con = DAO.Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;

            rs = st.executeQuery("select department_id, department_name from doctor_department");

            while (rs.next()) {
                regis rt = new regis();
                rt.setDep_id(rs.getInt("department_id"));
                rt.setDepart(rs.getString("department_name"));

                data.add(rt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    void clear(){
        setFname(null);
        setLname(null);
        setAddres(null);
        setDepart(null);
        setDesig(null);
        setEmail(null);
        setGender(null);
        setQuali(null);
        setPhone(null);
        setDep_id(0);
        setDate(null);
    }
}
