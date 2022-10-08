package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.TypeDepense;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Depense.
 */
@Entity
@Table(name = "depense")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Depense implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_depense", nullable = false)
    private TypeDepense typeDepense;

    @Column(name = "autre_depense")
    private String autreDepense;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "montant_depense", nullable = false)
    private String montantDepense;

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

    @OneToMany(mappedBy = "depense")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "depense", "lyceesTechniques", "comptableFinancier" }, allowSetters = true)
    private Set<Recette> recettes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Depense id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeDepense getTypeDepense() {
        return this.typeDepense;
    }

    public Depense typeDepense(TypeDepense typeDepense) {
        this.setTypeDepense(typeDepense);
        return this;
    }

    public void setTypeDepense(TypeDepense typeDepense) {
        this.typeDepense = typeDepense;
    }

    public String getAutreDepense() {
        return this.autreDepense;
    }

    public Depense autreDepense(String autreDepense) {
        this.setAutreDepense(autreDepense);
        return this;
    }

    public void setAutreDepense(String autreDepense) {
        this.autreDepense = autreDepense;
    }

    public String getDescription() {
        return this.description;
    }

    public Depense description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMontantDepense() {
        return this.montantDepense;
    }

    public Depense montantDepense(String montantDepense) {
        this.setMontantDepense(montantDepense);
        return this;
    }

    public void setMontantDepense(String montantDepense) {
        this.montantDepense = montantDepense;
    }

    public LyceesTechniques getLyceesTechniques() {
        return this.lyceesTechniques;
    }

    public void setLyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.lyceesTechniques = lyceesTechniques;
    }

    public Depense lyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.setLyceesTechniques(lyceesTechniques);
        return this;
    }

    public ComptableFinancier getComptableFinancier() {
        return this.comptableFinancier;
    }

    public void setComptableFinancier(ComptableFinancier comptableFinancier) {
        this.comptableFinancier = comptableFinancier;
    }

    public Depense comptableFinancier(ComptableFinancier comptableFinancier) {
        this.setComptableFinancier(comptableFinancier);
        return this;
    }

    public Set<Recette> getRecettes() {
        return this.recettes;
    }

    public void setRecettes(Set<Recette> recettes) {
        if (this.recettes != null) {
            this.recettes.forEach(i -> i.setDepense(null));
        }
        if (recettes != null) {
            recettes.forEach(i -> i.setDepense(this));
        }
        this.recettes = recettes;
    }

    public Depense recettes(Set<Recette> recettes) {
        this.setRecettes(recettes);
        return this;
    }

    public Depense addRecette(Recette recette) {
        this.recettes.add(recette);
        recette.setDepense(this);
        return this;
    }

    public Depense removeRecette(Recette recette) {
        this.recettes.remove(recette);
        recette.setDepense(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Depense)) {
            return false;
        }
        return id != null && id.equals(((Depense) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Depense{" +
            "id=" + getId() +
            ", typeDepense='" + getTypeDepense() + "'" +
            ", autreDepense='" + getAutreDepense() + "'" +
            ", description='" + getDescription() + "'" +
            ", montantDepense='" + getMontantDepense() + "'" +
            "}";
    }
}
