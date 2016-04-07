package patientTest.Bean;

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
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

@ManagedBean
@SessionScoped
public class PatientTest {

    private static final long serialVersionUID = 1L;
    private String test_name;
    Date date;
    OrderBean order;
    String patient_name, doctor_name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMed_id() {
        return med_id;
    }

    public void setMed_id(int med_id) {
        this.med_id = med_id;
    }
    int age, patient_id, doctor_id, med_id;

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

    public PatientTest() {
        date = new Date();
        test_name = "--Select--";
//        this.patient_name = "Rahim";


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

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }
    private static final ArrayList<OrderBean> orderList = new ArrayList<OrderBean>();

    public ArrayList<OrderBean> getOrderList() {
        return orderList;
    }

    public String addAction() {
        OrderBean orderitem = new OrderBean(this.test_name, this.date);
        orderList.add(orderitem);

        test_name = "--Select--";

        return null;
    }

    public void onEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Item Edited", ((OrderBean) event.getObject()).getTest_name());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Item Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        orderList.remove((OrderBean) event.getObject());
    }

    public List<SelectItem> getComBobox() {
        List<SelectItem> total = new ArrayList<SelectItem>();
        try {
            Connection con = (Connection) Database.getConnection();
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery("select test_name from test");
            while (rs.next()) {
                total.add(new SelectItem(rs.getString("test_name")));
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
            ResultSet rs = st.executeQuery("SELECT test_name from  test where test_id=(Select test_id from test where test_name='" + test_name + "')");
            while (rs.next()) {
                setTest_name(rs.getString("test_name"));
            }
        } catch (Exception e) {
        }
    }
//
//    public List<PatientTest> pationtInfo() {
//        String sql = "SELECT * FROM patient_info ";
//        List<PatientTest> list = new ArrayList<PatientTest>();
//        try {
//            Connection con = Database.getConnection();
//            Statement st = con.createStatement();
//            ResultSet rs=st.executeQuery(sql);
//            while(rs.next()){
//                PatientTest p = new PatientTest();
//                p.setPatient_name(rs.getString("patient_name"));
//                p.setPatient_id(rs.getInt("patient_id"));
//                list.add(p);
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }
//    
//    public List<PatientTest> doctiorId() {
//        String sql = "SELECT * FROM doctor_info ";
//        List<PatientTest> list = new ArrayList<PatientTest>();
//        try {
//            Connection con = Database.getConnection();
//            Statement st = con.createStatement();
//            ResultSet rs=st.executeQuery(sql);
//            while(rs.next()){
//                PatientTest p = new PatientTest();
//                p.setDoctor_name(rs.getString("doctor_fname"));
//                p.setDoctor_id(rs.getInt("doctor_id"));
//                list.add(p);
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }

    public void patientSelect() {
       
        try {
            Connection con = (Connection) Database.getConnection();
            Statement st = (Statement) con.createStatement();
//            ResultSet rs5 = st.executeQuery("SELECT diagonosis.patient_id,\n"
//                    + "       patient_info.patient_name,\n"
//                    + "       doctor_info.doctor_fname,\n"
//                    + "       doctor_info.doctor_lname,\n"
//                    + "       test.test_name\n"
//                    + "  FROM    (   (   (   health_care.diagonosis diagonosis\n"
//                    + "                   INNER JOIN\n"
//                    + "                      health_care.doctor_info doctor_info\n"
//                    + "                   ON (diagonosis.doctor_id = doctor_info.doctor_id))\n"
//                    + "               INNER JOIN\n"
//                    + "                  health_care.patient_info patient_info\n"
//                    + "               ON (diagonosis.patient_id = patient_info.patient_id))\n"
//                    + "           INNER JOIN\n"
//                    + "              health_care.diagonosis_line diagonosis_line\n"
//                    + "           ON (diagonosis_line.diag_id = diagonosis.diag_id))\n"
//                    + "       INNER JOIN\n"
//                    + "          health_care.test test\n"
//                    + "       ON (diagonosis_line.test_id = test.test_id) where patient_info.patient_id='" + patient_id + "'");
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
                setDoctor_id(rs5.getInt("doctor_id"));
                setPatient_id(rs5.getInt("patient_id"));
                setDoctor_name(rs5.getString("flname"));
//                System.out.println(patient_name+doctor_name);
            }
        } catch (Exception e) {
        }
    }

    public void save() {
        
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Statement st, st1, st2, st3;
            ResultSet rs, rs1, rs2, rs3;
            int diag_id;
            String dt = df.format(getDate());
            Connection con = (Connection) Database.getConnection();
            st = (Statement) con.createStatement();
            st1 = (Statement) con.createStatement();
            st2 = (Statement) con.createStatement();
            st3 = (Statement) con.createStatement();
System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkk");
//            st3.executeUpdate("insert into diagonosis(patient_id) values ((select patient_id from appointment where (select patient_id from patient_info where patient_name='"+patient_name+"')");
//            st.executeUpdate("insert into diagonosis (patient_id, doctor_id, diag_date) values "
//                    + "((select patient_id from patient_info where patient_name='" + this.patient_name + "'),"
//                    + "(select doctor_id from doctor_info where Concat(doctor_fname,' ',doctor_lname) ='"
//                    + this.doctor_name + "'),'" + dt + "');");
            st.executeUpdate("insert into diagonosis(patient_id, doctor_id, diag_date) "
                    + "values('"+this.getPatient_id()+"','"+this.getDoctor_id()+"','"+dt+"')");
//            System.out.println(patient_id);
            rs = st1.executeQuery("SELECT MAX(last_insert_id(diag_id)) FROM diagonosis");

            while (rs.next()) {
                int mg = 0;
                diag_id = rs.getInt(1);
                // System.out.println(diag_id);
                for (int i = 0; i < orderList.size(); i++) {
                    test_name = orderList.get(i).getTest_name();
                    //System.out.println(test_name);
                    mg = st2.executeUpdate("insert into diagonosis_line (diag_id, test_id) values ('" + diag_id + "', (select test_id from test where test_name='" + this.test_name + "'))");
               
                }
                if (mg > 0) {
                    FacesMessage msg1 = new FacesMessage("Data is saved");
                    FacesContext.getCurrentInstance().addMessage(null, msg1);
                }
            }
        } catch (Exception e) {
        }
    }
}