package com.steevnPrm.github.fluxExtractor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.steevnPrm.github.fluxExtractor.entity.ItemValidationErrorEntity;


@Repository
public interface ItemValidationErrorRepository extends JpaRepository<ItemValidationErrorEntity , String>{
    
}
