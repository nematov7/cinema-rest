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
public class Afisha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(cascade = CascadeType.ALL)
    private Movie movie;

    @ManyToOne(cascade = CascadeType.ALL)
    private Hall hall;

    private boolean isActive;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
