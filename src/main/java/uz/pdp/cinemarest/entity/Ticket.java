package uz.pdp.cinemarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import uz.pdp.cinemarest.entity.enums.Ticket_status;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@PackagePrivate
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @OneToOne
    MovieSession movieSession;

    @OneToOne
    Seat seat;

    @OneToOne
    Attachment attachment;

    double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Ticket_status ticket_status;

    @ManyToOne
    Cart cart;

}
