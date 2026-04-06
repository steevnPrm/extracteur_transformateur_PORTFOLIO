package com.steevnPrm.github.fluxExtractor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class ItemEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "value")
    private Integer value;

    @Column(name = "description")
    private String description;

    public void sanitize() {
        if (this.value == null) {
            this.value = 0;
        }
        if (this.description == null) {
            this.description = "nom d'item non défini : ID " + this.id;
        }
    }

    @PrePersist
    @PreUpdate
    private void onSave() {
        this.sanitize();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Integer getValue() { return value; }
    public void setValue(Integer value) { this.value = value; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}