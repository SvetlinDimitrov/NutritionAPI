package com.example.nutrition_api.web;

import com.example.nutrition_api.domain.vitamin.dto.VitaminView;
import com.example.nutrition_api.domain.vitamin.service.VitaminService;
import com.example.nutrition_api.infrastructure.exceptions.VitaminNotFoundException;
import com.example.nutrition_api.infrastructure.open_ai.VitaminControllerDocs;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vitamin")
@RequiredArgsConstructor
public class VitaminController implements VitaminControllerDocs {

  private final VitaminService vitaminService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<VitaminView> getAll() {
    return vitaminService.getAll();
  }

  @GetMapping("/{name}")
  @ResponseStatus(HttpStatus.OK)
  public VitaminView getByName(@PathVariable String name) throws VitaminNotFoundException {
    return vitaminService.getByName(name);
  }

}
