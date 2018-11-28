/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.UniversityBean;

/**
 *
 * @author admin
 */
public class ProfileDAOImpl implements ProfileDAO {

    private static ArrayList selectUniversitiesFromDB(String query) {
        Connection DBConn = null;
        ArrayList universityCollection = new ArrayList();
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/CreateUserDB";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String description, name;
            String[] majors = null;
            String[] sports = null;
            String[] housing = null;
            int tuition, enrollment, graduation;
            UniversityBean aUniversityBean;
            while (rs.next()) {
                String a = rs.getString("Majors");
                majors = a.split(";");
                String b = rs.getString("Sports");
                sports = b.split(";");
                String c = rs.getString("Housing");
                housing = c.split(";");
                description = rs.getString("description");
                tuition = rs.getInt("Tuition");
                enrollment = rs.getInt("Enrollment");
                graduation = rs.getInt("Graduation");
                name = rs.getString("name");

                aUniversityBean = new UniversityBean(name, majors, sports, housing, description, tuition, enrollment, graduation);

                universityCollection.add(aUniversityBean);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return universityCollection;
    }

    @Override
    public ArrayList findByName(String input) {
        // if interested in matching wild cards, use: LIKE and '%" + aName + "%'";
        String query = "SELECT * FROM ITKSTU.UNIVERSITY"
                + " where majors like '%" + input + "%'"
                + " Union"
                + " SELECT * FROM ITKSTU.UNIVERSITY"
                + " where sports like '%" + input + "%'"
                + " Union"
                + " SELECT * FROM ITKSTU.UNIVERSITY"
                + " where housing like '%" + input + "%'"
                + " Union"
                + " SELECT * FROM ITKSTU.UNIVERSITY"
                + " where description like '%" + input + "%'"
                + " Union"
                + " SELECT * FROM ITKSTU.UNIVERSITY"
                + " where name like '%" + input + "%'";

        ArrayList universities = selectUniversitiesFromDB(query);
        return universities;
    }
}
