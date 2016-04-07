/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diagnosis.Bean;

import patientPescribe.Bean.*;
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
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

@ManagedBean
@RequestScoped
public class Diagnosis {

    private static final long serialVersionUID = 1L;
   
    Date date, test_date, report_date;
    OrderBeanP order;
    String patient_name, doctor_name,report,test_name,dts,tstdt,rptdt;
    int patient_id, doctor_id,test_id;

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
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

    public Date getTest_date() {
        return test_date;
    }

    public void setTest_date(Date test_date) {
        this.test_date = test_date;
    }

    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public String getDts() {
        return dts;
    }

    public void setDts(String dts) {
        this.dts = dts;
    }

    public String getReport() {
        return report;
    }

    public String getTstdt() {
        return tstdt;
    }

    public void setTstdt(String tstdt) {
        this.tstdt = tstdt;
    }

    public String getRptdt() {
        return rptdt;
    }

    public void setRptdt(String rptdt) {
        this.rptdt = rptdt;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

   

    public Diagnosis() {
       selectDate();
        test_date = new Date();
        report_date=new Date();
  
//        this.patient_name = "Rahim";
//        this.doctor_name = "Ab Khan";

    }

  
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        OrderBeanP orderitem = new OrderBeanP(this.test_name, this.test_date, this.report, this.report_date);
        orderList.add(orderitem);
        
      

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
            int diag_id=0;
            Connection con = (Connection) Database.getConnection();
            Statement st = (Statement) con.createStatement();
            Statement st9 = (Statement) con.createStatement();
            ResultSet rs9=st9.executeQuery("select MAX(Last_insert_id(diag_id)) from diagonosis where patient_id='"+patient_id+"'");
            while(rs9.next()){
                diag_id=rs9.getInt(1);
            }
            ResultSet rs = st.executeQuery("SELECT test.test_name,\n" +
"       diagonosis_line.test_id,\n" +
"       diagonosis.patient_id,\n" +
"       diagonosis.diag_id\n" +
"  FROM    (   health_care.diagonosis_line diagonosis_line\n" +
"           INNER JOIN\n" +
"              health_care.diagonosis diagonosis\n" +
"           ON (diagonosis_line.diag_id = diagonosis.diag_id))\n" +
"       INNER JOIN\n" +
"          health_care.test test\n" +
"       ON (diagonosis_line.test_id = test.test_id) where diagonosis.patient_id='"+patient_id+"' and diagonosis.diag_id='"+diag_id+"' ;");
            //(select patient_id from patient_info where patient_name='"+patient_name+"')
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
            ResultSet rs = st.executeQuery("select test_id from test where test_name='"+test_name+"'");
            while (rs.next()) {
                setTest_id(rs.getInt("test_id"));
            }
        } catch (Exception e) {
        }
    }

//    public List getDouses() {
//        List list = new ArrayList();
//        list.add("1-0-1");
//        list.add("1-1-1");
//        list.add("0-0-1");
//        list.add("1-1-1-1");
//        list.add("1-0-0");
//        return list;
//    }
//
    public void save() {
        try {
         
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dtt = df.format(getTest_date());
            String dtr = df.format(getReport_date());
            Statement st, st1;
            ResultSet rs, rs1;
            int pres_id;
            Connection con = (Connection) Database.getConnection();
            st = (Statement) con.createStatement();
            st1 = (Statement) con.createStatement();            
//            st.executeUpdate("insert into diagonosis (patient_id, doctor_id, pres_date, next_consult_date,remarks) values ((select patient_id from patient_info where patient_name='" + this.patient_name + "'),(select doctor_id from doctor_info where Concat(doctor_fname,' ',doctor_lname)='" + this.doctor_name + "'),'" + dt + "','" + dt1 + "','"+textarea+"');");
//            rs = st1.executeQuery("SELECT MAX(last_insert_id(pres_id)) FROM prescription");
//            while (rs.next()) {
                int mg=0;
//                pres_id = rs.getInt(1);
//                // System.out.println(diag_id);
                for (int i = 0; i < orderList.size(); i++) {
                    test_name = orderList.get(i).getTest_name();
                    test_date = orderList.get(i).getTest_date();
                    report = orderList.get(i).getReport();
                    report_date = orderList.get(i).getReport_date();
                    //System.out.println(test_name);
                mg=st.executeUpdate("UPDATE diagonosis_line SET report='"+report+"', test_date='"+dtt+"', report_date='"+dtr+"' where test_id=(Select test_id from test where test_name='"+test_name+"')");
                    System.out.println(mg);
                    
                }
                if(mg>0){
                   FacesMessage msg1=new FacesMessage("Data is saved");
                 FacesContext.getCurrentInstance().addMessage(null,msg1);
                 
               }           
        } catch (Exception e) {
        }
    }

    public void selectDate(){
        try {
         Connection con = (Connection) Database.getConnection();
            Statement st = (Statement) con.createStatement();
            ResultSet rs5=st.executeQuery("select * from diagonosis where patient_id='"+patient_id+"' ");
            while(rs5.next()){
                Date dt=rs5.getDate("diag_date");
                DateFormat df=new SimpleDateFormat("dd-MM-yyyy");
               String d= df.format(dt);
                setDts(d);
            }
        } catch (Exception e) {
        }
  
    }
    
     public void doctor() {
        try {
            Connection con = (Connection) Database.getConnection();
            Statement st = (Statement) con.createStatement();
            ResultSet rs5;
            rs5 = st.executeQuery("SELECT diagonosis.patient_id,\n" +
"       patient_info.patient_name,\n" +
"       doctor_info.doctor_fname,\n" +
"       doctor_info.doctor_lname,\n" +
"       test.test_name\n" +
"  FROM    (   (   (   health_care.diagonosis diagonosis\n" +
"                   INNER JOIN\n" +
"                      health_care.doctor_info doctor_info\n" +
"                   ON (diagonosis.doctor_id = doctor_info.doctor_id))\n" +
"               INNER JOIN\n" +
"                  health_care.patient_info patient_info\n" +
"               ON (diagonosis.patient_id = patient_info.patient_id))\n" +
"           INNER JOIN\n" +
"              health_care.diagonosis_line diagonosis_line\n" +
"           ON (diagonosis_line.diag_id = diagonosis.diag_id))\n" +
"       INNER JOIN\n" +
"          health_care.test test\n" +
"       ON (diagonosis_line.test_id = test.test_id) where patient_info.patient_id='"+patient_id+"'");
            while (rs5.next()) {
               setPatient_name(rs5.getString(2));
               
               String doc_fname=rs5.getString(3);
               String doc_lname=rs5.getString(4);
                setDoctor_name(doc_fname+" "+doc_lname);
                System.out.println(patient_name+" "+doctor_name);
            }
        } catch (Exception e) {
        }
    }
}
