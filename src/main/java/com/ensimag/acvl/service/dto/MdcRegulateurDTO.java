package com.ensimag.acvl.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.ensimag.acvl.domain.enumeration.TypeCentre;
import com.ensimag.acvl.domain.enumeration.Etat;

/**
 * A DTO for the {@link com.ensimag.acvl.domain.MdcRegulateur} entity.
 */
public class MdcRegulateurDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    private String adresse;

    private String telephone;

    private TypeCentre centre;

    private Etat etat;

    private Boolean estMobile;

    private Boolean estFixe;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public TypeCentre getCentre() {
        return centre;
    }

    public void setCentre(TypeCentre centre) {
        this.centre = centre;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Boolean isEstMobile() {
        return estMobile;
    }

    public void setEstMobile(Boolean estMobile) {
        this.estMobile = estMobile;
    }

    public Boolean isEstFixe() {
        return estFixe;
    }

    public void setEstFixe(Boolean estFixe) {
        this.estFixe = estFixe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MdcRegulateurDTO)) {
            return false;
        }

        return id != null && id.equals(((MdcRegulateurDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MdcRegulateurDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", centre='" + getCentre() + "'" +
            ", etat='" + getEtat() + "'" +
            ", estMobile='" + isEstMobile() + "'" +
            ", estFixe='" + isEstFixe() + "'" +
            "}";
    }
}
