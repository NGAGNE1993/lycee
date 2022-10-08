package com.mycompany.myapp.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
// import org.springframework.cloud.client.ServiceInstance;
// import org.springframework.cloud.client.discovery.DiscoveryClient;
// import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.mycompany.myapp.domain.User.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Authority.class.getName());
            createCache(cm, com.mycompany.myapp.domain.User.class.getName() + ".authorities");
            createCache(cm, com.mycompany.myapp.domain.Apprenant.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Besoin.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Recette.class.getName());
            createCache(cm, com.mycompany.myapp.domain.NiveauxEtudes.class.getName());
            createCache(cm, com.mycompany.myapp.domain.NiveauxCalification.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Enseignant.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Examen.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Filiere.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Filiere.class.getName() + ".enseignants");
            createCache(cm, com.mycompany.myapp.domain.Filiere.class.getName() + ".niveauCalifications");
            createCache(cm, com.mycompany.myapp.domain.IventaireDesMatetiere.class.getName());
            createCache(cm, com.mycompany.myapp.domain.MouvementMatiere.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ReformeMatiere.class.getName());
            createCache(cm, com.mycompany.myapp.domain.OrganeGestion.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Partenaire.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Difficulte.class.getName());
            createCache(cm, com.mycompany.myapp.domain.PersonnelAdministratif.class.getName());
            createCache(cm, com.mycompany.myapp.domain.PersonnelAppui.class.getName());
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName());
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".concours");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".rapportRFS");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".examen");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".recettes");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".depenses");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".visites");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".organes");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".mouvementMatieres");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".partenaires");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".besoins");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".personnelAdmiistratifs");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".personnelAppuis");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".series");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".filieres");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".salles");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".apprenants");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".enseignants");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".difficultes");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".niveaucalifications");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".reformeMatieres");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".iventaireDesMatetieres");
            createCache(cm, com.mycompany.myapp.domain.LyceesTechniques.class.getName() + ".niveauetudes");
            createCache(cm, com.mycompany.myapp.domain.LyceeTechnique.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Salle.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Serie.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Serie.class.getName() + ".enseignants");
            createCache(cm, com.mycompany.myapp.domain.Serie.class.getName() + ".niveaus");
            createCache(cm, com.mycompany.myapp.domain.Depense.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Depense.class.getName() + ".recettes");
            createCache(cm, com.mycompany.myapp.domain.Visite.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Proviseur.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Proviseur.class.getName() + ".personnelAdmiistratifs");
            createCache(cm, com.mycompany.myapp.domain.Proviseur.class.getName() + ".partenaires");
            createCache(cm, com.mycompany.myapp.domain.Proviseur.class.getName() + ".besoins");
            createCache(cm, com.mycompany.myapp.domain.Proviseur.class.getName() + ".organes");
            createCache(cm, com.mycompany.myapp.domain.Proviseur.class.getName() + ".visites");
            createCache(cm, com.mycompany.myapp.domain.Proviseur.class.getName() + ".difficultes");
            createCache(cm, com.mycompany.myapp.domain.Proviseur.class.getName() + ".rapportRFS");
            createCache(cm, com.mycompany.myapp.domain.Proviseur.class.getName() + ".enseignants");
            createCache(cm, com.mycompany.myapp.domain.Proviseur.class.getName() + ".personnelAppuis");
            createCache(cm, com.mycompany.myapp.domain.DirecteurEtude.class.getName());
            createCache(cm, com.mycompany.myapp.domain.DirecteurEtude.class.getName() + ".concours");
            createCache(cm, com.mycompany.myapp.domain.DirecteurEtude.class.getName() + ".salles");
            createCache(cm, com.mycompany.myapp.domain.DirecteurEtude.class.getName() + ".series");
            createCache(cm, com.mycompany.myapp.domain.DirecteurEtude.class.getName() + ".niveaus");
            createCache(cm, com.mycompany.myapp.domain.DirecteurEtude.class.getName() + ".filieres");
            createCache(cm, com.mycompany.myapp.domain.DirecteurEtude.class.getName() + ".niveauCalifications");
            createCache(cm, com.mycompany.myapp.domain.DirecteurEtude.class.getName() + ".apprenants");
            createCache(cm, com.mycompany.myapp.domain.DirecteurEtude.class.getName() + ".examen");
            createCache(cm, com.mycompany.myapp.domain.ComptableMatiere.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ComptableMatiere.class.getName() + ".iventaireDesMatetieres");
            createCache(cm, com.mycompany.myapp.domain.ComptableMatiere.class.getName() + ".mouvementMatieres");
            createCache(cm, com.mycompany.myapp.domain.ComptableMatiere.class.getName() + ".reformeMatieres");
            createCache(cm, com.mycompany.myapp.domain.ComptableFinancier.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ComptableFinancier.class.getName() + ".recettes");
            createCache(cm, com.mycompany.myapp.domain.ComptableFinancier.class.getName() + ".depenses");
            createCache(cm, com.mycompany.myapp.domain.Concours.class.getName());
            createCache(cm, com.mycompany.myapp.domain.RapportRF.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
