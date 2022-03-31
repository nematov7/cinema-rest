package uz.pdp.cinemarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import uz.pdp.cinemarest.entity.enums.RoleEnum;


import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@PackagePrivate
public class Role  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    RoleEnum name;


}
