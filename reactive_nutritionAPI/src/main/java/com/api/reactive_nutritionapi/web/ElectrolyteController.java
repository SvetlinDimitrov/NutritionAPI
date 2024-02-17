package com.api.reactive_nutritionapi.web;

import com.api.reactive_nutritionapi.domain.dtos.viewDtos.ElectrolyteView;
import com.api.reactive_nutritionapi.exceptions.ElectrolyteNotFoundException;
import com.api.reactive_nutritionapi.service.ElectrolyteServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/nutritionApi/electrolyte")
public class ElectrolyteController {

    private final ElectrolyteServiceImp electrolyteService;

    public ElectrolyteController(ElectrolyteServiceImp electrolyteService) {
        this.electrolyteService = electrolyteService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<ElectrolyteView> getAllElectrolytes(){
        return electrolyteService.getAllViewElectrolytes();
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ElectrolyteView> getElectrolyteByName(@PathVariable String name) throws ElectrolyteNotFoundException {
        return electrolyteService.getElectrolyteViewByName(name);
    }

}
