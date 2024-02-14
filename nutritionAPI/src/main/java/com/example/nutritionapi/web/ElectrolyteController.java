package com.example.nutritionapi.web;

import com.example.nutritionapi.domain.dtos.viewDtos.ElectrolyteView;
import com.example.nutritionapi.exceptions.ElectrolyteNotFoundException;
import com.example.nutritionapi.service.ElectrolyteServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public List<ElectrolyteView> getAllElectrolytes(){
        return electrolyteService.getAllViewElectrolytes();
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ElectrolyteView getElectrolyteByName(@PathVariable String name) throws ElectrolyteNotFoundException {
        return electrolyteService.getElectrolyteViewByName(name);
    }

}
