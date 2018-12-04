/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author dlmanco
 */

@Named(value = "studentAccount")
@Dependent
public class StudentAccount {
    //student HP variables
    private int ACT;
    private int SAT;
    private int PSAT;
    private int NMSQT;
    private String essay;
    private String majors;
    private String videoLink;
    private String pictureLink;

    //student Acc variables
    private String firstN;
    private String lastN;
    private String email;
    private String pass;
    private String userN;
    private int icon;
    
    private int id;

    public StudentAccount(){
        
    }
    
    //HP
    public StudentAccount(int ACT, int SAT, int PSAT, int NMSQT, String essay, String majors, String videoLink, String pictureLink, int id) {
        this.ACT = ACT;
        this.SAT = SAT;
        this.PSAT = PSAT;
        this.NMSQT = NMSQT;
        this.essay = essay;
        this.majors = majors;
        this.videoLink = videoLink;
        this.pictureLink = pictureLink;
        this.id = id;
    }
    
    //Account
    public StudentAccount(String firstN, String lastN, String email, String pass, String userN, int icon, int id) {
        this.firstN = firstN;
        this.lastN = lastN;
        this.email = email;
        this.pass = pass;
        this.userN = userN;
        this.icon = icon;
        this.id = id;
    }
    
    //hp getters and setters
    public int getACT() {
        return ACT;
    }

    public void setACT(int ACT) {
        this.ACT = ACT;
    }

    public int getSAT() {
        return SAT;
    }

    public void setSAT(int SAT) {
        this.SAT = SAT;
    }

    public int getPSAT() {
        return PSAT;
    }

    public void setPSAT(int PSAT) {
        this.PSAT = PSAT;
    }

    public int getNMSQT() {
        return NMSQT;
    }

    public void setNMSQT(int NMSQT) {
        this.NMSQT = NMSQT;
    }

    public String getEssay() {
        return essay;
    }

    public void setEssay(String essay) {
        this.essay = essay;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //account getters and setters
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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
