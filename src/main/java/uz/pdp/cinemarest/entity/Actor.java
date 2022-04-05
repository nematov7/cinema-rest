package uz.pdp.cinemarest.entity;

import lombok.*;
import lombok.experimental.PackagePrivate;
import uz.pdp.cinemarest.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@PackagePrivate
public class Actor extends AbsEntity {

    @OneToOne(cascade = CascadeType.ALL)
    Attachment attachment;

    @ManyToMany(mappedBy = "actors", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Movie> movie;
}
