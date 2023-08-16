package com.example.nutritionapi.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pairs")
public class PairEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String key;
    @Column
    private String value;

    public Long getId() {
        return id;
    }

    public PairEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getKey() {
        return key;
    }

    public PairEntity setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public PairEntity setValue(String value) {
        this.value = value;
        return this;
    }

}
