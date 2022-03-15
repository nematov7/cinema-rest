package uz.pdp.cinemarest.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DistributorDto {
    @NotNull
    @Size(min = 3,max = 250)
    private String name;

    @NotNull
    @Size(min = 3,max = 250)
    private String description;
}
