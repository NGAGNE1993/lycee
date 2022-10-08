package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.ELEVAGE;
import com.mycompany.myapp.domain.enumeration.Filieres;
import com.mycompany.myapp.domain.enumeration.SANTEBIOLOGIECHIMIE;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Filiere.
 */
@Entity
@Table(name = "filiere")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Filiere implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nom_filiere", nullable = false)
    private Filieres nomFiliere;

    @Column(name = "nom_autre")
    private String nomAutre;

    @Enumerated(EnumType.STRING)
    @Column(name = "nom_programme")
    private SANTEBIOLOGIECHIMIE nomProgramme;

    @Column(name = "autre_agr")
    private String autreAGR;

    @Enumerated(EnumType.STRING)
    @Column(name = "nom_programme_el")
    private ELEVAGE nomProgrammeEl;

    @Column(name = "autre_el")
    private String autreEL;

    @Column(name = "autre_min")
    private String autreMIN;

    @Column(name = "autre_bat")
    private String autreBAT;

    @Column(name = "autre_mecan")
    private String autreMECAN;

    @Column(name = "autre_menu")
    private String autreMENU;

    @Column(name = "autre_agro")
    private String autreAGRO;

    @Column(name = "autre_electro")
    private String autreELECTRO;

    @Column(name = "autre_struc")
    private String autreSTRUC;

    @Column(name = "autre_ec")
    private String autreEC;

    @Column(name = "autre_com")
    private String autreCOM;

    @Column(name = "autre_in")
    private String autreIN;

    @Column(name = "autre_san")
    private String autreSAN;

    @Column(name = "autre_programme")
    private String autreProgramme;

    @OneToMany(mappedBy = "filiere")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "serie", "filiere", "proviseur" }, allowSetters = true)
    private Set<Enseignant> enseignants = new HashSet<>();

    @OneToMany(mappedBy = "filiere")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "directeur", "filiere" }, allowSetters = true)
    private Set<NiveauxCalification> niveauCalifications = new HashSet<>();

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

    public Filiere id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Filieres getNomFiliere() {
        return this.nomFiliere;
    }

    public Filiere nomFiliere(Filieres nomFiliere) {
        this.setNomFiliere(nomFiliere);
        return this;
    }

    public void setNomFiliere(Filieres nomFiliere) {
        this.nomFiliere = nomFiliere;
    }

    public String getNomAutre() {
        return this.nomAutre;
    }

    public Filiere nomAutre(String nomAutre) {
        this.setNomAutre(nomAutre);
        return this;
    }

    public void setNomAutre(String nomAutre) {
        this.nomAutre = nomAutre;
    }

    public SANTEBIOLOGIECHIMIE getNomProgramme() {
        return this.nomProgramme;
    }

    public Filiere nomProgramme(SANTEBIOLOGIECHIMIE nomProgramme) {
        this.setNomProgramme(nomProgramme);
        return this;
    }

    public void setNomProgramme(SANTEBIOLOGIECHIMIE nomProgramme) {
        this.nomProgramme = nomProgramme;
    }

    public String getAutreAGR() {
        return this.autreAGR;
    }

    public Filiere autreAGR(String autreAGR) {
        this.setAutreAGR(autreAGR);
        return this;
    }

    public void setAutreAGR(String autreAGR) {
        this.autreAGR = autreAGR;
    }

    public ELEVAGE getNomProgrammeEl() {
        return this.nomProgrammeEl;
    }

    public Filiere nomProgrammeEl(ELEVAGE nomProgrammeEl) {
        this.setNomProgrammeEl(nomProgrammeEl);
        return this;
    }

    public void setNomProgrammeEl(ELEVAGE nomProgrammeEl) {
        this.nomProgrammeEl = nomProgrammeEl;
    }

    public String getAutreEL() {
        return this.autreEL;
    }

    public Filiere autreEL(String autreEL) {
        this.setAutreEL(autreEL);
        return this;
    }

    public void setAutreEL(String autreEL) {
        this.autreEL = autreEL;
    }

    public String getAutreMIN() {
        return this.autreMIN;
    }

    public Filiere autreMIN(String autreMIN) {
        this.setAutreMIN(autreMIN);
        return this;
    }

    public void setAutreMIN(String autreMIN) {
        this.autreMIN = autreMIN;
    }

    public String getAutreBAT() {
        return this.autreBAT;
    }

    public Filiere autreBAT(String autreBAT) {
        this.setAutreBAT(autreBAT);
        return this;
    }

    public void setAutreBAT(String autreBAT) {
        this.autreBAT = autreBAT;
    }

    public String getAutreMECAN() {
        return this.autreMECAN;
    }

    public Filiere autreMECAN(String autreMECAN) {
        this.setAutreMECAN(autreMECAN);
        return this;
    }

    public void setAutreMECAN(String autreMECAN) {
        this.autreMECAN = autreMECAN;
    }

    public String getAutreMENU() {
        return this.autreMENU;
    }

    public Filiere autreMENU(String autreMENU) {
        this.setAutreMENU(autreMENU);
        return this;
    }

    public void setAutreMENU(String autreMENU) {
        this.autreMENU = autreMENU;
    }

    public String getAutreAGRO() {
        return this.autreAGRO;
    }

    public Filiere autreAGRO(String autreAGRO) {
        this.setAutreAGRO(autreAGRO);
        return this;
    }

    public void setAutreAGRO(String autreAGRO) {
        this.autreAGRO = autreAGRO;
    }

    public String getAutreELECTRO() {
        return this.autreELECTRO;
    }

    public Filiere autreELECTRO(String autreELECTRO) {
        this.setAutreELECTRO(autreELECTRO);
        return this;
    }

    public void setAutreELECTRO(String autreELECTRO) {
        this.autreELECTRO = autreELECTRO;
    }

    public String getAutreSTRUC() {
        return this.autreSTRUC;
    }

    public Filiere autreSTRUC(String autreSTRUC) {
        this.setAutreSTRUC(autreSTRUC);
        return this;
    }

    public void setAutreSTRUC(String autreSTRUC) {
        this.autreSTRUC = autreSTRUC;
    }

    public String getAutreEC() {
        return this.autreEC;
    }

    public Filiere autreEC(String autreEC) {
        this.setAutreEC(autreEC);
        return this;
    }

    public void setAutreEC(String autreEC) {
        this.autreEC = autreEC;
    }

    public String getAutreCOM() {
        return this.autreCOM;
    }

    public Filiere autreCOM(String autreCOM) {
        this.setAutreCOM(autreCOM);
        return this;
    }

    public void setAutreCOM(String autreCOM) {
        this.autreCOM = autreCOM;
    }

    public String getAutreIN() {
        return this.autreIN;
    }

    public Filiere autreIN(String autreIN) {
        this.setAutreIN(autreIN);
        return this;
    }

    public void setAutreIN(String autreIN) {
        this.autreIN = autreIN;
    }

    public String getAutreSAN() {
        return this.autreSAN;
    }

    public Filiere autreSAN(String autreSAN) {
        this.setAutreSAN(autreSAN);
        return this;
    }

    public void setAutreSAN(String autreSAN) {
        this.autreSAN = autreSAN;
    }

    public String getAutreProgramme() {
        return this.autreProgramme;
    }

    public Filiere autreProgramme(String autreProgramme) {
        this.setAutreProgramme(autreProgramme);
        return this;
    }

    public void setAutreProgramme(String autreProgramme) {
        this.autreProgramme = autreProgramme;
    }

    public Set<Enseignant> getEnseignants() {
        return this.enseignants;
    }

    public void setEnseignants(Set<Enseignant> enseignants) {
        if (this.enseignants != null) {
            this.enseignants.forEach(i -> i.setFiliere(null));
        }
        if (enseignants != null) {
            enseignants.forEach(i -> i.setFiliere(this));
        }
        this.enseignants = enseignants;
    }

    public Filiere enseignants(Set<Enseignant> enseignants) {
        this.setEnseignants(enseignants);
        return this;
    }

    public Filiere addEnseignant(Enseignant enseignant) {
        this.enseignants.add(enseignant);
        enseignant.setFiliere(this);
        return this;
    }

    public Filiere removeEnseignant(Enseignant enseignant) {
        this.enseignants.remove(enseignant);
        enseignant.setFiliere(null);
        return this;
    }

    public Set<NiveauxCalification> getNiveauCalifications() {
        return this.niveauCalifications;
    }

    public void setNiveauCalifications(Set<NiveauxCalification> niveauxCalifications) {
        if (this.niveauCalifications != null) {
            this.niveauCalifications.forEach(i -> i.setFiliere(null));
        }
        if (niveauxCalifications != null) {
            niveauxCalifications.forEach(i -> i.setFiliere(this));
        }
        this.niveauCalifications = niveauxCalifications;
    }

    public Filiere niveauCalifications(Set<NiveauxCalification> niveauxCalifications) {
        this.setNiveauCalifications(niveauxCalifications);
        return this;
    }

    public Filiere addNiveauCalification(NiveauxCalification niveauxCalification) {
        this.niveauCalifications.add(niveauxCalification);
        niveauxCalification.setFiliere(this);
        return this;
    }

    public Filiere removeNiveauCalification(NiveauxCalification niveauxCalification) {
        this.niveauCalifications.remove(niveauxCalification);
        niveauxCalification.setFiliere(null);
        return this;
    }

    public LyceesTechniques getLyceesTechniques() {
        return this.lyceesTechniques;
    }

    public void setLyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.lyceesTechniques = lyceesTechniques;
    }

    public Filiere lyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.setLyceesTechniques(lyceesTechniques);
        return this;
    }

    public DirecteurEtude getDirecteur() {
        return this.directeur;
    }

    public void setDirecteur(DirecteurEtude directeurEtude) {
        this.directeur = directeurEtude;
    }

    public Filiere directeur(DirecteurEtude directeurEtude) {
        this.setDirecteur(directeurEtude);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Filiere)) {
            return false;
        }
        return id != null && id.equals(((Filiere) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Filiere{" +
            "id=" + getId() +
            ", nomFiliere='" + getNomFiliere() + "'" +
            ", nomAutre='" + getNomAutre() + "'" +
            ", nomProgramme='" + getNomProgramme() + "'" +
            ", autreAGR='" + getAutreAGR() + "'" +
            ", nomProgrammeEl='" + getNomProgrammeEl() + "'" +
            ", autreEL='" + getAutreEL() + "'" +
            ", autreMIN='" + getAutreMIN() + "'" +
            ", autreBAT='" + getAutreBAT() + "'" +
            ", autreMECAN='" + getAutreMECAN() + "'" +
            ", autreMENU='" + getAutreMENU() + "'" +
            ", autreAGRO='" + getAutreAGRO() + "'" +
            ", autreELECTRO='" + getAutreELECTRO() + "'" +
            ", autreSTRUC='" + getAutreSTRUC() + "'" +
            ", autreEC='" + getAutreEC() + "'" +
            ", autreCOM='" + getAutreCOM() + "'" +
            ", autreIN='" + getAutreIN() + "'" +
            ", autreSAN='" + getAutreSAN() + "'" +
            ", autreProgramme='" + getAutreProgramme() + "'" +
            "}";
    }
}
