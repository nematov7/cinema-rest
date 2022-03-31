package uz.pdp.cinemarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class MovieSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    private MovieAnnouncement movieAnnouncement;

    @ManyToOne(cascade = CascadeType.ALL)
    private Hall hall;

    @ManyToOne(cascade = CascadeType.ALL)
    private SessionDate startDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private SessionTime startTime;

    @ManyToOne(cascade = CascadeType.ALL)
    private SessionTime endTime;
}
