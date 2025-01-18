package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "waste_category")
public class WasteCategory {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must not exceed 50 characters")
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    @Column(length = 255)
    private String description;

    @Column(name = "is_recyclable")
    private Boolean recyclable;


    // Constructors
    public WasteCategory(Long id, String name, String description, Boolean recyclable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.recyclable = recyclable;
    }

    public WasteCategory(String name, String description, Boolean recyclable) {
        this.name = name;
        this.description = description;
        this.recyclable = recyclable;
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

    public Boolean isRecyclable() {
        return recyclable;
    }

    public void setRecyclable(Boolean recyclable) {
        this.recyclable = recyclable;
    }

    @Override
    public String toString() {
        return "WasteCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", recyclable=" + recyclable +
                '}';
    }
}
