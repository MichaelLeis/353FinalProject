package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.ProfileDAO;
import dao.ProfileDAOImpl;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author meleis
 */
@ManagedBean
@Dependent
public class SearchController {

    private String searchField = "";
    private ArrayList universities;
    
    public SearchController() {
        
    }

    public SearchController(String searchField, ArrayList universities) {
        this.searchField = searchField;
        this.universities = universities;
    }

    public SearchController(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchField() {
        return searchField;
    }
    
    public ArrayList getUniversities() {
        return universities;
    }

    public void setUniversities(ArrayList universities) {
        this.universities = universities;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }
    
    public String searchUniversities() {
        universities = (new ProfileDAOImpl()).findByName(searchField);
        return "searchResponse.xhtml";
    }   
}
