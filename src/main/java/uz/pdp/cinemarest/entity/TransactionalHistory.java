package uz.pdp.cinemarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@PackagePrivate
public class TransactionalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToMany
    @JoinTable(name = "transactional_histories_tickets",
            joinColumns = {@JoinColumn(name = "transactional_history_id")},
            inverseJoinColumns = {@JoinColumn(name = "ticket_id")})
    private List<Ticket> ticketList;

    @OneToOne
    private PayType payType;
    private double totalAmount;
    private String paymentIntent;
    private boolean isRefunded;

    public TransactionalHistory(List<Ticket> ticketList, PayType payType, double totalAmount, String paymentIntent, boolean isRefunded) {
        this.ticketList = ticketList;
        this.payType = payType;
        this.totalAmount = totalAmount;
        this.paymentIntent = paymentIntent;
        this.isRefunded = isRefunded;
    }
}
