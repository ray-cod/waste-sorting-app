package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "disposal_guidelines")
public class DisposalGuideline {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must not exceed 50 characters")
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    @Column(length = 255)
    private String description;

    @NotBlank(message = "Disposal Instructions are required")
    @Size(min = 10, message = "Disposal instructions should be at least 10 characters long")
    private String disposalInstructions;

    private boolean recyclable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private WasteCategory wasteCategory;


    // Constructors
    public DisposalGuideline(Long id, String name, String description, String disposalInstructions, boolean recyclable, WasteCategory wasteCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.disposalInstructions = disposalInstructions;
        this.recyclable = recyclable;
        this.wasteCategory = wasteCategory;
    }

    public DisposalGuideline(String name, String description, String disposalInstructions, boolean recyclable, WasteCategory wasteCategory) {
        this.name = name;
        this.description = description;
        this.disposalInstructions = disposalInstructions;
        this.recyclable = recyclable;
        this.wasteCategory = wasteCategory;
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Name is required") @Size(max = 50, message = "Name must not exceed 50 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") @Size(max = 50, message = "Name must not exceed 50 characters") String name) {
        this.name = name;
    }

    public @Size(max = 255, message = "Description must not exceed 255 characters") String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 255, message = "Description must not exceed 255 characters") String description) {
        this.description = description;
    }

    public @NotBlank(message = "Disposal Instructions are required") @Size(min = 10, message = "Disposal instructions should be at least 10 characters long") String getDisposalInstructions() {
        return disposalInstructions;
    }

    public void setDisposalInstructions(@NotBlank(message = "Disposal Instructions are required") @Size(min = 10, message = "Disposal instructions should be at least 10 characters long") String disposalInstructions) {
        this.disposalInstructions = disposalInstructions;
    }

    public boolean isRecyclable() {
        return recyclable;
    }

    public void setRecyclable(boolean recyclable) {
        this.recyclable = recyclable;
    }

    public WasteCategory getWasteCategory() {
        return wasteCategory;
    }

    public void setWasteCategory(WasteCategory wasteCategory) {
        this.wasteCategory = wasteCategory;
    }

    @Override
    public String toString() {
        return "DisposalGuideline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", disposalInstructions='" + disposalInstructions + '\'' +
                ", recyclable=" + recyclable +
                ", wasteCategory=" + wasteCategory +
                '}';
    }
}
