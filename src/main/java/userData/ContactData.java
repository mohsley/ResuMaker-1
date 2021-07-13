package userData;

import java.util.HashMap;

public class ContactData {
    HashMap<String, String> input;

    public ContactData(){
        input = new HashMap<>() {{
            put("name", "N/A");
            put("email", "N/A");
            put("phoneNumber", "N/A");
            put("customContact", "N/A");
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
    public void setCustomContact(String data){
        if(input.get("customContact").equals("N/A")){
            input.remove("customContact");
        }
        input.put("customContact",data);
    }

    public String getName() {
        return input.get("name");
    }
    public String getEmail() {
        return input.get("email");
    }
    public String getPhoneNumber(){
        return input.get("phoneNumber");
    }
    public String getCustomContact() { return input.get("customContact"); }
}
