package resu.resumaker;

import java.util.HashMap;

public class UserData {
    HashMap<String, String> input;

    public UserData(){
        input = new HashMap<>() {{
            put("name", "N/A");
            put("job", "N/A");
            put("email", "N/A");
            put("phoneNumber", "N/A");
        }};
    }

    public HashMap<String, String> getInput() {
        return input;
    }
    public void setName(String name) {
        if(input.get("name").equals("N/A")){
            input.remove("name");
        }
        input.put("name", name);
    }
    public void setJob(String job) {
        if(input.get("job").equals("N/A")){
            input.remove("job");
        }
        input.put("job", job);
    }
    public void setEmail(String email) {
        if(input.get("email").equals("N/A")){
            input.remove("email");
        }
        input.put("email", email);
    }
    public void setPhoneNumber(String phoneNumber){
        if(input.get("phoneNumber").equals("N/A")){
            input.remove("phoneNumber");
        }
        input.put("phoneNumber",phoneNumber);
    }

    public String getName() {
        return input.get("name");
    }
    public String getJob() {
        return input.get("job");
    }
    public String getEmail() {
        return input.get("email");
    }
    public String getPhoneNumber(){
        return input.get("phoneNumber");
    }
}
