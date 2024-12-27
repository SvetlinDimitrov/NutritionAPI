package com.example.nutrition_api.domain.shared.entity;

import lombok.Getter;

@Getter
public class Pair {

  private String key;
  private String value;

  public Pair setKey(String key) {
    this.key = key;
    return this;
  }

  public Pair setValue(String value) {
    this.value = value;
    return this;
  }

}
