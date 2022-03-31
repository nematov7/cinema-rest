package uz.pdp.cinemarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import uz.pdp.cinemarest.entity.enums.TicketStatus;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@PackagePrivate
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String serialNumber= String.valueOf(UUID.randomUUID());

    @ManyToOne
    private MovieSession movieSession;

    @ManyToOne
    private Seat seat;

    @OneToOne
    private Attachment qrCode;

    private double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @ManyToOne
    private User user;

    public Ticket(MovieSession movieSession, Seat seat, Attachment qrCode, double price, TicketStatus ticketStatus, User user) {
        this.movieSession = movieSession;
        this.seat = seat;
        this.qrCode = qrCode;
        this.price = price;
        this.ticketStatus = ticketStatus;
        this.user = user;
    }

    public Ticket(String serialNumber, MovieSession movieSession, Seat seat, Attachment qrCode, double price, TicketStatus ticketStatus, User user) {
        this.serialNumber = serialNumber;
        this.movieSession = movieSession;
        this.seat = seat;
        this.qrCode = qrCode;
        this.price = price;
        this.ticketStatus = ticketStatus;
        this.user = user;
    }
}