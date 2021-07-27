package userData;

import java.util.HashMap;

public class EducationData {
    private String school;
    private String degree;
    private String gpa;
    private String location;
    private String dates;

    public EducationData(String school, String degree, String gpa, String location, String dates) {
        this.school = school;
        this.degree = degree;
        this.gpa = gpa;
        this.location = location;
        this.dates = dates;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }
}