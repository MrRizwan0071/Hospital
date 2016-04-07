package Report_view;

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
public class report {
    
    String repo,test;
    int pat_id;
    Date date;

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
    public report(){
    }
    
    public List<report>Port(){
        List<report>data=new ArrayList<report>();
        
        try {
            Connection con=DAO.Database.getConnection();
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
                report rp=new report();
//                System.out.println(pat_id);
                rp.setTest(rs.getString("test_name"));
                rp.setRepo(rs.getString("report"));
                rp.setDate(rs.getDate("report_date"));
                
                data.add(rp);
            
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return data;
    }
}
