package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.repositories;

import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.RecyclingTip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecyclingTipRepository extends JpaRepository<RecyclingTip, Long>  {
    List<RecyclingTip> findByApplicableMaterial(String applicableMaterial);
}
