package com.example.nutritionapi.web;

import com.example.nutritionapi.domain.dtos.viewDtos.VitaminView;
import com.example.nutritionapi.exceptions.VitaminNotFoundException;
import com.example.nutritionapi.service.VitaminServiceImp;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public List<VitaminView> getAllVitamins() {
        return vitaminService.getVitamins();
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public VitaminView getVitaminByName(@PathVariable String name) throws VitaminNotFoundException {
        return vitaminService.getVitaminViewByName(name);
    }

}
