package uz.pdp.cinemarest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.PackagePrivate;


import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@PackagePrivate
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String title;
    String description;
    Integer durationInMinutes;
    @ManyToMany
    List<Director> directors;
    @ManyToMany
    List<Actor> actors;
    @ManyToMany
    List<Genre> genres;
    @OneToOne
    Attachment coverImg;
    @OneToOne
    Attachment trailerVideo;
    Double minPrice;
    @OneToOne
    Distributor distributor;
    Double distributorShareInPercent;
}
