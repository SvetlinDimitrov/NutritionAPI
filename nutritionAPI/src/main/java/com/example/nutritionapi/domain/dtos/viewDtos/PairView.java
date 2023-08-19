package com.example.nutritionapi.domain.dtos.viewDtos;

import com.example.nutritionapi.domain.constants.entity.Pair;

public class PairView {
    private String key;
    private String value;

    public PairView() {}
    public PairView(Pair entity) {
        this.key = entity.getKey();
        this.value = entity.getValue();
    }

    public String getKey() {
        return key;
    }

    public PairView setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public PairView setValue(String value) {
        this.value = value;
        return this;
    }
}
