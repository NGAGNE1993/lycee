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
 * A LyceesTechniques.
 */
@Entity
@Table(name = "lycees_techniques")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LyceesTechniques implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nom_lycee", nullable = false)
    private String nomLycee;

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "directeur" }, allowSetters = true)
    private Set<Concours> concours = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "proviseur" }, allowSetters = true)
    private Set<RapportRF> rapportRFS = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "directeur" }, allowSetters = true)
    private Set<Examen> examen = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "depense", "lyceesTechniques", "comptableFinancier" }, allowSetters = true)
    private Set<Recette> recettes = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "comptableFinancier", "recettes" }, allowSetters = true)
    private Set<Depense> depenses = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "proviseur" }, allowSetters = true)
    private Set<Visite> visites = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "proviseur" }, allowSetters = true)
    private Set<OrganeGestion> organes = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "comptableMatiere" }, allowSetters = true)
    private Set<MouvementMatiere> mouvementMatieres = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "proviseur" }, allowSetters = true)
    private Set<Partenaire> partenaires = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "proviseur" }, allowSetters = true)
    private Set<Besoin> besoins = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "proviseur" }, allowSetters = true)
    private Set<PersonnelAdministratif> personnelAdmiistratifs = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "directeur" }, allowSetters = true)
    private Set<PersonnelAppui> personnelAppuis = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "enseignants", "niveaus", "lyceesTechniques", "directeur" }, allowSetters = true)
    private Set<Serie> series = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "enseignants", "niveauCalifications", "lyceesTechniques", "directeur" }, allowSetters = true)
    private Set<Filiere> filieres = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "directeur" }, allowSetters = true)
    private Set<Salle> salles = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "serie", "filiere", "niveauxEtudes", "niveauxCalification", "lyceesTechniques", "directeur" },
        allowSetters = true
    )
    private Set<Apprenant> apprenants = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "serie", "filiere", "proviseur" }, allowSetters = true)
    private Set<Enseignant> enseignants = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "proviseur" }, allowSetters = true)
    private Set<Difficulte> difficultes = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "directeur", "filiere" }, allowSetters = true)
    private Set<NiveauxCalification> niveaucalifications = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "comptableMatiere" }, allowSetters = true)
    private Set<ReformeMatiere> reformeMatieres = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "comptableMatiere" }, allowSetters = true)
    private Set<IventaireDesMatetiere> iventaireDesMatetieres = new HashSet<>();

    @OneToMany(mappedBy = "lyceesTechniques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "directeur", "serie" }, allowSetters = true)
    private Set<NiveauxEtudes> niveauetudes = new HashSet<>();

    @JsonIgnoreProperties(value = { "proviseur", "nomLycee" }, allowSetters = true)
    @OneToOne(mappedBy = "nomLycee")
    private LyceeTechnique lyceeTechnique;

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
    @OneToOne(mappedBy = "nomLycee")
    private Proviseur proviseur;

    @JsonIgnoreProperties(
        value = {
            "user", "nomLycee", "concours", "salles", "series", "niveaus", "filieres", "niveauCalifications", "apprenants", "examen",
        },
        allowSetters = true
    )
    @OneToOne(mappedBy = "nomLycee")
    private DirecteurEtude directeurEtude;

    @JsonIgnoreProperties(value = { "user", "nomLycee", "recettes", "depenses" }, allowSetters = true)
    @OneToOne(mappedBy = "nomLycee")
    private ComptableFinancier comptableFinancie;

    @JsonIgnoreProperties(
        value = { "user", "nomLycee", "iventaireDesMatetieres", "mouvementMatieres", "reformeMatieres" },
        allowSetters = true
    )
    @OneToOne(mappedBy = "nomLycee")
    private ComptableMatiere comptableMatiere;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LyceesTechniques id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomLycee() {
        return this.nomLycee;
    }

    public LyceesTechniques nomLycee(String nomLycee) {
        this.setNomLycee(nomLycee);
        return this;
    }

    public void setNomLycee(String nomLycee) {
        this.nomLycee = nomLycee;
    }

    public Set<Concours> getConcours() {
        return this.concours;
    }

    public void setConcours(Set<Concours> concours) {
        if (this.concours != null) {
            this.concours.forEach(i -> i.setLyceesTechniques(null));
        }
        if (concours != null) {
            concours.forEach(i -> i.setLyceesTechniques(this));
        }
        this.concours = concours;
    }

    public LyceesTechniques concours(Set<Concours> concours) {
        this.setConcours(concours);
        return this;
    }

    public LyceesTechniques addConcours(Concours concours) {
        this.concours.add(concours);
        concours.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeConcours(Concours concours) {
        this.concours.remove(concours);
        concours.setLyceesTechniques(null);
        return this;
    }

    public Set<RapportRF> getRapportRFS() {
        return this.rapportRFS;
    }

    public void setRapportRFS(Set<RapportRF> rapportRFS) {
        if (this.rapportRFS != null) {
            this.rapportRFS.forEach(i -> i.setLyceesTechniques(null));
        }
        if (rapportRFS != null) {
            rapportRFS.forEach(i -> i.setLyceesTechniques(this));
        }
        this.rapportRFS = rapportRFS;
    }

    public LyceesTechniques rapportRFS(Set<RapportRF> rapportRFS) {
        this.setRapportRFS(rapportRFS);
        return this;
    }

    public LyceesTechniques addRapportRF(RapportRF rapportRF) {
        this.rapportRFS.add(rapportRF);
        rapportRF.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeRapportRF(RapportRF rapportRF) {
        this.rapportRFS.remove(rapportRF);
        rapportRF.setLyceesTechniques(null);
        return this;
    }

    public Set<Examen> getExamen() {
        return this.examen;
    }

    public void setExamen(Set<Examen> examen) {
        if (this.examen != null) {
            this.examen.forEach(i -> i.setLyceesTechniques(null));
        }
        if (examen != null) {
            examen.forEach(i -> i.setLyceesTechniques(this));
        }
        this.examen = examen;
    }

    public LyceesTechniques examen(Set<Examen> examen) {
        this.setExamen(examen);
        return this;
    }

    public LyceesTechniques addExamen(Examen examen) {
        this.examen.add(examen);
        examen.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeExamen(Examen examen) {
        this.examen.remove(examen);
        examen.setLyceesTechniques(null);
        return this;
    }

    public Set<Recette> getRecettes() {
        return this.recettes;
    }

    public void setRecettes(Set<Recette> recettes) {
        if (this.recettes != null) {
            this.recettes.forEach(i -> i.setLyceesTechniques(null));
        }
        if (recettes != null) {
            recettes.forEach(i -> i.setLyceesTechniques(this));
        }
        this.recettes = recettes;
    }

    public LyceesTechniques recettes(Set<Recette> recettes) {
        this.setRecettes(recettes);
        return this;
    }

    public LyceesTechniques addRecette(Recette recette) {
        this.recettes.add(recette);
        recette.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeRecette(Recette recette) {
        this.recettes.remove(recette);
        recette.setLyceesTechniques(null);
        return this;
    }

    public Set<Depense> getDepenses() {
        return this.depenses;
    }

    public void setDepenses(Set<Depense> depenses) {
        if (this.depenses != null) {
            this.depenses.forEach(i -> i.setLyceesTechniques(null));
        }
        if (depenses != null) {
            depenses.forEach(i -> i.setLyceesTechniques(this));
        }
        this.depenses = depenses;
    }

    public LyceesTechniques depenses(Set<Depense> depenses) {
        this.setDepenses(depenses);
        return this;
    }

    public LyceesTechniques addDepense(Depense depense) {
        this.depenses.add(depense);
        depense.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeDepense(Depense depense) {
        this.depenses.remove(depense);
        depense.setLyceesTechniques(null);
        return this;
    }

    public Set<Visite> getVisites() {
        return this.visites;
    }

    public void setVisites(Set<Visite> visites) {
        if (this.visites != null) {
            this.visites.forEach(i -> i.setLyceesTechniques(null));
        }
        if (visites != null) {
            visites.forEach(i -> i.setLyceesTechniques(this));
        }
        this.visites = visites;
    }

    public LyceesTechniques visites(Set<Visite> visites) {
        this.setVisites(visites);
        return this;
    }

    public LyceesTechniques addVisite(Visite visite) {
        this.visites.add(visite);
        visite.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeVisite(Visite visite) {
        this.visites.remove(visite);
        visite.setLyceesTechniques(null);
        return this;
    }

    public Set<OrganeGestion> getOrganes() {
        return this.organes;
    }

    public void setOrganes(Set<OrganeGestion> organeGestions) {
        if (this.organes != null) {
            this.organes.forEach(i -> i.setLyceesTechniques(null));
        }
        if (organeGestions != null) {
            organeGestions.forEach(i -> i.setLyceesTechniques(this));
        }
        this.organes = organeGestions;
    }

    public LyceesTechniques organes(Set<OrganeGestion> organeGestions) {
        this.setOrganes(organeGestions);
        return this;
    }

    public LyceesTechniques addOrgane(OrganeGestion organeGestion) {
        this.organes.add(organeGestion);
        organeGestion.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeOrgane(OrganeGestion organeGestion) {
        this.organes.remove(organeGestion);
        organeGestion.setLyceesTechniques(null);
        return this;
    }

    public Set<MouvementMatiere> getMouvementMatieres() {
        return this.mouvementMatieres;
    }

    public void setMouvementMatieres(Set<MouvementMatiere> mouvementMatieres) {
        if (this.mouvementMatieres != null) {
            this.mouvementMatieres.forEach(i -> i.setLyceesTechniques(null));
        }
        if (mouvementMatieres != null) {
            mouvementMatieres.forEach(i -> i.setLyceesTechniques(this));
        }
        this.mouvementMatieres = mouvementMatieres;
    }

    public LyceesTechniques mouvementMatieres(Set<MouvementMatiere> mouvementMatieres) {
        this.setMouvementMatieres(mouvementMatieres);
        return this;
    }

    public LyceesTechniques addMouvementMatiere(MouvementMatiere mouvementMatiere) {
        this.mouvementMatieres.add(mouvementMatiere);
        mouvementMatiere.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeMouvementMatiere(MouvementMatiere mouvementMatiere) {
        this.mouvementMatieres.remove(mouvementMatiere);
        mouvementMatiere.setLyceesTechniques(null);
        return this;
    }

    public Set<Partenaire> getPartenaires() {
        return this.partenaires;
    }

    public void setPartenaires(Set<Partenaire> partenaires) {
        if (this.partenaires != null) {
            this.partenaires.forEach(i -> i.setLyceesTechniques(null));
        }
        if (partenaires != null) {
            partenaires.forEach(i -> i.setLyceesTechniques(this));
        }
        this.partenaires = partenaires;
    }

    public LyceesTechniques partenaires(Set<Partenaire> partenaires) {
        this.setPartenaires(partenaires);
        return this;
    }

    public LyceesTechniques addPartenaire(Partenaire partenaire) {
        this.partenaires.add(partenaire);
        partenaire.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removePartenaire(Partenaire partenaire) {
        this.partenaires.remove(partenaire);
        partenaire.setLyceesTechniques(null);
        return this;
    }

    public Set<Besoin> getBesoins() {
        return this.besoins;
    }

    public void setBesoins(Set<Besoin> besoins) {
        if (this.besoins != null) {
            this.besoins.forEach(i -> i.setLyceesTechniques(null));
        }
        if (besoins != null) {
            besoins.forEach(i -> i.setLyceesTechniques(this));
        }
        this.besoins = besoins;
    }

    public LyceesTechniques besoins(Set<Besoin> besoins) {
        this.setBesoins(besoins);
        return this;
    }

    public LyceesTechniques addBesoin(Besoin besoin) {
        this.besoins.add(besoin);
        besoin.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeBesoin(Besoin besoin) {
        this.besoins.remove(besoin);
        besoin.setLyceesTechniques(null);
        return this;
    }

    public Set<PersonnelAdministratif> getPersonnelAdmiistratifs() {
        return this.personnelAdmiistratifs;
    }

    public void setPersonnelAdmiistratifs(Set<PersonnelAdministratif> personnelAdministratifs) {
        if (this.personnelAdmiistratifs != null) {
            this.personnelAdmiistratifs.forEach(i -> i.setLyceesTechniques(null));
        }
        if (personnelAdministratifs != null) {
            personnelAdministratifs.forEach(i -> i.setLyceesTechniques(this));
        }
        this.personnelAdmiistratifs = personnelAdministratifs;
    }

    public LyceesTechniques personnelAdmiistratifs(Set<PersonnelAdministratif> personnelAdministratifs) {
        this.setPersonnelAdmiistratifs(personnelAdministratifs);
        return this;
    }

    public LyceesTechniques addPersonnelAdmiistratif(PersonnelAdministratif personnelAdministratif) {
        this.personnelAdmiistratifs.add(personnelAdministratif);
        personnelAdministratif.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removePersonnelAdmiistratif(PersonnelAdministratif personnelAdministratif) {
        this.personnelAdmiistratifs.remove(personnelAdministratif);
        personnelAdministratif.setLyceesTechniques(null);
        return this;
    }

    public Set<PersonnelAppui> getPersonnelAppuis() {
        return this.personnelAppuis;
    }

    public void setPersonnelAppuis(Set<PersonnelAppui> personnelAppuis) {
        if (this.personnelAppuis != null) {
            this.personnelAppuis.forEach(i -> i.setLyceesTechniques(null));
        }
        if (personnelAppuis != null) {
            personnelAppuis.forEach(i -> i.setLyceesTechniques(this));
        }
        this.personnelAppuis = personnelAppuis;
    }

    public LyceesTechniques personnelAppuis(Set<PersonnelAppui> personnelAppuis) {
        this.setPersonnelAppuis(personnelAppuis);
        return this;
    }

    public LyceesTechniques addPersonnelAppui(PersonnelAppui personnelAppui) {
        this.personnelAppuis.add(personnelAppui);
        personnelAppui.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removePersonnelAppui(PersonnelAppui personnelAppui) {
        this.personnelAppuis.remove(personnelAppui);
        personnelAppui.setLyceesTechniques(null);
        return this;
    }

    public Set<Serie> getSeries() {
        return this.series;
    }

    public void setSeries(Set<Serie> series) {
        if (this.series != null) {
            this.series.forEach(i -> i.setLyceesTechniques(null));
        }
        if (series != null) {
            series.forEach(i -> i.setLyceesTechniques(this));
        }
        this.series = series;
    }

    public LyceesTechniques series(Set<Serie> series) {
        this.setSeries(series);
        return this;
    }

    public LyceesTechniques addSerie(Serie serie) {
        this.series.add(serie);
        serie.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeSerie(Serie serie) {
        this.series.remove(serie);
        serie.setLyceesTechniques(null);
        return this;
    }

    public Set<Filiere> getFilieres() {
        return this.filieres;
    }

    public void setFilieres(Set<Filiere> filieres) {
        if (this.filieres != null) {
            this.filieres.forEach(i -> i.setLyceesTechniques(null));
        }
        if (filieres != null) {
            filieres.forEach(i -> i.setLyceesTechniques(this));
        }
        this.filieres = filieres;
    }

    public LyceesTechniques filieres(Set<Filiere> filieres) {
        this.setFilieres(filieres);
        return this;
    }

    public LyceesTechniques addFiliere(Filiere filiere) {
        this.filieres.add(filiere);
        filiere.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeFiliere(Filiere filiere) {
        this.filieres.remove(filiere);
        filiere.setLyceesTechniques(null);
        return this;
    }

    public Set<Salle> getSalles() {
        return this.salles;
    }

    public void setSalles(Set<Salle> salles) {
        if (this.salles != null) {
            this.salles.forEach(i -> i.setLyceesTechniques(null));
        }
        if (salles != null) {
            salles.forEach(i -> i.setLyceesTechniques(this));
        }
        this.salles = salles;
    }

    public LyceesTechniques salles(Set<Salle> salles) {
        this.setSalles(salles);
        return this;
    }

    public LyceesTechniques addSalle(Salle salle) {
        this.salles.add(salle);
        salle.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeSalle(Salle salle) {
        this.salles.remove(salle);
        salle.setLyceesTechniques(null);
        return this;
    }

    public Set<Apprenant> getApprenants() {
        return this.apprenants;
    }

    public void setApprenants(Set<Apprenant> apprenants) {
        if (this.apprenants != null) {
            this.apprenants.forEach(i -> i.setLyceesTechniques(null));
        }
        if (apprenants != null) {
            apprenants.forEach(i -> i.setLyceesTechniques(this));
        }
        this.apprenants = apprenants;
    }

    public LyceesTechniques apprenants(Set<Apprenant> apprenants) {
        this.setApprenants(apprenants);
        return this;
    }

    public LyceesTechniques addApprenant(Apprenant apprenant) {
        this.apprenants.add(apprenant);
        apprenant.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeApprenant(Apprenant apprenant) {
        this.apprenants.remove(apprenant);
        apprenant.setLyceesTechniques(null);
        return this;
    }

    public Set<Enseignant> getEnseignants() {
        return this.enseignants;
    }

    public void setEnseignants(Set<Enseignant> enseignants) {
        if (this.enseignants != null) {
            this.enseignants.forEach(i -> i.setLyceesTechniques(null));
        }
        if (enseignants != null) {
            enseignants.forEach(i -> i.setLyceesTechniques(this));
        }
        this.enseignants = enseignants;
    }

    public LyceesTechniques enseignants(Set<Enseignant> enseignants) {
        this.setEnseignants(enseignants);
        return this;
    }

    public LyceesTechniques addEnseignant(Enseignant enseignant) {
        this.enseignants.add(enseignant);
        enseignant.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeEnseignant(Enseignant enseignant) {
        this.enseignants.remove(enseignant);
        enseignant.setLyceesTechniques(null);
        return this;
    }

    public Set<Difficulte> getDifficultes() {
        return this.difficultes;
    }

    public void setDifficultes(Set<Difficulte> difficultes) {
        if (this.difficultes != null) {
            this.difficultes.forEach(i -> i.setLyceesTechniques(null));
        }
        if (difficultes != null) {
            difficultes.forEach(i -> i.setLyceesTechniques(this));
        }
        this.difficultes = difficultes;
    }

    public LyceesTechniques difficultes(Set<Difficulte> difficultes) {
        this.setDifficultes(difficultes);
        return this;
    }

    public LyceesTechniques addDifficulte(Difficulte difficulte) {
        this.difficultes.add(difficulte);
        difficulte.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeDifficulte(Difficulte difficulte) {
        this.difficultes.remove(difficulte);
        difficulte.setLyceesTechniques(null);
        return this;
    }

    public Set<NiveauxCalification> getNiveaucalifications() {
        return this.niveaucalifications;
    }

    public void setNiveaucalifications(Set<NiveauxCalification> niveauxCalifications) {
        if (this.niveaucalifications != null) {
            this.niveaucalifications.forEach(i -> i.setLyceesTechniques(null));
        }
        if (niveauxCalifications != null) {
            niveauxCalifications.forEach(i -> i.setLyceesTechniques(this));
        }
        this.niveaucalifications = niveauxCalifications;
    }

    public LyceesTechniques niveaucalifications(Set<NiveauxCalification> niveauxCalifications) {
        this.setNiveaucalifications(niveauxCalifications);
        return this;
    }

    public LyceesTechniques addNiveaucalification(NiveauxCalification niveauxCalification) {
        this.niveaucalifications.add(niveauxCalification);
        niveauxCalification.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeNiveaucalification(NiveauxCalification niveauxCalification) {
        this.niveaucalifications.remove(niveauxCalification);
        niveauxCalification.setLyceesTechniques(null);
        return this;
    }

    public Set<ReformeMatiere> getReformeMatieres() {
        return this.reformeMatieres;
    }

    public void setReformeMatieres(Set<ReformeMatiere> reformeMatieres) {
        if (this.reformeMatieres != null) {
            this.reformeMatieres.forEach(i -> i.setLyceesTechniques(null));
        }
        if (reformeMatieres != null) {
            reformeMatieres.forEach(i -> i.setLyceesTechniques(this));
        }
        this.reformeMatieres = reformeMatieres;
    }

    public LyceesTechniques reformeMatieres(Set<ReformeMatiere> reformeMatieres) {
        this.setReformeMatieres(reformeMatieres);
        return this;
    }

    public LyceesTechniques addReformeMatiere(ReformeMatiere reformeMatiere) {
        this.reformeMatieres.add(reformeMatiere);
        reformeMatiere.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeReformeMatiere(ReformeMatiere reformeMatiere) {
        this.reformeMatieres.remove(reformeMatiere);
        reformeMatiere.setLyceesTechniques(null);
        return this;
    }

    public Set<IventaireDesMatetiere> getIventaireDesMatetieres() {
        return this.iventaireDesMatetieres;
    }

    public void setIventaireDesMatetieres(Set<IventaireDesMatetiere> iventaireDesMatetieres) {
        if (this.iventaireDesMatetieres != null) {
            this.iventaireDesMatetieres.forEach(i -> i.setLyceesTechniques(null));
        }
        if (iventaireDesMatetieres != null) {
            iventaireDesMatetieres.forEach(i -> i.setLyceesTechniques(this));
        }
        this.iventaireDesMatetieres = iventaireDesMatetieres;
    }

    public LyceesTechniques iventaireDesMatetieres(Set<IventaireDesMatetiere> iventaireDesMatetieres) {
        this.setIventaireDesMatetieres(iventaireDesMatetieres);
        return this;
    }

    public LyceesTechniques addIventaireDesMatetiere(IventaireDesMatetiere iventaireDesMatetiere) {
        this.iventaireDesMatetieres.add(iventaireDesMatetiere);
        iventaireDesMatetiere.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeIventaireDesMatetiere(IventaireDesMatetiere iventaireDesMatetiere) {
        this.iventaireDesMatetieres.remove(iventaireDesMatetiere);
        iventaireDesMatetiere.setLyceesTechniques(null);
        return this;
    }

    public Set<NiveauxEtudes> getNiveauetudes() {
        return this.niveauetudes;
    }

    public void setNiveauetudes(Set<NiveauxEtudes> niveauxEtudes) {
        if (this.niveauetudes != null) {
            this.niveauetudes.forEach(i -> i.setLyceesTechniques(null));
        }
        if (niveauxEtudes != null) {
            niveauxEtudes.forEach(i -> i.setLyceesTechniques(this));
        }
        this.niveauetudes = niveauxEtudes;
    }

    public LyceesTechniques niveauetudes(Set<NiveauxEtudes> niveauxEtudes) {
        this.setNiveauetudes(niveauxEtudes);
        return this;
    }

    public LyceesTechniques addNiveauetude(NiveauxEtudes niveauxEtudes) {
        this.niveauetudes.add(niveauxEtudes);
        niveauxEtudes.setLyceesTechniques(this);
        return this;
    }

    public LyceesTechniques removeNiveauetude(NiveauxEtudes niveauxEtudes) {
        this.niveauetudes.remove(niveauxEtudes);
        niveauxEtudes.setLyceesTechniques(null);
        return this;
    }

    public LyceeTechnique getLyceeTechnique() {
        return this.lyceeTechnique;
    }

    public void setLyceeTechnique(LyceeTechnique lyceeTechnique) {
        if (this.lyceeTechnique != null) {
            this.lyceeTechnique.setNomLycee(null);
        }
        if (lyceeTechnique != null) {
            lyceeTechnique.setNomLycee(this);
        }
        this.lyceeTechnique = lyceeTechnique;
    }

    public LyceesTechniques lyceeTechnique(LyceeTechnique lyceeTechnique) {
        this.setLyceeTechnique(lyceeTechnique);
        return this;
    }

    public Proviseur getProviseur() {
        return this.proviseur;
    }

    public void setProviseur(Proviseur proviseur) {
        if (this.proviseur != null) {
            this.proviseur.setNomLycee(null);
        }
        if (proviseur != null) {
            proviseur.setNomLycee(this);
        }
        this.proviseur = proviseur;
    }

    public LyceesTechniques proviseur(Proviseur proviseur) {
        this.setProviseur(proviseur);
        return this;
    }

    public DirecteurEtude getDirecteurEtude() {
        return this.directeurEtude;
    }

    public void setDirecteurEtude(DirecteurEtude directeurEtude) {
        if (this.directeurEtude != null) {
            this.directeurEtude.setNomLycee(null);
        }
        if (directeurEtude != null) {
            directeurEtude.setNomLycee(this);
        }
        this.directeurEtude = directeurEtude;
    }

    public LyceesTechniques directeurEtude(DirecteurEtude directeurEtude) {
        this.setDirecteurEtude(directeurEtude);
        return this;
    }

    public ComptableFinancier getComptableFinancie() {
        return this.comptableFinancie;
    }

    public void setComptableFinancie(ComptableFinancier comptableFinancier) {
        if (this.comptableFinancie != null) {
            this.comptableFinancie.setNomLycee(null);
        }
        if (comptableFinancier != null) {
            comptableFinancier.setNomLycee(this);
        }
        this.comptableFinancie = comptableFinancier;
    }

    public LyceesTechniques comptableFinancie(ComptableFinancier comptableFinancier) {
        this.setComptableFinancie(comptableFinancier);
        return this;
    }

    public ComptableMatiere getComptableMatiere() {
        return this.comptableMatiere;
    }

    public void setComptableMatiere(ComptableMatiere comptableMatiere) {
        if (this.comptableMatiere != null) {
            this.comptableMatiere.setNomLycee(null);
        }
        if (comptableMatiere != null) {
            comptableMatiere.setNomLycee(this);
        }
        this.comptableMatiere = comptableMatiere;
    }

    public LyceesTechniques comptableMatiere(ComptableMatiere comptableMatiere) {
        this.setComptableMatiere(comptableMatiere);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LyceesTechniques)) {
            return false;
        }
        return id != null && id.equals(((LyceesTechniques) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LyceesTechniques{" +
            "id=" + getId() +
            ", nomLycee='" + getNomLycee() + "'" +
            "}";
    }
}
