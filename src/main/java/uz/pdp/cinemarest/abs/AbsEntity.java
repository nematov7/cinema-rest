package uz.pdp.cinemarest.abs;

import lombok.*;

import javax.persistence.*;

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
