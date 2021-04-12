package by.tc.task05.entity;

import jakarta.servlet.http.Part;

import java.io.Serializable;
import java.util.Objects;

public class UserRegistrationForm implements Serializable {
    private static final long serialVersionUID = 4136281669111828719L;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String repeatPassword;

    public UserRegistrationForm() {
    }

    public UserRegistrationForm(String email, String firstName, String lastName,
                                String password, String repeatPassword) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRegistrationForm that = (UserRegistrationForm) o;

        if (email != null ? !email.equals(that.email) : that.email != null) {
            return false;
        }
        if (firstName != null ? !firstName.equals(that.firstName) :
                that.firstName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(that.lastName) :
                that.lastName != null) {
            return false;
        }
        if (password != null ? !password.equals(that.password) :
                that.password != null) {
            return false;
        }
        return repeatPassword != null ?
                repeatPassword.equals(that.repeatPassword) :
                that.repeatPassword == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result +
                (repeatPassword != null ? repeatPassword.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "UserRegistrationForm{" + "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' + ", lastName='" + lastName +
                '\'' + ", password='" + password + '\'' + ", repeatPassword='" +
                repeatPassword + '\'' + '}';
    }
}
