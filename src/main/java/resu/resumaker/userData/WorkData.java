package resu.resumaker.userData;

public class WorkData {
    private String title;
    private String company;
    private String dates;
    private String location;
    private String description;

    public WorkData(String title, String company, String dates, String location, String description){
        this.title = title;
        this.company = company;
        this.dates = dates;
        this.location = location;
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getDates() {
        return dates;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
}
