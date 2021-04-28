package by.tc.task05.entity;

import java.io.Serializable;

/**
 * Entity that needed to be used to change 'unimportant' information about user
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 3624190281383855689L;
    private int id;
    private String firstName;
    private String lastName;

    public UserInfo(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (id != userInfo.id) return false;
        if (firstName != null ? !firstName.equals(userInfo.firstName) :
                userInfo.firstName != null) {
            return false;
        }
        return lastName != null ? lastName.equals(userInfo.lastName) :
                userInfo.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserInfo{" + "id=" + id + ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' + '}';
    }
}
