package uz.pdp.cinemarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@PackagePrivate
public class MovieSession {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id;

    @ManyToOne
    Movie movie;


    @ManyToOne
    Hall hall;

    boolean isActive;

}
