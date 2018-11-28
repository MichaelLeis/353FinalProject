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
@Named(value = "universityBean")
@Dependent
public class UniversityBean {

    private String schoolName;
    private String[] majors; //Could be a select option to list all of the majors,sports, and housing options
    private String[] sports; 
    private String[] housing;
    private String description;
    private int tuition;
    private int enrollment;
    private int graduation;
    private String majorsConcat = "";
    private String sportsConcat = "";
    private String housingConcat = "";
    
    public UniversityBean() {
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public UniversityBean(String schoolName, String[] majors, String[] sports, String[] housing, String description, int tuition, int enrollment, int graduation) {
        this.schoolName = schoolName;
        this.majors = majors;
        this.sports = sports;
        this.housing = housing;
        this.description = description;
        this.tuition = tuition;
        this.enrollment = enrollment;
        this.graduation = graduation;
    }
    
    public String getDescription() {
        return description;
    }

    public String getMajorsConcat() {
        for (int i = 0; i < majors.length; i ++){
            if(majorsConcat == ""){
                majorsConcat = majorsConcat + majors[i];
            } else {
                majorsConcat = majorsConcat + "; " + majors[i];
            }
        }
        return majorsConcat;
    }

    public void setMajorsConcat(String majorsConcat) {
        this.majorsConcat = majorsConcat;
    }

    public String getSportsConcat() {
        for (int i = 0; i < sports.length; i ++){
            if(sportsConcat == ""){
                sportsConcat = sportsConcat + sports[i];
            } else {
                sportsConcat = sportsConcat + "; " + sports[i];
            }
        }
        return sportsConcat;
    }

    public void setSportsConcat(String sportsConcat) {
        this.sportsConcat = sportsConcat;
    }

    public String getHousingConcat() {
        for (int i = 0; i < housing.length; i ++){
            if(housingConcat == ""){
                housingConcat = housingConcat + housing[i];
            } else {
                housingConcat = housingConcat + "; " + housing[i];
            }
        }
        return housingConcat;
    }

    public void setHousingConcat(String housingConcat) {
        this.housingConcat = housingConcat;
    }

    public void setDescription(String description) {
        this.description = description;
    }  

    public String[] getMajors() {
        return majors;
    }

    public void setMajors(String[] majors) {
        this.majors = majors;
    }

    public String[] getSports() {
        return sports;
    }

    public void setSports(String[] sports) {
        this.sports = sports;
    }

    public String[] getHousing() {
        return housing;
    }

    public void setHousing(String[] housing) {
        this.housing = housing;
    }

    public int getTuition() {
        return tuition;
    }

    public void setTuition(int tuition) {
        this.tuition = tuition;
    }

    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public int getGraduation() {
        return graduation;
    }

    public void setGraduation(int gradiation) {
        this.graduation = gradiation;
    }



}
