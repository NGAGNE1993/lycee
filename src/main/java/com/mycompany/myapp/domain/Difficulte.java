package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.NatureDif;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Difficulte.
 */
@Entity
@Table(name = "difficulte")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Difficulte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nature", nullable = false)
    private NatureDif nature;

    @Column(name = "autre_nature")
    private String autreNature;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Lob
    @Column(name = "solution", nullable = false)
    private String solution;

    @Lob
    @Column(name = "observation", nullable = false)
    private String observation;

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

    public Difficulte id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NatureDif getNature() {
        return this.nature;
    }

    public Difficulte nature(NatureDif nature) {
        this.setNature(nature);
        return this;
    }

    public void setNature(NatureDif nature) {
        this.nature = nature;
    }

    public String getAutreNature() {
        return this.autreNature;
    }

    public Difficulte autreNature(String autreNature) {
        this.setAutreNature(autreNature);
        return this;
    }

    public void setAutreNature(String autreNature) {
        this.autreNature = autreNature;
    }

    public String getDescription() {
        return this.description;
    }

    public Difficulte description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSolution() {
        return this.solution;
    }

    public Difficulte solution(String solution) {
        this.setSolution(solution);
        return this;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getObservation() {
        return this.observation;
    }

    public Difficulte observation(String observation) {
        this.setObservation(observation);
        return this;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public LyceesTechniques getLyceesTechniques() {
        return this.lyceesTechniques;
    }

    public void setLyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.lyceesTechniques = lyceesTechniques;
    }

    public Difficulte lyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.setLyceesTechniques(lyceesTechniques);
        return this;
    }

    public Proviseur getProviseur() {
        return this.proviseur;
    }

    public void setProviseur(Proviseur proviseur) {
        this.proviseur = proviseur;
    }

    public Difficulte proviseur(Proviseur proviseur) {
        this.setProviseur(proviseur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Difficulte)) {
            return false;
        }
        return id != null && id.equals(((Difficulte) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Difficulte{" +
            "id=" + getId() +
            ", nature='" + getNature() + "'" +
            ", autreNature='" + getAutreNature() + "'" +
            ", description='" + getDescription() + "'" +
            ", solution='" + getSolution() + "'" +
            ", observation='" + getObservation() + "'" +
            "}";
    }
}
