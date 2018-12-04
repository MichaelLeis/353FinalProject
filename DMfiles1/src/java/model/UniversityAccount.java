/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author meleis
 */
@Named(value = "universityAccount")
@Dependent
public class UniversityAccount {
    //university HP variables
    private String description;
    private String majors;
    private String sports;
    private String housing;
    private int tuition;
    private int gradRate;
    private int avgFinAid;
    
    //university account variables
    private String firstN;
    private String lastN;
    private String email;
    private int id;
    private String pass;
    private String userN;
    private String jobTitle;
    private String univN;
    
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
 
    
    public UniversityAccount() {
    }

    public UniversityAccount(String firstN, String lastN, String email, int id, String pass, String userN, String jobTitle, String univN) {
        this.firstN = firstN;
        this.lastN = lastN;
        this.email = email;
        this.id = id;
        this.pass = pass;
        this.userN = userN;
        this.jobTitle = jobTitle;
        this.univN = univN;
    }

    public UniversityAccount(String description, String majors, String sports, String housing, int tuition, int gradRate, int avgFinAid) {
        this.description = description;
        this.majors = majors;
        this.sports = sports;
        this.housing = housing;
        this.tuition = tuition;
        this.gradRate = gradRate;
        this.avgFinAid = avgFinAid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }

    public int getTuition() {
        return tuition;
    }

    public void setTuition(int tuition) {
        this.tuition = tuition;
    }

    public int getGradRate() {
        return gradRate;
    }

    public void setGradRate(int gradRate) {
        this.gradRate = gradRate;
    }

    public int getAvgFinAid() {
        return avgFinAid;
    }

    public void setAvgFinAid(int avgFinAid) {
        this.avgFinAid = avgFinAid;
    }

    public String getFirstN() {
        return firstN;
    }

    public void setFirstN(String firstN) {
        this.firstN = firstN;
    }

    public String getLastN() {
        return lastN;
    }

    public void setLastN(String lastN) {
        this.lastN = lastN;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUserN() {
        return userN;
    }

    public void setUserN(String userN) {
        this.userN = userN;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getUnivN() {
        return univN;
    }

    public void setUnivN(String univN) {
        this.univN = univN;
    }

     
}
