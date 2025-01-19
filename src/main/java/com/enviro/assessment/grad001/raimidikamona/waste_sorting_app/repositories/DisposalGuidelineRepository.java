package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.repositories;

import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.DisposalGuideline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DisposalGuidelineRepository extends JpaRepository<DisposalGuideline, Long> {

    @Query(value = "SELECT * FROM DISPOSAL_GUIDELINE WHERE CATEGORY_ID = :id", nativeQuery = true)
    List<DisposalGuideline> findByWasteCategoryId(Long wasteCategoryId);
}
