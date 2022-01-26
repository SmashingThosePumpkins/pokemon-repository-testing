package br.com.smashingthosepumpkins.di;

import br.com.smashingthosepumpkins.di.env.ApplicationSettings;
import br.com.smashingthosepumpkins.di.env.DatabaseSettings;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static br.com.smashingthosepumpkins.app.ShutdownService.shutdown;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static java.lang.System.getenv;

@Configuration
@ComponentScan({"br/com/smashingthosepumpkins/app", "br/com/smashingthosepumpkins/core", "br/com/smashingthosepumpkins/infra"})
public class SpringConfiguration {
    private static EntityManagerFactory emFactory;
    private static SessionFactory sessionFactory;
    private static ApplicationSettings appSettings;
    private static DatabaseSettings dbSettings;
    private static final String PERSISTENCE_UNIT_NAME = "APP";

    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        if (emFactory != null) {
            return emFactory;
        }

        Map<Object, Object> settingsMap = new HashMap<>();
        DatabaseSettings databaseSettings = getDatabaseSettings();
        String jdbcUrl = toJdbcUrl(databaseSettings.getHost(),
                databaseSettings.getPort(),
                databaseSettings.getDatabaseName());
        settingsMap.put("javax.persistence.jdbc.url", jdbcUrl);
        settingsMap.put("javax.persistence.jdbc.user", databaseSettings.getUsername());
        settingsMap.put("javax.persistence.jdbc.password", databaseSettings.getPassword());
        settingsMap.put("hibernate.show_sql", databaseSettings.isShowSql());
        emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, settingsMap);

        return getEntityManagerFactory();
    }

    @Bean
    public EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    @Bean
    public DatabaseSettings getDatabaseSettings() {
        if (dbSettings != null) {
            return dbSettings;
        }

        dbSettings = new DatabaseSettings(
                getApplicationSettings().getDatabaseSettings().getUsername(),
                getApplicationSettings().getDatabaseSettings().getPassword(),
                getApplicationSettings().getDatabaseSettings().getDatabaseName(),
                getApplicationSettings().getDatabaseSettings().isShowSql(),
                getApplicationSettings().getDatabaseSettings().getHost(),
                getApplicationSettings().getDatabaseSettings().getPort());

        return getDatabaseSettings();
    }

    @Bean
    public ApplicationSettings getApplicationSettings() {
        if (appSettings != null) {
            return appSettings;
        }

        try {
            String env = getenv("APP_SETTINGS");
            appSettings = getObjectMapper().readValue(env, ApplicationSettings.class);
            return getApplicationSettings();

        } catch (IOException e) {
            e.printStackTrace();
            shutdown();
            return null;
        }
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper()
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private String toJdbcUrl(String host, int port, String dbName) {
        return "jdbc:mysql://" + host + ":" + port + "/" + dbName;
    }
}
