package com.example.nutrition_api.web;

import com.example.nutrition_api.domain.macros.dto.MacronutrientView;
import com.example.nutrition_api.domain.macros.service.MacronutrientService;
import com.example.nutrition_api.infrastructure.open_ai.MacronutrientControllerDocs;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/macronutrient")
@RequiredArgsConstructor
public class MacronutrientController implements MacronutrientControllerDocs {

  private final MacronutrientService macronutrientService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<MacronutrientView> getAll() {
    return macronutrientService.getAll();
  }

  @GetMapping("/{name}")
  @ResponseStatus(HttpStatus.OK)
  public MacronutrientView getByName(@PathVariable String name) {
    return macronutrientService.getByName(name);
  }

}
