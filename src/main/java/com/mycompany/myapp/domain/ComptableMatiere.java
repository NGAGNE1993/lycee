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
 * A ComptableMatiere.
 */
@Entity
@Table(name = "comptable_matiere")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ComptableMatiere implements Serializable {

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

    @OneToMany(mappedBy = "comptableMatiere")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "comptableMatiere" }, allowSetters = true)
    private Set<IventaireDesMatetiere> iventaireDesMatetieres = new HashSet<>();

    @OneToMany(mappedBy = "comptableMatiere")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "comptableMatiere" }, allowSetters = true)
    private Set<MouvementMatiere> mouvementMatieres = new HashSet<>();

    @OneToMany(mappedBy = "comptableMatiere")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyceesTechniques", "comptableMatiere" }, allowSetters = true)
    private Set<ReformeMatiere> reformeMatieres = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ComptableMatiere id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomPrenom() {
        return this.nomPrenom;
    }

    public ComptableMatiere nomPrenom(String nomPrenom) {
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

    public ComptableMatiere user(User user) {
        this.setUser(user);
        return this;
    }

    public LyceesTechniques getNomLycee() {
        return this.nomLycee;
    }

    public void setNomLycee(LyceesTechniques lyceesTechniques) {
        this.nomLycee = lyceesTechniques;
    }

    public ComptableMatiere nomLycee(LyceesTechniques lyceesTechniques) {
        this.setNomLycee(lyceesTechniques);
        return this;
    }

    public Set<IventaireDesMatetiere> getIventaireDesMatetieres() {
        return this.iventaireDesMatetieres;
    }

    public void setIventaireDesMatetieres(Set<IventaireDesMatetiere> iventaireDesMatetieres) {
        if (this.iventaireDesMatetieres != null) {
            this.iventaireDesMatetieres.forEach(i -> i.setComptableMatiere(null));
        }
        if (iventaireDesMatetieres != null) {
            iventaireDesMatetieres.forEach(i -> i.setComptableMatiere(this));
        }
        this.iventaireDesMatetieres = iventaireDesMatetieres;
    }

    public ComptableMatiere iventaireDesMatetieres(Set<IventaireDesMatetiere> iventaireDesMatetieres) {
        this.setIventaireDesMatetieres(iventaireDesMatetieres);
        return this;
    }

    public ComptableMatiere addIventaireDesMatetiere(IventaireDesMatetiere iventaireDesMatetiere) {
        this.iventaireDesMatetieres.add(iventaireDesMatetiere);
        iventaireDesMatetiere.setComptableMatiere(this);
        return this;
    }

    public ComptableMatiere removeIventaireDesMatetiere(IventaireDesMatetiere iventaireDesMatetiere) {
        this.iventaireDesMatetieres.remove(iventaireDesMatetiere);
        iventaireDesMatetiere.setComptableMatiere(null);
        return this;
    }

    public Set<MouvementMatiere> getMouvementMatieres() {
        return this.mouvementMatieres;
    }

    public void setMouvementMatieres(Set<MouvementMatiere> mouvementMatieres) {
        if (this.mouvementMatieres != null) {
            this.mouvementMatieres.forEach(i -> i.setComptableMatiere(null));
        }
        if (mouvementMatieres != null) {
            mouvementMatieres.forEach(i -> i.setComptableMatiere(this));
        }
        this.mouvementMatieres = mouvementMatieres;
    }

    public ComptableMatiere mouvementMatieres(Set<MouvementMatiere> mouvementMatieres) {
        this.setMouvementMatieres(mouvementMatieres);
        return this;
    }

    public ComptableMatiere addMouvementMatiere(MouvementMatiere mouvementMatiere) {
        this.mouvementMatieres.add(mouvementMatiere);
        mouvementMatiere.setComptableMatiere(this);
        return this;
    }

    public ComptableMatiere removeMouvementMatiere(MouvementMatiere mouvementMatiere) {
        this.mouvementMatieres.remove(mouvementMatiere);
        mouvementMatiere.setComptableMatiere(null);
        return this;
    }

    public Set<ReformeMatiere> getReformeMatieres() {
        return this.reformeMatieres;
    }

    public void setReformeMatieres(Set<ReformeMatiere> reformeMatieres) {
        if (this.reformeMatieres != null) {
            this.reformeMatieres.forEach(i -> i.setComptableMatiere(null));
        }
        if (reformeMatieres != null) {
            reformeMatieres.forEach(i -> i.setComptableMatiere(this));
        }
        this.reformeMatieres = reformeMatieres;
    }

    public ComptableMatiere reformeMatieres(Set<ReformeMatiere> reformeMatieres) {
        this.setReformeMatieres(reformeMatieres);
        return this;
    }

    public ComptableMatiere addReformeMatiere(ReformeMatiere reformeMatiere) {
        this.reformeMatieres.add(reformeMatiere);
        reformeMatiere.setComptableMatiere(this);
        return this;
    }

    public ComptableMatiere removeReformeMatiere(ReformeMatiere reformeMatiere) {
        this.reformeMatieres.remove(reformeMatiere);
        reformeMatiere.setComptableMatiere(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComptableMatiere)) {
            return false;
        }
        return id != null && id.equals(((ComptableMatiere) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ComptableMatiere{" +
            "id=" + getId() +
            ", nomPrenom='" + getNomPrenom() + "'" +
            "}";
    }
}
