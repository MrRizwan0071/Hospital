package Report_view;

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
public class reportxyz {
    
    String repo,test,patient_name;
    int pat_id;
    Date date;

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

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
    public reportxyz(){
    }
    public void abc(){
        
    }
    public List<reportxyz> Port(){
        List<reportxyz>data=new ArrayList<reportxyz>();
        
        try {
            Connection con=DAO.Database.getConnection();
            Statement st=con.createStatement();
            ResultSet rs=null;
            
            
            rs=st.executeQuery("SELECT distinct(patient_info.patient_name),\n" +
"       test.test_name,\n" +
"       diagonosis_line.report,\n" +
"       diagonosis_line.report_date,\n" +
"       diagonosis_line.test_date\n" +
"  FROM    (   (   health_care.diagonosis_line diagonosis_line\n" +
"               INNER JOIN\n" +
"                  health_care.test test\n" +
"               ON (diagonosis_line.test_id = test.test_id))\n" +
"           INNER JOIN\n" +
"              health_care.diagonosis diagonosis\n" +
"           ON (diagonosis_line.diag_id = diagonosis.diag_id))\n" +
"       INNER JOIN\n" +
"          health_care.patient_info patient_info\n" +
"       ON (diagonosis.patient_id = patient_info.patient_id) where diagonosis.patient_id='"+pat_id+"'");
      
            while(rs.next()){
                reportxyz rp=new reportxyz();
//                System.out.println(pat_id);
                rp.setPatient_name(rs.getString(1));
                rp.setTest(rs.getString(2));
                rp.setRepo(rs.getString(3));
                rp.setDate(rs.getDate(4));
                
                data.add(rp);
            
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return data;
    }
    
     public void patientPrescription() {
        try {
            Connection con = Database.getConnection();
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery("select patient_name from patient_info where patient_id='" + pat_id + "'");
            while (rs.next()) {
                setPatient_name(rs.getString(1));
            }
        } catch (Exception e) {
        }
    }
}
