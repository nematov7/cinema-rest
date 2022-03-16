package uz.pdp.cinemarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import uz.pdp.cinemarest.entity.template.AbsEntity;


import javax.persistence.Entity;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate
public class Hall extends AbsEntity {
    double vipAdditionalFeeInPercent;
}
