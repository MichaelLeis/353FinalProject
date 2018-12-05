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
@Named(value = "studentBean")
@Dependent
public class StudentBean {

    private int ACT;
    private int SAT;
    private int PSAT;
    private int NMSQT;
    private String essay;
    private String majors;
    private String videoLink;
    private String pictureLink;
    private int ID;

    public StudentBean() {
    }

    public StudentBean(int ACT, int SAT, int PSAT, int NMSQT, String essay, String majors, String videoLink, String pictureLink, int ID) {
        this.ACT = ACT;
        this.SAT = SAT;
        this.PSAT = PSAT;
        this.NMSQT = NMSQT;
        this.essay = essay;
        this.majors = majors;
        this.videoLink = videoLink;
        this.pictureLink = pictureLink;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

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
}
