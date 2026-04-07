package com.steevnPrm.github.fluxExtractor.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "item_validation_errors")
public class ItemValidationErrorEntity {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false)
    private LocalDateTime errorTimestamp = LocalDateTime.now();

    @Column(columnDefinition = "TEXT") 
    private String rawData;

    @Column(nullable = false)
    private String errorMessage;


    public ItemValidationErrorEntity() {}

    public ItemValidationErrorEntity(String rawData, String errorMessage) {
        this.rawData = rawData;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public void setErrorMessage(String message){
        this.errorMessage = message;
    }

    public void setData(ItemEntity data) {
    if (data == null) {
        this.rawData = "NULL_ITEM";
        return;
    }
    
    String dataToRaw = data.toString(); 
    this.rawData = dataToRaw;
}

}