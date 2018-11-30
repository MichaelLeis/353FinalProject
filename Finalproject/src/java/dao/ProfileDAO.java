/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.UniversityAccount;

/**
 *
 * @author admin
 */
public interface ProfileDAO {
    
    public ArrayList findByName(String input);
//    public int createProfile(UniversityAccount aProfile);
//    public int updateProfile(UniversityAccount aProfile);
    public String[] findUserIDs();
    public UniversityAccount findUserName(String aName);
    
}
