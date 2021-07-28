package resu.resumaker.userData;

import javax.persistence.*;

@Entity
@Table(name="Contact")
public class ContactData {
    private String name;
    private String email;
    private String phone;
    private Long id;

    public ContactData() {}

    public ContactData(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format(
                "Contact[id=%d, name='%s', email='%s', phone='%s']", id, name, email, phone);
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}

