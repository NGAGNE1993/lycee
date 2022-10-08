package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.Genre;
import com.mycompany.myapp.domain.enumeration.Option;
import com.mycompany.myapp.domain.enumeration.Situation;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Apprenant.
 */
@Entity
@Table(name = "apprenant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Apprenant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nom_prenom", nullable = false)
    private String nomPrenom;

    @NotNull
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @NotNull
    @Column(name = "lieu_naissance", nullable = false)
    private String lieuNaissance;

    @NotNull
    @Column(name = "telephone", nullable = false)
    private String telephone;

    @NotNull
    @Column(name = "adresse", nullable = false)
    private String adresse;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sexe", nullable = false)
    private Genre sexe;

    @NotNull
    @Column(name = "annee_scolaire", nullable = false)
    private LocalDate anneeScolaire;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_option")
    private Option option;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "situation_matrimoniale", nullable = false)
    private Situation situationMatrimoniale;

    @NotNull
    @Column(name = "tuteur", nullable = false)
    private String tuteur;

    @NotNull
    @Column(name = "contact_tuteur", nullable = false)
    private String contactTuteur;

    @JsonIgnoreProperties(value = { "enseignants", "niveaus", "lyceesTechniques", "directeur" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Serie serie;

    @JsonIgnoreProperties(value = { "enseignants", "niveauCalifications", "lyceesTechniques", "directeur" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Filiere filiere;

    @JsonIgnoreProperties(value = { "lyceesTechniques", "directeur", "serie" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private NiveauxEtudes niveauxEtudes;

    @JsonIgnoreProperties(value = { "lyceesTechniques", "directeur", "filiere" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private NiveauxCalification niveauxCalification;

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

    public Apprenant id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomPrenom() {
        return this.nomPrenom;
    }

    public Apprenant nomPrenom(String nomPrenom) {
        this.setNomPrenom(nomPrenom);
        return this;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    public Apprenant dateNaissance(LocalDate dateNaissance) {
        this.setDateNaissance(dateNaissance);
        return this;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return this.lieuNaissance;
    }

    public Apprenant lieuNaissance(String lieuNaissance) {
        this.setLieuNaissance(lieuNaissance);
        return this;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public Apprenant telephone(String telephone) {
        this.setTelephone(telephone);
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public Apprenant adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Genre getSexe() {
        return this.sexe;
    }

    public Apprenant sexe(Genre sexe) {
        this.setSexe(sexe);
        return this;
    }

    public void setSexe(Genre sexe) {
        this.sexe = sexe;
    }

    public LocalDate getAnneeScolaire() {
        return this.anneeScolaire;
    }

    public Apprenant anneeScolaire(LocalDate anneeScolaire) {
        this.setAnneeScolaire(anneeScolaire);
        return this;
    }

    public void setAnneeScolaire(LocalDate anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public Option getOption() {
        return this.option;
    }

    public Apprenant option(Option option) {
        this.setOption(option);
        return this;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public Situation getSituationMatrimoniale() {
        return this.situationMatrimoniale;
    }

    public Apprenant situationMatrimoniale(Situation situationMatrimoniale) {
        this.setSituationMatrimoniale(situationMatrimoniale);
        return this;
    }

    public void setSituationMatrimoniale(Situation situationMatrimoniale) {
        this.situationMatrimoniale = situationMatrimoniale;
    }

    public String getTuteur() {
        return this.tuteur;
    }

    public Apprenant tuteur(String tuteur) {
        this.setTuteur(tuteur);
        return this;
    }

    public void setTuteur(String tuteur) {
        this.tuteur = tuteur;
    }

    public String getContactTuteur() {
        return this.contactTuteur;
    }

    public Apprenant contactTuteur(String contactTuteur) {
        this.setContactTuteur(contactTuteur);
        return this;
    }

    public void setContactTuteur(String contactTuteur) {
        this.contactTuteur = contactTuteur;
    }

    public Serie getSerie() {
        return this.serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Apprenant serie(Serie serie) {
        this.setSerie(serie);
        return this;
    }

    public Filiere getFiliere() {
        return this.filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Apprenant filiere(Filiere filiere) {
        this.setFiliere(filiere);
        return this;
    }

    public NiveauxEtudes getNiveauxEtudes() {
        return this.niveauxEtudes;
    }

    public void setNiveauxEtudes(NiveauxEtudes niveauxEtudes) {
        this.niveauxEtudes = niveauxEtudes;
    }

    public Apprenant niveauxEtudes(NiveauxEtudes niveauxEtudes) {
        this.setNiveauxEtudes(niveauxEtudes);
        return this;
    }

    public NiveauxCalification getNiveauxCalification() {
        return this.niveauxCalification;
    }

    public void setNiveauxCalification(NiveauxCalification niveauxCalification) {
        this.niveauxCalification = niveauxCalification;
    }

    public Apprenant niveauxCalification(NiveauxCalification niveauxCalification) {
        this.setNiveauxCalification(niveauxCalification);
        return this;
    }

    public LyceesTechniques getLyceesTechniques() {
        return this.lyceesTechniques;
    }

    public void setLyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.lyceesTechniques = lyceesTechniques;
    }

    public Apprenant lyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.setLyceesTechniques(lyceesTechniques);
        return this;
    }

    public DirecteurEtude getDirecteur() {
        return this.directeur;
    }

    public void setDirecteur(DirecteurEtude directeurEtude) {
        this.directeur = directeurEtude;
    }

    public Apprenant directeur(DirecteurEtude directeurEtude) {
        this.setDirecteur(directeurEtude);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Apprenant)) {
            return false;
        }
        return id != null && id.equals(((Apprenant) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Apprenant{" +
            "id=" + getId() +
            ", nomPrenom='" + getNomPrenom() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", lieuNaissance='" + getLieuNaissance() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", anneeScolaire='" + getAnneeScolaire() + "'" +
            ", option='" + getOption() + "'" +
            ", situationMatrimoniale='" + getSituationMatrimoniale() + "'" +
            ", tuteur='" + getTuteur() + "'" +
            ", contactTuteur='" + getContactTuteur() + "'" +
            "}";
    }
}
