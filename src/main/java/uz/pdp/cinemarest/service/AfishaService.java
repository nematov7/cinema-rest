package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.projection.CustomAfisha;
import uz.pdp.cinemarest.repository.AfishaRepository;

import java.util.List;

@Service
public class AfishaService {
    @Autowired
    AfishaRepository afishaRepository;

}
