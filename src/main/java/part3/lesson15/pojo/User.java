package part3.lesson15.pojo;

import java.util.Date;

/**
 * @autor Aleksey Danilchik
 */
public class User {
    private Integer id;
    private String name;
    private String birthday;
    private String loginId;
    private String city;
    private String email;
    private String description;

    public User() {}

    public User(Integer id, String name, String birthday, String loginId, String city, String email, String description) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.loginId = loginId;
        this.city = city;
        this.email = email;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", loginId='" + loginId + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
