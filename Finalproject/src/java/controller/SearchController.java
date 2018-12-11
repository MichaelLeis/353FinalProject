package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dao.ProfileDAO;
import dao.ProfileDAOImpl;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.StudentBean;
import model.UniversityBean;

/**
 *
 * @author meleis
 */
@ManagedBean
@SessionScoped
public class SearchController {

    private String searchField = "";
    private ArrayList<UniversityBean> universities;
    private ArrayList<StudentBean> students;
    int tuitionSort = 0;
    int afaSort = 0;
    int gradRateSort = 0;

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
        searchField = "";
        return searchField;
    }

    public ArrayList getStudents() {
        return students;
    }

    public void setStudents(ArrayList students) {
        this.students = students;
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

    public String getStudent(int ID) {
        LoginController newLogin = new LoginController();
        newLogin.changeID(ID);
        newLogin.clearPage();
        return "studentHP_1.xhtml";
    }
    
    public String getUniversity(int ID) {
        LoginController newLogin = new LoginController();
        newLogin.changeID(ID);
        newLogin.clearPage();
        return "universityHP_1.xhtml";
    }

    public String sortByTuition() {
        ArrayList<UniversityBean> temp = new ArrayList<UniversityBean>();
        int size = universities.size();
        int[] myArray = new int[size];
        for (int i = 0; i < size; i++) {
            myArray[i] = i;
        }
        int num;
        for (int i = 1; i < size; i++) {
            for (int x = i; x > 0; x--) {
                if (tuitionSort == 0) {
                    if (universities.get(myArray[x]).getTuition() < universities.get(myArray[x - 1]).getTuition()) {
                        num = myArray[x];
                        myArray[x] = myArray[x - 1];
                        myArray[x - 1] = num;
                    }
                } else {
                    if (universities.get(myArray[x]).getTuition() > universities.get(myArray[x - 1]).getTuition()) {
                        num = myArray[x];
                        myArray[x] = myArray[x - 1];
                        myArray[x - 1] = num;
                    }
                }
            }
        }
        for (int i = 0; i < myArray.length; i++) {
            temp.add(universities.get(myArray[i]));
        }
        universities = temp;
        if (tuitionSort == 0) {
            tuitionSort = 1;
        } else {
            tuitionSort = 0;
        }
        return "searchResponse.xhtml";
    }

    public String sortByAFA() {
        ArrayList<UniversityBean> temp = new ArrayList<UniversityBean>();
        int size = universities.size();
        int[] myArray = new int[size];
        for (int i = 0; i < size; i++) {
            myArray[i] = i;
        }
        int num;
        for (int i = 1; i < size; i++) {
            for (int x = i; x > 0; x--) {
                if (afaSort == 0) {
                    if (universities.get(myArray[x]).getAvgFinAid() > universities.get(myArray[x - 1]).getAvgFinAid()) {
                        num = myArray[x];
                        myArray[x] = myArray[x - 1];
                        myArray[x - 1] = num;
                    }
                } else {
                    if (universities.get(myArray[x]).getAvgFinAid() < universities.get(myArray[x - 1]).getAvgFinAid()) {
                        num = myArray[x];
                        myArray[x] = myArray[x - 1];
                        myArray[x - 1] = num;
                    }
                }
            }
        }
        for (int i = 0; i < myArray.length; i++) {
            temp.add(universities.get(myArray[i]));
        }
        universities = temp;
        if (afaSort == 0) {
            afaSort = 1;
        } else {
            afaSort = 0;
        }
        return "searchResponse.xhtml";
    }

    public String sortByGradRate() {
        ArrayList<UniversityBean> temp = new ArrayList<UniversityBean>();
        int size = universities.size();
        int[] myArray = new int[size];
        for (int i = 0; i < size; i++) {
            myArray[i] = i;
        }
        int num;
        for (int i = 1; i < size; i++) {
            for (int x = i; x > 0; x--) {
                if (gradRateSort == 0) {
                    if (universities.get(myArray[x]).getGraduation() > universities.get(myArray[x - 1]).getGraduation()) {
                        num = myArray[x];
                        myArray[x] = myArray[x - 1];
                        myArray[x - 1] = num;
                    }
                } else {
                    if (universities.get(myArray[x]).getGraduation() < universities.get(myArray[x - 1]).getGraduation()) {
                        num = myArray[x];
                        myArray[x] = myArray[x - 1];
                        myArray[x - 1] = num;
                    }
                }
            }
        }
        for (int i = 0; i < myArray.length; i++) {
            temp.add(universities.get(myArray[i]));
        }
        universities = temp;
        if (gradRateSort == 0) {
            gradRateSort = 1;
        } else {
            gradRateSort = 0;
        }
        return "searchResponse.xhtml";
    }

    public String searchUniversities() {
        if (universities != null) {
            universities.clear();
        }
        universities = (new ProfileDAOImpl()).findByName(searchField);
        ArrayList<UniversityBean> temp = (new ProfileDAOImpl()).findByName(searchField.substring(0, 1).toUpperCase() + searchField.substring(1).toLowerCase());
        for (int i = 0; i < temp.size(); i++) {
            boolean newU = true;
            for (int x = 0; x < universities.size(); x++) {
                if (temp.get(i) == universities.get(x)) {
                    newU = false;
                    break;
                } else {
                    newU = true;
                }
            }
            if (newU == true) {
                universities.add(temp.get(i));
            }
        }
        return "searchResponse.xhtml";
    }

    public String searchStudents() {
        if (students != null) {
            students.clear();
        }
        students = (new ProfileDAOImpl()).findStuByName(searchField);
        ArrayList<StudentBean> temp = (new ProfileDAOImpl()).findStuByName(searchField.substring(0, 1).toUpperCase() + searchField.substring(1).toLowerCase());
        for (int i = 0; i < temp.size(); i++) {
            boolean newU = true;
            for (int x = 0; x < students.size(); x++) {
                if (temp.get(i) == students.get(x)) {
                    newU = false;
                    break;
                } else {
                    newU = true;
                }
            }
            if (newU == true) {
                students.add(temp.get(i));
            }
        }
        return "searchStuResponse.xhtml";
    }
}
