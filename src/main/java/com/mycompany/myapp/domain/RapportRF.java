package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.TypeRapport;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RapportRF.
 */
@Entity
@Table(name = "rapport_rf")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RapportRF implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_raport")
    private TypeRapport typeRaport;

    @Lob
    @Column(name = "rentre")
    private byte[] rentre;

    @Column(name = "rentre_content_type")
    private String rentreContentType;

    @Lob
    @Column(name = "fin")
    private byte[] fin;

    @Column(name = "fin_content_type")
    private String finContentType;

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

    public RapportRF id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeRapport getTypeRaport() {
        return this.typeRaport;
    }

    public RapportRF typeRaport(TypeRapport typeRaport) {
        this.setTypeRaport(typeRaport);
        return this;
    }

    public void setTypeRaport(TypeRapport typeRaport) {
        this.typeRaport = typeRaport;
    }

    public byte[] getRentre() {
        return this.rentre;
    }

    public RapportRF rentre(byte[] rentre) {
        this.setRentre(rentre);
        return this;
    }

    public void setRentre(byte[] rentre) {
        this.rentre = rentre;
    }

    public String getRentreContentType() {
        return this.rentreContentType;
    }

    public RapportRF rentreContentType(String rentreContentType) {
        this.rentreContentType = rentreContentType;
        return this;
    }

    public void setRentreContentType(String rentreContentType) {
        this.rentreContentType = rentreContentType;
    }

    public byte[] getFin() {
        return this.fin;
    }

    public RapportRF fin(byte[] fin) {
        this.setFin(fin);
        return this;
    }

    public void setFin(byte[] fin) {
        this.fin = fin;
    }

    public String getFinContentType() {
        return this.finContentType;
    }

    public RapportRF finContentType(String finContentType) {
        this.finContentType = finContentType;
        return this;
    }

    public void setFinContentType(String finContentType) {
        this.finContentType = finContentType;
    }

    public LyceesTechniques getLyceesTechniques() {
        return this.lyceesTechniques;
    }

    public void setLyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.lyceesTechniques = lyceesTechniques;
    }

    public RapportRF lyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.setLyceesTechniques(lyceesTechniques);
        return this;
    }

    public Proviseur getProviseur() {
        return this.proviseur;
    }

    public void setProviseur(Proviseur proviseur) {
        this.proviseur = proviseur;
    }

    public RapportRF proviseur(Proviseur proviseur) {
        this.setProviseur(proviseur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RapportRF)) {
            return false;
        }
        return id != null && id.equals(((RapportRF) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RapportRF{" +
            "id=" + getId() +
            ", typeRaport='" + getTypeRaport() + "'" +
            ", rentre='" + getRentre() + "'" +
            ", rentreContentType='" + getRentreContentType() + "'" +
            ", fin='" + getFin() + "'" +
            ", finContentType='" + getFinContentType() + "'" +
            "}";
    }
}
