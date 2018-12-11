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
import model.StudentBean;
import model.UniversityAccount;
import model.UniversityBean;

/**
 *
 * @author admin
 */
public class ProfileDAOImpl implements ProfileDAO {

    private static UniversityBean selectUniversityFromDB(String query) {
        Connection DBConn = null;
        UniversityBean aUniversityBean = new UniversityBean();
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String description, name;
            String majors, sports, housing;
            int tuition, gradRate, avgFinAid, ID;
            while (rs.next()) {
                majors = rs.getString("Majors");
                sports = rs.getString("Sports");
                housing = rs.getString("Housing");
                description = rs.getString("DESCRIPTION");
                tuition = rs.getInt("TUTITION");
                avgFinAid = rs.getInt("AVGFINAID");
                gradRate = rs.getInt("GRADRATE");
                ID = rs.getInt("ID");

                aUniversityBean = new UniversityBean(majors, sports, housing, description, tuition, gradRate, avgFinAid, ID);
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
        return aUniversityBean;
    }

    @Override
    public UniversityBean findHP(int ID) {
        String query = "SELECT * from Linkedu.UNIVHP where ID = " + ID;

        UniversityBean university = selectUniversityFromDB(query);
        return university;
    }

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
            String majors, sports, housing;
            int tuition, gradRate, avgFinAid, ID;
            UniversityBean aUniversityBean;
            while (rs.next()) {
                majors = rs.getString("Majors");
                sports = rs.getString("Sports");
                housing = rs.getString("Housing");
                description = rs.getString("DESCRIPTION");
                tuition = rs.getInt("TUTITION");
                avgFinAid = rs.getInt("AVGFINAID");
                gradRate = rs.getInt("GRADRATE");
                ID = rs.getInt("ID");

                aUniversityBean = new UniversityBean(majors, sports, housing, description, tuition, gradRate, avgFinAid, ID);

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
        String query = "SELECT a.description, a.majors, a.sports, a.housing, a.tutition, a.GRADRATE, a.AVGFINAID, a.ID FROM linkedU.UNIVHP as a join linkedu.univacct as b on a.ID = b.ID "
                + " where a.majors like '%" + input + "%'"
                + " or a.sports like '%" + input + "%'"
                + " or a.housing like '%" + input + "%'"
                + " or a.description like '%" + input + "%'"
                + " or b.univName like '%" + input + "%'";

        ArrayList universities = selectUniversitiesFromDB(query);
        return universities;
    }

    @Override
    public ArrayList findStuByName(String input) {
        // if interested in matching wild cards, use: LIKE and '%" + aName + "%'";
        String query = "select act, sat, psat, nmsqt, essay, majors, videolinks, picturelinks, a.id from linkedu.studentHp as a join linkedu.studentacct as b on a.ID = b.ID " +
                "where b.firstn like '%" + input + "%'"
                + " or b.lastn like '%" + input + "%'";

        ArrayList students = selectStudentsFromDB(query);
        return students;
    }

    private static UniversityAccount selectUAccountFromDB(String query) {
        Connection DBConn = null;
        UniversityAccount aUniversityAccount = new UniversityAccount();
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String first, last, email, pass, userN, jobTitle, univN;
            int ID;
            while (rs.next()) {
                first = rs.getString("FIRSTN");
                last = rs.getString("LASTN");
                email = rs.getString("EMAIL");
                ID = rs.getInt("ID");
                pass = rs.getString("PASS");
                userN = rs.getString("USERN");
                jobTitle = rs.getString("JOBTITLE");
                univN = rs.getString("UNIVNAME");

                aUniversityAccount = new UniversityAccount(first, last, email, ID, pass, userN, jobTitle, univN);
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
        return aUniversityAccount;
    }

    private static StudentBean selectStudentFromDB(String query) {
        Connection DBConn = null;
        StudentBean aStudentBean = new StudentBean();
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String essay, majors, videos, pictures;
            int ACT, SAT, PSAT, NMSQT, ID;
            while (rs.next()) {
                essay = rs.getString("ESSAY");
                majors = rs.getString("MAJORS");
                videos = rs.getString("VIDEOLINKS");
                pictures = rs.getString("PICTURELINKS");
                ACT = rs.getInt("ACT");
                SAT = rs.getInt("SAT");
                PSAT = rs.getInt("PSAT");
                NMSQT = rs.getInt("NMSQT");
                ID = rs.getInt("ID");

                aStudentBean = new StudentBean(ACT, SAT, PSAT, NMSQT, essay, majors, videos, pictures, ID);
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
        return aStudentBean;
    }

    private static ArrayList selectStudentsFromDB(String query) {
        Connection DBConn = null;
        StudentBean aStudentBean = new StudentBean();
        ArrayList studentCollection = new ArrayList();
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String essay, majors, videos, pictures;
            int ACT, SAT, PSAT, NMSQT, ID;
            while (rs.next()) {
                essay = rs.getString("ESSAY");
                majors = rs.getString("MAJORS");
                videos = rs.getString("VIDEOLINKS");
                pictures = rs.getString("PICTURELINKS");
                ACT = rs.getInt("ACT");
                SAT = rs.getInt("SAT");
                PSAT = rs.getInt("PSAT");
                NMSQT = rs.getInt("NMSQT");
                ID = rs.getInt("ID");

                aStudentBean = new StudentBean(ACT, SAT, PSAT, NMSQT, essay, majors, videos, pictures, ID);

                studentCollection.add(aStudentBean);
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
        return studentCollection;
    }

    private static StudentAccount selectSAccountFromDB(String query) {
        Connection DBConn = null;
        StudentAccount aStudentAccount = new StudentAccount();
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String first, last, email, pass, userN;
            int ID, icon;
            while (rs.next()) {
                first = rs.getString("FIRSTN");
                last = rs.getString("LASTN");
                email = rs.getString("EMAIL");
                ID = rs.getInt("ID");
                pass = rs.getString("PASS");
                userN = rs.getString("USERN");
                icon = rs.getInt("icon");

                aStudentAccount = new StudentAccount(first, last, email, ID, pass, userN, icon);
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
        return aStudentAccount;
    }

    @Override
    public StudentBean findStudentHP(int ID) {
        String query = "SELECT * from Linkedu.STUDENTHP where ID = " + ID;

        StudentBean student = selectStudentFromDB(query);
        return student;
    }

    @Override
    public UniversityAccount findAccount(int ID) {
        String query = "SELECT * from Linkedu.UNIVACCT where ID = " + ID;

        UniversityAccount university = selectUAccountFromDB(query);
        return university;
    }

    @Override
    public StudentAccount findStudentAccount(int ID) {
        String query = "SELECT * from Linkedu.STUDENTACCT where ID = " + ID;

        StudentAccount student = selectSAccountFromDB(query);
        return student;
    }

    @Override
    public int getNewID() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int idCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "SELECT SUM(dum.tab) AS total FROM ("
                    + "SELECT COUNT(*) AS tab FROM linkedu.UNIVACCT AS b "
                    + "UNION  ALL "
                    + "SELECT COUNT(*) AS tab FROM linkedu.STUDENTACCT AS a"
                    + ") AS dum";
            ResultSet rs = stmt.executeQuery(insertString);

            while (rs.next()) {
                idCount = rs.getInt("total");
            }
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return idCount + 1;
    }

    @Override
    public int createUnivAccount(UniversityAccount aProfile) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            PassHash hash = new PassHash();
            String insertString, insertString2;
            Statement stmt = DBConn.createStatement();
            insertString = "INSERT INTO linkedU.UNIVACCT VALUES ('"
                    + aProfile.getFirst()
                    + "','" + aProfile.getLast()
                    + "','" + aProfile.getEmail()
                    + "'," + aProfile.getID()
                    + " ,'" + aProfile.getPassword()
                    + "','" + aProfile.getUserN()
                    + "','" + aProfile.getJobTitle()
                    + "','" + aProfile.getUniversityN()
                    + "')";

            insertString2 = "INSERT INTO linkedU.UNIVHP VALUES (' ', ' ', ' ', ' ', 0, 0, 0, "
                    + aProfile.getID()
                    + ")";

            rowCount = stmt.executeUpdate(insertString);
            rowCount = stmt.executeUpdate(insertString2);

            System.out.println("insert string =" + insertString);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;
    }

    @Override
    public int createStudentAccount(StudentAccount aProfile) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            PassHash hash = new PassHash();
            String insertString, insertString2;
            Statement stmt = DBConn.createStatement();
            insertString = "INSERT INTO linkedU.STUDENTACCT VALUES ('"
                    + aProfile.getFirst()
                    + "','" + aProfile.getLast()
                    + "','" + aProfile.getEmail()
                    + "'," + aProfile.getID()
                    + " ,'" + aProfile.getPassword()
                    + "','" + aProfile.getUserN()
                    + "'," + aProfile.getIcon()
                    + ")";

            insertString2 = "INSERT INTO linkedU.STUDENTHP VALUES (0, 0, 0, 0, ' ', ' ', ' ', ' ',"
                    + aProfile.getID()
                    + ")";

            rowCount = stmt.executeUpdate(insertString);
            rowCount = stmt.executeUpdate(insertString2);

            System.out.println("insert string =" + insertString);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;
    }

    @Override
    public int updateStudentHP(StudentBean aProfile) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student"); //my pass and user are backwards from normal

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
                    + "' WHERE ID = " + aProfile.getID();

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
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student"); //my pass and user are backwards from normal

            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "UPDATE LINKEDU.STUDENTACCT "
                    + "SET FIRSTN = '" + aProfile.getFirst()
                    + "', LASTN = '" + aProfile.getLast()
                    + "', EMAIL = '" + aProfile.getEmail()
                    + "', PASS ='" + aProfile.getPassword()
                    + "', USERN ='" + aProfile.getUserN()
                    + "', ICON = " + aProfile.getIcon()
                    + " WHERE ID = " + aProfile.getID();

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
    public int updateUnivHP(UniversityBean aProfile) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "UPDATE LINKEDU.UNIVHP "
                    + "SET DESCRIPTION = '" + aProfile.getDescription()
                    + "', MAJORS ='" + aProfile.getMajors()
                    + "', SPORTS ='" + aProfile.getSports()
                    + "', HOUSING ='" + aProfile.getHousing()
                    + "', TUTITION =" + aProfile.getTuition() //tuition is spelled incorrecttly in the db
                    + ", GRADRATE =" + aProfile.getGraduation()
                    + ", AVGFINAID =" + aProfile.getAvgFinAid()
                    + " WHERE ID = " + aProfile.getID();

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
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student"); //my pass and user are backwards from normal

            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "UPDATE LINKEDU.UNIVACCT "
                    + "SET FIRSTN = '" + aProfile.getFirst()
                    + "', LASTN = '" + aProfile.getLast()
                    + "', EMAIL = '" + aProfile.getEmail()
                    + "', PASS ='" + aProfile.getPassword()
                    + "', USERN ='" + aProfile.getUserN()
                    + "', JOBTITLE ='" + aProfile.getJobTitle()
                    + "', UNIVNAME ='" + aProfile.getUniversityN()
                    + "' WHERE ID = " + aProfile.getID();

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
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        String[] ids = new String[10000];
        try {
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            Statement stmt = DBConn.createStatement();
            String query = "SELECT userN FROM linkedU.UNIVACCT UNION SELECT userN FROM linkedU.STUDENTACCT";
            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                ids[i] = rs.getString("USERN");
                i++;
            }
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return ids;
    }

    public int getIDPass() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int idCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "SELECT SUM(dum.tab) AS total FROM ("
                    + "SELECT COUNT(*) AS tab FROM linkedu.UNIVACCT AS b "
                    + "UNION  ALL "
                    + "SELECT COUNT(*) AS tab FROM linkedu.STUDENTACCT AS a"
                    + ") AS dum";
            ResultSet rs = stmt.executeQuery(insertString);

            while (rs.next()) {
                idCount = rs.getInt("total");
            }
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return idCount + 1;
    }

    @Override
    public String[] login(String aName) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        String[] login = new String[4];
        try {
            String myDB = "jdbc:derby://localhost:1527/finalProject";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "Select userN, pass, ID, 'univ' from linkedu.univacct where USERN = '" + aName
                    + "' union select userN, pass, ID, 'stu' from linkedu.studentacct where USERN = '" + aName + "'";
            ResultSet rs = stmt.executeQuery(insertString);
            int ID;
            while (rs.next()) {
                login[0] = rs.getString("PASS");
                ID = rs.getInt("ID");
                login[1] = Integer.toString(ID);
                login[2] = rs.getString("4");
            }

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return login;
    }
}
