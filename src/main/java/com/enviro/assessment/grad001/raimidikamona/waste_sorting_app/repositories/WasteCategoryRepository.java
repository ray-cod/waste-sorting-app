package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.repositories;

import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.WasteCategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long> {
    List<WasteCategory> findByName(String name);
}
