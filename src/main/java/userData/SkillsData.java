package userData;

import java.util.ArrayList;

public class SkillsData {
    private ArrayList<String> skills = new ArrayList<>();

    public ArrayList getSkills(){
        return skills;
    }
    public void setSkills(String skills){
        this.skills.add(skills);
    }
    // Java, C++ -->retrivie skills | skills += this.skills; sendback-> to database
}
