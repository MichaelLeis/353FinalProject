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
import model.SignUpBean;

/**
 *
 * @author admin
 */
public class ProfileDAOImpl implements ProfileDAO {

    private String[] userIDs = new String[10000];

    @Override
    public int createProfile(SignUpBean aProfile) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/CreateUserDB";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            PassHash hash = new PassHash();
            String insertString, insertString2;
            Statement stmt = DBConn.createStatement();
            insertString = "INSERT INTO PROJECT353.USERS VALUES ('"
                    + aProfile.getFirstName()
                    + "','" + aProfile.getLastName()
                    + "','" + aProfile.getUserID()
                    + "','" + hash.hashPass(aProfile.getPassword())
                    + "','" + aProfile.getEmail()
                    + "','" + aProfile.getSecQuestion()
                    + "','" + aProfile.getSecAnswer()
                    + "')";

            insertString2 = "INSERT INTO PROJECT353.LOGININFO VALUES ('"
                    + aProfile.getUserID()
                    + "','" + hash.hashPass(aProfile.getPassword())
                    + "')";

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
    public int updateProfile(SignUpBean aProfile) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/CreateUserDB";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            PassHash hash = new PassHash();
            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "UPDATE PROJECT353.USERS SET FIRSTN = '"
                    + aProfile.getFirstName()
                    + "', LASTN ='" + aProfile.getLastName()
                    + "', PASSWORD ='" + hash.hashPass(aProfile.getPassword())
                    + "', EMAIL ='" + aProfile.getEmail()
                    + "', SECQUESTION ='" + aProfile.getSecQuestion()
                    + "', SECANSWER ='" + aProfile.getSecAnswer()
                    + "' WHERE USERID = '" + aProfile.getUserID() + "'";

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
        selectProfilesFromDB(query);
        return userIDs;

    }

    private SignUpBean selectProfilesFromDB(String query) {
        SignUpBean aSignUpBean = null;
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/CreateUserDB";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String first, last, userID, password, email, secAnswer, secQuestion;
            int i = 0;

            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
                first = rs.getString("FIRSTN");
                last = rs.getString("LASTN");
                userID = rs.getString("USERID");
                password = rs.getString("PASSWORD");
                email = rs.getString("EMAIL");
                secQuestion = rs.getString("SECQUESTION");
                secAnswer = rs.getString("SECANSWER");

                userIDs[i] = userID;
                i++;

                aSignUpBean = new SignUpBean(first, last, userID, password, email, secQuestion, secAnswer);

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
        return aSignUpBean;
    }

    @Override
    public SignUpBean findByName(String aName) {
        // if interested in matching wild cards, use: LIKE and '%" + aName + "%'";
        String query = "SELECT * FROM PROJECT353.USERS ";
        query += "WHERE USERID = '" + aName + "'";

        SignUpBean aProfile = selectProfilesFromDB(query);
        return aProfile;
    }
}
