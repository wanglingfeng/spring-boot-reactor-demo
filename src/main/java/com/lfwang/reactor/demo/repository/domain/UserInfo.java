package com.lfwang.reactor.demo.repository.domain;

/**
 * @author keleguo
 * @date Created in 2018/5/18
 */
public class UserInfo {

    public static final UserInfo SKYLER = new UserInfo("swhite", "Skyler", "White");
    public static final UserInfo JESSE = new UserInfo("jpinkman", "Jesse", "Pinkman");
    public static final UserInfo WALTER = new UserInfo("wwhite", "Walter", "White");
    public static final UserInfo SAUL = new UserInfo("sgoodman", "Saul", "Goodman");

    private final String username;

    private final String firstname;

    private final String lastname;

    public UserInfo(String username, String firstname, String lastname) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserInfo userInfo = (UserInfo) o;

        if (!username.equals(userInfo.username)) {
            return false;
        }
        if (!firstname.equals(userInfo.firstname)) {
            return false;
        }
        return lastname.equals(userInfo.lastname);

    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
