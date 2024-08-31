package com.taskMangament.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;



@Table(name = "_user")
@Entity
@Data
@Builder
@EqualsAndHashCode(exclude = { "roles"})
@ToString(exclude = { "roles"})
@AllArgsConstructor
@NoArgsConstructor
//@SequenceGenerator(name = "USERS_SEQUENCE", sequenceName = "USERS_SEQUENCE", allocationSize = 1)
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email")
    private String email;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLES",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID") })
    private Set<Roles> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        List<String> roleList = new ArrayList<>();
        roles.forEach((userRoles) -> {
            roleList.add(userRoles.getName());
        });
        var list =roleList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//        var list = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + username));
        return list;
    }

    /*
    Mapping to Authorities:
    The roleList list is then converted to a stream, and for each role name in the list,
    a new SimpleGrantedAuthority object is created.
    SimpleGrantedAuthority is a basic implementation of the GrantedAuthority interface that wraps a role or authority name (typically a String).
     */
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
