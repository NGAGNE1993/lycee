package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.TypeExam;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Examen.
 */
@Entity
@Table(name = "examen")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Examen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeExam type;

    @Column(name = "autres")
    private String autres;

    @Column(name = "nombrereussi")
    private Long nombrereussi;

    @Column(name = "nombre_echeque")
    private Long nombreEcheque;

    @Column(name = "nomre_absent")
    private Long nomreAbsent;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Examen id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeExam getType() {
        return this.type;
    }

    public Examen type(TypeExam type) {
        this.setType(type);
        return this;
    }

    public void setType(TypeExam type) {
        this.type = type;
    }

    public String getAutres() {
        return this.autres;
    }

    public Examen autres(String autres) {
        this.setAutres(autres);
        return this;
    }

    public void setAutres(String autres) {
        this.autres = autres;
    }

    public Long getNombrereussi() {
        return this.nombrereussi;
    }

    public Examen nombrereussi(Long nombrereussi) {
        this.setNombrereussi(nombrereussi);
        return this;
    }

    public void setNombrereussi(Long nombrereussi) {
        this.nombrereussi = nombrereussi;
    }

    public Long getNombreEcheque() {
        return this.nombreEcheque;
    }

    public Examen nombreEcheque(Long nombreEcheque) {
        this.setNombreEcheque(nombreEcheque);
        return this;
    }

    public void setNombreEcheque(Long nombreEcheque) {
        this.nombreEcheque = nombreEcheque;
    }

    public Long getNomreAbsent() {
        return this.nomreAbsent;
    }

    public Examen nomreAbsent(Long nomreAbsent) {
        this.setNomreAbsent(nomreAbsent);
        return this;
    }

    public void setNomreAbsent(Long nomreAbsent) {
        this.nomreAbsent = nomreAbsent;
    }

    public LyceesTechniques getLyceesTechniques() {
        return this.lyceesTechniques;
    }

    public void setLyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.lyceesTechniques = lyceesTechniques;
    }

    public Examen lyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.setLyceesTechniques(lyceesTechniques);
        return this;
    }

    public DirecteurEtude getDirecteur() {
        return this.directeur;
    }

    public void setDirecteur(DirecteurEtude directeurEtude) {
        this.directeur = directeurEtude;
    }

    public Examen directeur(DirecteurEtude directeurEtude) {
        this.setDirecteur(directeurEtude);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Examen)) {
            return false;
        }
        return id != null && id.equals(((Examen) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Examen{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", autres='" + getAutres() + "'" +
            ", nombrereussi=" + getNombrereussi() +
            ", nombreEcheque=" + getNombreEcheque() +
            ", nomreAbsent=" + getNomreAbsent() +
            "}";
    }
}
