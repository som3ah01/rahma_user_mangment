package com.taskMangament.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Roles")
@Data
@Builder
@EqualsAndHashCode(exclude = { "users"})
@ToString(exclude = { "users"})
@AllArgsConstructor
@NoArgsConstructor
//@SequenceGenerator(name = "ROLES_SEQUENCE", sequenceName = "ROLES_SEQUENCE", allocationSize = 1)
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String name;


    @ManyToMany(mappedBy = "roles", fetch=FetchType.LAZY)
    @JsonIgnore
    private Set<User> users;
}
