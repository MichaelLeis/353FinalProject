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
import model.PassHash;
import model.StudentAccount;
import model.UniversityAccount;
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
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String description, name;
            String[] majors = null;
            String[] sports = null;
            String[] housing = null;
            int tuition, gradRate, avgFinAid, ID;
            UniversityBean aUniversityBean;
            while (rs.next()) {
                String a = rs.getString("Majors");
                majors = a.split(";");
                String b = rs.getString("Sports");
                sports = b.split(";");
                String c = rs.getString("Housing");
                housing = c.split(";");
                description = rs.getString("DESCRIPTION");
                name = rs.getString("UNIVNAME");
                tuition = rs.getInt("TUTITION");
                avgFinAid = rs.getInt("AVGFINAID");
                gradRate = rs.getInt("GRADRATE");
                ID = rs.getInt("ID");

                aUniversityBean = new UniversityBean(majors, sports, housing, description, name, tuition, gradRate, avgFinAid, ID);

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
        String query = "SELECT * FROM linkedU.UNIVHP"
                + " where majors like '%" + input + "%'"
                + " Union"
                + " SELECT * FROM linkedU.UNIVHP"
                + " where sports like '%" + input + "%'"
                + " Union"
                + " SELECT * FROM linkedU.UNIVHP"
                + " where housing like '%" + input + "%'"
                + " Union"
                + " SELECT * FROM linkedU.UNIVHP"
                + " where description like '%" + input + "%'"
                + " Union"
                + " SELECT * FROM linkedU.UNIVHP"
                + " where univName like '%" + input + "%'";

        ArrayList universities = selectUniversitiesFromDB(query);
        return universities;
    }
    
    private String[] userIDs = new String[10000];

//    @Override
//    public int createProfile(UniversityAccount aProfile) {
//        try {
//            Class.forName("org.apache.derby.jdbc.ClientDriver");
//        } catch (ClassNotFoundException e) {
//            System.err.println(e.getMessage());
//            System.exit(0);
//        }
//
//        int rowCount = 0;
//        try {
//            String myDB = "jdbc:derby://localhost:1527/CreateUserDB";
//            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
//
//            PassHash hash = new PassHash();
//            String insertString, insertString2;
//            Statement stmt = DBConn.createStatement();
//            insertString = "INSERT INTO PROJECT353.USERS VALUES ('"
//                    + aProfile.getFirstName()
//                    + "','" + aProfile.getLastName()
//                    + "','" + aProfile.getUserID()
//                    + "','" + hash.hashPass(aProfile.getPassword())
//                    + "','" + aProfile.getEmail()
//                    + "','" + aProfile.getSecQuestion()
//                    + "','" + aProfile.getSecAnswer()
//                    + "')";
//
//            insertString2 = "INSERT INTO PROJECT353.LOGININFO VALUES ('"
//                    + aProfile.getUserID()
//                    + "','" + hash.hashPass(aProfile.getPassword())
//                    + "')";
//
//            rowCount = stmt.executeUpdate(insertString);
//            rowCount = stmt.executeUpdate(insertString2);
//
//            System.out.println("insert string =" + insertString);
//            DBConn.close();
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//
//        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
//        return rowCount;
//    }
//
    @Override
    public int updateStudentHP(StudentAccount aProfile) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            Connection DBConn = DriverManager.getConnection(myDB, "student", "itkstu"); //my pass and user are backwards from normal
            
            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "UPDATE LINKEDU.STUDENTHP "
                    + "SET ACT = " + aProfile.getACT()
                    + ", SAT = " + aProfile.getSAT()
                    + ", PSAT = " + aProfile.getPSAT()
                    + ", NMSQT =" + aProfile.getNMSQT()
                    + ", ESSAY ='" + aProfile.getEssay()
                    + "', MAJORS ='" + aProfile.getMajors()
                    + "', VIDEOLINKS ='" + aProfile.getVideoLink()
                    + "', PICTURELINKS = '" + aProfile.getPictureLink()
                    + "' WHERE ID = 1";

            rowCount = stmt.executeUpdate(insertString);

            System.out.println("insert string =" + insertString);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;
    }
    
        @Override
    public int updateStudentAcc(StudentAccount aProfile) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            Connection DBConn = DriverManager.getConnection(myDB, "student", "itkstu"); //my pass and user are backwards from normal
            
            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "UPDATE LINKEDU.STUDENTACCT "
                    + "SET FIRSTN = '" + aProfile.getFirstN()
                    + "', LASTN = '" + aProfile.getLastN()
                    + "', EMAIL = '" + aProfile.getEmail()
                    + "', PASS ='" + aProfile.getPass()
                    + "', USERN ='" + aProfile.getUserN()
                    + "', ICON = " + aProfile.getIcon()
                    + " WHERE ID = 1";

            rowCount = stmt.executeUpdate(insertString);

            System.out.println("insert string =" + insertString);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;
    }
    
    @Override
    public int updateUnivHP(UniversityAccount aProfile) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            Connection DBConn = DriverManager.getConnection(myDB, "student", "itkstu");
            
            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "UPDATE LINKEDU.UNIVHP "
                    + "SET DESCRIPTION = '" + aProfile.getDescription()
                    + "', MAJORS ='" + aProfile.getMajors()
                    + "', SPORTS ='" + aProfile.getSports()
                    + "', HOUSING ='" + aProfile.getHousing()
                    + "', TUTITION =" + aProfile.getTuition() //tuition is spelled incorrecttly in the db
                    + ", GRADRATE =" + aProfile.getGradRate()
                    + ", AVGFINAID =" + aProfile.getAvgFinAid() 
                    + " WHERE ID = 1";

            rowCount = stmt.executeUpdate(insertString);

            System.out.println("insert string =" + insertString);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;
    }

    public int updateUnivAcc(UniversityAccount aProfile) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            Connection DBConn = DriverManager.getConnection(myDB, "student", "itkstu"); //my pass and user are backwards from normal
            
            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "UPDATE LINKEDU.UNIVACCT "
                    + "SET FIRSTN = '" + aProfile.getFirstN()
                    + "', LASTN = '" + aProfile.getLastN()
                    + "', EMAIL = '" + aProfile.getEmail()
                    + "', PASS ='" + aProfile.getPass()
                    + "', USERN ='" + aProfile.getUserN()
                    + "', JOBTITLE ='" + aProfile.getJobTitle()
                    + "', UNIVNAME ='" + aProfile.getUnivN()
                    + "' WHERE ID = 1";

            rowCount = stmt.executeUpdate(insertString);

            System.out.println("insert string =" + insertString);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;
    }
     
    @Override
    public String[] findUserIDs() {

        String query = "SELECT * FROM PROJECT353.USERS";
        selectUnivAcctsFromDB(query);
        return userIDs;

    }

    private UniversityAccount selectUnivAcctsFromDB(String query) {
        UniversityAccount aUniversityAccount = null;
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String first, last, password, email, userName, jobTitle, univName;
            int ID;
            int i = 0;

            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
                first = rs.getString("FIRSTN");
                last = rs.getString("LASTN");
                ID = rs.getInt("ID");
                password = rs.getString("PASS");
                email = rs.getString("EMAIL");
                userName = rs.getString("USERN");
                jobTitle = rs.getString("JOBTITLE");
                univName = rs.getString("UNIVNAME");

                userIDs[i] = userName;
                i++;

                aUniversityAccount = new UniversityAccount(first, last, email, ID, password, userName, jobTitle, univName);

            }
            i = 0;

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
        return aUniversityAccount;
    }

    @Override
    public UniversityAccount findUserName(String aName) {
        // if interested in matching wild cards, use: LIKE and '%" + aName + "%'";
        String query = "SELECT * FROM linkedU.UNIVACCT ";
        query += "WHERE USERN = '" + aName + "'";

        UniversityAccount aUniversityAccount = selectUnivAcctsFromDB(query);
        return aUniversityAccount;
    }
}
