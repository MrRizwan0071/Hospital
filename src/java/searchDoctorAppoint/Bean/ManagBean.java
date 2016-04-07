package searchDoctorAppoint.Bean;

import DAO.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;
import javax.faces.model.SelectItem;

/**
 *
 * @author User
 */
@ManagedBean
@SessionScoped
public class ManagBean {

    public String department_name, name, day, designation, time, p_name, gender, phone;
    public Date a_date;
    public Integer fee;
    int  y, z;
    Integer x;
    List<ManagBean> data = new ArrayList<ManagBean>();

    public ManagBean() {

        day = "--Select--";

    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getA_date() {
        return a_date;
    }

    public void setA_date(Date a_date) {
        this.a_date = a_date;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public void insert() {
        try {


            Connection con = Database.getConnection();
            Statement st = con.createStatement();
            st.executeUpdate("insert into doctor_department(department_name)values('" + department_name + "')");
            System.out.println("no inseryt");
            JOptionPane.showMessageDialog(null, "Data Saved");
        } catch (Exception e) {
        }
    }

    public List<SelectItem> ComboType() {
        List<SelectItem> data = new ArrayList<SelectItem>();
        try {
            Connection con = Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery("select department_name from doctor_department ");
            while (rs.next()) {
                data.add(new SelectItem(rs.getString("department_name")));
            }

        } catch (Exception e) {
        }
        return data;
    }

    public List<SelectItem> nameCombo() {
        List<SelectItem> data = new ArrayList<SelectItem>();
        try {
            Connection con = Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery("select concat(doctor_fname,doctor_lname) flname from doctor_info ");
            while (rs.next()) {
                data.add(new SelectItem(rs.getString("flname")));
            }

        } catch (Exception e) {
        }
        return data;
    }

    public List getDo() {
        return data;
    }

    public List<SelectItem> dayCombo() {
        List<SelectItem> data = new ArrayList<SelectItem>();
        try {
            Connection con = Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery("select doctor_sch_day from doctor_schedule ");
            while (rs.next()) {
                data.add(new SelectItem(rs.getString("doctor_sch_day")));
            }

        } catch (Exception e) {
        }
        return data;
    }

    public void selctinfo() {

        Connection con = Database.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT doctor_department.department_name\n"
                    + " FROM    health_care.doctor_info doctor_info\n"
                    + "INNER JOIN\n"
                    + "  health_care.doctor_department doctor_department\n"
                    + " ON (doctor_info.department_id = doctor_department.department_id) where concat(doctor_info.doctor_fname,doctor_info.doctor_lname)='" + name + "';");

            while (rs.next()) {

                setDepartment_name(rs.getString("department_name"));

            }

        } catch (Exception e) {
        }
    }

    public void doctorName() {

        Connection con = Database.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT  concat(doctor_info.doctor_fname, doctor_info.doctor_lname) as fname\n"
                    + " FROM    health_care.doctor_info doctor_info\n"
                    + " INNER JOIN\n"
                    + " health_care.doctor_department doctor_department\n"
                    + " ON (doctor_info.department_id = doctor_department.department_id) where department_name='" + department_name + "';");


            while (rs.next()) {
                setName(rs.getString("fname"));


            }

        } catch (Exception e) {
        }
    }

    public List<ManagBean> allInfo() {
        List<ManagBean> data = new ArrayList<ManagBean>();
        try {
            Connection con = Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;


            rs = st.executeQuery("SELECT concat(doctor_info.doctor_fname,\n"
                    + "       doctor_info.doctor_lname) flname,\n"
                    + "       doctor_info.doctor_designation,\n"
                    + "       doctor_department.department_name,\n"
                    + "       doctor_schedule.doctor_sch_day,\n"
                    + "       doctor_schedule.doctor_fee,\n"
                    + "       doctor_schedule.doctor_sch_time\n"
                    + "  FROM    (   health_care.doctor_info doctor_info\n"
                    + "           INNER JOIN\n"
                    + "              health_care.doctor_department doctor_department\n"
                    + "           ON (doctor_info.department_id = doctor_department.department_id))\n"
                    + "       INNER JOIN\n"
                    + "          health_care.doctor_schedule doctor_schedule\n"
                    + "       ON (doctor_schedule.doctor_id = doctor_info.doctor_id) where concat(doctor_info.doctor_fname,doctor_info.doctor_lname)='" + name + "' OR doctor_department.department_name='" + department_name + "';");
            while (rs.next()) {
                ManagBean ma = new ManagBean();
                ma.setName(rs.getString("flname"));
                ma.setDesignation(rs.getString("doctor_designation"));
                ma.setDepartment_name(rs.getString("department_name"));
                ma.setTime(rs.getString("doctor_sch_time"));
                ma.setDay(rs.getString("doctor_sch_day"));
                ma.setFee(rs.getInt("doctor_fee"));
                //ma.setDepartment_name(rs.getString("department_name"));
                data.add(ma);
            }

        } catch (Exception e) {
        }
        return data;
    }

//    public void showAppoin() {
//
//        try {
//            Connection con = Database.getConnection();
//            Statement st = con.createStatement();
//            ResultSet rs = null;
//            rs = st.executeQuery("select concat(doctor_fname,doctor_lname) flname from doctor_info ");
//            while (rs.next()) {
//                setName(rs.getString("flname"));
//            }
//
//        } catch (Exception e) {
//        }
//
//    }
//    public void showSchd() {
//
//        try {
//            Connection con = Database.getConnection();
//            Statement st = con.createStatement();
//            ResultSet rs = null;
//            rs = st.executeQuery("select doctor_sch_day from doctor_schedule ");
//            while (rs.next()) {
//                setDay(rs.getString("doctor_sch_day"));
//            }
//
//        } catch (Exception e) {
//        }
//
//    }
    public void appoinment() {
        try {
            int id2 = 0;
            Statement st, st1, st2;
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String bd = df.format(getA_date());
            DateFormat dt = new SimpleDateFormat("yyyyMMdd");
            String patient_ids = dt.format(getA_date());
            //System.out.println(patient_ids);
            int ids = Integer.parseInt(patient_ids);
            st = Database.getConnection().createStatement();
            st2 = Database.getConnection().createStatement();
            st1 = Database.getConnection().createStatement();

            ResultSet rs2 = null, rs3 = null, rs4 = null;
            rs2 = st1.executeQuery("SELECT max(last_insert_id(patient_id)) FROM patient_info");
            if (rs2.next()) {
                id2 = rs2.getInt(1);
                //System.out.println(id2);
                if (ids <= id2) {
                    id2 = id2 + 1;

                    //System.out.println(id2);
                   // showid();
                }
            
//            System.out.println(patient_ids);

            ResultSet rs = null;
            int i = st.executeUpdate("insert into patient_info(patient_id,patient_name,gender,patient_cell)values ('" + id2 + "','" + p_name + "','" + gender + "','" + phone + "')");

            if (i > 0) {
//                System.out.println("insert");
                      //generateid();
                rs = st.executeQuery("SELECT max(last_insert_id(patient_id)) from patient_info where patient_name='" + p_name + "'");
                while (rs.next()) {
                    this.setY(rs.getInt(1)) ;
//                    
                    generateid();

                    
//                    clear();
                   //showid();
                }
            }}


            //showid();



        } catch (Exception e) {
        }
    }

    public void generateid() {
        try {
            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
            String bd1 = df1.format(getA_date());

            // String sql="(select doctor_id from doctor_info where concat(doctor_info.doctor_fname,doctor_info.doctor_lname)='"+name+"')";
            // String sql1="SELECT doctor_schedule.doctor_sch_id FROM health_care.doctor_schedule  where doctor_schedule.doctor_sch_time='"+time+"';";
            // System.out.println(sql);
            // System.out.println(sql1);
            System.out.println(bd1);
            //Statement st4=Database.getConnection().createStatement();
            Connection con = Database.getConnection();
            Statement st4 = con.createStatement();

            String sql = "insert into appointment ( patient_id, doctor_id, doctor_sch_id, appoint_date) values ('" + x + "',(select doctor_id from doctor_info where concat(doctor_info.doctor_fname,doctor_info.doctor_lname)='" + name + "'),(SELECT doctor_schedule.doctor_sch_id FROM health_care.doctor_schedule  where doctor_schedule.doctor_sch_time='" + time + "'),'" + bd1 + "')";
            int s = st4.executeUpdate(sql);


//            if (i > 0) {
////                FacesContext context = FacesContext.getCurrentInstance();
////                context.addMessage(null, new FacesMessage("Your Id is!", "" + x));
//                // return "Searchdoctor";
//                // System.out.println("success");
//                showid();
//            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       // return "Searchdoctor";

    }

    public void clear() {
        //public  String department_name, name, day, designation, time, p_name, gender, phone;
        String name = "";
        String depart_name = "";
        String day = "";
        String time = "";
        String p_name = "";
        String gender = "";
        String phone = "";

    }

    public String showid() {

        // List<ManagBean> data = new ArrayList<ManagBean>();

        try {
            Connection con = Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(last_insert_id(patient_id)) from patient_info ");
            while (rs.next()) {
               x=rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return "Your ID is : '"+x+"'   Please save your id";

    }

    public static void main(String[] args) {

        ManagBean b = new ManagBean();
        b.generateid();
    }
//public void appoin(){
//
//    try {
//        Statement st1;
//         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            String bd = df.format(getA_date());
//            y=0;
//             st1 = Database.getConnection().createStatement();
//             
//         int results=st1.executeUpdate("insert into appointment(doctor_id,doctor_sch_id,patient_id,appoint_date,is_visited)values"
//                    + "(select doctor_id from doctor_info where concat(doctor_fname,doctor_lname)='"
//                    + name + "'),"
//                    + " (select doctor_sch_id from doctor_schedule where doctor_sch_time='"
//                    + time + "'),'"
//                    + x + "','" + bd + "','" +y+"')");
//
//                     JOptionPane.showMessageDialog(null, "Your appointment Id is=" + y);
//    } catch (Exception e) {
//    }
//    
//}
    //    public static void main(String[] args) {
//        ManagBean ms = new ManagBean();
//        ms.setName("AshrafulAlam");
//        ms.appon();
//        
//        for (Iterator<ManagBean> it = ms.; it.hasNext();) {
//            ManagBean ms1 = it.next();
//            System.out.println(ms1.getDepartment_name());
//        }
//      
}
