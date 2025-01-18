package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "recycling_tips")
public class RecyclingTip {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tip is required")
    @Size(min = 10, message = "Tip should be at least 10 characters long")
    private String tip;

    @NotBlank(message = "Applicable material is required")
    private String applicableMaterial;  // Material this tip applies to (e.g., plastic, paper)

    @Size(max = 255, message = "Link should be a valid URL and within 255 characters")
    private String externalLink;    // External tips link


    // Constructors
    public RecyclingTip(){}

    public RecyclingTip(Long id, String tip, String applicableMaterial, String externalLink) {
        this.id = id;
        this.tip = tip;
        this.applicableMaterial = applicableMaterial;
        this.externalLink = externalLink;
    }

    public RecyclingTip(String tip, String applicableMaterial, String externalLink) {
        this.tip = tip;
        this.applicableMaterial = applicableMaterial;
        this.externalLink = externalLink;
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Tip is required") @Size(min = 10, message = "Tip should be at least 10 characters long") String getTip() {
        return tip;
    }

    public void setTip(@NotBlank(message = "Tip is required") @Size(min = 10, message = "Tip should be at least 10 characters long") String tip) {
        this.tip = tip;
    }

    public @NotBlank(message = "Applicable material is required") String getApplicableMaterial() {
        return applicableMaterial;
    }

    public void setApplicableMaterial(@NotBlank(message = "Applicable material is required") String applicableMaterial) {
        this.applicableMaterial = applicableMaterial;
    }

    public @Size(max = 255, message = "Link should be a valid URL and within 255 characters") String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(@Size(max = 255, message = "Link should be a valid URL and within 255 characters") String externalLink) {
        this.externalLink = externalLink;
    }

    @Override
    public String toString() {
        return "RecyclingTip{" +
                "id=" + id +
                ", tip='" + tip + '\'' +
                ", applicableMaterial='" + applicableMaterial + '\'' +
                ", externalLink='" + externalLink + '\'' +
                '}';
    }
}
