package com.example.nutrition_api.web;

import com.example.nutrition_api.domain.vitamin.dto.VitaminView;
import com.example.nutrition_api.domain.vitamin.service.VitaminServiceImp;
import com.example.nutrition_api.infrastructure.exceptions.VitaminNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nutritionApi/v1/vitamin")
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
