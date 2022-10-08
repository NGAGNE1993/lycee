package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.DAKAR;
import com.mycompany.myapp.domain.enumeration.DIOURBEL;
import com.mycompany.myapp.domain.enumeration.FATICK;
import com.mycompany.myapp.domain.enumeration.KAFFRINE;
import com.mycompany.myapp.domain.enumeration.KAOLACK;
import com.mycompany.myapp.domain.enumeration.KEDOUGOU;
import com.mycompany.myapp.domain.enumeration.KOLDA;
import com.mycompany.myapp.domain.enumeration.LOUGA;
import com.mycompany.myapp.domain.enumeration.MATAM;
import com.mycompany.myapp.domain.enumeration.Region;
import com.mycompany.myapp.domain.enumeration.SAINTLOUIS;
import com.mycompany.myapp.domain.enumeration.SEDHIOU;
import com.mycompany.myapp.domain.enumeration.TAMBACOUNDA;
import com.mycompany.myapp.domain.enumeration.THIES;
import com.mycompany.myapp.domain.enumeration.ZIGINCHOR;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A LyceeTechnique.
 */
@Entity
@Table(name = "lycee_technique")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LyceeTechnique implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "prenom_nom", nullable = false)
    private String prenomNom;

    @NotNull
    @Column(name = "adresse", nullable = false)
    private String adresse;

    @NotNull
    @Column(name = "mail", nullable = false)
    private String mail;

    @NotNull
    @Column(name = "tel_1", nullable = false)
    private String tel1;

    @NotNull
    @Column(name = "tel_2", nullable = false)
    private String tel2;

    @NotNull
    @Column(name = "boite_postal", nullable = false)
    private String boitePostal;

    @NotNull
    @Column(name = "decret_creation", nullable = false)
    private String decretCreation;

    @NotNull
    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "region", nullable = false)
    private Region region;

    @Column(name = "autre_region")
    private String autreRegion;

    @Enumerated(EnumType.STRING)
    @Column(name = "departement_dakar")
    private DAKAR departementDakar;

    @Enumerated(EnumType.STRING)
    @Column(name = "departement_diourbel")
    private DIOURBEL departementDiourbel;

    @Enumerated(EnumType.STRING)
    @Column(name = "departement_fatick")
    private FATICK departementFatick;

    @Enumerated(EnumType.STRING)
    @Column(name = "departement_kaffrine")
    private KAFFRINE departementKaffrine;

    @Enumerated(EnumType.STRING)
    @Column(name = "departement_kaolack")
    private KAOLACK departementKaolack;

    @Enumerated(EnumType.STRING)
    @Column(name = "departement_kedougou")
    private KEDOUGOU departementKedougou;

    @Enumerated(EnumType.STRING)
    @Column(name = "departement_kolda")
    private KOLDA departementKolda;

    @Enumerated(EnumType.STRING)
    @Column(name = "departement_louga")
    private LOUGA departementLouga;

    @Enumerated(EnumType.STRING)
    @Column(name = "departement_matam")
    private MATAM departementMatam;

    @Enumerated(EnumType.STRING)
    @Column(name = "departement_saint")
    private SAINTLOUIS departementSaint;

    @Enumerated(EnumType.STRING)
    @Column(name = "departement_sedhiou")
    private SEDHIOU departementSedhiou;

    @Enumerated(EnumType.STRING)
    @Column(name = "departement_tambacounda")
    private TAMBACOUNDA departementTambacounda;

    @Enumerated(EnumType.STRING)
    @Column(name = "departement_this")
    private THIES departementThis;

    @Enumerated(EnumType.STRING)
    @Column(name = "departement_ziguinchor")
    private ZIGINCHOR departementZiguinchor;

    @Column(name = "autredepartement_dakar")
    private String autredepartementDakar;

    @Column(name = "autredepartement_diourbel")
    private String autredepartementDiourbel;

    @Column(name = "autredepartement_fatick")
    private String autredepartementFatick;

    @Column(name = "autredepartement_kaffrine")
    private String autredepartementKaffrine;

    @Column(name = "autredepartement_kaolack")
    private String autredepartementKaolack;

    @Column(name = "autredepartement_kedougou")
    private String autredepartementKedougou;

    @Column(name = "autredepartement_kolda")
    private String autredepartementKolda;

    @Column(name = "autredepartement_louga")
    private String autredepartementLouga;

    @Column(name = "autredepartement_matam")
    private String autredepartementMatam;

    @Column(name = "autredepartement_saint")
    private String autredepartementSaint;

    @Column(name = "autredepartement_sedhiou")
    private String autredepartementSedhiou;

    @Column(name = "autredepartement_tambacounda")
    private String autredepartementTambacounda;

    @Column(name = "autredepartement_this")
    private String autredepartementThis;

    @Column(name = "autredepartement_ziguinchor")
    private String autredepartementZiguinchor;

    @NotNull
    @Column(name = "commune", nullable = false)
    private String commune;

    @NotNull
    @Column(name = "ia", nullable = false)
    private String ia;

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
    @OneToOne
    @JoinColumn(unique = true)
    private Proviseur proviseur;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LyceeTechnique id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenomNom() {
        return this.prenomNom;
    }

    public LyceeTechnique prenomNom(String prenomNom) {
        this.setPrenomNom(prenomNom);
        return this;
    }

    public void setPrenomNom(String prenomNom) {
        this.prenomNom = prenomNom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public LyceeTechnique adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return this.mail;
    }

    public LyceeTechnique mail(String mail) {
        this.setMail(mail);
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel1() {
        return this.tel1;
    }

    public LyceeTechnique tel1(String tel1) {
        this.setTel1(tel1);
        return this;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return this.tel2;
    }

    public LyceeTechnique tel2(String tel2) {
        this.setTel2(tel2);
        return this;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getBoitePostal() {
        return this.boitePostal;
    }

    public LyceeTechnique boitePostal(String boitePostal) {
        this.setBoitePostal(boitePostal);
        return this;
    }

    public void setBoitePostal(String boitePostal) {
        this.boitePostal = boitePostal;
    }

    public String getDecretCreation() {
        return this.decretCreation;
    }

    public LyceeTechnique decretCreation(String decretCreation) {
        this.setDecretCreation(decretCreation);
        return this;
    }

    public void setDecretCreation(String decretCreation) {
        this.decretCreation = decretCreation;
    }

    public LocalDate getDateCreation() {
        return this.dateCreation;
    }

    public LyceeTechnique dateCreation(LocalDate dateCreation) {
        this.setDateCreation(dateCreation);
        return this;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Region getRegion() {
        return this.region;
    }

    public LyceeTechnique region(Region region) {
        this.setRegion(region);
        return this;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getAutreRegion() {
        return this.autreRegion;
    }

    public LyceeTechnique autreRegion(String autreRegion) {
        this.setAutreRegion(autreRegion);
        return this;
    }

    public void setAutreRegion(String autreRegion) {
        this.autreRegion = autreRegion;
    }

    public DAKAR getDepartementDakar() {
        return this.departementDakar;
    }

    public LyceeTechnique departementDakar(DAKAR departementDakar) {
        this.setDepartementDakar(departementDakar);
        return this;
    }

    public void setDepartementDakar(DAKAR departementDakar) {
        this.departementDakar = departementDakar;
    }

    public DIOURBEL getDepartementDiourbel() {
        return this.departementDiourbel;
    }

    public LyceeTechnique departementDiourbel(DIOURBEL departementDiourbel) {
        this.setDepartementDiourbel(departementDiourbel);
        return this;
    }

    public void setDepartementDiourbel(DIOURBEL departementDiourbel) {
        this.departementDiourbel = departementDiourbel;
    }

    public FATICK getDepartementFatick() {
        return this.departementFatick;
    }

    public LyceeTechnique departementFatick(FATICK departementFatick) {
        this.setDepartementFatick(departementFatick);
        return this;
    }

    public void setDepartementFatick(FATICK departementFatick) {
        this.departementFatick = departementFatick;
    }

    public KAFFRINE getDepartementKaffrine() {
        return this.departementKaffrine;
    }

    public LyceeTechnique departementKaffrine(KAFFRINE departementKaffrine) {
        this.setDepartementKaffrine(departementKaffrine);
        return this;
    }

    public void setDepartementKaffrine(KAFFRINE departementKaffrine) {
        this.departementKaffrine = departementKaffrine;
    }

    public KAOLACK getDepartementKaolack() {
        return this.departementKaolack;
    }

    public LyceeTechnique departementKaolack(KAOLACK departementKaolack) {
        this.setDepartementKaolack(departementKaolack);
        return this;
    }

    public void setDepartementKaolack(KAOLACK departementKaolack) {
        this.departementKaolack = departementKaolack;
    }

    public KEDOUGOU getDepartementKedougou() {
        return this.departementKedougou;
    }

    public LyceeTechnique departementKedougou(KEDOUGOU departementKedougou) {
        this.setDepartementKedougou(departementKedougou);
        return this;
    }

    public void setDepartementKedougou(KEDOUGOU departementKedougou) {
        this.departementKedougou = departementKedougou;
    }

    public KOLDA getDepartementKolda() {
        return this.departementKolda;
    }

    public LyceeTechnique departementKolda(KOLDA departementKolda) {
        this.setDepartementKolda(departementKolda);
        return this;
    }

    public void setDepartementKolda(KOLDA departementKolda) {
        this.departementKolda = departementKolda;
    }

    public LOUGA getDepartementLouga() {
        return this.departementLouga;
    }

    public LyceeTechnique departementLouga(LOUGA departementLouga) {
        this.setDepartementLouga(departementLouga);
        return this;
    }

    public void setDepartementLouga(LOUGA departementLouga) {
        this.departementLouga = departementLouga;
    }

    public MATAM getDepartementMatam() {
        return this.departementMatam;
    }

    public LyceeTechnique departementMatam(MATAM departementMatam) {
        this.setDepartementMatam(departementMatam);
        return this;
    }

    public void setDepartementMatam(MATAM departementMatam) {
        this.departementMatam = departementMatam;
    }

    public SAINTLOUIS getDepartementSaint() {
        return this.departementSaint;
    }

    public LyceeTechnique departementSaint(SAINTLOUIS departementSaint) {
        this.setDepartementSaint(departementSaint);
        return this;
    }

    public void setDepartementSaint(SAINTLOUIS departementSaint) {
        this.departementSaint = departementSaint;
    }

    public SEDHIOU getDepartementSedhiou() {
        return this.departementSedhiou;
    }

    public LyceeTechnique departementSedhiou(SEDHIOU departementSedhiou) {
        this.setDepartementSedhiou(departementSedhiou);
        return this;
    }

    public void setDepartementSedhiou(SEDHIOU departementSedhiou) {
        this.departementSedhiou = departementSedhiou;
    }

    public TAMBACOUNDA getDepartementTambacounda() {
        return this.departementTambacounda;
    }

    public LyceeTechnique departementTambacounda(TAMBACOUNDA departementTambacounda) {
        this.setDepartementTambacounda(departementTambacounda);
        return this;
    }

    public void setDepartementTambacounda(TAMBACOUNDA departementTambacounda) {
        this.departementTambacounda = departementTambacounda;
    }

    public THIES getDepartementThis() {
        return this.departementThis;
    }

    public LyceeTechnique departementThis(THIES departementThis) {
        this.setDepartementThis(departementThis);
        return this;
    }

    public void setDepartementThis(THIES departementThis) {
        this.departementThis = departementThis;
    }

    public ZIGINCHOR getDepartementZiguinchor() {
        return this.departementZiguinchor;
    }

    public LyceeTechnique departementZiguinchor(ZIGINCHOR departementZiguinchor) {
        this.setDepartementZiguinchor(departementZiguinchor);
        return this;
    }

    public void setDepartementZiguinchor(ZIGINCHOR departementZiguinchor) {
        this.departementZiguinchor = departementZiguinchor;
    }

    public String getAutredepartementDakar() {
        return this.autredepartementDakar;
    }

    public LyceeTechnique autredepartementDakar(String autredepartementDakar) {
        this.setAutredepartementDakar(autredepartementDakar);
        return this;
    }

    public void setAutredepartementDakar(String autredepartementDakar) {
        this.autredepartementDakar = autredepartementDakar;
    }

    public String getAutredepartementDiourbel() {
        return this.autredepartementDiourbel;
    }

    public LyceeTechnique autredepartementDiourbel(String autredepartementDiourbel) {
        this.setAutredepartementDiourbel(autredepartementDiourbel);
        return this;
    }

    public void setAutredepartementDiourbel(String autredepartementDiourbel) {
        this.autredepartementDiourbel = autredepartementDiourbel;
    }

    public String getAutredepartementFatick() {
        return this.autredepartementFatick;
    }

    public LyceeTechnique autredepartementFatick(String autredepartementFatick) {
        this.setAutredepartementFatick(autredepartementFatick);
        return this;
    }

    public void setAutredepartementFatick(String autredepartementFatick) {
        this.autredepartementFatick = autredepartementFatick;
    }

    public String getAutredepartementKaffrine() {
        return this.autredepartementKaffrine;
    }

    public LyceeTechnique autredepartementKaffrine(String autredepartementKaffrine) {
        this.setAutredepartementKaffrine(autredepartementKaffrine);
        return this;
    }

    public void setAutredepartementKaffrine(String autredepartementKaffrine) {
        this.autredepartementKaffrine = autredepartementKaffrine;
    }

    public String getAutredepartementKaolack() {
        return this.autredepartementKaolack;
    }

    public LyceeTechnique autredepartementKaolack(String autredepartementKaolack) {
        this.setAutredepartementKaolack(autredepartementKaolack);
        return this;
    }

    public void setAutredepartementKaolack(String autredepartementKaolack) {
        this.autredepartementKaolack = autredepartementKaolack;
    }

    public String getAutredepartementKedougou() {
        return this.autredepartementKedougou;
    }

    public LyceeTechnique autredepartementKedougou(String autredepartementKedougou) {
        this.setAutredepartementKedougou(autredepartementKedougou);
        return this;
    }

    public void setAutredepartementKedougou(String autredepartementKedougou) {
        this.autredepartementKedougou = autredepartementKedougou;
    }

    public String getAutredepartementKolda() {
        return this.autredepartementKolda;
    }

    public LyceeTechnique autredepartementKolda(String autredepartementKolda) {
        this.setAutredepartementKolda(autredepartementKolda);
        return this;
    }

    public void setAutredepartementKolda(String autredepartementKolda) {
        this.autredepartementKolda = autredepartementKolda;
    }

    public String getAutredepartementLouga() {
        return this.autredepartementLouga;
    }

    public LyceeTechnique autredepartementLouga(String autredepartementLouga) {
        this.setAutredepartementLouga(autredepartementLouga);
        return this;
    }

    public void setAutredepartementLouga(String autredepartementLouga) {
        this.autredepartementLouga = autredepartementLouga;
    }

    public String getAutredepartementMatam() {
        return this.autredepartementMatam;
    }

    public LyceeTechnique autredepartementMatam(String autredepartementMatam) {
        this.setAutredepartementMatam(autredepartementMatam);
        return this;
    }

    public void setAutredepartementMatam(String autredepartementMatam) {
        this.autredepartementMatam = autredepartementMatam;
    }

    public String getAutredepartementSaint() {
        return this.autredepartementSaint;
    }

    public LyceeTechnique autredepartementSaint(String autredepartementSaint) {
        this.setAutredepartementSaint(autredepartementSaint);
        return this;
    }

    public void setAutredepartementSaint(String autredepartementSaint) {
        this.autredepartementSaint = autredepartementSaint;
    }

    public String getAutredepartementSedhiou() {
        return this.autredepartementSedhiou;
    }

    public LyceeTechnique autredepartementSedhiou(String autredepartementSedhiou) {
        this.setAutredepartementSedhiou(autredepartementSedhiou);
        return this;
    }

    public void setAutredepartementSedhiou(String autredepartementSedhiou) {
        this.autredepartementSedhiou = autredepartementSedhiou;
    }

    public String getAutredepartementTambacounda() {
        return this.autredepartementTambacounda;
    }

    public LyceeTechnique autredepartementTambacounda(String autredepartementTambacounda) {
        this.setAutredepartementTambacounda(autredepartementTambacounda);
        return this;
    }

    public void setAutredepartementTambacounda(String autredepartementTambacounda) {
        this.autredepartementTambacounda = autredepartementTambacounda;
    }

    public String getAutredepartementThis() {
        return this.autredepartementThis;
    }

    public LyceeTechnique autredepartementThis(String autredepartementThis) {
        this.setAutredepartementThis(autredepartementThis);
        return this;
    }

    public void setAutredepartementThis(String autredepartementThis) {
        this.autredepartementThis = autredepartementThis;
    }

    public String getAutredepartementZiguinchor() {
        return this.autredepartementZiguinchor;
    }

    public LyceeTechnique autredepartementZiguinchor(String autredepartementZiguinchor) {
        this.setAutredepartementZiguinchor(autredepartementZiguinchor);
        return this;
    }

    public void setAutredepartementZiguinchor(String autredepartementZiguinchor) {
        this.autredepartementZiguinchor = autredepartementZiguinchor;
    }

    public String getCommune() {
        return this.commune;
    }

    public LyceeTechnique commune(String commune) {
        this.setCommune(commune);
        return this;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getIa() {
        return this.ia;
    }

    public LyceeTechnique ia(String ia) {
        this.setIa(ia);
        return this;
    }

    public void setIa(String ia) {
        this.ia = ia;
    }

    public Proviseur getProviseur() {
        return this.proviseur;
    }

    public void setProviseur(Proviseur proviseur) {
        this.proviseur = proviseur;
    }

    public LyceeTechnique proviseur(Proviseur proviseur) {
        this.setProviseur(proviseur);
        return this;
    }

    public LyceesTechniques getNomLycee() {
        return this.nomLycee;
    }

    public void setNomLycee(LyceesTechniques lyceesTechniques) {
        this.nomLycee = lyceesTechniques;
    }

    public LyceeTechnique nomLycee(LyceesTechniques lyceesTechniques) {
        this.setNomLycee(lyceesTechniques);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LyceeTechnique)) {
            return false;
        }
        return id != null && id.equals(((LyceeTechnique) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LyceeTechnique{" +
            "id=" + getId() +
            ", prenomNom='" + getPrenomNom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", mail='" + getMail() + "'" +
            ", tel1='" + getTel1() + "'" +
            ", tel2='" + getTel2() + "'" +
            ", boitePostal='" + getBoitePostal() + "'" +
            ", decretCreation='" + getDecretCreation() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", region='" + getRegion() + "'" +
            ", autreRegion='" + getAutreRegion() + "'" +
            ", departementDakar='" + getDepartementDakar() + "'" +
            ", departementDiourbel='" + getDepartementDiourbel() + "'" +
            ", departementFatick='" + getDepartementFatick() + "'" +
            ", departementKaffrine='" + getDepartementKaffrine() + "'" +
            ", departementKaolack='" + getDepartementKaolack() + "'" +
            ", departementKedougou='" + getDepartementKedougou() + "'" +
            ", departementKolda='" + getDepartementKolda() + "'" +
            ", departementLouga='" + getDepartementLouga() + "'" +
            ", departementMatam='" + getDepartementMatam() + "'" +
            ", departementSaint='" + getDepartementSaint() + "'" +
            ", departementSedhiou='" + getDepartementSedhiou() + "'" +
            ", departementTambacounda='" + getDepartementTambacounda() + "'" +
            ", departementThis='" + getDepartementThis() + "'" +
            ", departementZiguinchor='" + getDepartementZiguinchor() + "'" +
            ", autredepartementDakar='" + getAutredepartementDakar() + "'" +
            ", autredepartementDiourbel='" + getAutredepartementDiourbel() + "'" +
            ", autredepartementFatick='" + getAutredepartementFatick() + "'" +
            ", autredepartementKaffrine='" + getAutredepartementKaffrine() + "'" +
            ", autredepartementKaolack='" + getAutredepartementKaolack() + "'" +
            ", autredepartementKedougou='" + getAutredepartementKedougou() + "'" +
            ", autredepartementKolda='" + getAutredepartementKolda() + "'" +
            ", autredepartementLouga='" + getAutredepartementLouga() + "'" +
            ", autredepartementMatam='" + getAutredepartementMatam() + "'" +
            ", autredepartementSaint='" + getAutredepartementSaint() + "'" +
            ", autredepartementSedhiou='" + getAutredepartementSedhiou() + "'" +
            ", autredepartementTambacounda='" + getAutredepartementTambacounda() + "'" +
            ", autredepartementThis='" + getAutredepartementThis() + "'" +
            ", autredepartementZiguinchor='" + getAutredepartementZiguinchor() + "'" +
            ", commune='" + getCommune() + "'" +
            ", ia='" + getIa() + "'" +
            "}";
    }
}
