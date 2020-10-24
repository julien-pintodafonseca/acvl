package com.ensimag.acvl.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.ensimag.acvl.domain.enumeration.TypeCentre;

import com.ensimag.acvl.domain.enumeration.Etat;

/**
 * A MdcMobile.
 */
@Entity
@Table(name = "mdc_mobile")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MdcMobile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "telephone")
    private String telephone;

    @Enumerated(EnumType.STRING)
    @Column(name = "centre")
    private TypeCentre centre;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private Etat etat;

    @Column(name = "est_mobile")
    private Boolean estMobile;

    @Column(name = "est_fixe")
    private Boolean estFixe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public MdcMobile nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public MdcMobile prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public MdcMobile adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public MdcMobile telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public TypeCentre getCentre() {
        return centre;
    }

    public MdcMobile centre(TypeCentre centre) {
        this.centre = centre;
        return this;
    }

    public void setCentre(TypeCentre centre) {
        this.centre = centre;
    }

    public Etat getEtat() {
        return etat;
    }

    public MdcMobile etat(Etat etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Boolean isEstMobile() {
        return estMobile;
    }

    public MdcMobile estMobile(Boolean estMobile) {
        this.estMobile = estMobile;
        return this;
    }

    public void setEstMobile(Boolean estMobile) {
        this.estMobile = estMobile;
    }

    public Boolean isEstFixe() {
        return estFixe;
    }

    public MdcMobile estFixe(Boolean estFixe) {
        this.estFixe = estFixe;
        return this;
    }

    public void setEstFixe(Boolean estFixe) {
        this.estFixe = estFixe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MdcMobile)) {
            return false;
        }
        return id != null && id.equals(((MdcMobile) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MdcMobile{" +
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
