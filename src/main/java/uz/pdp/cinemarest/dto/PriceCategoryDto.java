package uz.pdp.cinemarest.dto;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate
public class PriceCategoryDto {
    String name;
    double additionalFeeInPercent;
    String color;
}
