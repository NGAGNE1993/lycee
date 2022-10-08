package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.Group;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ReformeMatiere.
 */
@Entity
@Table(name = "reforme_matiere")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReformeMatiere implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_group")
    private Group group;

    @Lob
    @Column(name = "designation_desmembre")
    private byte[] designationDesmembre;

    @Column(name = "designation_desmembre_content_type")
    private String designationDesmembreContentType;

    @Lob
    @Column(name = "pv_reforme")
    private byte[] pvReforme;

    @Column(name = "pv_reforme_content_type")
    private String pvReformeContentType;

    @Lob
    @Column(name = "sortie_definitive")
    private byte[] sortieDefinitive;

    @Column(name = "sortie_definitive_content_type")
    private String sortieDefinitiveContentType;

    @Lob
    @Column(name = "certificat_administratif")
    private byte[] certificatAdministratif;

    @Column(name = "certificat_administratif_content_type")
    private String certificatAdministratifContentType;

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
        value = { "user", "nomLycee", "iventaireDesMatetieres", "mouvementMatieres", "reformeMatieres" },
        allowSetters = true
    )
    private ComptableMatiere comptableMatiere;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ReformeMatiere id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return this.group;
    }

    public ReformeMatiere group(Group group) {
        this.setGroup(group);
        return this;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public byte[] getDesignationDesmembre() {
        return this.designationDesmembre;
    }

    public ReformeMatiere designationDesmembre(byte[] designationDesmembre) {
        this.setDesignationDesmembre(designationDesmembre);
        return this;
    }

    public void setDesignationDesmembre(byte[] designationDesmembre) {
        this.designationDesmembre = designationDesmembre;
    }

    public String getDesignationDesmembreContentType() {
        return this.designationDesmembreContentType;
    }

    public ReformeMatiere designationDesmembreContentType(String designationDesmembreContentType) {
        this.designationDesmembreContentType = designationDesmembreContentType;
        return this;
    }

    public void setDesignationDesmembreContentType(String designationDesmembreContentType) {
        this.designationDesmembreContentType = designationDesmembreContentType;
    }

    public byte[] getPvReforme() {
        return this.pvReforme;
    }

    public ReformeMatiere pvReforme(byte[] pvReforme) {
        this.setPvReforme(pvReforme);
        return this;
    }

    public void setPvReforme(byte[] pvReforme) {
        this.pvReforme = pvReforme;
    }

    public String getPvReformeContentType() {
        return this.pvReformeContentType;
    }

    public ReformeMatiere pvReformeContentType(String pvReformeContentType) {
        this.pvReformeContentType = pvReformeContentType;
        return this;
    }

    public void setPvReformeContentType(String pvReformeContentType) {
        this.pvReformeContentType = pvReformeContentType;
    }

    public byte[] getSortieDefinitive() {
        return this.sortieDefinitive;
    }

    public ReformeMatiere sortieDefinitive(byte[] sortieDefinitive) {
        this.setSortieDefinitive(sortieDefinitive);
        return this;
    }

    public void setSortieDefinitive(byte[] sortieDefinitive) {
        this.sortieDefinitive = sortieDefinitive;
    }

    public String getSortieDefinitiveContentType() {
        return this.sortieDefinitiveContentType;
    }

    public ReformeMatiere sortieDefinitiveContentType(String sortieDefinitiveContentType) {
        this.sortieDefinitiveContentType = sortieDefinitiveContentType;
        return this;
    }

    public void setSortieDefinitiveContentType(String sortieDefinitiveContentType) {
        this.sortieDefinitiveContentType = sortieDefinitiveContentType;
    }

    public byte[] getCertificatAdministratif() {
        return this.certificatAdministratif;
    }

    public ReformeMatiere certificatAdministratif(byte[] certificatAdministratif) {
        this.setCertificatAdministratif(certificatAdministratif);
        return this;
    }

    public void setCertificatAdministratif(byte[] certificatAdministratif) {
        this.certificatAdministratif = certificatAdministratif;
    }

    public String getCertificatAdministratifContentType() {
        return this.certificatAdministratifContentType;
    }

    public ReformeMatiere certificatAdministratifContentType(String certificatAdministratifContentType) {
        this.certificatAdministratifContentType = certificatAdministratifContentType;
        return this;
    }

    public void setCertificatAdministratifContentType(String certificatAdministratifContentType) {
        this.certificatAdministratifContentType = certificatAdministratifContentType;
    }

    public LyceesTechniques getLyceesTechniques() {
        return this.lyceesTechniques;
    }

    public void setLyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.lyceesTechniques = lyceesTechniques;
    }

    public ReformeMatiere lyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.setLyceesTechniques(lyceesTechniques);
        return this;
    }

    public ComptableMatiere getComptableMatiere() {
        return this.comptableMatiere;
    }

    public void setComptableMatiere(ComptableMatiere comptableMatiere) {
        this.comptableMatiere = comptableMatiere;
    }

    public ReformeMatiere comptableMatiere(ComptableMatiere comptableMatiere) {
        this.setComptableMatiere(comptableMatiere);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReformeMatiere)) {
            return false;
        }
        return id != null && id.equals(((ReformeMatiere) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReformeMatiere{" +
            "id=" + getId() +
            ", group='" + getGroup() + "'" +
            ", designationDesmembre='" + getDesignationDesmembre() + "'" +
            ", designationDesmembreContentType='" + getDesignationDesmembreContentType() + "'" +
            ", pvReforme='" + getPvReforme() + "'" +
            ", pvReformeContentType='" + getPvReformeContentType() + "'" +
            ", sortieDefinitive='" + getSortieDefinitive() + "'" +
            ", sortieDefinitiveContentType='" + getSortieDefinitiveContentType() + "'" +
            ", certificatAdministratif='" + getCertificatAdministratif() + "'" +
            ", certificatAdministratifContentType='" + getCertificatAdministratifContentType() + "'" +
            "}";
    }
}
