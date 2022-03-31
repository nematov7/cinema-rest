package uz.pdp.cinemarest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate
public class MovieDto {
     String title;

     String description;

     int durationInMinutes;

     MultipartFile coverImage;

     MultipartFile trailerVideo;

     List<Integer> directorIds;

     List<Integer> genreIds;

     double minPrice;

     Integer distributorId;

     List<Integer> actorIds;

     double distributorShareInPercent;
}
