package by.tc.task05.entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -3634818008132749930L;

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String passwordHash;
    private String avatar;

    public User() {
    }

    public User(int id, String email, String firstName, String lastName,
                String passwordHash, String avatar) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = passwordHash;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }
        if (firstName != null ? !firstName.equals(user.firstName) :
                user.firstName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(user.lastName) :
                user.lastName != null) {
            return false;
        }
        if (passwordHash != null ? !passwordHash.equals(user.passwordHash) :
                user.passwordHash != null) {
            return false;
        }
        return avatar != null ? avatar.equals(user.avatar) :
                user.avatar == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result +
                (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' + ", lastName='" + lastName +
                '\'' + ", passwordHash='" + passwordHash + '\'' + ", avatar='" +
                avatar + '\'' + '}';
    }
}
