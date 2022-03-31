package uz.pdp.cinemarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@PackagePrivate
public class SessionDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Date data;

}
