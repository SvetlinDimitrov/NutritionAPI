package com.example.nutrition_api.web;

import com.example.nutrition_api.domain.electrolyte.dto.ElectrolyteView;
import com.example.nutrition_api.domain.electrolyte.service.ElectrolyteServiceImp;
import com.example.nutrition_api.infrastructure.exceptions.ElectrolyteNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nutritionApi/electrolyte")
public class ElectrolyteController {

  private final ElectrolyteServiceImp electrolyteService;

  public ElectrolyteController(ElectrolyteServiceImp electrolyteService) {
    this.electrolyteService = electrolyteService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ElectrolyteView> getAllElectrolytes() {
    return electrolyteService.getAllViewElectrolytes();
  }

  @GetMapping("/{name}")
  @ResponseStatus(HttpStatus.OK)
  public ElectrolyteView getElectrolyteByName(@PathVariable String name) throws ElectrolyteNotFoundException {
    return electrolyteService.getElectrolyteViewByName(name);
  }

}
