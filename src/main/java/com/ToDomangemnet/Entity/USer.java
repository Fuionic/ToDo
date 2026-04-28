package com.ToDomangemnet.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class USer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    @Column(name = "email" , nullable = false, unique = true)
    private String email;
    @Column(name = "password" , nullable = false, unique = true)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
      @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

}
