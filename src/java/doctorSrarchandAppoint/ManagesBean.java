package doctorSrarchandAppoint;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.JOptionPane;
import javax.faces.model.SelectItem;

/**
 *
 * @author User
 */
@ManagedBean
@SessionScoped
public class ManagesBean {

    String department_name, name, day, designation, time, p_name, gender, phone;
    Date a_date;
    Integer fee;
    int x,y;

    public ManagesBean() {

        day = "--Select--";

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
public void selctinfo(){
    
}
    public void insert() {
        try {


            Connection con = DAO.Database.getConnection();
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
            Connection con = DAO.Database.getConnection();
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
            Connection con = DAO.Database.getConnection();
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

    public List<SelectItem> dayCombo() {
        List<SelectItem> data = new ArrayList<SelectItem>();
        try {
            Connection con =DAO.Database.getConnection();
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

    public List<ManagesBean> allInfo() {
        List<ManagesBean> data = new ArrayList<ManagesBean>();
        try {
            Connection con = DAO.Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
            
            
            rs = st.executeQuery("SELECT concat(doctor_info.doctor_fname,\n" +
"       doctor_info.doctor_lname) flname,\n" +
"       doctor_info.doctor_designation,\n" +
"       doctor_department.department_name,\n" +
"       doctor_schedule.doctor_sch_day,\n" +
"       doctor_schedule.doctor_fee,\n" +
"       doctor_schedule.doctor_sch_time\n" +
"  FROM    (   health_care.doctor_info doctor_info\n" +
"           INNER JOIN\n" +
"              health_care.doctor_department doctor_department\n" +
"           ON (doctor_info.department_id = doctor_department.department_id))\n" +
"       INNER JOIN\n" +
"          health_care.doctor_schedule doctor_schedule\n" +
"       ON (doctor_schedule.doctor_id = doctor_info.doctor_id) where concat(doctor_info.doctor_fname,doctor_info.doctor_lname)='" + name + "';");
            while (rs.next()) {
                ManagesBean ma = new ManagesBean();
                ma.setName(rs.getString("flname"));
                ma.setDesignation(rs.getString("doctor_designation"));
                ma.setDepartment_name(rs.getString("department_name"));
                ma.setTime(rs.getString("doctor_sch_time"));
                ma.setDay(rs.getString("doctor_sch_day"));
                ma.setFee(rs.getInt("doctor_fee"));
                data.add(ma);
            }

        } catch (Exception e) {
        }
        return data;
    }

    public void showAppoin() {

        try {
            Connection con = DAO.Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery("select concat(doctor_fname,doctor_lname) flname from doctor_info ");
            while (rs.next()) {
                setName(rs.getString("flname"));
            }

        } catch (Exception e) {
        }

    }

    public void showSchd() {

        try {
            Connection con = DAO.Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery("select doctor_sch_day from doctor_schedule ");
            while (rs.next()) {
                setDay(rs.getString("doctor_sch_day"));
            }

        } catch (Exception e) {
        }

    }

    public void appoinment() {
        try {
            int id2=0;
            Statement st,st1,st2;
              DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String bd = df.format(getA_date());
            DateFormat dt=new SimpleDateFormat("yyyyMMdd");
            String patient_ids=dt.format(getA_date());
            int ids=Integer.parseInt(patient_ids);
             st = DAO.Database.getConnection().createStatement();
             st2 = DAO.Database.getConnection().createStatement();
             st1 = DAO.Database.getConnection().createStatement();
            ResultSet rs = null;
            ResultSet rs2=null,rs3=null,rs4=null;
            rs2=st1.executeQuery("select last_insert_id(patient_id) FROM patient_info");
            while(rs2.next()){
                 id2=rs2.getInt(1);
                System.out.println(id2);
                 if(ids<=id2){
                    id2=id2+1;
                    
                    System.out.println(id2);
                }
            }
//            System.out.println(patient_ids);
            

            st.executeUpdate("insert into patient_info(patient_id,patient_name,gender,patient_cell)values ('"+id2+"','" + p_name + "','" + gender + "','" + phone + "')");


            rs = st.executeQuery("select patient_id from patient_info where patient_name='" + p_name + "'");
            while (rs.next() == true) {
                x = rs.getInt("patient_id");

            }
            JOptionPane.showMessageDialog(null, "Your appointment Id is="+(x));
           
            
                     appoin();
        } catch (Exception e) {
        }
    }
public void appoin(){

    try {
        Statement st1;
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String bd = df.format(getA_date());
            y=0;
             st1 =  DAO.Database.getConnection().createStatement();
             
         int results=st1.executeUpdate("insert into appointment(doctor_id,doctor_sch_id,patient_id,appoint_date,is_visited)values"
                    + "(select doctor_id from doctor_info where concat(doctor_fname,doctor_lname)='"
                    + name + "'),"
                    + " (select doctor_sch_id from doctor_schedule where doctor_sch_time='"
                    + time + "'),'"
                    + (x) + "','" + bd + "','" +(y)+"')");

                     JOptionPane.showMessageDialog(null, "Your appointment Id is=" + y);
    } catch (Exception e) {
    }
    
}
  
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
