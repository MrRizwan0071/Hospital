/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package purchaseReport;

import DAO.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import stockBean.StockSupport;

/**
 *
 * @author NAHID
 */
@ManagedBean
@RequestScoped
public class PurReport {

    Date fromDate, toDate, purDate;

    public Date getPurDate() {
        return purDate;
    }

    public void setPurDate(Date purDate) {
        this.purDate = purDate;
    }
    String med_name, med_group;
    int amount;
    Double price;
    private ArrayList<PurReport> list = new ArrayList<PurReport>();

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public String getMed_group() {
        return med_group;
    }

    public void setMed_group(String med_group) {
        this.med_group = med_group;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<PurReport> getTableData() {
          
        
        return list;
    }

    public void selectDate() {
        Date dt=this.getToDate();
                DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
               String d= df.format(dt);
        Date dt1=this.getFromDate();
                DateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
               String d1= df1.format(dt1);

       try {
            Connection con = null;
            Statement stm, stm1, stm2 = null;
            ResultSet rs, rs1, rs2 = null;
            con = Database.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT medicine_purchase.med_pur_date,\n"
                    + "medicine_purchase.med_pur_amount,\n"
                    + "medicine_list.med_name,\n"
                    + "medicine_purchase_line.med_quantity,\n"
                    + "medicine_purchase_line.med_pur_price,\n"
                    + "medicine_group.med_group_name\n"
                    + "FROM    (   (   health_care.medicine_list medicine_list\n"
                    + "INNER JOIN\n"
                    + "health_care.medicine_group medicine_group\n"
                    + "ON (medicine_list.med_group_id = medicine_group.med_group_id))\n"
                    + "INNER JOIN\n"
                    + "health_care.medicine_purchase_line medicine_purchase_line\n"
                    + "ON (medicine_purchase_line.med_id = medicine_list.med_id))\n"
                    + "INNER JOIN\n"
                    + "health_care.medicine_purchase medicine_purchase\n"
                    + "ON (medicine_purchase_line.med_pur_id = medicine_purchase.med_pur_id)"
                    + "WHERE medicine_purchase.med_pur_date between '"+d1+"' and '"+d+"';");
           
            while (rs.next()) {
                PurReport report = new PurReport();
                report.setMed_name(rs.getString("med_name"));
                System.out.println(getMed_name());
                report.setMed_group(rs.getString("med_group_name"));
                report.setPurDate(rs.getDate("med_pur_date"));
                report.setAmount(rs.getInt("med_quantity"));
                report.setPrice(rs.getDouble("med_pur_price"));
                list.add(report);
            }
        } catch (Exception e) {
        }



    }

    public PurReport() {
        this.fromDate = new Date();
        this.toDate = new Date();
    }

    
}
