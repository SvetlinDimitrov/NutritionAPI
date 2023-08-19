package com.example.nutritionapi.domain.dtos.viewDtos;

import com.example.nutritionapi.domain.constants.entity.Pair;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairView pairView = (PairView) o;
        return Objects.equals(key, pairView.key) && Objects.equals(value, pairView.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
