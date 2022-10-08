package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DirecteurEtude.
 */
@Entity
@Table(name = "directeur_etude")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DirecteurEtude implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nom_prenom", nullable = false)
    private String nomPrenom;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

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
    @OneToOne
    @JoinColumn(unique = true)
    private LyceesTechniques nomLycee;

    @OneToMany(mappedBy = "directeur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "directeur" }, allowSetters = true)
    private Set<Concours> concours = new HashSet<>();

    @OneToMany(mappedBy = "directeur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "directeur" }, allowSetters = true)
    private Set<Salle> salles = new HashSet<>();

    @OneToMany(mappedBy = "directeur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "enseignants", "niveaus", "lyceesTechniques", "directeur" }, allowSetters = true)
    private Set<Serie> series = new HashSet<>();

    @OneToMany(mappedBy = "directeur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "directeur", "serie" }, allowSetters = true)
    private Set<NiveauxEtudes> niveaus = new HashSet<>();

    @OneToMany(mappedBy = "directeur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "enseignants", "niveauCalifications", "lyceesTechniques", "directeur" }, allowSetters = true)
    private Set<Filiere> filieres = new HashSet<>();

    @OneToMany(mappedBy = "directeur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "directeur", "filiere" }, allowSetters = true)
    private Set<NiveauxCalification> niveauCalifications = new HashSet<>();

    @OneToMany(mappedBy = "directeur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "serie", "filiere", "niveauxEtudes", "niveauxCalification", "lyceesTechniques", "directeur" },
        allowSetters = true
    )
    private Set<Apprenant> apprenants = new HashSet<>();

    @OneToMany(mappedBy = "directeur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "directeur" }, allowSetters = true)
    private Set<Examen> examen = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DirecteurEtude id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomPrenom() {
        return this.nomPrenom;
    }

    public DirecteurEtude nomPrenom(String nomPrenom) {
        this.setNomPrenom(nomPrenom);
        return this;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DirecteurEtude user(User user) {
        this.setUser(user);
        return this;
    }

    public LyceesTechniques getNomLycee() {
        return this.nomLycee;
    }

    public void setNomLycee(LyceesTechniques lyceesTechniques) {
        this.nomLycee = lyceesTechniques;
    }

    public DirecteurEtude nomLycee(LyceesTechniques lyceesTechniques) {
        this.setNomLycee(lyceesTechniques);
        return this;
    }

    public Set<Concours> getConcours() {
        return this.concours;
    }

    public void setConcours(Set<Concours> concours) {
        if (this.concours != null) {
            this.concours.forEach(i -> i.setDirecteur(null));
        }
        if (concours != null) {
            concours.forEach(i -> i.setDirecteur(this));
        }
        this.concours = concours;
    }

    public DirecteurEtude concours(Set<Concours> concours) {
        this.setConcours(concours);
        return this;
    }

    public DirecteurEtude addConcours(Concours concours) {
        this.concours.add(concours);
        concours.setDirecteur(this);
        return this;
    }

    public DirecteurEtude removeConcours(Concours concours) {
        this.concours.remove(concours);
        concours.setDirecteur(null);
        return this;
    }

    public Set<Salle> getSalles() {
        return this.salles;
    }

    public void setSalles(Set<Salle> salles) {
        if (this.salles != null) {
            this.salles.forEach(i -> i.setDirecteur(null));
        }
        if (salles != null) {
            salles.forEach(i -> i.setDirecteur(this));
        }
        this.salles = salles;
    }

    public DirecteurEtude salles(Set<Salle> salles) {
        this.setSalles(salles);
        return this;
    }

    public DirecteurEtude addSalle(Salle salle) {
        this.salles.add(salle);
        salle.setDirecteur(this);
        return this;
    }

    public DirecteurEtude removeSalle(Salle salle) {
        this.salles.remove(salle);
        salle.setDirecteur(null);
        return this;
    }

    public Set<Serie> getSeries() {
        return this.series;
    }

    public void setSeries(Set<Serie> series) {
        if (this.series != null) {
            this.series.forEach(i -> i.setDirecteur(null));
        }
        if (series != null) {
            series.forEach(i -> i.setDirecteur(this));
        }
        this.series = series;
    }

    public DirecteurEtude series(Set<Serie> series) {
        this.setSeries(series);
        return this;
    }

    public DirecteurEtude addSerie(Serie serie) {
        this.series.add(serie);
        serie.setDirecteur(this);
        return this;
    }

    public DirecteurEtude removeSerie(Serie serie) {
        this.series.remove(serie);
        serie.setDirecteur(null);
        return this;
    }

    public Set<NiveauxEtudes> getNiveaus() {
        return this.niveaus;
    }

    public void setNiveaus(Set<NiveauxEtudes> niveauxEtudes) {
        if (this.niveaus != null) {
            this.niveaus.forEach(i -> i.setDirecteur(null));
        }
        if (niveauxEtudes != null) {
            niveauxEtudes.forEach(i -> i.setDirecteur(this));
        }
        this.niveaus = niveauxEtudes;
    }

    public DirecteurEtude niveaus(Set<NiveauxEtudes> niveauxEtudes) {
        this.setNiveaus(niveauxEtudes);
        return this;
    }

    public DirecteurEtude addNiveau(NiveauxEtudes niveauxEtudes) {
        this.niveaus.add(niveauxEtudes);
        niveauxEtudes.setDirecteur(this);
        return this;
    }

    public DirecteurEtude removeNiveau(NiveauxEtudes niveauxEtudes) {
        this.niveaus.remove(niveauxEtudes);
        niveauxEtudes.setDirecteur(null);
        return this;
    }

    public Set<Filiere> getFilieres() {
        return this.filieres;
    }

    public void setFilieres(Set<Filiere> filieres) {
        if (this.filieres != null) {
            this.filieres.forEach(i -> i.setDirecteur(null));
        }
        if (filieres != null) {
            filieres.forEach(i -> i.setDirecteur(this));
        }
        this.filieres = filieres;
    }

    public DirecteurEtude filieres(Set<Filiere> filieres) {
        this.setFilieres(filieres);
        return this;
    }

    public DirecteurEtude addFiliere(Filiere filiere) {
        this.filieres.add(filiere);
        filiere.setDirecteur(this);
        return this;
    }

    public DirecteurEtude removeFiliere(Filiere filiere) {
        this.filieres.remove(filiere);
        filiere.setDirecteur(null);
        return this;
    }

    public Set<NiveauxCalification> getNiveauCalifications() {
        return this.niveauCalifications;
    }

    public void setNiveauCalifications(Set<NiveauxCalification> niveauxCalifications) {
        if (this.niveauCalifications != null) {
            this.niveauCalifications.forEach(i -> i.setDirecteur(null));
        }
        if (niveauxCalifications != null) {
            niveauxCalifications.forEach(i -> i.setDirecteur(this));
        }
        this.niveauCalifications = niveauxCalifications;
    }

    public DirecteurEtude niveauCalifications(Set<NiveauxCalification> niveauxCalifications) {
        this.setNiveauCalifications(niveauxCalifications);
        return this;
    }

    public DirecteurEtude addNiveauCalification(NiveauxCalification niveauxCalification) {
        this.niveauCalifications.add(niveauxCalification);
        niveauxCalification.setDirecteur(this);
        return this;
    }

    public DirecteurEtude removeNiveauCalification(NiveauxCalification niveauxCalification) {
        this.niveauCalifications.remove(niveauxCalification);
        niveauxCalification.setDirecteur(null);
        return this;
    }

    public Set<Apprenant> getApprenants() {
        return this.apprenants;
    }

    public void setApprenants(Set<Apprenant> apprenants) {
        if (this.apprenants != null) {
            this.apprenants.forEach(i -> i.setDirecteur(null));
        }
        if (apprenants != null) {
            apprenants.forEach(i -> i.setDirecteur(this));
        }
        this.apprenants = apprenants;
    }

    public DirecteurEtude apprenants(Set<Apprenant> apprenants) {
        this.setApprenants(apprenants);
        return this;
    }

    public DirecteurEtude addApprenant(Apprenant apprenant) {
        this.apprenants.add(apprenant);
        apprenant.setDirecteur(this);
        return this;
    }

    public DirecteurEtude removeApprenant(Apprenant apprenant) {
        this.apprenants.remove(apprenant);
        apprenant.setDirecteur(null);
        return this;
    }

    public Set<Examen> getExamen() {
        return this.examen;
    }

    public void setExamen(Set<Examen> examen) {
        if (this.examen != null) {
            this.examen.forEach(i -> i.setDirecteur(null));
        }
        if (examen != null) {
            examen.forEach(i -> i.setDirecteur(this));
        }
        this.examen = examen;
    }

    public DirecteurEtude examen(Set<Examen> examen) {
        this.setExamen(examen);
        return this;
    }

    public DirecteurEtude addExamen(Examen examen) {
        this.examen.add(examen);
        examen.setDirecteur(this);
        return this;
    }

    public DirecteurEtude removeExamen(Examen examen) {
        this.examen.remove(examen);
        examen.setDirecteur(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DirecteurEtude)) {
            return false;
        }
        return id != null && id.equals(((DirecteurEtude) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DirecteurEtude{" +
            "id=" + getId() +
            ", nomPrenom='" + getNomPrenom() + "'" +
            "}";
    }
}
