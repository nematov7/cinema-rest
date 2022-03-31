package uz.pdp.cinemarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@PackagePrivate
public class MovieAnnouncement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(cascade = CascadeType.ALL)
    private Movie movie;

    private boolean isActive;
}
