package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.NaturePart;
import com.mycompany.myapp.domain.enumeration.Provenance;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Partenaire.
 */
@Entity
@Table(name = "partenaire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Partenaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "denomination_partenaire", nullable = false)
    private String denominationPartenaire;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "statau_partenaire", nullable = false)
    private Provenance statauPartenaire;

    @Column(name = "autre_categorie")
    private String autreCategorie;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_appui", nullable = false)
    private NaturePart typeAppui;

    @Column(name = "autre_nature")
    private String autreNature;

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

    public Partenaire id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenominationPartenaire() {
        return this.denominationPartenaire;
    }

    public Partenaire denominationPartenaire(String denominationPartenaire) {
        this.setDenominationPartenaire(denominationPartenaire);
        return this;
    }

    public void setDenominationPartenaire(String denominationPartenaire) {
        this.denominationPartenaire = denominationPartenaire;
    }

    public Provenance getStatauPartenaire() {
        return this.statauPartenaire;
    }

    public Partenaire statauPartenaire(Provenance statauPartenaire) {
        this.setStatauPartenaire(statauPartenaire);
        return this;
    }

    public void setStatauPartenaire(Provenance statauPartenaire) {
        this.statauPartenaire = statauPartenaire;
    }

    public String getAutreCategorie() {
        return this.autreCategorie;
    }

    public Partenaire autreCategorie(String autreCategorie) {
        this.setAutreCategorie(autreCategorie);
        return this;
    }

    public void setAutreCategorie(String autreCategorie) {
        this.autreCategorie = autreCategorie;
    }

    public NaturePart getTypeAppui() {
        return this.typeAppui;
    }

    public Partenaire typeAppui(NaturePart typeAppui) {
        this.setTypeAppui(typeAppui);
        return this;
    }

    public void setTypeAppui(NaturePart typeAppui) {
        this.typeAppui = typeAppui;
    }

    public String getAutreNature() {
        return this.autreNature;
    }

    public Partenaire autreNature(String autreNature) {
        this.setAutreNature(autreNature);
        return this;
    }

    public void setAutreNature(String autreNature) {
        this.autreNature = autreNature;
    }

    public LyceesTechniques getLyceesTechniques() {
        return this.lyceesTechniques;
    }

    public void setLyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.lyceesTechniques = lyceesTechniques;
    }

    public Partenaire lyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.setLyceesTechniques(lyceesTechniques);
        return this;
    }

    public Proviseur getProviseur() {
        return this.proviseur;
    }

    public void setProviseur(Proviseur proviseur) {
        this.proviseur = proviseur;
    }

    public Partenaire proviseur(Proviseur proviseur) {
        this.setProviseur(proviseur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Partenaire)) {
            return false;
        }
        return id != null && id.equals(((Partenaire) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Partenaire{" +
            "id=" + getId() +
            ", denominationPartenaire='" + getDenominationPartenaire() + "'" +
            ", statauPartenaire='" + getStatauPartenaire() + "'" +
            ", autreCategorie='" + getAutreCategorie() + "'" +
            ", typeAppui='" + getTypeAppui() + "'" +
            ", autreNature='" + getAutreNature() + "'" +
            "}";
    }
}
