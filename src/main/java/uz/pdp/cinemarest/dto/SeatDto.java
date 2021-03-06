package uz.pdp.cinemarest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate
public class SeatDto {
    int rowNumberDan;
    int rowNumbeGacha;
    int seatQuantity;
}
