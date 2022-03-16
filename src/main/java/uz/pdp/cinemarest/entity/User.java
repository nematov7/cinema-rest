package uz.pdp.cinemarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import uz.pdp.cinemarest.entity.enums.Gender;


import javax.persistence.*;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@PackagePrivate
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer id;

    @Column(nullable = false)
     String fullName;

    @Column(nullable = false,unique = true)
     String  userName;

    @Column(nullable = false)
     String password;

     Date dateOfBirth;

    @ManyToMany(cascade = CascadeType.ALL)
     List<Role> role;

    @ManyToMany(cascade = CascadeType.ALL)
     List<Permission> permission;

     Gender gender;
}
