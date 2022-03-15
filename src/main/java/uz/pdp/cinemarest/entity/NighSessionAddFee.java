package uz.pdp.cinemarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@PackagePrivate
public class NighSessionAddFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
   double percentage ;
   Date date;
}
