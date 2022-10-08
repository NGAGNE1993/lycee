package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.TypeR;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Recette.
 */
@Entity
@Table(name = "recette")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Recette implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeR type;

    @Column(name = "autre_recette")
    private String autreRecette;

    @Column(name = "type_ressource")
    private String typeRessource;

    @Column(name = "montant")
    private String montant;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JsonIgnoreProperties(value = { "lyceesTechniques", "comptableFinancier", "recettes" }, allowSetters = true)
    private Depense depense;

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
    @JsonIgnoreProperties(value = { "user", "nomLycee", "recettes", "depenses" }, allowSetters = true)
    private ComptableFinancier comptableFinancier;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Recette id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeR getType() {
        return this.type;
    }

    public Recette type(TypeR type) {
        this.setType(type);
        return this;
    }

    public void setType(TypeR type) {
        this.type = type;
    }

    public String getAutreRecette() {
        return this.autreRecette;
    }

    public Recette autreRecette(String autreRecette) {
        this.setAutreRecette(autreRecette);
        return this;
    }

    public void setAutreRecette(String autreRecette) {
        this.autreRecette = autreRecette;
    }

    public String getTypeRessource() {
        return this.typeRessource;
    }

    public Recette typeRessource(String typeRessource) {
        this.setTypeRessource(typeRessource);
        return this;
    }

    public void setTypeRessource(String typeRessource) {
        this.typeRessource = typeRessource;
    }

    public String getMontant() {
        return this.montant;
    }

    public Recette montant(String montant) {
        this.setMontant(montant);
        return this;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Recette date(LocalDate date) {
        this.setDate(date);
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Depense getDepense() {
        return this.depense;
    }

    public void setDepense(Depense depense) {
        this.depense = depense;
    }

    public Recette depense(Depense depense) {
        this.setDepense(depense);
        return this;
    }

    public LyceesTechniques getLyceesTechniques() {
        return this.lyceesTechniques;
    }

    public void setLyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.lyceesTechniques = lyceesTechniques;
    }

    public Recette lyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.setLyceesTechniques(lyceesTechniques);
        return this;
    }

    public ComptableFinancier getComptableFinancier() {
        return this.comptableFinancier;
    }

    public void setComptableFinancier(ComptableFinancier comptableFinancier) {
        this.comptableFinancier = comptableFinancier;
    }

    public Recette comptableFinancier(ComptableFinancier comptableFinancier) {
        this.setComptableFinancier(comptableFinancier);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Recette)) {
            return false;
        }
        return id != null && id.equals(((Recette) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Recette{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", autreRecette='" + getAutreRecette() + "'" +
            ", typeRessource='" + getTypeRessource() + "'" +
            ", montant='" + getMontant() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
