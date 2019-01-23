package com.potatospy.bookcatalog.model;


import javax.persistence.*;

/* User class backs the DB users table

From database:
describe users;

user_index	int(11)	NO	PRI		auto_increment
        username	varchar(40)	NO	UNI
        password	varchar(128)	NO
        email	varchar(128)	YES

*/

@Entity
@Table(name="users")
public class User {


    private String username;
    private String password;
    private String email;


    @Id
    @Column(name="user_index")
    private Integer user_index;
    public Integer getUser_index() { return user_index; }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "\n\nUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", user_index=" + user_index +
                '}';
    }
}
