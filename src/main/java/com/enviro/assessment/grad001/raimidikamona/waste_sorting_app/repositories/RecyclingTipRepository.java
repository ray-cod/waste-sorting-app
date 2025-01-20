package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.repositories;

import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.RecyclingTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecyclingTipRepository extends JpaRepository<RecyclingTip, Long>  {
    List<RecyclingTip> findByApplicableMaterial(String applicableMaterial);
}
