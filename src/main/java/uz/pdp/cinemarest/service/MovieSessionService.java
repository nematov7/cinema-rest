package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.projection.MovieSessionProjection;
import uz.pdp.cinemarest.repository.MovieSessionRepository;

@Service

public class MovieSessionService {

    @Autowired
    MovieSessionRepository movieSessionRepository;


    public HttpEntity getAllMovieSessions(int page, int size, String search) {
        Pageable pageable = PageRequest.of(
                page - 1,
                size
        );
        Page<MovieSessionProjection> all = movieSessionRepository.findAllSessionsByPage(
                pageable,
                search);

        return ResponseEntity.ok(new ApiResponse("success", true, all));

    }

}
