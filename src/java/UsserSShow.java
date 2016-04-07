
import DAO.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kaniz
 */
@ManagedBean
@RequestScoped
public class UsserSShow {

    String un, passw, uroles;
    int ee_ids;

    /**
     * Creates a new instance of UsserSShow
     */
    public UsserSShow() {
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public String getUroles() {
        return uroles;
    }

    public void setUroles(String uroles) {
        this.uroles = uroles;
    }

    public int getEe_ids() {
        return ee_ids;
    }

    public void setEe_ids(int ee_ids) {
        this.ee_ids = ee_ids;
    }

    public List<UsserSShow> getAllData() {
        List<UsserSShow> data = new ArrayList<UsserSShow>();
        try {
            Connection con = null;
            Statement stm = null;
            ResultSet rs = null;
            con = Database.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM health_care.userinfo;");
            //order by test_id desc limit 10;
            while (rs.next()) {
                UsserSShow su = new UsserSShow();
                su.setUn(rs.getString("user"));
                su.setPassw(rs.getString("pass"));
                su.setUroles(rs.getString("role"));
                su.setEe_ids(rs.getInt("id"));

                data.add(su);
            }
        } catch (Exception e) {
        }
        return data;
    }
}
