/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.StudentAccount;
import model.UniversityAccount;

/**
 *
 * @author admin
 */
public interface ProfileDAO {
    
    public ArrayList findByName(String input);
//    public int createProfile(UniversityAccount aProfile);
   // public int updateProfile(StudentAccount aProfile);
    public int updateStudentHP(StudentAccount aProfile);
    public int updateStudentAcc(StudentAccount aProfile);
    public int updateUnivHP(UniversityAccount aProfile);
    public int updateUnivAcc(UniversityAccount aProfile);
    public String[] findUserIDs();
    public UniversityAccount findUserName(String aName);
    
}
