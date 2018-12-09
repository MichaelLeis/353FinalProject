/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.StudentAccount;
import model.StudentBean;
import model.UniversityAccount;
import model.UniversityBean;

/**
 *
 * @author admin
 */
public interface ProfileDAO {
    
    public ArrayList findByName(String input);
    public int updateStudentHP(StudentBean aProfile);
    public int updateStudentAcc(StudentAccount aProfile);
    public int updateUnivHP(UniversityBean aProfile);
    public int updateUnivAcc(UniversityAccount aProfile);
    public int createUnivAccount(UniversityAccount aProfile);
    public int createStudentAccount(StudentAccount aProfile);
    public int getNewID();
    public UniversityBean findHP(int ID);
    public StudentBean findStudentHP(int ID);
    public UniversityAccount findAccount(int ID);
    public StudentAccount findStudentAccount(int ID);
//    public int updateProfile(UniversityAccount aProfile);
    public String[] findUserIDs();
    public String[] login(String aName);
    
}
