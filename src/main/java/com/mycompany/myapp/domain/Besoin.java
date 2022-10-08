package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.TypeB;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Besoin.
 */
@Entity
@Table(name = "besoin")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Besoin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeB type;

    @Column(name = "autre_besoin")
    private String autreBesoin;

    @Lob
    @Column(name = "designation")
    private String designation;

    @Column(name = "etat_actuel")
    private String etatActuel;

    @Lob
    @Column(name = "intervention_souhaitee", nullable = false)
    private String interventionSouhaitee;

    @Lob
    @Column(name = "justification", nullable = false)
    private String justification;

    @ManyToOne
    @JsonIgnoreProperties(
        value = {
            "concours",
            "rapportRFS",
            "examen",
            "recettes",
            "depenses",
            "visites",
            "organes",
            "mouvementMatieres",
            "partenaires",
            "besoins",
            "personnelAdmiistratifs",
            "personnelAppuis",
            "series",
            "filieres",
            "salles",
            "apprenants",
            "enseignants",
            "difficultes",
            "niveaucalifications",
            "reformeMatieres",
            "iventaireDesMatetieres",
            "niveauetudes",
            "lyceeTechnique",
            "proviseur",
            "directeurEtude",
            "comptableFinancie",
            "comptableMatiere",
        },
        allowSetters = true
    )
    private LyceesTechniques lyceesTechniques;

    @ManyToOne
    @JsonIgnoreProperties(
        value = {
            "user",
            "nomLycee",
            "personnelAdmiistratifs",
            "partenaires",
            "besoins",
            "organes",
            "visites",
            "difficultes",
            "rapportRFS",
            "enseignants",
            "personnelAppuis",
        },
        allowSetters = true
    )
    private Proviseur proviseur;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Besoin id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeB getType() {
        return this.type;
    }

    public Besoin type(TypeB type) {
        this.setType(type);
        return this;
    }

    public void setType(TypeB type) {
        this.type = type;
    }

    public String getAutreBesoin() {
        return this.autreBesoin;
    }

    public Besoin autreBesoin(String autreBesoin) {
        this.setAutreBesoin(autreBesoin);
        return this;
    }

    public void setAutreBesoin(String autreBesoin) {
        this.autreBesoin = autreBesoin;
    }

    public String getDesignation() {
        return this.designation;
    }

    public Besoin designation(String designation) {
        this.setDesignation(designation);
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEtatActuel() {
        return this.etatActuel;
    }

    public Besoin etatActuel(String etatActuel) {
        this.setEtatActuel(etatActuel);
        return this;
    }

    public void setEtatActuel(String etatActuel) {
        this.etatActuel = etatActuel;
    }

    public String getInterventionSouhaitee() {
        return this.interventionSouhaitee;
    }

    public Besoin interventionSouhaitee(String interventionSouhaitee) {
        this.setInterventionSouhaitee(interventionSouhaitee);
        return this;
    }

    public void setInterventionSouhaitee(String interventionSouhaitee) {
        this.interventionSouhaitee = interventionSouhaitee;
    }

    public String getJustification() {
        return this.justification;
    }

    public Besoin justification(String justification) {
        this.setJustification(justification);
        return this;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public LyceesTechniques getLyceesTechniques() {
        return this.lyceesTechniques;
    }

    public void setLyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.lyceesTechniques = lyceesTechniques;
    }

    public Besoin lyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.setLyceesTechniques(lyceesTechniques);
        return this;
    }

    public Proviseur getProviseur() {
        return this.proviseur;
    }

    public void setProviseur(Proviseur proviseur) {
        this.proviseur = proviseur;
    }

    public Besoin proviseur(Proviseur proviseur) {
        this.setProviseur(proviseur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Besoin)) {
            return false;
        }
        return id != null && id.equals(((Besoin) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Besoin{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", autreBesoin='" + getAutreBesoin() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", etatActuel='" + getEtatActuel() + "'" +
            ", interventionSouhaitee='" + getInterventionSouhaitee() + "'" +
            ", justification='" + getJustification() + "'" +
            "}";
    }
}
