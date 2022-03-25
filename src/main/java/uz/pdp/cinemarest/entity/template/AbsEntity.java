package uz.pdp.cinemarest.entity.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
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
