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

    private int iconChoice;
    
    public StudentBean() {
    }

    public int getIconChoice() {
        return iconChoice;
    }

    public void setIconChoice(int iconChoice) {
        this.iconChoice = iconChoice;
    }

    public StudentBean(int iconChoice) {
        this.iconChoice = iconChoice;
    }
}
