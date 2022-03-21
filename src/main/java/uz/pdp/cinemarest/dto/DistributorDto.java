package uz.pdp.cinemarest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate
public class DistributorDto {
    @NotNull
    @Size(min = 3,max = 250)
     String name;

    @NotNull
    @Size(min = 3,max = 250)
     String description;
}
