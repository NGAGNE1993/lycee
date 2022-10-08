package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.Cdi;
import com.mycompany.myapp.domain.enumeration.Gym;
import com.mycompany.myapp.domain.enumeration.Specialise;
import com.mycompany.myapp.domain.enumeration.TerrainSport;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Salle.
 */
@Entity
@Table(name = "salle")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Salle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nombre_salleclasse", nullable = false)
    private String nombreSalleclasse;

    @NotNull
    @Column(name = "nombre_ateliers", nullable = false)
    private String nombreAteliers;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialiase")
    private Specialise specialiase;

    @NotNull
    @Column(name = "nombre_salle_specialise", nullable = false)
    private String nombreSalleSpecialise;

    @Lob
    @Column(name = "justification_salle_spe")
    private String justificationSalleSpe;

    @Enumerated(EnumType.STRING)
    @Column(name = "cdi")
    private Cdi cdi;

    @Column(name = "nombre_cdi")
    private String nombreCDI;

    @Lob
    @Column(name = "justifiactif_salle_cdi")
    private String justifiactifSalleCDI;

    @Enumerated(EnumType.STRING)
    @Column(name = "gym")
    private Gym gym;

    @Enumerated(EnumType.STRING)
    @Column(name = "terrain_sport")
    private TerrainSport terrainSport;

    @Column(name = "autre_salle")
    private String autreSalle;

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

    public Salle id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreSalleclasse() {
        return this.nombreSalleclasse;
    }

    public Salle nombreSalleclasse(String nombreSalleclasse) {
        this.setNombreSalleclasse(nombreSalleclasse);
        return this;
    }

    public void setNombreSalleclasse(String nombreSalleclasse) {
        this.nombreSalleclasse = nombreSalleclasse;
    }

    public String getNombreAteliers() {
        return this.nombreAteliers;
    }

    public Salle nombreAteliers(String nombreAteliers) {
        this.setNombreAteliers(nombreAteliers);
        return this;
    }

    public void setNombreAteliers(String nombreAteliers) {
        this.nombreAteliers = nombreAteliers;
    }

    public Specialise getSpecialiase() {
        return this.specialiase;
    }

    public Salle specialiase(Specialise specialiase) {
        this.setSpecialiase(specialiase);
        return this;
    }

    public void setSpecialiase(Specialise specialiase) {
        this.specialiase = specialiase;
    }

    public String getNombreSalleSpecialise() {
        return this.nombreSalleSpecialise;
    }

    public Salle nombreSalleSpecialise(String nombreSalleSpecialise) {
        this.setNombreSalleSpecialise(nombreSalleSpecialise);
        return this;
    }

    public void setNombreSalleSpecialise(String nombreSalleSpecialise) {
        this.nombreSalleSpecialise = nombreSalleSpecialise;
    }

    public String getJustificationSalleSpe() {
        return this.justificationSalleSpe;
    }

    public Salle justificationSalleSpe(String justificationSalleSpe) {
        this.setJustificationSalleSpe(justificationSalleSpe);
        return this;
    }

    public void setJustificationSalleSpe(String justificationSalleSpe) {
        this.justificationSalleSpe = justificationSalleSpe;
    }

    public Cdi getCdi() {
        return this.cdi;
    }

    public Salle cdi(Cdi cdi) {
        this.setCdi(cdi);
        return this;
    }

    public void setCdi(Cdi cdi) {
        this.cdi = cdi;
    }

    public String getNombreCDI() {
        return this.nombreCDI;
    }

    public Salle nombreCDI(String nombreCDI) {
        this.setNombreCDI(nombreCDI);
        return this;
    }

    public void setNombreCDI(String nombreCDI) {
        this.nombreCDI = nombreCDI;
    }

    public String getJustifiactifSalleCDI() {
        return this.justifiactifSalleCDI;
    }

    public Salle justifiactifSalleCDI(String justifiactifSalleCDI) {
        this.setJustifiactifSalleCDI(justifiactifSalleCDI);
        return this;
    }

    public void setJustifiactifSalleCDI(String justifiactifSalleCDI) {
        this.justifiactifSalleCDI = justifiactifSalleCDI;
    }

    public Gym getGym() {
        return this.gym;
    }

    public Salle gym(Gym gym) {
        this.setGym(gym);
        return this;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public TerrainSport getTerrainSport() {
        return this.terrainSport;
    }

    public Salle terrainSport(TerrainSport terrainSport) {
        this.setTerrainSport(terrainSport);
        return this;
    }

    public void setTerrainSport(TerrainSport terrainSport) {
        this.terrainSport = terrainSport;
    }

    public String getAutreSalle() {
        return this.autreSalle;
    }

    public Salle autreSalle(String autreSalle) {
        this.setAutreSalle(autreSalle);
        return this;
    }

    public void setAutreSalle(String autreSalle) {
        this.autreSalle = autreSalle;
    }

    public LyceesTechniques getLyceesTechniques() {
        return this.lyceesTechniques;
    }

    public void setLyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.lyceesTechniques = lyceesTechniques;
    }

    public Salle lyceesTechniques(LyceesTechniques lyceesTechniques) {
        this.setLyceesTechniques(lyceesTechniques);
        return this;
    }

    public DirecteurEtude getDirecteur() {
        return this.directeur;
    }

    public void setDirecteur(DirecteurEtude directeurEtude) {
        this.directeur = directeurEtude;
    }

    public Salle directeur(DirecteurEtude directeurEtude) {
        this.setDirecteur(directeurEtude);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Salle)) {
            return false;
        }
        return id != null && id.equals(((Salle) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Salle{" +
            "id=" + getId() +
            ", nombreSalleclasse='" + getNombreSalleclasse() + "'" +
            ", nombreAteliers='" + getNombreAteliers() + "'" +
            ", specialiase='" + getSpecialiase() + "'" +
            ", nombreSalleSpecialise='" + getNombreSalleSpecialise() + "'" +
            ", justificationSalleSpe='" + getJustificationSalleSpe() + "'" +
            ", cdi='" + getCdi() + "'" +
            ", nombreCDI='" + getNombreCDI() + "'" +
            ", justifiactifSalleCDI='" + getJustifiactifSalleCDI() + "'" +
            ", gym='" + getGym() + "'" +
            ", terrainSport='" + getTerrainSport() + "'" +
            ", autreSalle='" + getAutreSalle() + "'" +
            "}";
    }
}
