package com.example.nutritionapi.web;

import com.example.nutritionapi.domain.dtos.viewDtos.VitaminView;
import com.example.nutritionapi.exceptions.VitaminNotFound;
import com.example.nutritionapi.service.VitaminServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nutritionApi/vitamin")
public class VitaminController {

    private final VitaminServiceImp vitaminService;

    public VitaminController(VitaminServiceImp vitaminService) {
        this.vitaminService = vitaminService;
    }

    @GetMapping
    public ResponseEntity<List<VitaminView>> getAllVitamins(){
        return new ResponseEntity<>(vitaminService.getVitamins() , HttpStatus.OK);
    }
    @GetMapping("/{name}")
    public ResponseEntity<VitaminView> getVitaminByName(@PathVariable String name) throws VitaminNotFound {
        VitaminView vitaminView = vitaminService.getVitaminViewByName(name);
        return new ResponseEntity<>(vitaminView, HttpStatus.FOUND);
    }

    @ExceptionHandler(VitaminNotFound.class)
    public ResponseEntity<String> notFoundVitamin(VitaminNotFound exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
