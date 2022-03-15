package uz.pdp.cinemarest.entity.template;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// Zuhridin Bakhriddinov 3/14/2022 3:16 PM

@Getter
@Setter
@MappedSuperclass
public abstract class AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    public AbsEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public AbsEntity() {
    }
}
