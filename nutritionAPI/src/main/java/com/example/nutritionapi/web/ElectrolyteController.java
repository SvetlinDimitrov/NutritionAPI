package com.example.nutritionapi.web;

import com.example.nutritionapi.domain.dtos.viewDtos.ElectrolyteView;
import com.example.nutritionapi.exceptions.ElectrolyteNotFoundException;
import com.example.nutritionapi.service.ElectrolyteServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ElectrolyteView>> getAllElectrolytes(){
        return new ResponseEntity<>(electrolyteService.getAllViewElectrolytes() , HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ElectrolyteView> getElectrolyteByName(@PathVariable String name) throws ElectrolyteNotFoundException {
        return new ResponseEntity<>(electrolyteService.getElectrolyteViewByName(name) , HttpStatus.OK);
    }

}
