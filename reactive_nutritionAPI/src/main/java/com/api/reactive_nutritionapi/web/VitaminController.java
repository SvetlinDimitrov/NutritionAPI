package com.api.reactive_nutritionapi.web;

import com.api.reactive_nutritionapi.domain.dtos.viewDtos.VitaminView;
import com.api.reactive_nutritionapi.exceptions.VitaminNotFoundException;
import com.api.reactive_nutritionapi.service.VitaminServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Flux<VitaminView> getAllVitamins() {
        return vitaminService.getVitamins();
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<VitaminView> getVitaminByName(@PathVariable String name) throws VitaminNotFoundException {
        return vitaminService.getVitaminViewByName(name);
    }

}
