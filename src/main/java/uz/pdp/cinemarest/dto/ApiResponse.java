package uz.pdp.cinemarest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate
public class ApiResponse {
    String massage;
    boolean success;
    Object data;

    public ApiResponse(String massage, boolean success) {
        this.massage = massage;
        this.success = success;
    }
}
