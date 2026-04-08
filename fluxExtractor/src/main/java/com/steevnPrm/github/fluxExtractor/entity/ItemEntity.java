package com.steevnPrm.github.fluxExtractor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "value")
    private Integer value;

    @Column(name = "description")
    private String description;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Integer getValue() { return value; }
    public void setValue(Integer value) { this.value = value; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
    return "ItemEntity{" +
            "id='" + id + '\'' +
            ", value=" + value +
            ", description='" + description + '\'' +
            '}';
}
}