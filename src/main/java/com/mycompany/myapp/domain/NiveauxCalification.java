package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.NiveauQualif;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NiveauxCalification.
 */
@Entity
@Table(name = "niveaux_calification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NiveauxCalification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "niveau_calification", nullable = false)
    private String niveauCalification;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_niveau_calification")
    private NiveauQualif typeNiveauCalification;

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
            "user", "nomLycee", "concours", "salles", "series", "niveaus", "filieres", "niveauCalifications", "apprenants", "examen",
        },
        allowSetters = true
    )
    private DirecteurEtude directeur;

    @ManyToOne
    @JsonIgnoreProperties(value = { "enseignants", "niveauCalifications", "lyceesTechniques", "directeur" }, allowSetters = true)
    private Filiere filiere;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public NiveauxCalification id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNiveauCalification() {
        return this.niveauCalification;
    }

    public NiveauxCalification niveauCalification(String niveauCalification) {
        this.setNiveauCalification(niveauCalification);
        return this;
    }

    public void setNiveauCalification(String niveauCalification) {
        this.niveauCalification = niveauCalification;
    }

    public NiveauQualif getTypeNiveauCalification() {
        return this.typeNiveauCalification;
    }

    public NiveauxCalification typeNiveauCalification(NiveauQualif typeNiveauCalification) {
        this.setTypeNiveauCalification(typeNiveauCalification);
        return this;
    }

    public void setTypeNiveauCalification(NiveauQualif typeNiveauCalification) {
        this.typeNiveauCalification = typeNiveauCalification;
    }

    public LyceesTechniques getLyceesTechniques() {
        return this.lyceesTechniques;
    }

    public void setLyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.lyceesTechniques = lyceesTechniques;
    }

    public NiveauxCalification lyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.setLyceesTechniques(lyceesTechniques);
        return this;
    }

    public DirecteurEtude getDirecteur() {
        return this.directeur;
    }

    public void setDirecteur(DirecteurEtude directeurEtude) {
        this.directeur = directeurEtude;
    }

    public NiveauxCalification directeur(DirecteurEtude directeurEtude) {
        this.setDirecteur(directeurEtude);
        return this;
    }

    public Filiere getFiliere() {
        return this.filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public NiveauxCalification filiere(Filiere filiere) {
        this.setFiliere(filiere);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NiveauxCalification)) {
            return false;
        }
        return id != null && id.equals(((NiveauxCalification) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NiveauxCalification{" +
            "id=" + getId() +
            ", niveauCalification='" + getNiveauCalification() + "'" +
            ", typeNiveauCalification='" + getTypeNiveauCalification() + "'" +
            "}";
    }
}
