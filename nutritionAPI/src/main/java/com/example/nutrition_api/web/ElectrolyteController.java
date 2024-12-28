package com.example.nutrition_api.web;

import com.example.nutrition_api.domain.electrolyte.dto.ElectrolyteView;
import com.example.nutrition_api.domain.electrolyte.service.ElectrolyteServiceImp;
import com.example.nutrition_api.infrastructure.exceptions.ElectrolyteNotFoundException;
import com.example.nutrition_api.infrastructure.open_ai.ElectrolyteControllerDocs;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/nutritionApi/v1/electrolyte")
@RequiredArgsConstructor
public class ElectrolyteController implements ElectrolyteControllerDocs {

  private final ElectrolyteServiceImp electrolyteService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ElectrolyteView> getAll() {
    return electrolyteService.getAllViewElectrolytes();
  }

  @GetMapping("/{name}")
  @ResponseStatus(HttpStatus.OK)
  public ElectrolyteView getByName(@PathVariable String name) throws ElectrolyteNotFoundException {
    return electrolyteService.getElectrolyteViewByName(name);
  }

}
