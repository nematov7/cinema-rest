package uz.pdp.cinemarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import uz.pdp.cinemarest.entity.enums.Gender;

import javax.persistence.*;
import java.util.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@PackagePrivate
@Table(name = "users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer id;

    @Column(nullable = false)
     String fullName;

    @Column(nullable = false,unique = true)
     String  username;

    @Column(nullable = false)
     String password;

     Date dateOfBirth;

//    @ManyToMany(cascade = CascadeType.ALL)
//     List<Role> role;
    @ManyToMany(cascade = CascadeType.ALL)
     List<Permission> permission;

    @Enumerated(EnumType.STRING)
     Gender gender;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Set<Role> roles;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//
//        for (Role role : this.roles) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName().toString()));
//        }
//        return grantedAuthorities;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
