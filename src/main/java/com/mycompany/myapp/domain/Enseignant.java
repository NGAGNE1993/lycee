package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.Genre;
import com.mycompany.myapp.domain.enumeration.Option;
import com.mycompany.myapp.domain.enumeration.Situation;
import com.mycompany.myapp.domain.enumeration.StatusEns;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Enseignant.
 */
@Entity
@Table(name = "enseignant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Enseignant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "matricule_ens", nullable = false)
    private String matriculeEns;

    @NotNull
    @Column(name = "nom_prenom", nullable = false)
    private String nomPrenom;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sexe", nullable = false)
    private Genre sexe;

    @NotNull
    @Column(name = "telephone", nullable = false)
    private String telephone;

    @NotNull
    @Column(name = "mail", nullable = false, unique = true)
    private String mail;

    @NotNull
    @Column(name = "grade", nullable = false)
    private String grade;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_option")
    private Option option;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "situation_matrimoniale", nullable = false)
    private Situation situationMatrimoniale;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEns status;

    @NotNull
    @Column(name = "fonction", nullable = false)
    private String fonction;

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
    @JsonIgnoreProperties(value = { "enseignants", "niveaus", "lyceesTechniques", "directeur" }, allowSetters = true)
    private Serie serie;

    @ManyToOne
    @JsonIgnoreProperties(value = { "enseignants", "niveauCalifications", "lyceesTechniques", "directeur" }, allowSetters = true)
    private Filiere filiere;

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

    public Enseignant id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatriculeEns() {
        return this.matriculeEns;
    }

    public Enseignant matriculeEns(String matriculeEns) {
        this.setMatriculeEns(matriculeEns);
        return this;
    }

    public void setMatriculeEns(String matriculeEns) {
        this.matriculeEns = matriculeEns;
    }

    public String getNomPrenom() {
        return this.nomPrenom;
    }

    public Enseignant nomPrenom(String nomPrenom) {
        this.setNomPrenom(nomPrenom);
        return this;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public Genre getSexe() {
        return this.sexe;
    }

    public Enseignant sexe(Genre sexe) {
        this.setSexe(sexe);
        return this;
    }

    public void setSexe(Genre sexe) {
        this.sexe = sexe;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public Enseignant telephone(String telephone) {
        this.setTelephone(telephone);
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return this.mail;
    }

    public Enseignant mail(String mail) {
        this.setMail(mail);
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getGrade() {
        return this.grade;
    }

    public Enseignant grade(String grade) {
        this.setGrade(grade);
        return this;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Option getOption() {
        return this.option;
    }

    public Enseignant option(Option option) {
        this.setOption(option);
        return this;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public Situation getSituationMatrimoniale() {
        return this.situationMatrimoniale;
    }

    public Enseignant situationMatrimoniale(Situation situationMatrimoniale) {
        this.setSituationMatrimoniale(situationMatrimoniale);
        return this;
    }

    public void setSituationMatrimoniale(Situation situationMatrimoniale) {
        this.situationMatrimoniale = situationMatrimoniale;
    }

    public StatusEns getStatus() {
        return this.status;
    }

    public Enseignant status(StatusEns status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(StatusEns status) {
        this.status = status;
    }

    public String getFonction() {
        return this.fonction;
    }

    public Enseignant fonction(String fonction) {
        this.setFonction(fonction);
        return this;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public LyceesTechniques getLyceesTechniques() {
        return this.lyceesTechniques;
    }

    public void setLyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.lyceesTechniques = lyceesTechniques;
    }

    public Enseignant lyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.setLyceesTechniques(lyceesTechniques);
        return this;
    }

    public Serie getSerie() {
        return this.serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Enseignant serie(Serie serie) {
        this.setSerie(serie);
        return this;
    }

    public Filiere getFiliere() {
        return this.filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Enseignant filiere(Filiere filiere) {
        this.setFiliere(filiere);
        return this;
    }

    public Proviseur getProviseur() {
        return this.proviseur;
    }

    public void setProviseur(Proviseur proviseur) {
        this.proviseur = proviseur;
    }

    public Enseignant proviseur(Proviseur proviseur) {
        this.setProviseur(proviseur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Enseignant)) {
            return false;
        }
        return id != null && id.equals(((Enseignant) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Enseignant{" +
            "id=" + getId() +
            ", matriculeEns='" + getMatriculeEns() + "'" +
            ", nomPrenom='" + getNomPrenom() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", mail='" + getMail() + "'" +
            ", grade='" + getGrade() + "'" +
            ", option='" + getOption() + "'" +
            ", situationMatrimoniale='" + getSituationMatrimoniale() + "'" +
            ", status='" + getStatus() + "'" +
            ", fonction='" + getFonction() + "'" +
            "}";
    }
}
