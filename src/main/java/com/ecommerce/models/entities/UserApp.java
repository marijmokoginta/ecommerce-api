package com.ecommerce.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tbl_user")
@Data @NoArgsConstructor @AllArgsConstructor
public class UserApp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    @Column(length = 150, nullable = false, unique = true)
    private String email;
    @Column(length = 150, nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
    private Date lastLogin;
    private Date lastLoginDisplay;
    private Date joinDate;
    private boolean isActive;
    private boolean isEnabled;
}
