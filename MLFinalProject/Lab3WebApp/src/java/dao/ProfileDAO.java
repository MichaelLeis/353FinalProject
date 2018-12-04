/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.SignUpBean;

/**
 *
 * @author admin
 */
public interface ProfileDAO {
    
    public int createProfile(SignUpBean aProfile);
    public int updateProfile(SignUpBean aProfile);
    public String[] findUserIDs();
    public SignUpBean findByName(String aName); // either get one back or several if multiple same name allowed  
    
}
