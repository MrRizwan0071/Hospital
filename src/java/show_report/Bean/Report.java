package show_report.Bean;

import DAO.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class Report {
    
    String repo,test;
    int pat_id;
    Date date;
List<Report> data=new ArrayList<Report>();
    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPat_id() {
        return pat_id;
    }

    public void setPat_id(int pat_id) {
        this.pat_id = pat_id;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }
    public Report(){
    }
    
    public void etPort(){
        data.clear();
       // List<report>data=new ArrayList<report>();
        
        try {
            Connection con=Database.getConnection();
            Statement st=con.createStatement();
            ResultSet rs=null;
            
            
            rs=st.executeQuery("SELECT test.test_name, "
                    + "diagonosis_line.report, diagonosis_line.report_date, "
                    + "diagonosis.patient_id "
                    + "FROM    (   health_care.diagonosis_line diagonosis_line  "
                    + "INNER JOIN  health_care.diagonosis diagonosis  "
                    + "ON (diagonosis_line.diag_id = diagonosis.diag_id))  "
                    + "INNER JOIN   health_care.test test   "
                    + "ON (diagonosis_line.test_id = test.test_id) "
                    + "where diagonosis.patient_id='"+pat_id+"'");
      
            while(rs.next()){
                Report rp=new Report();
//                System.out.println(pat_id);
                rp.setTest(rs.getString("test_name"));
                rp.setRepo(rs.getString("report"));
                rp.setDate(rs.getDate("report_date"));
                rp.setPat_id(rs.getInt("patient_id"));
                data.add(rp);
            
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
       
    }
    
    public void clear(){
     
    }
     public List getDt(){
    return data;
    }
}

  
