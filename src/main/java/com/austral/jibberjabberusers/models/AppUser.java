package com.austral.jibberjabberusers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(nullable = false)
    private String id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    @ElementCollection
    private Set<String> followers;

    @ElementCollection
    private Set<String> following;

    public AppUser(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.followers = new HashSet<>();
        this.following = new HashSet<>();
    }

    public void addFollower(String userId) {
        followers.add(userId);
    }

    public void removeFollower(String userId) {
        followers.remove(userId);
    }

    public void addFollowing(String userId) {
        following.add(userId);
    }

    public void removeFollowing(String userId) {
        following.remove(userId);
    }
}
