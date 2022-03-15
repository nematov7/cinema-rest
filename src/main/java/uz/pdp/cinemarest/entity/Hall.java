package uz.pdp.cinemarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinemarest.entity.template.AbsEntity;


import javax.persistence.Entity;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hall extends AbsEntity {

    private double vipAdditionalFeeInPercent;
}
