package uz.pdp.cinemarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate
public class Row  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    int number;

    @ManyToOne
     Hall hall;


}
