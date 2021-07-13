package userData;

import java.util.HashMap;

public class EducationData {
    HashMap<String, String> input;


    public EducationData() {
        input = new HashMap<>() {{
            put("schoolName", "N/A");
            put("degreeAndMajor", "N/A");
            put("GPA", "N/A");
            put("location", "N/A");
            put("dates", "N/A");
        }};
    }

    public HashMap<String, String> getInput() {
        return input;
    }

    public void setSchoolName(String data) {
        if(input.get("schoolName").equals("N/A")){
            input.remove("schoolName");
        }
        input.put("schoolName", data);
    }
    public void setDegreeAndMajor(String data) {
        if(input.get("degreeAndMajor").equals("N/A")){
            input.remove("degreeAndMajor");
        }
        input.put("degreeAndMajor", data);
    }
    public void setGPA(String data) {
        if(input.get("GPA").equals("N/A")){
            input.remove("GPA");
        }
        input.put("GPA", data);
    }
    public void setLocation(String data) {
        if(input.get("location").equals("N/A")){
            input.remove("location");
        }
        input.put("location", data);
    }
    public void setDates(String data) {
        if(input.get("dates").equals("N/A")){
            input.remove("dates");
        }
        input.put("dates", data);
    }

    public String getSchoolName() {
        return input.get("schoolName");
    }
    public String getDegreeAndMajor() {
        return input.get("degreeAndMajor");
    }
    public String getGPA() {
        return input.get("GPA");
    }
    public String getLocation() {
        return input.get("location");
    }
    public String getDates() {
        return input.get("dates");
    }

}
