package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.Fonctionnment;
import com.mycompany.myapp.domain.enumeration.TypeO;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OrganeGestion.
 */
@Entity
@Table(name = "organe_gestion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrganeGestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeO type;

    @Column(name = "autre_type")
    private String autreType;

    @Enumerated(EnumType.STRING)
    @Column(name = "fonctionnel")
    private Fonctionnment fonctionnel;

    @Lob
    @Column(name = "activite", nullable = false)
    private String activite;

    @NotNull
    @Column(name = "date_activite", nullable = false)
    private LocalDate dateActivite;

    @Lob
    @Column(name = "rapport")
    private byte[] rapport;

    @Column(name = "rapport_content_type")
    private String rapportContentType;

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

    public OrganeGestion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeO getType() {
        return this.type;
    }

    public OrganeGestion type(TypeO type) {
        this.setType(type);
        return this;
    }

    public void setType(TypeO type) {
        this.type = type;
    }

    public String getAutreType() {
        return this.autreType;
    }

    public OrganeGestion autreType(String autreType) {
        this.setAutreType(autreType);
        return this;
    }

    public void setAutreType(String autreType) {
        this.autreType = autreType;
    }

    public Fonctionnment getFonctionnel() {
        return this.fonctionnel;
    }

    public OrganeGestion fonctionnel(Fonctionnment fonctionnel) {
        this.setFonctionnel(fonctionnel);
        return this;
    }

    public void setFonctionnel(Fonctionnment fonctionnel) {
        this.fonctionnel = fonctionnel;
    }

    public String getActivite() {
        return this.activite;
    }

    public OrganeGestion activite(String activite) {
        this.setActivite(activite);
        return this;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public LocalDate getDateActivite() {
        return this.dateActivite;
    }

    public OrganeGestion dateActivite(LocalDate dateActivite) {
        this.setDateActivite(dateActivite);
        return this;
    }

    public void setDateActivite(LocalDate dateActivite) {
        this.dateActivite = dateActivite;
    }

    public byte[] getRapport() {
        return this.rapport;
    }

    public OrganeGestion rapport(byte[] rapport) {
        this.setRapport(rapport);
        return this;
    }

    public void setRapport(byte[] rapport) {
        this.rapport = rapport;
    }

    public String getRapportContentType() {
        return this.rapportContentType;
    }

    public OrganeGestion rapportContentType(String rapportContentType) {
        this.rapportContentType = rapportContentType;
        return this;
    }

    public void setRapportContentType(String rapportContentType) {
        this.rapportContentType = rapportContentType;
    }

    public LyceesTechniques getLyceesTechniques() {
        return this.lyceesTechniques;
    }

    public void setLyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.lyceesTechniques = lyceesTechniques;
    }

    public OrganeGestion lyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.setLyceesTechniques(lyceesTechniques);
        return this;
    }

    public Proviseur getProviseur() {
        return this.proviseur;
    }

    public void setProviseur(Proviseur proviseur) {
        this.proviseur = proviseur;
    }

    public OrganeGestion proviseur(Proviseur proviseur) {
        this.setProviseur(proviseur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganeGestion)) {
            return false;
        }
        return id != null && id.equals(((OrganeGestion) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrganeGestion{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", autreType='" + getAutreType() + "'" +
            ", fonctionnel='" + getFonctionnel() + "'" +
            ", activite='" + getActivite() + "'" +
            ", dateActivite='" + getDateActivite() + "'" +
            ", rapport='" + getRapport() + "'" +
            ", rapportContentType='" + getRapportContentType() + "'" +
            "}";
    }
}
